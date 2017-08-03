package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.Shop;

import java.util.List;

/**
 * Created by Justin on 2016/2/26.
 */
public interface ShopDao {

    public Shop find(int id);

    public List<Shop> findAll();

    public void save(Shop shop);

    public void update(Shop shop);

    public void delete(int id);

}
