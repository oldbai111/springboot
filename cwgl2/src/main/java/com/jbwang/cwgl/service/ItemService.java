package com.jbwang.cwgl.service;

import com.jbwang.cwgl.entity.Item;
import com.jbwang.cwgl.entity.request.ItemRequest;

import java.util.List;

public interface ItemService {
    String addItem(Item item);

    List<Item> selectItemInfo(ItemRequest req);
    List<Item> selectItemInfo2(ItemRequest req);

    String itemPass(Item item);

    String itemrefuse(Item item);
}
