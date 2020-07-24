package com.jbwang.cwgl.service.impl;

import com.jbwang.cwgl.dao.ManageMapper;
import com.jbwang.cwgl.entity.Manager;
import com.jbwang.cwgl.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ManageMapper manageMapper;

    @Override
    public String loginByNameAndPassword(Manager manager) {
        logger.info("ManagerServicelmpl-loginByNameAndPassword START");
        String result = "";
        try {
            int total = manageMapper.loginByNameAndPassword(manager);
            if(total==1){
                result="成功";
            }else{
                result="失败";
            }
        }catch (Exception e){
            logger.info("ManagerServicelmpl-loginByNameAndPassword ERROR");
        }
        logger.info("ManagerServicelmpl-loginByNameAndPassword END");
        return result;
    }
}
