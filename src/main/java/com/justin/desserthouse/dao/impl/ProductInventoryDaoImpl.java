package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.ProductInventoryDao;
import com.justin.desserthouse.model.ProductInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin Chou on 2016/3/9.
 */

@Repository
public class ProductInventoryDaoImpl implements ProductInventoryDao {

    @Autowired
    private BaseDao baseDao;

    public ProductInventory find(Date date) {
        return (ProductInventory) baseDao.load(ProductInventory.class, "date", date.toString());
    }

    public void save(ProductInventory productInventory) {
        baseDao.save(productInventory);
    }

    public void update(ProductInventory productInventory) {
        baseDao.update(productInventory);
    }

}
