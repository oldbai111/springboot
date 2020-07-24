package com.jbwang.cwgl.dao;

import com.jbwang.cwgl.entity.Item;
import com.jbwang.cwgl.entity.request.ItemRequest;

import java.util.List;

public interface ItemMapper {
    void addItem(Item item);

    List<Item> selectItemInfo(ItemRequest req);
    List<Item> selectItemInfo2(ItemRequest req);

    void itemPass(Item item);

    void itemrefuse(Item item);
}
