package com.justin.desserthouse.service;

import com.justin.desserthouse.model.*;

import java.util.List;

/**
 * Created by Justin on 2016/2/5.
 */
public interface MemberService {

    public Product getProduct(int productId);

    public Shop getShop(int shopId);

    public double queryBalance(String username);

    public Member getMember(String username);

    public void exchangePoint(String username);

    public void validateMember(String username);

    public void stopMember(String username);

    public void updateProfile(String username, String age, String gender, String livingPlace);

    public String getProductNameWithId(String productId);

    public List<List<Product>> getProductsWithGroup();

    public List<ProductPlan> getProductPlanWithProductId(String productId);

    public ProductInventory getInventory(String productId, String dateStr, String shopId);

    public void activate(String username, String money);

    public void reserve(String number, String price, String purchaseDate, String username, String productId, String purchaseShop);

    public void cancelReservation(String id);

    public List<Reservation> getReservationList(String username);
}
