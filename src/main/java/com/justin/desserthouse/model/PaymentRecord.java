package com.justin.desserthouse.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Justin on 2016/2/2.
 * 缴费记录
 */

@Entity
public class PaymentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Date time;
    double money;
    int wayToPay;   //0 memberCard   1 积分
    @ManyToOne
    Member member;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getWayToPay() {
        return wayToPay;
    }

    public void setWayToPay(int wayToPay) {
        this.wayToPay = wayToPay;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
