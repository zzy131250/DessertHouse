package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.MemberCardDao;
import com.justin.desserthouse.model.MemberCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Justin on 2016/2/26.
 */

@Repository
public class MemberCardDaoImpl implements MemberCardDao {

    @Autowired
    private BaseDao baseDao;

    public MemberCard find(int id) {
        return (MemberCard) baseDao.load(MemberCard.class, id);
    }

    public void save(MemberCard memberCard) {
        baseDao.save(memberCard);
    }

    public void update(MemberCard memberCard) {
        baseDao.update(memberCard);
    }
}
