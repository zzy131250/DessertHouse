package com.justin.desserthouse.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Justin on 2016/2/2.
 */

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @OneToMany(mappedBy = "product")
    @OrderBy("time desc")
    List<ExpenseRecord> expenseRecords;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "product")
    @OrderBy("reserveDate desc")
    List<Reservation> reservations;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "product")
    @OrderBy("date desc")
    List<ProductPlan> productPlan;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "product")
    @OrderBy("date desc")
    List<ProductInventory> productInventories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<ProductPlan> getProductPlan() {
        return productPlan;
    }

    public void setProductPlan(List<ProductPlan> productPlan) {
        this.productPlan = productPlan;
    }

    public List<ProductInventory> getProductInventories() {
        return productInventories;
    }

    public void setProductInventories(List<ProductInventory> productInventories) {
        this.productInventories = productInventories;
    }
}
