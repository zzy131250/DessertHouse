package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.StaffDao;
import com.justin.desserthouse.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Justin on 2016/2/5.
 */

@Repository
public class StaffDaoImpl implements StaffDao {

    @Autowired
    private BaseDao baseDao;

    public Staff find(int id) {
        return (Staff) baseDao.load(Staff.class, id);
    }

    public List<Staff> findAll() {
        return baseDao.getAllList(Staff.class);
    }

    public void save(Staff staff) {
        baseDao.save(staff);
    }

    public void update(Staff staff) {
        baseDao.update(staff);
    }

    public void delete(int id) {
        baseDao.delete(Staff.class, id);
    }
}
