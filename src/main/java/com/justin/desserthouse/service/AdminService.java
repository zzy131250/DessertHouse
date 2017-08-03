package com.justin.desserthouse.service;

import com.justin.desserthouse.model.Shop;
import com.justin.desserthouse.model.Staff;

import java.util.List;

/**
 * Created by Justin on 2016/2/5.
 */
public interface AdminService {

    public List<Shop> getAllShops();

    public void addShop(String name, String address);

    public void updateShop(String id, String name, String address);

    public void deleteShop(String id);

    public List<Staff> getAllStaff();

    public void addStaff(String role, String shopId);

    public void updateStaff(String id, String role, String shopId);

    public void deleteStaff(String id);
}
