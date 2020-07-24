package com.jbwang.cwgl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jbwang.cwgl.entity.Item;
import com.jbwang.cwgl.entity.request.ItemRequest;
import com.jbwang.cwgl.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "item")
public class ItemController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    //添加项目申请
    @PostMapping(value="addItem")
    public String addItem(@RequestBody String data){
        logger.info("ItemController-addItem START");
        Map<String, Object> map = new HashMap<>();

        try{
            JSONObject object  = JSON.parseObject(data);

            String id= UUID.randomUUID().toString();
            String mingcheng = object.getString("name");
            String money=object.getString("money");
            String renson=object.getString("desc");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(object.getString("date1"));
            String userid=object.getString("userid");;

          Item item=new Item();
           item.setItemId(id);
           item.setItemMingcheng(mingcheng);
           item.setItemMoney(money);
           item.setItemRenson(renson);
           item.setItemDate(date);
           item.setUserId(userid);

            String status=itemService.addItem(item);
            System.out.println(status);
//            String status=userService.userZhuce(user);
            map.put("status",status);
            return JSON.toJSONString(map);
        }catch (Exception e){
            logger.info("ItemController-addItem ERROR"+e);
        }
        logger.info("ItemController-addItem END");
        map.put("status","异常");
        return JSON.toJSONString(map);
    }

    @PostMapping("selectItemInfo")
    public String selectItemInfo(@RequestBody String data){
        logger.info("AccountController-selectItemInfo START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject jsonObject = JSON.parseObject(data);

            int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
            int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
            ItemRequest req=new ItemRequest();
            req.setPageNo(pageNo);
            req.setPageSize(pageSize);

            List<Item> result = itemService.selectItemInfo(req);

            map.put("result",result);
            req.setPageNo(pageNo);
            map.put("query",req);
            map.put("status","查询成功");
            return JSON.toJSONString(map);
        }catch(Exception e){
            logger.info("AccountController-selectItemInfo ERROR"+e);
        }
        logger.info("AccountController-selectItemInfo END");
        map.put("status","查询失败");
        return JSON.toJSONString(map);
    }
//用户查看审核结果
    @PostMapping("selectItemInfo2")
    public String selectItemInfo2(@RequestBody String data){
        logger.info("AccountController-selectItemInfo2 START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject jsonObject = JSON.parseObject(data);
            String userId = jsonObject.getString("userId");
            int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
            int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
            ItemRequest req=new ItemRequest();
            req.setUserId(userId);
            req.setPageNo(pageNo);
            req.setPageSize(pageSize);

            List<Item> result = itemService.selectItemInfo2(req);

            map.put("result",result);
            req.setPageNo(pageNo);
            map.put("query",req);
            map.put("status","查询成功");
            return JSON.toJSONString(map);
        }catch(Exception e){
            logger.info("AccountController-selectItemInfo2 ERROR"+e);
        }
        logger.info("AccountController-selectItemInfo2 END");
        map.put("status","查询失败");
        return JSON.toJSONString(map);
    }

    //审核通过
    @PostMapping("/itemPass")
    public String itemPass(@RequestBody String data){
        logger.info("AccountController-deleteAccount START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject object  = JSON.parseObject(data);

            String itemId= object.getString("itemId");

            Item item=new Item();
            item.setItemId(itemId);

            String status=itemService.itemPass(item);

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

    //拒绝申请
    @PostMapping("/itemrefuse")
    public String itemrefuse(@RequestBody String data){
        logger.info("AccountController-deleteAccount START");
        Map<String,Object> map = new HashMap<>();
        try{
            JSONObject object  = JSON.parseObject(data);

            String itemId= object.getString("itemId");

            Item item=new Item();
            item.setItemId(itemId);

            String status=itemService.itemrefuse(item);

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

}

