package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.TestDao;
import com.justin.desserthouse.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Justin on 2016/1/27.
 */

@Repository
public class TestDaoImpl implements TestDao {

    @Autowired
    private BaseDao baseDao;

    public void save(Test test) {
        baseDao.save(test);
    }

}
