package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.Reservation;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin Chou on 2016/3/15.
 */
public interface ReservationDao {

    public Reservation find(int id);

    public List<Reservation> findWithTime(Date start, Date end);

    public void save(Reservation reservation);

    public void delete(int id);

}
