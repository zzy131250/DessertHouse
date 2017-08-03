package com.justin.desserthouse.service;

import com.justin.desserthouse.model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Justin on 2016/2/5.
 */
public interface StaffService {

    public Staff getStaff(String username);

    public List<Staff> getAllStaff();

    public int getStaffShopId(String username);

    public List<ProductPlan> getProductPlanCheckList();

    public List<ProductPlan> getProductPlanPeriod(String username, String start, String end);

    public ProductPlan getProductPlan(Date date, String productId, String shopId);

    public List<ProductPlan> getAllProductPlan(String username);

    public void addProductPlan(String staffId, String shopId, String productId, String date, String salesNumber, String price);

    public void updateProductPlan(String id, String staffId, String shopId, String productId, String date, String salesNumber, String price);

    public void checkProductPlan(String id, int state);

    public void deleteProductPlan(String id);

    public List<Product> getProductListWithInventory(String shopId);

    public List<Product> getAllProducts();

    public Product getProduct(String productId);

    public void addProduct(String name);

    public void updateProduct(String id, String name);

    public void deleteProduct(String id);

    public Member getMemberWithMemberCardId(String memberCardId);

    public Member getMemberWithReservationId(String reservationId);

    public ProductInventory getInventory(String productId, String shopId);

    public void memberSell(String number, String price, String memberCardId, String shopId, String productId, String staffId);

    public void cashSell(String number, String price, String shopId, String productId, String staffId);

    public void reserveSell(String reservationId, String staffId);

    public int getMemberStartAge();

    public int getMemberEndAge();

    public Map<String, Double> getMemberAgeDistribute();

    public Map<String, Double> getMemberGenderDistribute();

    public Map<String, Integer> getMemberCardStateDistribute();

    public List<Map.Entry<String, Double>> getMemberMonthExpense();

    public Map<String, Map<String, Integer>> getShopReservation();

    public Map<String, Map<String, Integer>> getShopSale();

    public List<Map.Entry<String, Integer>> getHotProduct();
}
