package com.jbwang.cwgl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jbwang.cwgl.entity.User;
import com.jbwang.cwgl.entity.request.UserRequest;
import com.jbwang.cwgl.service.ManagerService;
import com.jbwang.cwgl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;


    //测试
    @RequestMapping(value = "test")
    public String test(HttpServletRequest request){
        logger.info("UserController-test START");
        Map<String, Object> map = new HashMap<>();
        try{
            String id = request.getParameter("id");
            map.put("id",id);
            map.put("status","成功");
        }catch(Exception e){
            logger.info("UserController-test ERROR"+e);
        }
        logger.info("UserController-test END");
        return JSON.toJSONString(map);
    }
      //登录
    @PostMapping(value = "testMysql")
    public String testMysql(@RequestBody String data){
        logger.info("UserController-testMysql START");
        Map<String, Object> map = new HashMap<>();
        try{
            JSONObject object  = JSON.parseObject(data);
            String name = object.getString("name");
            String password = object.getString("password");
            String loadpower=object.getString("type");
            User user = new User();
            user.setUserName(name);
            user.setUserPassword(password);
            user.setLoadPower(loadpower);

            String status = "";
            status = userService.loginByNameAndPassword(user);

            if(StringUtils.equals(status,"成功")){
                User u = userService.selectUserInfoByNameAndPwd(user);
                map.put("user",u);
            }

            map.put("status",status);
            return JSON.toJSONString(map);

        }catch(Exception e){
            logger.info("UserController-testMysql ERROR"+e);
        }
        logger.info("UserController-testMysql END");
        map.put("status","异常");
        return JSON.toJSONString(map);
    }

    //注册
    @PostMapping (value = "zhuceMysql")
    public  String zhuceMysql(@RequestBody String data){
        logger.info("UserController-zhuceMysql START");
        Map<String, Object> map = new HashMap<>();
        try{
            JSONObject object  = JSON.parseObject(data);

            String id=UUID.randomUUID().toString();
            String name = object.getString("name");
            String password = object.getString("password");
            String sex=object.getString("radio");
             char[] sexchar =sex.toCharArray();
            String touxiang=null;

            User user = new User();
            user.setUserId(id);
            user.setUserName(name);
            user.setUserPassword(password);
            user.setUserSex(sexchar[0]);
            user.setUserTouxiang(touxiang);

            String status=userService.userZhuce(user);
            map.put("status",status);
            return JSON.toJSONString(map);
        }catch (Exception e){
            logger.info("UserController-zhuceMysql ERROR"+e);
        }
        logger.info("UserController-zhuceMysql END");
        map.put("status","异常");
        return JSON.toJSONString(map);
    }

  //管理员查询用户信息
  @PostMapping("selectUserInfo")
  public String selectUserInfo(@RequestBody String data){
      logger.info("AccountController-selectUserInfo START");
      Map<String,Object> map = new HashMap<>();
      try{
          JSONObject jsonObject = JSON.parseObject(data);

          int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
          int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));

          UserRequest req=new UserRequest();
          req.setPageNo(pageNo);
          req.setPageSize(pageSize);

          List<User> result = userService.selectUserInfo(req);

          map.put("result",result);
          req.setPageNo(pageNo);
          map.put("query",req);
          map.put("status","查询成功");
          return JSON.toJSONString(map);
      }catch(Exception e){
          logger.info("AccountController-selectUserInfo ERROR"+e);
      }
      logger.info("AccountController-selectUserInfo END");
      map.put("status","查询失败");
      return JSON.toJSONString(map);
  }

    //    修改用户
    @PostMapping("/updateuser")
    public String updateuser(@RequestBody String data){
        logger.info("AccountController-updateuser START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject object  = JSON.parseObject(data);

            String userId= object.getString("userId");
            String userName = object.getString("userName");
            String userSex=object.getString("userSex");
            char[] sexchar =userSex.toCharArray();
            String userPassword=object.getString("userPassword");
            String loadPower=object.getString("loadPower");

           User user =new User();
          user.setUserId(userId);
          user.setUserName(userName);
            user.setUserSex(sexchar[0]);
            user.setUserPassword(userPassword);
            user.setLoadPower(loadPower);

            String status=userService.updateuser(user);
            System.out.println(status);
            map.put("status",status);
            return JSON.toJSONString(map);
        }catch(Exception e){
            logger.info("AccountController-updateuser ERROR"+e);
        }
        logger.info("AccountController-updateuser END");
        map.put("status","查询失败");
        return JSON.toJSONString(map);
    }

    //删除用户
    @PostMapping("/deleteuser")
    public String deleteuser(@RequestBody String data){
        logger.info("AccountController-deleteuser START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject object  = JSON.parseObject(data);

            String userId= object.getString("userId");
            User user=new User();
            user.setUserId(userId);

            String status=userService.deleteuser(user);
            System.out.println(status);
            map.put("status",status);
            return JSON.toJSONString(map);
        }catch(Exception e){
            logger.info("AccountController-deleteuser ERROR"+e);
        }
        logger.info("AccountController-deleteuser END");
        map.put("status","查询失败");
        return JSON.toJSONString(map);
    }

    //管理员添加用户
    @PostMapping (value = "adduser")
    public  String adduser(@RequestBody String data){
        logger.info("UserController-adduser START");
        Map<String, Object> map = new HashMap<>();
        try{
            JSONObject object  = JSON.parseObject(data);

            String id=UUID.randomUUID().toString();
            String name = object.getString("name");
            String password = object.getString("password");
            String sex=object.getString("sex");
            char[] sexchar =sex.toCharArray();
            String  region=object.getString("region");
            String touxiang=null;

            User user = new User();
            user.setUserId(id);
            user.setUserName(name);
            user.setUserPassword(password);
            user.setUserSex(sexchar[0]);
            user.setLoadPower(region);
            user.setUserTouxiang(touxiang);

            String status=userService.adduser(user);
            map.put("status",status);
            return JSON.toJSONString(map);
        }catch (Exception e){
            logger.info("UserController-adduser ERROR"+e);
        }
        logger.info("UserController-adduser END");
        map.put("status","异常");
        return JSON.toJSONString(map);
    }

    //个人信息
    @PostMapping(value = "selectUserInfoById")
    public String selectUserInfoById(@RequestBody String data){
        logger.info("UserController-selectUserInfoById START");
        Map<String, Object> map = new HashMap<>();
        try{
            JSONObject object  = JSON.parseObject(data);
            String userId = object.getString("userId");

            User user = new User();
          user.setUserId(userId);

            String status = "";
            status="成功";

            User u = userService.selectUserInfoById(userId);
            map.put("user",u);

            map.put("status",status);
            return JSON.toJSONString(map);

        }catch(Exception e){
            logger.info("UserController-selectUserInfoById ERROR"+e);
        }
        logger.info("UserController-selectUserInfoById END");
        map.put("status","异常");
        return JSON.toJSONString(map);
    }

    /**
     * 修改用户头像
     *
     * @param file
     * @param request
     * @return
     * @throws SocketException
     * @throws IOException
     */
    @RequestMapping(value = "uploadUserTx")
    public String uploadUserTx(MultipartFile file, HttpServletRequest request) throws IOException {
        logger.info("用户头像上传（/file/uploadUserTx）开始");
        Map map = new HashMap<>();

        // 获取文件类型
        String fileType = file.getContentType();

        // 获取文件名
        String OriginalFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().replace("-","") + OriginalFileName.substring(OriginalFileName.indexOf('.')); //文件重命名


        String id = request.getParameter("id");
        //利用redis查找用户信息从而找到用户专属文件夹
//        String token = request.getHeader("Authorization");
//        String mobile = stringRedisTemplate.opsForValue().get(token);
//        if (StringUtils.isEmpty(mobile)) {
//            return "用户信息无效";
//        }

        //先判断是否有用户名下的文件夹，没有则创建，名为用户手机号
        File dir = new File("src/main/resources/static/img");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 获取文件输入流
        FileOutputStream out = new FileOutputStream("src/main/resources/static/img/" + fileName);
        try {
            User user = userService.selectUserInfoById(id);
            out.write(file.getBytes());
            String sb = "src/main/resources/static/img/" + user.getUserTouxiang();
            File file2 = new File(sb);
            userService.saveUserTx(id, fileName);
//            user.setUserTouxiang(fileName);
            if (file2.exists()) {
                if (file2.delete()) {
                    map.put("status", "1-删除成功");
                } else {
                    map.put("status", "0-删除失败");
                }
            } else {
                map.put("status", "文件不存在！");
            }
            map.put("user", user);
        } finally {
            out.flush();
            out.close();
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("queryUserTouxiang")
    public String queryUserTouxiang(String id){
        String result = userService.selectUserInfoById(id).getUserTouxiang();
        return result;
    }
}
