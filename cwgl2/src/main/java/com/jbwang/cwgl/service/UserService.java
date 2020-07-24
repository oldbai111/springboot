package com.jbwang.cwgl.service;

import com.jbwang.cwgl.entity.User;
import com.jbwang.cwgl.entity.request.UserRequest;

import java.util.List;

public interface UserService {
    String loginByNameAndPassword(User user);

    User selectUserInfoByNameAndPwd(User user);

    String userZhuce(User user);

    List<User> selectUserInfo(UserRequest req);

    String updateuser(User user);

    String deleteuser(User user);

    String adduser(User user);

    User selectUserInfoById(String id);

    void saveUserTx(String id, String fileName);
}
