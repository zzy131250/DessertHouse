package com.justin.desserthouse.dao;

import org.hibernate.Session;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin on 2016/1/29.
 */
public interface BaseDao {

    public Session getSession();

    public Object load(Class c, int id);

    public Object load(Class c, String column, String value);

    public List getListWithTime(Class c, String time, Date start, Date end);

    public List getListWithCondition(Class c, String column, int value);

    public List getAllList(Class c);

    public Long getTotalCount(Class c);

    public void delete(Class c, int id);

    public void delete(Class c, int[] ids);

    public void save(Object bean);

    public void update(Object bean);

    public void delete(Object bean);

}
