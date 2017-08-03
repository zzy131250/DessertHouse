package com.justin.desserthouse.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Justin on 2016/2/2.
 */

@Entity
@TableGenerator(
        name = "Staff_GEN",
        table = "GEN_TABLE",
        pkColumnName = "pk_key",
        valueColumnName = "pk_value",
        pkColumnValue = "Staff",
        initialValue = 100000,
        allocationSize = 1
)
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Staff_GEN")
    @Column(length = 6)
    int id;
    int role;   //0经理    1总店服务员    2分店服务员    3系统管理员
    @ManyToOne
    Shop shop;
    @OneToMany(mappedBy = "staff")
    @OrderBy("time desc")
    List<ExpenseRecord> expenseRecords;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "staff")
    @OrderBy("date desc")
    List<ProductPlan> productPlan;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<ExpenseRecord> getExpenseRecords() {
        return expenseRecords;
    }

    public void setExpenseRecords(List<ExpenseRecord> expenseRecords) {
        this.expenseRecords = expenseRecords;
    }

    public List<ProductPlan> getProductPlan() {
        return productPlan;
    }

    public void setProductPlan(List<ProductPlan> productPlan) {
        this.productPlan = productPlan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
