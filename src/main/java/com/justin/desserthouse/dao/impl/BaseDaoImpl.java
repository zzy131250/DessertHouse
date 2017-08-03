package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin on 2016/1/27.
 */

@Repository
public class BaseDaoImpl implements BaseDao {

    @Autowired
    protected SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Object load(Class c, int id) {
        Session session = getSession();
        return session.get(c, id);
    }

    public Object load(Class c, String column, String value) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(c);
        criteria.add(Expression.eq(column, value));
        List list = criteria.list();
        if (list.size() == 0) return null;
        return list.get(0);
    }

    public List getListWithTime(Class c, String time, Date start, Date end) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(c);
        List list = criteria.add(Expression.between(time, start, end)).addOrder(Order.desc(time)).list();
        if (list.size() == 0) return null;
        return list;
    }

    public List getListWithCondition(Class c, String column, int value) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(c);
        criteria.add(Expression.eq(column, value));
        List list = criteria.addOrder(Order.asc("id")).list();
        if (list.size() == 0) return null;
        return list;
    }

    public List getAllList(Class c) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(c);
        List list = criteria.addOrder(Order.asc("id")).list();
        if (list.size() == 0) return null;
        return list;
    }

    public Long getTotalCount(Class c) {
        Session session = getSession();
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        return count != null ? count.longValue() : 0;
    }

    public void delete(Class c, int id) {
        Session session = getSession();
        Object obj = session.get(c, id);
        session.delete(obj);
    }

    public void delete(Class c, int[] ids) {
        for (int id : ids) {
            Object obj = getSession().get(c, id);
            if (obj != null) {
                getSession().delete(obj);
            }
        }
    }

    public void save(Object bean) {
        Session session = getSession();
        session.save(bean);    //返回主键
    }

    public void update(Object bean) {
        Session session = getSession();
        session.update(bean);
    }

    public void delete(Object bean) {
        Session session = getSession();
        session.delete(bean);
    }

}
