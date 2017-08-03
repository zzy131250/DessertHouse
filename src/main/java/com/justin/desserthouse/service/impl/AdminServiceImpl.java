package com.justin.desserthouse.service.impl;

import com.justin.desserthouse.dao.ShopDao;
import com.justin.desserthouse.dao.StaffDao;
import com.justin.desserthouse.dao.UserDao;
import com.justin.desserthouse.model.Shop;
import com.justin.desserthouse.model.Staff;
import com.justin.desserthouse.model.User;
import com.justin.desserthouse.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Justin on 2016/2/5.
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ShopDao shopDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private UserDao userDao;

    public List<Shop> getAllShops() {
        return shopDao.findAll();
    }

    public void addShop(String name, String address) {
        Shop shop = new Shop();
        shop.setName(name);
        shop.setAddress(address);
        shopDao.save(shop);
    }

    public void updateShop(String id, String name, String address) {
        Shop shop = new Shop();
        shop.setId(Integer.parseInt(id));
        shop.setName(name);
        shop.setAddress(address);
        shopDao.update(shop);
    }

    public void deleteShop(String id) {
        shopDao.delete(Integer.parseInt(id));
    }

    public List<Staff> getAllStaff() {
        return staffDao.findAll();
    }

    public void addStaff(String role, String shopId) {
        Staff staff = new Staff();
        staff.setRole(Integer.parseInt(role));
        Shop shop = shopDao.find(Integer.parseInt(shopId));
        staff.setShop(shop);
        User user = new User();
        user.setPassword(Utils.md5("123"));
        staff.setUser(user);
        userDao.save(user);
        staffDao.save(staff);
    }

    public void updateStaff(String id, String role, String shopId) {
        Staff staff = new Staff();
        staff.setId(Integer.parseInt(id));
        staff.setRole(Integer.parseInt(role));
        Shop shop = shopDao.find(Integer.parseInt(shopId));
        staff.setShop(shop);
        staffDao.update(staff);
    }

    public void deleteStaff(String id) {
        staffDao.delete(Integer.parseInt(id));
    }
}
