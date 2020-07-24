package com.jbwang.cwgl.dao;

import com.jbwang.cwgl.entity.Account;
import com.jbwang.cwgl.entity.request.AccountRequest;

import java.util.List;

public interface AccountMapper {
    void addAccount(Account account);
    List<Account> selecAccountInfoByuserId(AccountRequest req);
    int selecAccountInfoByuserIdTotal(AccountRequest req);

    void updateAccount(Account account);
    void deleteAccount(Account account);
    List<Account> pointing(AccountRequest req);
    List<Account> pointing2(AccountRequest req);
    List<Account> pointing3(AccountRequest req);

    List<Account> selecAccountInfoByuserId2(AccountRequest req);
    int selecAccountInfoByuserId2Total(AccountRequest req);
}
