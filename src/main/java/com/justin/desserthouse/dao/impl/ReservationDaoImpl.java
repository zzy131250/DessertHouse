package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.ReservationDao;
import com.justin.desserthouse.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin Chou on 2016/3/15.
 */

@Repository
public class ReservationDaoImpl implements ReservationDao {

    @Autowired
    private BaseDao baseDao;

    public Reservation find(int id) {
        return (Reservation) baseDao.load(Reservation.class, id);
    }

    public List<Reservation> findWithTime(Date start, Date end) {
        return baseDao.getListWithTime(Reservation.class, "reserveDate", start, end);
    }

    public void save(Reservation reservation) {
        baseDao.save(reservation);
    }

    public void delete(int id) {
        baseDao.delete(Reservation.class, id);
    }
}
