package com.justin.desserthouse.action;

import com.justin.desserthouse.model.Shop;
import com.justin.desserthouse.model.Staff;
import com.justin.desserthouse.service.AdminService;
import com.justin.desserthouse.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Justin on 2016/2/5.
 */

@Repository
public class AdminAction extends BaseAction {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StaffService staffService;

    private List<Shop> shopList;
    private List<Staff> staffList;
    private String message;

    public String shop() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            shopList = adminService.getAllShops();
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("add")) {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            adminService.addShop(name, address);
            return "reload";
        } else if (behavior.equals("update")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            adminService.updateShop(id, name, address);
            return "reload";
        } else {
            String id = request.getParameter("id");
            adminService.deleteShop(id);
            message = "ok";
            return "message";
        }
    }

    public String staff() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            staffList = adminService.getAllStaff();
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("add")) {
            String role = request.getParameter("role");
            String shopId = request.getParameter("shopId");
            adminService.addStaff(role, shopId);
            return "reload";
        } else if (behavior.equals("update")) {
            String id = request.getParameter("id");
            String role = request.getParameter("role");
            String shopId = request.getParameter("shopId");
            adminService.updateStaff(id, role, shopId);
            return "reload";
        } else {
            String id = request.getParameter("id");
            adminService.deleteStaff(id);
            message = "ok";
            return "message";
        }
    }

    public String shopJson() {
        shopList = adminService.getAllShops();
        return "message";
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
