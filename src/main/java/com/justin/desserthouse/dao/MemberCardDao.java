package com.justin.desserthouse.dao;

import com.justin.desserthouse.model.MemberCard;

/**
 * Created by Justin on 2016/2/26.
 */
public interface MemberCardDao {

    public MemberCard find(int id);

    public void save(MemberCard memberCard);

    public void update(MemberCard memberCard);

}
