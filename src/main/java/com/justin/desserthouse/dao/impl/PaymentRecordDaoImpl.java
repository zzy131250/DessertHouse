package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.PaymentRecordDao;
import com.justin.desserthouse.model.PaymentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Justin Chou on 2016/3/15.
 */

@Repository
public class PaymentRecordDaoImpl implements PaymentRecordDao {

    @Autowired
    private BaseDao baseDao;

    public void save(PaymentRecord paymentRecord) {
        baseDao.save(paymentRecord);
    }
}
