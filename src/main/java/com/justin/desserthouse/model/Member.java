package com.justin.desserthouse.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Justin on 2016/2/2.
 */

@Entity
@TableGenerator(
        name = "Member_GEN",
        table = "GEN_TABLE",
        pkColumnName = "pk_key",
        valueColumnName = "pk_value",
        pkColumnValue = "Member",
        initialValue = 10000000,
        allocationSize = 1
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Member_GEN")
    int id;
    @Column(unique = true)
    String username;
    int point;    //积分
    int state;    //0正常  1暂停  2停止
    Date recentPaymentTime;
    int age;
    String gender;    //male, female
    String livingPlace;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    BankCard bankCard;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    MemberCard memberCard;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "member")
    @OrderBy("time desc")
    List<PaymentRecord> paymentRecords;
    @OneToMany(mappedBy = "member")
    @OrderBy("time desc")
    List<ExpenseRecord> expenseRecords;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "member")
    @OrderBy("reserveDate desc")
    List<Reservation> reservations;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getRecentPaymentTime() {
        return recentPaymentTime;
    }

    public void setRecentPaymentTime(Date recentPaymentTime) {
        this.recentPaymentTime = recentPaymentTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace = livingPlace;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public MemberCard getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(MemberCard memberCard) {
        this.memberCard = memberCard;
    }

    public List<PaymentRecord> getPaymentRecords() {
        return paymentRecords;
    }

    public void setPaymentRecords(List<PaymentRecord> paymentRecords) {
        this.paymentRecords = paymentRecords;
    }

    public List<ExpenseRecord> getExpenseRecords() {
        return expenseRecords;
    }

    public void setExpenseRecords(List<ExpenseRecord> expenseRecords) {
        this.expenseRecords = expenseRecords;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
