package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.ProductPlan;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin on 2016/3/4.
 */
public interface ProductPlanDao {

    public ProductPlan find(int id);

    public List<ProductPlan> findWithState(String column, int value);

    public List<ProductPlan> findWithTime(Date start, Date end);

    public List<ProductPlan> findAll();

    public void save(ProductPlan productPlan);

    public void update(ProductPlan productPlan);

    public void delete(int id);

}
