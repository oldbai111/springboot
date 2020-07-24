package com.jbwang.cwgl.service.impl;

import com.jbwang.cwgl.dao.ItemMapper;
import com.jbwang.cwgl.entity.Item;
import com.jbwang.cwgl.entity.request.ItemRequest;
import com.jbwang.cwgl.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Resource
    private  ItemMapper itemMapper;

    @Override
    public String addItem(Item item) {
        String result = "";
        int total = 1;
        itemMapper.addItem(item);
        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }

    @Override
    public List<Item> selectItemInfo(ItemRequest req) {
        int start = (req.getPageNo()-1)*req.getPageSize();
        req.setPageNo(start);
        return itemMapper.selectItemInfo(req);
    }

    @Override
    public List<Item> selectItemInfo2(ItemRequest req) {
        int start = (req.getPageNo()-1)*req.getPageSize();
        req.setPageNo(start);
        return itemMapper.selectItemInfo2(req);
    }

    @Override
    public String itemPass(Item item) {
        String result = "";
        int total = 1;
     itemMapper.itemPass(item);

        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }

    @Override
    public String itemrefuse(Item item) {
        String result = "";
        int total = 1;
        itemMapper.itemrefuse(item);

        if(total==1){
            result="成功";
        }else{
            result="失败";
        }
        return result;
    }
}
