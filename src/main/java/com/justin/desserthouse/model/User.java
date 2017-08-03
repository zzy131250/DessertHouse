package com.justin.desserthouse.model;

import javax.persistence.*;

/**
 * Created by Justin on 2016/2/5.
 */

@Entity
public class User {    //保存所有用户

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String password;
    @OneToOne(mappedBy = "user")
    Staff staff;
    @OneToOne(mappedBy = "user")
    Member member;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
