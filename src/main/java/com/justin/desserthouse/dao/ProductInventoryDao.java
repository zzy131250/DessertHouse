package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.ProductInventory;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin Chou on 2016/3/9.
 */
public interface ProductInventoryDao {

    public ProductInventory find(Date date);

    public void save(ProductInventory productInventory);

    public void update(ProductInventory productInventory);

}
