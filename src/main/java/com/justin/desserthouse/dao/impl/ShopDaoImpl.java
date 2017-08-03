package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.ShopDao;
import com.justin.desserthouse.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Justin on 2016/2/26.
 */

@Repository
public class ShopDaoImpl implements ShopDao {

    @Autowired
    private BaseDao baseDao;

    public Shop find(int id) {
        return (Shop) baseDao.load(Shop.class, id);
    }

    public List<Shop> findAll() {
        return baseDao.getAllList(Shop.class);
    }

    public void save(Shop shop) {
        baseDao.save(shop);
    }

    public void update(Shop shop) {
        baseDao.update(shop);
    }

    public void delete(int id) {
        baseDao.delete(Shop.class, id);
    }
}
