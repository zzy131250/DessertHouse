package com.justin.desserthouse.model;

import javax.persistence.*;

/**
 * Created by Justin on 2016/2/1.
 */

@Entity
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    double money;
    @OneToOne(mappedBy = "bankCard")
    Member member;

    public BankCard() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
