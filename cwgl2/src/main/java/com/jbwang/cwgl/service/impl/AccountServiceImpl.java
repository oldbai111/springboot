package com.jbwang.cwgl.service.impl;

import com.jbwang.cwgl.dao.AccountMapper;
import com.jbwang.cwgl.entity.Account;
import com.jbwang.cwgl.entity.request.AccountRequest;
import com.jbwang.cwgl.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService{

    @Resource
    private AccountMapper accountMapper;

    @Override
    public String addAccount(Account account) {

        String result = "";
        int total = 1;
        accountMapper.addAccount(account);
        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;

    }

    @Override
    public List<Account> selecAccountInfoByuserId(AccountRequest req) {
        int start = (req.getPageNo()-1)*req.getPageSize();
        req.setPageNo(start);
        return accountMapper.selecAccountInfoByuserId(req);
    }

    @Override
    public int selecAccountInfoByuserIdTotal(AccountRequest req) {
        return accountMapper.selecAccountInfoByuserIdTotal(req);
    }

    @Override
    public List<Account> selecAccountInfoByuserId2(AccountRequest req) {
        int start = (req.getPageNo()-1)*req.getPageSize();
        req.setPageNo(start);
        return accountMapper.selecAccountInfoByuserId2(req);
    }

    @Override
    public int selecAccountInfoByuserId2Total(AccountRequest req) {
        return accountMapper.selecAccountInfoByuserId2Total(req);
    }

    @Override
    public String updateAccount(Account account) {

        String result = "";
        int total = 1;
        accountMapper.updateAccount(account);
        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }

    @Override
    public String deleteAccount(Account account) {
        String result = "";
        int total = 1;
        accountMapper.deleteAccount(account);

            if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }

    @Override
    public List<Account> pointing(AccountRequest req) {
        int start = (req.getPageNo()-1)*req.getPageSize();
        req.setPageNo(start);
        return accountMapper.pointing(req);
    }

    @Override
    public List<Account> pointing2(AccountRequest req) {
        int start = (req.getPageNo()-1)*req.getPageSize();
        req.setPageNo(start);
        return accountMapper.pointing2(req);
    }

    @Override
    public List<Account> pointing3(AccountRequest req) {
        int start = (req.getPageNo()-1)*req.getPageSize();
        req.setPageNo(start);
        return accountMapper.pointing3(req);
    }

}
