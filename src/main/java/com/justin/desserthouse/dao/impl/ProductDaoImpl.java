package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.ProductDao;
import com.justin.desserthouse.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Justin on 2016/3/1.
 */

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private BaseDao baseDao;

    public Product find(int id) {
        return (Product) baseDao.load(Product.class, id);
    }

    public List<Product> findAll() {
        return baseDao.getAllList(Product.class);
    }

    public void save(Product product) {
        baseDao.save(product);
    }

    public void update(Product product) {
        baseDao.update(product);
    }

    public void delete(int id) {
        baseDao.delete(Product.class, id);
    }
}
