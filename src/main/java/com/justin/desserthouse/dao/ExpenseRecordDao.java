package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.ExpenseRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by Justin Chou on 2016/3/15.
 */
public interface ExpenseRecordDao {

    public List<ExpenseRecord> findWithTime(Date start, Date end);

    public void save(ExpenseRecord expenseRecord);

}
