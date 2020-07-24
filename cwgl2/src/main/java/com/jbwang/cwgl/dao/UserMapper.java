package com.jbwang.cwgl.dao;

import com.jbwang.cwgl.entity.User;
import com.jbwang.cwgl.entity.request.UserRequest;

import java.util.List;

public interface UserMapper {
    int loginByNameAndPassword(User user);

   void userZhuce(User user);

    User selectUserInfoByNameAndPwd(User user);

    List<User> selectUserInfo(UserRequest req);

    void updateuser(User user);
    void deleteuser(User user);

    void adduser(User user);

    User selectUserInfoById(String id);

    void saveUserTx(String id, String fileName);
}
