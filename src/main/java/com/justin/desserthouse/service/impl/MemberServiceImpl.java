package com.justin.desserthouse.service.impl;

import com.justin.desserthouse.dao.*;
import com.justin.desserthouse.model.*;
import com.justin.desserthouse.service.MemberService;
import com.mysql.jdbc.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Justin on 2016/2/5.
 */

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private BankcardDao bankcardDao;
    @Autowired
    private MemberCardDao memberCardDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private ProductInventoryDao productInventoryDao;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private PaymentRecordDao paymentRecordDao;
    @Autowired
    private ExpenseRecordDao expenseRecordDao;

    public Product getProduct(int productId) {
        return productDao.find(productId);
    }

    public Shop getShop(int shopId) {
        return shopDao.find(shopId);
    }

    public double queryBalance(String username) {
        Member member = memberDao.find(username);
        MemberCard memberCard = member.getMemberCard();
        return memberCard.getMoney();
    }

    public Member getMember(String username) {
        return memberDao.find(username);
    }

    public void exchangePoint(String username) {
        Member member = memberDao.find(username);
        MemberCard memberCard = member.getMemberCard();
        memberCard.setMoney(memberCard.getMoney()+member.getPoint()/100.0);
        memberCardDao.update(memberCard);
        //record
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setMember(member);
        paymentRecord.setWayToPay(1);
        paymentRecord.setMoney(member.getPoint()/100.0);
        paymentRecord.setTime(new Date());
        paymentRecordDao.save(paymentRecord);
        member.setPoint(0);
        memberDao.update(member);
    }

    public void validateMember(String username) {
        Member member = memberDao.find(username);
        if (null != member.getRecentPaymentTime()) {
            Date paymentTime = member.getRecentPaymentTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(paymentTime);
            calendar.add(Calendar.YEAR, 1);
            Date oneYearLater = calendar.getTime();
            calendar = Calendar.getInstance();
            calendar.setTime(paymentTime);
            calendar.add(Calendar.YEAR, 2);
            Date twoYearLater = calendar.getTime();
            Date now = new Date();
            if (now.after(oneYearLater) && now.before(twoYearLater)) {
                member.setState(1);
                memberDao.update(member);
            }
            if (now.after(twoYearLater)) {
                member.setState(2);
                memberDao.update(member);
            }
        }
    }

    public void stopMember(String username) {
        Member member = memberDao.find(username);
        member.setState(2);
        memberDao.update(member);
    }

    public void updateProfile(String username, String age, String gender, String livingPlace) {
        Member member = memberDao.find(username);
        member.setAge(Integer.parseInt(age));
        member.setGender(gender);
        member.setLivingPlace(livingPlace);
        memberDao.update(member);
    }

    public String getProductNameWithId(String productId) {
        Product product = productDao.find(Integer.parseInt(productId));
        return product.getName();
    }

    public List<List<Product>> getProductsWithGroup() {
        List<Product> productList = productDao.findAll();
        List<List<Product>> groupedProductList = new ArrayList<List<Product>>();
        for (int i = 0; i < productList.size(); ) {
            List<Product> temp = new ArrayList<Product>();
            temp.add(productList.get(i++));
            while(i % 4 != 0) {
                if (i >= productList.size()) break;
                temp.add(productList.get(i++));
            }
            groupedProductList.add(temp);
        }
        return groupedProductList;
    }

    public List<ProductPlan> getProductPlanWithProductId(String productId) {
        List<ProductPlan> productPlanFull = productDao.find(Integer.parseInt(productId)).getProductPlan();
        List<ProductPlan> productPlan = new ArrayList<ProductPlan>();
        for (ProductPlan temp : productPlanFull) {
            if (temp.getState() == 1 && temp.getDate().after(new Date())) productPlan.add(temp);
        }
        return productPlan;
    }

    public ProductInventory getInventory(String productId, String dateStr, String shopId) {
        List<ProductInventory> productInventoryFull = productDao.find(Integer.parseInt(productId)).getProductInventories();
        List<ProductInventory> productInventoryMiddle = new ArrayList<ProductInventory>();
        ProductInventory productInventory = new ProductInventory();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            for (ProductInventory temp : productInventoryFull) {
                Date tempDate = temp.getDate();
                if (Utils.isTheSameDay(date, tempDate) && temp.getShop().getId() == Integer.parseInt(shopId)) productInventoryMiddle.add(temp);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (ProductInventory temp : productInventoryMiddle) {
            if (temp.getShop().getId() == Integer.parseInt(shopId)) productInventory = temp;
        }
        return productInventory;
    }

    public void activate(String username, String money) {
        Member member = memberDao.find(username);
        BankCard bankCard = member.getBankCard();
        bankCard.setMoney(bankCard.getMoney() - Double.parseDouble(money));
        bankcardDao.update(bankCard);
        MemberCard memberCard = member.getMemberCard();
        memberCard.setMoney(Double.parseDouble(money));
        memberCard.setLevel(Utils.level(Double.parseDouble(money)));
        memberCard.setDiscount(Utils.discount(memberCard.getLevel()));
        memberCardDao.update(memberCard);
        member.setRecentPaymentTime(new Date());
        member.setState(0);
        memberDao.update(member);
        //record
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setMoney(Double.parseDouble(money));
        paymentRecord.setMember(member);
        paymentRecord.setTime(new Date());
        paymentRecord.setWayToPay(0);
        paymentRecordDao.save(paymentRecord);
    }

    public void reserve(String number, String price, String purchaseDate, String username, String productId, String purchaseShop) {
        Member member = getMember(username);
        member.setPoint((int) (Integer.parseInt(number)*Double.parseDouble(price)));
        memberDao.update(member);
        Reservation reservation = new Reservation();
        ProductInventory productInventory = getInventory(productId, purchaseDate, purchaseShop);
        productInventory.setNumber(productInventory.getNumber()-Integer.parseInt(number));
        productInventoryDao.update(productInventory);
        reservation.setNumber(Integer.parseInt(number));
        reservation.setPrice(Double.parseDouble(price));
        reservation.setTotalPrice(Utils.numberFormatter(Integer.parseInt(number)*Double.parseDouble(price)));
        MemberCard memberCard = member.getMemberCard();
        reservation.setDiscount(memberCard.getDiscount());
        reservation.setTotalPriceAfterDiscount(Utils.numberFormatter(reservation.getTotalPrice()*reservation.getDiscount()));
        memberCard.setMoney(memberCard.getMoney()-reservation.getTotalPriceAfterDiscount());
        memberCardDao.update(memberCard);
        reservation.setPoint((int) reservation.getTotalPriceAfterDiscount());
        reservation.setReserveDate(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            reservation.setPurchaseDate(sdf.parse(purchaseDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setMember(member);
        Product product = productDao.find(Integer.parseInt(productId));
        reservation.setProduct(product);
        Shop shop = shopDao.find(Integer.parseInt(purchaseShop));
        reservation.setShop(shop);
        reservationDao.save(reservation);
    }

    public void cancelReservation(String id) {
        Reservation reservation = reservationDao.find(Integer.parseInt(id));
        Member member = reservation.getMember();
        ProductInventory productInventory = getInventory(Integer.toString(reservation.getProduct().getId()), reservation.getPurchaseDate().toString(), Integer.toString(reservation.getShop().getId()));
        productInventory.setNumber(productInventory.getNumber()+reservation.getNumber());
        productInventoryDao.update(productInventory);
        MemberCard memberCard = member.getMemberCard();
        memberCard.setMoney(memberCard.getMoney()+reservation.getTotalPrice()*0.9);
        memberCardDao.update(memberCard);
        //expense record
        ExpenseRecord expenseRecord = new ExpenseRecord();
        expenseRecord.setMember(member);
        expenseRecord.setTime(new Date());
        expenseRecord.setWayToPay(2);
        expenseRecord.setDiscount(1);
        expenseRecord.setTotalPrice(Utils.numberFormatter(reservation.getTotalPrice()*0.1));
        expenseRecord.setTotalPriceAfterDiscount(Utils.numberFormatter(reservation.getTotalPrice()*0.1));
        expenseRecordDao.save(expenseRecord);
        reservationDao.delete(Integer.parseInt(id));
    }

    public List<Reservation> getReservationList(String username) {
        return memberDao.find(username).getReservations();
    }
}
