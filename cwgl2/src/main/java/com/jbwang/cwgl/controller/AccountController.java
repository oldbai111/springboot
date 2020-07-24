package com.jbwang.cwgl.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jbwang.cwgl.entity.Account;
import com.jbwang.cwgl.entity.request.AccountRequest;
import com.jbwang.cwgl.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "account")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

 @Autowired
private AccountService accountService;

    //添加账单
    @PostMapping(value = "addAccount")
    public  String addAccount(@RequestBody String data){
        logger.info("AccountController-addAccount START");
        Map<String, Object> map = new HashMap<>();
        try{
            JSONObject object  = JSON.parseObject(data);

            String id= UUID.randomUUID().toString();
            String name = object.getString("name");
            String type=object.getString("region");
            String money=object.getString("money");
            String beizhu=object.getString("desc");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = simpleDateFormat.parse(object.getString("date1"));
            String date=object.getString("date1");
            String userid=object.getString("userid");;

            Account account=new Account();
            account.setAccountId(id);
           account.setAccountName(name);
           account.setAccountType(type);
           account.setAccountMoney(money);
           account.setAccountBeizhu(beizhu);
           account.setAccountDate(date);
           account.setUserId(userid);

           String status=accountService.addAccount(account);
           System.out.println(status);
//            String status=userService.userZhuce(user);
           map.put("status",status);
            return JSON.toJSONString(map);
        }catch (Exception e){
            logger.info("AccountController-addAccount  ERROR"+e);
        }
        logger.info("AccountController-addAccount  END");
        map.put("status","异常");
        return JSON.toJSONString(map);
    }

//    查询全部账单
    @PostMapping("/selecAccountInfoByuserId")
    public String selecAccountInfoByuserId(@RequestBody String data){
        logger.info("AccountController-selecAccountInfoByuserId START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject jsonObject = JSON.parseObject(data);

            String userId = jsonObject.getString("userId");
            int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
            int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
            AccountRequest req = new AccountRequest();
            req.setUserId(userId);
            req.setPageNo(pageNo);
            req.setPageSize(pageSize);
            List<Account> result = accountService.selecAccountInfoByuserId(req);
            int total = accountService.selecAccountInfoByuserIdTotal(req);
            req.setTotal(total);
            map.put("result",result);
            pageNo=(pageNo/pageSize)+1;
            req.setPageNo(pageNo);
            map.put("query",req);
            map.put("status","查询成功");
            return JSON.toJSONString(map);
        }catch(Exception e){
            logger.info("AccountController-selecAccountInfoByuserId ERROR"+e);
        }
        logger.info("AccountController-selecAccountInfoByuserId END");
        map.put("status","查询失败");
        return JSON.toJSONString(map);
    }

//    修改账单
@PostMapping("/updateAccount")
public String updateAccount(@RequestBody String data){
    logger.info("AccountController-updateAccount START");
    Map<String,Object> map = new HashMap<>();
    try{
        JSONObject object  = JSON.parseObject(data);

        String accountId= object.getString("accountId");
        String accountName = object.getString("accountName");
        String accountType=object.getString("accountType");
        String accountMoney=object.getString("accountMoney");
        String accountBeizhu=object.getString("accountBeizhu");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date accountDate = new Date(Integer.parseInt(object.getString("accountDate").substring(0,10)));
//System.out.println(object.getString("accountDate").substring(0,10));
        String accountDate=object.getString("accountDate");
        Account account=new Account();
        account.setAccountId(accountId);
        account.setAccountName(accountName);
        account.setAccountType(accountType);
        account.setAccountMoney(accountMoney);
        account.setAccountBeizhu(accountBeizhu);
        account.setAccountDate(accountDate);

        String status=accountService.updateAccount(account);
        System.out.println(status);
        map.put("status",status);
        return JSON.toJSONString(map);
    }catch(Exception e){
        logger.info("AccountController-updateAccount ERROR"+e);
    }
    logger.info("AccountController-updateAccount END");
    map.put("status","查询失败");
    return JSON.toJSONString(map);
}

//删除账单
@PostMapping("/deleteAccount")
public String deleteAccount(@RequestBody String data){
    logger.info("AccountController-deleteAccount START");
    Map<String,Object> map = new HashMap<>();
    try{
        JSONObject object  = JSON.parseObject(data);

        String accountId= object.getString("accountId");

        Account account=new Account();
        account.setAccountId(accountId);


        String status=accountService.deleteAccount(account);
        System.out.println(status);
        map.put("status",status);
        return JSON.toJSONString(map);
    }catch(Exception e){
        logger.info("AccountController-deleteAccount ERROR"+e);
    }
    logger.info("AccountController-deleteAccount END");
    map.put("status","查询失败");
    return JSON.toJSONString(map);
}

    //    画图1
    @PostMapping("/pointing")
    public String pointing(@RequestBody String data){
        logger.info("AccountController-pointing START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject jsonObject = JSON.parseObject(data);

            String userId = jsonObject.getString("userId");
            int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
            int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
            AccountRequest req = new AccountRequest();
            req.setUserId(userId);
            req.setPageNo(pageNo);
            req.setPageSize(pageSize);
            List<Account> result = accountService.pointing(req);

            List<Map<String,Object>> res = new ArrayList<>();
            for(Account acc : result){
                Map<String,Object> m = new HashMap<>();
                m.put("name",acc.getName());
                m.put("value",acc.getValue());
                res.add(m);
            }
            map.put("result",res);
            req.setPageNo(pageNo);
            map.put("query",req);
            map.put("status","查询成功");
            return JSON.toJSONString(map);
        }catch(Exception e){
            logger.info("AccountController-pointing ERROR"+e);
        }
        logger.info("AccountController-pointing END");
        map.put("status","查询失败");
        return JSON.toJSONString(map);
    }

    //    画图2
    @PostMapping("pointing2")
    public String pointing2(@RequestBody String data){
        logger.info("AccountController-pointing START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject jsonObject = JSON.parseObject(data);

            String userId = jsonObject.getString("userId");
            int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
            int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
            AccountRequest req = new AccountRequest();
            req.setUserId(userId);
            req.setPageNo(pageNo);
            req.setPageSize(pageSize);
            List<Account> result = accountService.pointing2(req);

            List<Map<String,Object>> res = new ArrayList<>();
            for(Account acc : result){
                Map<String,Object> m = new HashMap<>();
                m.put("name",acc.getName());
                m.put("value",acc.getValue());
                res.add(m);
            }
            map.put("result",res);
            req.setPageNo(pageNo);
            map.put("query",req);
            map.put("status","查询成功");
            return JSON.toJSONString(map);
        }catch(Exception e){
            logger.info("AccountController-pointing ERROR"+e);
        }
        logger.info("AccountController-pointing END");
        map.put("status","查询失败");
        return JSON.toJSONString(map);
    }

    //    画图3
    @PostMapping("pointing3")
    public String pointing3(@RequestBody String data){
        logger.info("AccountController-pointing3 START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject jsonObject = JSON.parseObject(data);

            String userId = jsonObject.getString("userId");
            int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
            int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
            AccountRequest req = new AccountRequest();
            req.setUserId(userId);
            req.setPageNo(pageNo);
            req.setPageSize(pageSize);
            List<Account> result = accountService.pointing3(req);

            List<Map<String,Object>> res = new ArrayList<>();
            for(Account acc : result){
                Map<String,Object> m = new HashMap<>();
                m.put("name",acc.getName());
                m.put("value",acc.getValue());
                m.put("value2",acc.getValue2());
                res.add(m);
            }
            map.put("result",res);
            req.setPageNo(pageNo);
            map.put("query",req);
            map.put("status","查询成功");
            return JSON.toJSONString(map);
        }catch(Exception e){
            logger.info("AccountController-pointing3 ERROR"+e);
        }
        logger.info("AccountController-pointing3 END");
        map.put("status","查询失败");
        return JSON.toJSONString(map);
    }

    //    查询全部账单
    @PostMapping("/selecAccountInfoByuserId2")
    public String selecAccountInfoByuserId2(@RequestBody String data){
        logger.info("AccountController-selecAccountInfoByuserId START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject jsonObject = JSON.parseObject(data);

            String userId = jsonObject.getString("userId");
            int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
            int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
            AccountRequest req = new AccountRequest();
            req.setUserId(userId);
            req.setPageNo(pageNo);
            req.setPageSize(pageSize);
            List<Account> result = accountService.selecAccountInfoByuserId2(req);
            int total = accountService.selecAccountInfoByuserId2Total(req);
            req.setTotal(total);
            map.put("result",result);
            pageNo=(pageNo/pageSize)+1;
            req.setPageNo(pageNo);
            map.put("query",req);
            map.put("status","查询成功");
            return JSON.toJSONString(map);
        }catch(Exception e){
            logger.info("AccountController-selecAccountInfoByuserId ERROR"+e);
        }
        logger.info("AccountController-selecAccountInfoByuserId END");
        map.put("status","查询失败");
        return JSON.toJSONString(map);
    }
}
