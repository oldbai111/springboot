package com.jbwang.cwgl.dao;

import com.jbwang.cwgl.entity.Manager;

public interface ManageMapper {
    int loginByNameAndPassword(Manager manager);

}
