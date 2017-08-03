package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.UserDao;
import com.justin.desserthouse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Justin on 2016/2/13.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private BaseDao baseDao;

    public void save(User user) {
        baseDao.save(user);
    }

}
