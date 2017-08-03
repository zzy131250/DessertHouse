package com.justin.desserthouse.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Justin on 2016/2/2.
 * 产品库存
 */

@Entity
public class ProductInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Date date;
    int number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
