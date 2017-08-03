package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.Product;

import java.util.List;

/**
 * Created by Justin on 2016/3/1.
 */
public interface ProductDao {

    public Product find(int id);

    public List<Product> findAll();

    public void save(Product product);

    public void update(Product product);

    public void delete(int id);

}
