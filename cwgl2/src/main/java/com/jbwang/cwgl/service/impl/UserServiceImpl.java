package com.jbwang.cwgl.service.impl;

import com.jbwang.cwgl.dao.UserMapper;
import com.jbwang.cwgl.entity.User;
import com.jbwang.cwgl.entity.request.UserRequest;
import com.jbwang.cwgl.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String loginByNameAndPassword(User user) {
        String result = "";
        int total = userMapper.loginByNameAndPassword(user);
        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }

    @Override
    public User selectUserInfoByNameAndPwd(User user) {
        return userMapper.selectUserInfoByNameAndPwd(user);
    }

    @Override
    public String userZhuce(User user) {

        String result = "";
        int total = 1;
        userMapper.userZhuce(user);
        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }

    @Override
    public List<User> selectUserInfo(UserRequest req) {
        int start = (req.getPageNo()-1)*req.getPageSize();
        req.setPageNo(start);
        return userMapper.selectUserInfo(req);
    }

    @Override
    public String updateuser(User user) {

        String result = "";
        int total = 1;
     userMapper.updateuser(user);
        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }

    @Override
    public String deleteuser(User user) {
        String result = "";
        int total = 1;
         userMapper.deleteuser(user);
        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }

    @Override
    public String adduser(User user) {
        String result = "";
        int total = 1;
        userMapper.adduser(user);
        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }

    @Override
    public User selectUserInfoById(String id) {
        return userMapper.selectUserInfoById(id);
    }

    @Override
    public void saveUserTx(String id, String fileName) {
        userMapper.saveUserTx(id, fileName);
    }
}
