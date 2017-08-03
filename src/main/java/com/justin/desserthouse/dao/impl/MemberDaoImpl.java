package com.justin.desserthouse.dao.impl;

import com.justin.desserthouse.dao.BaseDao;
import com.justin.desserthouse.dao.MemberDao;
import com.justin.desserthouse.model.BankCard;
import com.justin.desserthouse.model.Member;
import com.justin.desserthouse.model.MemberCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Justin on 2016/2/5.
 */

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private BaseDao baseDao;

    public Member find(String username) {
        return (Member) baseDao.load(Member.class, "username", username);
    }

    public List<Member> findAll() {
        return baseDao.getAllList(Member.class);
    }

    public void save(Member member) {
        baseDao.save(member);
    }

    public void update(Member member) {
        baseDao.update(member);
    }

}
