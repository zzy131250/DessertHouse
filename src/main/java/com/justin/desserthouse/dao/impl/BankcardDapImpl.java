package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BankcardDao;
import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.model.BankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Justin on 2016/2/26.
 */

@Repository
public class BankcardDapImpl implements BankcardDao {

    @Autowired
    private BaseDao baseDao;

    public BankCard find(int id) {
        return (BankCard) baseDao.load(BankCard.class, id);
    }

    public void update(BankCard bankCard) {
        baseDao.update(bankCard);
    }

}
