package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.Staff;

import java.util.List;

/**
 * Created by Justin on 2016/2/5.
 */
public interface StaffDao {

    public Staff find(int id);

    public List<Staff> findAll();

    public void save(Staff staff);

    public void update(Staff staff);

    public void delete(int id);

}
