package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.BankCard;
import com.justin.desserthouse.model.Member;
import com.justin.desserthouse.model.MemberCard;

import java.util.List;

/**
 * Created by Justin on 2016/2/5.
 */
public interface MemberDao {

    public Member find(String username);

    public List<Member> findAll();

    public void save(Member member);

    public void update(Member member);

}
