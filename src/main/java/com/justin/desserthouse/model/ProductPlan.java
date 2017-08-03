package com.justin.desserthouse.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Justin on 2016/2/2.
 */

@Entity
public class ProductPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Date date;
    int saleNumber;
    double price;
    int state;    //0待批准    1通过     2未通过
    @ManyToOne
    Staff staff;
    @ManyToOne
    Shop shop;
    @ManyToOne
    Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
