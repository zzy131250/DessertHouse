package com.justin.desserthouse.action;

import com.justin.desserthouse.model.*;
import com.justin.desserthouse.service.MemberService;
import com.justin.desserthouse.service.impl.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Justin on 2016/2/3.
 */

@Repository
public class MemberAction extends BaseAction {

    @Autowired
    private MemberService memberService;

    private List<List<Product>> productList;
    private List<ProductPlan> productPlanList;
    private List<Shop> shopList;
    private List<Reservation> reservationList;
    private List<ExpenseRecord> expenseRecordList;
    private List<PaymentRecord> paymentRecordList;
    private ProductPlan productPlan;
    private ProductInventory productInventory;
    private Member member;
    private int inventory;
    private double price;
    private String message;
    public static List<CartItem> shoppingCart;


    public String index() {
        return "index";
    }

    public String activate() {
        member = memberService.getMember(session.get("username").toString());
        if(request.getMethod().equalsIgnoreCase("get")) {
            if (member.getState() != 1) session.put("state", "normal");
            session.put("bankcardId", member.getBankCard().getId());
            session.put("memberCardId", member.getMemberCard().getId());
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("query bankcard money")) {
            String money = request.getParameter("money");
            if (member.getBankCard().getMoney() >= Double.parseDouble(money)) message = "ok";
            else message = "no";
            return "message";
        } else if (behavior.equals("activate")) {
            String money = request.getParameter("money");
            memberService.activate(session.get("username").toString(), money);
            message = "ok";
            return "message";
        }
        return null;
    }

    public String product() {
        productList = memberService.getProductsWithGroup();
        if (null != session.get("username")) memberService.validateMember(session.get("username").toString());
        return "show";
    }

    public String reserve() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            String productId = request.getParameter("productId");
            productPlanList = memberService.getProductPlanWithProductId(productId);
            session.put("productName", memberService.getProductNameWithId(productId));
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("query shop")) {
            shopList = new ArrayList<Shop>();
            String dateStr = request.getParameter("date");
            String productId = request.getParameter("productId");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(dateStr);
                if (productId != null) {
                    List<ProductPlan> list = memberService.getProductPlanWithProductId(productId);
                    for (ProductPlan temp : list) {
                        Date tempDate = temp.getDate();
                        if (Utils.isTheSameDay(date, tempDate)) shopList.add(temp.getShop());
                    }
                } else {
                    for (ProductPlan temp : productPlanList) {
                        Date tempDate = temp.getDate();
                        if (Utils.isTheSameDay(date, tempDate)) shopList.add(temp.getShop());
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return "shopMessage";
        } else if (behavior.equals("query inventory")) {
            String productId = request.getParameter("productId");
            String dateStr = request.getParameter("date");
            String shopId = request.getParameter("shopId");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(dateStr);
                for (ProductPlan temp : productPlanList) {
                    if (Utils.isTheSameDay(temp.getDate(), date) && temp.getShop().getId() == Integer.parseInt(shopId)) productPlan = temp;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            productInventory = memberService.getInventory(productId, dateStr, shopId);
            inventory = productInventory.getNumber();
            price = productPlan.getPrice();
            return "inventoryMessage";
        } else if (behavior.equals("query member state and balance")) {
            String username = request.getParameter("username");
            String number = request.getParameter("number");
            String price = request.getParameter("price");
            member = memberService.getMember(username);
            int state = member.getState();
            if (state == 0) message = "normal";
            else if (state == 1) message = "paused";
            else if (state == 2) message = "stopped";
            if ((state == 0) && (Double.parseDouble(number)*Double.parseDouble(price) > memberService.queryBalance(session.get("username").toString()))) {
                message = "insufficient balance";
            }
            return "stateMessage";
        } else if (behavior.equals("reserve")) {
            String number = request.getParameter("number");
            String price = request.getParameter("price");
            String purchaseDate = request.getParameter("purchaseDate");
            String productId = request.getParameter("productId");
            String purchaseShop = request.getParameter("purchaseShopId");
            memberService.reserve(number, price, purchaseDate, session.get("username").toString(), productId, purchaseShop);
            return "reload";
        } else if (behavior.equals("reserve ajax")) {
            String number = request.getParameter("number");
            String price = request.getParameter("price");
            String purchaseDate = request.getParameter("purchaseDate");
            String productId = request.getParameter("productId");
            String purchaseShop = request.getParameter("purchaseShopId");
            memberService.reserve(number, price, purchaseDate, session.get("username").toString(), productId, purchaseShop);
            CartItem item = new CartItem();
            for (CartItem temp: shoppingCart) {
                if (Integer.toString(temp.getProduct().getId()).equals(productId)) {
                    item = temp;
                    break;
                }
            }
            shoppingCart.remove(item);
            message = "ok";
            return "ajax";
        }
        return null;
    }

    public String reservation() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            if (null != session.get("username")) reservationList = memberService.getReservationList(session.get("username").toString());
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("cancel")) {
            String reservationId = request.getParameter("reservationId");
            memberService.cancelReservation(reservationId);
            return "reload";
        }
        return null;
    }

    public String contact() {
        return "show";
    }

    public String profile() {
        if(request.getMethod().equalsIgnoreCase("get")) {
            if (null != session.get("username")) {
                member = memberService.getMember(session.get("username").toString());
                BankCard bankCard = member.getBankCard();
                bankCard.setMoney(Utils.numberFormatter(bankCard.getMoney()));
                reservationList = member.getReservations();
                expenseRecordList = member.getExpenseRecords();
                paymentRecordList = member.getPaymentRecords();
            }
            return "show";
        }
        String behavior = request.getParameter("behavior");
        if (behavior.equals("edit")) {
            String age = request.getParameter("age");
            String gender = request.getParameter("gender");
            String livingPlace = request.getParameter("livingPlace");
            memberService.updateProfile(session.get("username").toString(), age, gender, livingPlace);
            return "reload";
        } else if (behavior.equals("exchange")) {
            memberService.exchangePoint(session.get("username").toString());
            return "message";
        } else if (behavior.equals("stop")) {
            memberService.stopMember(session.get("username").toString());
            session.clear();
            return "message";
        }
        return null;
    }

    public String shoppingCart() {
        String productId = request.getParameter("productId");
        String price = request.getParameter("price");
        String purchaseDate = request.getParameter("purchaseDate");
        String purchaseShopId = request.getParameter("purchaseShopId");
        String number = request.getParameter("number");
        CartItem item = new CartItem();
        item.setProduct(memberService.getProduct(Integer.parseInt(productId)));
        item.setPrice(Double.parseDouble(price));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            item.setDate(sdf.parse(purchaseDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        item.setShop(memberService.getShop(Integer.parseInt(purchaseShopId)));
        item.setNumber(Integer.parseInt(number));
        shoppingCart.add(item);
        message = "ok";
        return "ok";
    }

    public List<List<Product>> getProductList() {
        return productList;
    }

    public void setProductList(List<List<Product>> productList) {
        this.productList = productList;
    }

    public List<ProductPlan> getProductPlanList() {
        return productPlanList;
    }

    public void setProductPlanList(List<ProductPlan> productPlanList) {
        this.productPlanList = productPlanList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
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

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CartItem> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<CartItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
