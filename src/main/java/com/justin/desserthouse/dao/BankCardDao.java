package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.BankCard;

/**
 * Created by Justin Chou on 2016/3/9.
 */
public interface BankcardDao {

    public BankCard find(int id);

    public void update(BankCard bankCard);

}
