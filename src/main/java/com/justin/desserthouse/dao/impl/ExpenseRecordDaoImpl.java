package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.ExpenseRecordDao;
import com.justin.desserthouse.model.ExpenseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin Chou on 2016/3/15.
 */

@Repository
public class ExpenseRecordDaoImpl implements ExpenseRecordDao {

    @Autowired
    private BaseDao baseDao;

    public List<ExpenseRecord> findWithTime(Date start, Date end) {
        return baseDao.getListWithTime(ExpenseRecord.class, "time", start, end);
    }

    public void save(ExpenseRecord expenseRecord) {
        baseDao.save(expenseRecord);
    }
}
