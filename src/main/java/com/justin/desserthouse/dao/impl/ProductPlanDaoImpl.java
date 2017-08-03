package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.ProductPlanDao;
import com.justin.desserthouse.model.ProductPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin on 2016/3/4.
 */

@Repository
public class ProductPlanDaoImpl implements ProductPlanDao {

    @Autowired
    private BaseDao baseDao;

    public ProductPlan find(int id) {
        return (ProductPlan) baseDao.load(ProductPlan.class, id);
    }

    public List<ProductPlan> findWithState(String column, int value) {
        return baseDao.getListWithCondition(ProductPlan.class, column, value);
    }

    public List<ProductPlan> findWithTime(Date start, Date end) {
        return baseDao.getListWithTime(ProductPlan.class, "date", start, end);
    }

    public List<ProductPlan> findAll() {
        return baseDao.getAllList(ProductPlan.class);
    }

    public void save(ProductPlan productPlan) {
        baseDao.save(productPlan);
    }

    public void update(ProductPlan productPlan) {
        baseDao.update(productPlan);
    }

    public void delete(int id) {
        baseDao.delete(ProductPlan.class, id);
    }
}
