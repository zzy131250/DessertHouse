package com.justin.desserthouse.model;

import javax.persistence.*;

/**
 * Created by Justin on 2016/2/1.
 */

@Entity
@TableGenerator(
        name = "MemberCard_GEN",
        table = "GEN_TABLE",
        pkColumnName = "pk_key",
        valueColumnName = "pk_value",
        pkColumnValue = "MemberCard",
        initialValue = 1000000,
        allocationSize = 1
)
public class MemberCard {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MemberCard_GEN")
    int id;
    double money;
    int level;       //1, 2, 3
    double discount;     //0.8
    @OneToOne(mappedBy = "memberCard")
    Member member;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
