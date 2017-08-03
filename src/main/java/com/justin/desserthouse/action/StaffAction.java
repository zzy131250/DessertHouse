package com.justin.desserthouse.action;

import com.justin.desserthouse.model.*;
import com.justin.desserthouse.service.StaffService;
import com.justin.desserthouse.service.impl.Utils;
import com.mysql.jdbc.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Justin on 2016/2/3.
 */

@Repository
public class StaffAction extends BaseAction {

    @Autowired
    private StaffService staffService;

    private List<List<Product>> productListUi;
    private List<Product> productList;
    private String message;
    private List<ProductPlan> productPlanList;
    private List<ProductPlan> productPlanCheckList;
    private Map<String, String> mapMessage;
    private List<ExpenseRecord> expenseRecordList;
    private List<PaymentRecord> paymentRecordList;
    private List<Reservation> reservationList;
    private Member member;

    private Map<String, Double> memberAgeDistribute;
    private Map<String, Double> memberGenderDistribute;
    private Map<String, Integer> memberCardStateDistribute;
    private List<Map.Entry<String, Double>> memberExpenseDistribute;
    private Map<String, Map<String, Integer>> shopReservation;
    private Map<String, Map<String, Integer>> shopSale;
    private List<Map.Entry<String, Integer>> hotProduct;

    public String sale() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            session.put("shop", staffService.getStaffShopId(session.get("username").toString()));
            productList = staffService.getProductListWithInventory(session.get("shop").toString());
            productListUi = new ArrayList<List<Product>>();
            for (int i = 0; i < productList.size(); ) {
                List<Product> temp = new ArrayList<Product>();
                temp.add(productList.get(i++));
                while (i % 4 != 0 && i < productList.size()) {
                    temp.add(productList.get(i++));
                }
                productListUi.add(temp);
            }
            return "show";
        }
        return null;
    }

    public String sell() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            String productId = request.getParameter("productId");
            session.put("productName", staffService.getProduct(productId).getName());
            session.put("price", staffService.getProductPlan(new Date(), productId, Integer.toString(staffService.getStaffShopId(session.get("username").toString()))).getPrice());
            session.put("inventory", staffService.getInventory(productId, Integer.toString(staffService.getStaffShopId(session.get("username").toString()))).getNumber());
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("choose member")) {
            String memberCardId = request.getParameter("memberCard");
            Member member = staffService.getMemberWithMemberCardId(memberCardId);
            if (null == member) {
                message = "Wrong Id";
                return "wrong card";
            }
            mapMessage = new HashMap<String, String>();
            mapMessage.put("Money", Double.toString(Utils.numberFormatter(member.getMemberCard().getMoney())));
            mapMessage.put("Level", Integer.toString(member.getMemberCard().getLevel()));
            mapMessage.put("Discount", Double.toString(member.getMemberCard().getDiscount()));
            mapMessage.put("Username", member.getUsername());
            return "message";
        } else if (behavior.equals("member sell")) {
            String price = request.getParameter("price");
            String number = request.getParameter("number");
            String memberCardId = request.getParameter("memberCard");
            String shopId = Integer.toString(staffService.getStaffShopId(session.get("username").toString()));
            String productId = request.getParameter("productId");
            String staffId = session.get("username").toString();
            staffService.memberSell(number, price, memberCardId, shopId, productId, staffId);
            return "success";
        } else if (behavior.equals("cash sell")) {
            String number = request.getParameter("number");
            String price = request.getParameter("price");
            String shopId = Integer.toString(staffService.getStaffShopId(session.get("username").toString()));
            String productId = request.getParameter("productId");
            String staffId = session.get("username").toString();
            staffService.cashSell(number, price, shopId, productId, staffId);
            message = "success";
            return "cash";
        }
        return null;
    }

    public String memberInfo() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("search")) {
            String memberCardId = request.getParameter("memberCard");
            member = staffService.getMemberWithMemberCardId(memberCardId);
            reservationList = member.getReservations();
            expenseRecordList = member.getExpenseRecords();
            paymentRecordList = member.getPaymentRecords();
            return "message";
        } else if (behavior.equals("sell")) {
            String reservationId = request.getParameter("reservationId");
            member = staffService.getMemberWithReservationId(reservationId);
            staffService.reserveSell(reservationId, session.get("username").toString());
            reservationList = member.getReservations();
            expenseRecordList = member.getExpenseRecords();
            paymentRecordList = member.getPaymentRecords();
            return "message";
        }
        return null;
    }

    public String productPlan() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            String username = session.get("username").toString();
            productPlanList = staffService.getAllProductPlan(username);
            session.put("shop", staffService.getStaffShopId(username));
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("add")) {
            String shopId = request.getParameter("shopId");
            String productId = request.getParameter("productId");
            String date = request.getParameter("date");
            String saleNumber = request.getParameter("saleNumber");
            String price = request.getParameter("price");
            staffService.addProductPlan(session.get("username").toString(), shopId, productId, date, saleNumber, price);
            return "reload";
        } else if(behavior.equals("update")) {
            String id = request.getParameter("id");
            String shopId = request.getParameter("shopId");
            String productId = request.getParameter("productId");
            String date = request.getParameter("date");
            String saleNumber = request.getParameter("saleNumber");
            String price = request.getParameter("price");
            staffService.updateProductPlan(id, session.get("username").toString(), shopId, productId, date, saleNumber, price);
            return "reload";
        } else if (behavior.equals("delete")) {
            String id = request.getParameter("id");
            staffService.deleteProductPlan(id);
            message = "ok";
            return "message";
        } else {
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            productPlanList = staffService.getProductPlanPeriod(session.get("username").toString(), start, end);
            return "show";
        }
    }

    public String productListJson() {
        productList = staffService.getAllProducts();
        return "message";
    }

    public String productPlanCheck() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            productPlanCheckList = staffService.getProductPlanCheckList();
            return "show";
        }
        String behavior = request.getParameter("behavior");
        String id = request.getParameter("id");
        if (behavior.equals("approve")) {
            staffService.checkProductPlan(id, 1);
        } else if (behavior.equals("reject")) {
            staffService.checkProductPlan(id, 2);
        }
        message = "ok";
        return "message";
    }

    public String memberData() {
        memberAgeDistribute = staffService.getMemberAgeDistribute();
        memberGenderDistribute = staffService.getMemberGenderDistribute();
        memberCardStateDistribute = staffService.getMemberCardStateDistribute();
        memberExpenseDistribute = staffService.getMemberMonthExpense();
        return "show";
    }

    public String reservationSale() {
        productList = staffService.getAllProducts();
        shopSale = staffService.getShopSale();
        shopReservation = staffService.getShopReservation();
        return "show";
    }

    public String hotProduct() {
        hotProduct = staffService.getHotProduct();
        return "show";
    }

    public String productList() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            productList = staffService.getAllProducts();
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("add")) {
            String name = request.getParameter("name");
            staffService.addProduct(name);
            return "reload";
        } else if (behavior.equals("update")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            staffService.updateProduct(id, name);
            return "reload";
        } else {
            String id = request.getParameter("id");
            staffService.deleteProduct(id);
            message = "ok";
            return "message";
        }
    }

    public List<List<Product>> getProductListUi() {
        return productListUi;
    }

    public void setProductListUi(List<List<Product>> productListUi) {
        this.productListUi = productListUi;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductPlan> getProductPlanList() {
        return productPlanList;
    }

    public void setProductPlanList(List<ProductPlan> productPlanList) {
        this.productPlanList = productPlanList;
    }

    public List<ProductPlan> getProductPlanCheckList() {
        return productPlanCheckList;
    }

    public void setProductPlanCheckList(List<ProductPlan> productPlanCheckList) {
        this.productPlanCheckList = productPlanCheckList;
    }

    public Map<String, String> getMapMessage() {
        return mapMessage;
    }

    public void setMapMessage(Map<String, String> mapMessage) {
        this.mapMessage = mapMessage;
    }

    public List<ExpenseRecord> getExpenseRecordList() {
        return expenseRecordList;
    }

    public void setExpenseRecordList(List<ExpenseRecord> expenseRecordList) {
        this.expenseRecordList = expenseRecordList;
    }

    public List<PaymentRecord> getPaymentRecordList() {
        return paymentRecordList;
    }

    public void setPaymentRecordList(List<PaymentRecord> paymentRecordList) {
        this.paymentRecordList = paymentRecordList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Map<String, Double> getMemberAgeDistribute() {
        return memberAgeDistribute;
    }

    public void setMemberAgeDistribute(Map<String, Double> memberAgeDistribute) {
        memberAgeDistribute = memberAgeDistribute;
    }

    public Map<String, Double> getMemberGenderDistribute() {
        return memberGenderDistribute;
    }

    public void setMemberGenderDistribute(Map<String, Double> memberGenderDistribute) {
        this.memberGenderDistribute = memberGenderDistribute;
    }

    public Map<String, Integer> getMemberCardStateDistribute() {
        return memberCardStateDistribute;
    }

    public void setMemberCardStateDistribute(Map<String, Integer> memberCardStateDistribute) {
        this.memberCardStateDistribute = memberCardStateDistribute;
    }

    public List<Map.Entry<String, Double>> getMemberExpenseDistribute() {
        return memberExpenseDistribute;
    }

    public void setMemberExpenseDistribute(List<Map.Entry<String, Double>> memberExpenseDistribute) {
        this.memberExpenseDistribute = memberExpenseDistribute;
    }

    public Map<String, Map<String, Integer>> getShopReservation() {
        return shopReservation;
    }

    public void setShopReservation(Map<String, Map<String, Integer>> shopReservation) {
        this.shopReservation = shopReservation;
    }

    public Map<String, Map<String, Integer>> getShopSale() {
        return shopSale;
    }

    public void setShopSale(Map<String, Map<String, Integer>> shopSale) {
        this.shopSale = shopSale;
    }

    public List<Map.Entry<String, Integer>> getHotProduct() {
        return hotProduct;
    }

    public void setHotProduct(List<Map.Entry<String, Integer>> hotProduct) {
        this.hotProduct = hotProduct;
    }
}
