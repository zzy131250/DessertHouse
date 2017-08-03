package com.justin.desserthouse.service.impl;

import com.justin.desserthouse.dao.*;
import com.justin.desserthouse.model.*;
import com.justin.desserthouse.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Justin on 2016/2/5.
 */

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ProductPlanDao productPlanDao;
    @Autowired
    private ProductInventoryDao productInventoryDao;
    @Autowired
    private MemberCardDao memberCardDao;
    @Autowired
    private ExpenseRecordDao expenseRecordDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private MemberDao memberDao;

    public Staff getStaff(String username) {
        return staffDao.find(Integer.parseInt(username));
    }

    public List<Staff> getAllStaff() {
        return staffDao.findAll();
    }

    public int getStaffShopId(String username) {
        return staffDao.find(Integer.parseInt(username)).getShop().getId();
    }

    public List<ProductPlan> getProductPlanCheckList() {
        return productPlanDao.findWithState("state", 0);
    }

    public List<ProductPlan> getProductPlanPeriod(String username, String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<ProductPlan> productPlanListFull = productPlanDao.findWithTime(sdf.parse(start), sdf.parse(end));
            List<ProductPlan> productPlanList = new ArrayList<ProductPlan>();
            for (ProductPlan temp : productPlanListFull) {
                if (temp.getStaff().getId() == Integer.parseInt(username)) productPlanList.add(temp);
            }
            return productPlanList;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProductPlan getProductPlan(Date date, String productId, String shopId) {
        Product product = productDao.find(Integer.parseInt(productId));
        List<ProductPlan> productPlanList = product.getProductPlan();
        for (ProductPlan temp : productPlanList) {
            if (Utils.isTheSameDay(temp.getDate(), date) && temp.getShop().getId() == Integer.parseInt(shopId)) return temp;
        }
        return null;
    }

    public List<ProductPlan> getAllProductPlan(String username) {
        return staffDao.find(Integer.parseInt(username)).getProductPlan();
    }

    public void addProductPlan(String staffId, String shopId, String productId, String date, String salesNumber, String price) {
        ProductPlan productPlan = new ProductPlan();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date dateSql = format.parse(date);
            productPlan.setDate(dateSql);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        productPlan.setSaleNumber(Integer.parseInt(salesNumber));
        productPlan.setPrice(Double.parseDouble(price));
        productPlan.setState(0);
        Staff staff = staffDao.find(Integer.parseInt(staffId));
        productPlan.setStaff(staff);
        Shop shop = shopDao.find(Integer.parseInt(shopId));
        productPlan.setShop(shop);
        Product product = productDao.find(Integer.parseInt(productId));
        productPlan.setProduct(product);
        productPlanDao.save(productPlan);
    }

    public void updateProductPlan(String id, String staffId, String shopId, String productId, String date, String salesNumber, String price) {
        ProductPlan productPlan = productPlanDao.find(Integer.parseInt(id));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date dateSql = format.parse(date);
            productPlan.setDate(dateSql);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        productPlan.setSaleNumber(Integer.parseInt(salesNumber));
        productPlan.setPrice(Double.parseDouble(price));
        productPlan.setState(0);
        Staff staff = staffDao.find(Integer.parseInt(staffId));
        productPlan.setStaff(staff);
        Shop shop = shopDao.find(Integer.parseInt(shopId));
        productPlan.setShop(shop);
        Product product = productDao.find(Integer.parseInt(productId));
        productPlan.setProduct(product);
        productPlanDao.update(productPlan);
    }

    public void checkProductPlan(String id, int state) {
        ProductPlan productPlan = productPlanDao.find(Integer.parseInt(id));
        productPlan.setState(state);
        if (state == 1) {
            ProductInventory productInventory = new ProductInventory();
            productInventory.setDate(productPlan.getDate());
            productInventory.setNumber(productPlan.getSaleNumber());
            productInventory.setShop(productPlan.getShop());
            productInventory.setProduct(productPlan.getProduct());
            productInventoryDao.save(productInventory);
        }
        productPlanDao.update(productPlan);
    }

    public void deleteProductPlan(String id) {
        productPlanDao.delete(Integer.parseInt(id));
    }

    public List<Product> getProductListWithInventory(String shopId) {
        Shop shop = shopDao.find(Integer.parseInt(shopId));
        List<ProductInventory> productInventoryListFull = shop.getProductInventories();
        List<Product> productList = new ArrayList<Product>();
        for (ProductInventory temp : productInventoryListFull) {
            if (Utils.isTheSameDay(temp.getDate(), new Date())) productList.add(temp.getProduct());
        }
        return productList;
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProduct(String productId) {
        return productDao.find(Integer.parseInt(productId));
    }

    public void addProduct(String name) {
        Product product = new Product();
        product.setName(name);
        productDao.save(product);
    }

    public void updateProduct(String id, String name) {
        Product product = new Product();
        product.setId(Integer.parseInt(id));
        product.setName(name);
        productDao.update(product);
    }

    public void deleteProduct(String id) {
        productDao.delete(Integer.parseInt(id));
    }

    public Member getMemberWithMemberCardId(String memberCardId) {
        MemberCard memberCard = memberCardDao.find(Integer.parseInt(memberCardId));
        if (null == memberCard) return null;
        return memberCard.getMember();
    }

    public Member getMemberWithReservationId(String reservationId) {
        return reservationDao.find(Integer.parseInt(reservationId)).getMember();
    }

    public ProductInventory getInventory(String productId, String shopId) {
        List<ProductInventory> productInventoryFull = productDao.find(Integer.parseInt(productId)).getProductInventories();
        List<ProductInventory> productInventoryMiddle = new ArrayList<ProductInventory>();
        ProductInventory productInventory = new ProductInventory();
        for (ProductInventory temp : productInventoryFull) {
            Date tempDate = temp.getDate();
            if (Utils.isTheSameDay(new Date(), tempDate) && temp.getShop().getId() == Integer.parseInt(shopId)) productInventoryMiddle.add(temp);
        }
        for (ProductInventory temp : productInventoryMiddle) {
            if (temp.getShop().getId() == Integer.parseInt(shopId)) productInventory = temp;
        }
        return productInventory;
    }

    public void memberSell(String number, String price, String memberCardId, String shopId, String productId, String staffId) {
        MemberCard memberCard = memberCardDao.find(Integer.parseInt(memberCardId));
        //point
        Member member = memberCard.getMember();
        member.setPoint((int) (Integer.parseInt(number)*Double.parseDouble(price)));
        memberDao.update(member);
        //member card
        double totalPrice = Utils.numberFormatter(Double.parseDouble(number)*Double.parseDouble(price));
        memberCard.setMoney(memberCard.getMoney()-totalPrice*memberCard.getDiscount());
        memberCardDao.update(memberCard);
        //inventory
        ProductInventory productInventory = getInventory(productId, shopId);
        productInventory.setNumber(productInventory.getNumber()-Integer.parseInt(number));
        productInventoryDao.update(productInventory);
        //record
        ExpenseRecord expenseRecord = new ExpenseRecord();
        expenseRecord.setTime(new Date());
        expenseRecord.setNumber(Integer.parseInt(number));
        expenseRecord.setPrice(Double.parseDouble(price));
        expenseRecord.setTotalPrice(totalPrice);
        expenseRecord.setWayToPay(0);
        expenseRecord.setDiscount(memberCard.getDiscount());
        expenseRecord.setTotalPriceAfterDiscount(Utils.numberFormatter(totalPrice*expenseRecord.getDiscount()));
        expenseRecord.setPoint((int) totalPrice);
        expenseRecord.setMember(memberCard.getMember());
        expenseRecord.setShop(shopDao.find(Integer.parseInt(shopId)));
        expenseRecord.setProduct(productDao.find(Integer.parseInt(productId)));
        expenseRecord.setStaff(staffDao.find(Integer.parseInt(staffId)));
        expenseRecordDao.save(expenseRecord);
    }

    public void cashSell(String number, String price, String shopId, String productId, String staffId) {
        //inventory
        ProductInventory productInventory = getInventory(productId, shopId);
        productInventory.setNumber(productInventory.getNumber()-Integer.parseInt(number));
        productInventoryDao.update(productInventory);
        //record
        double totalPrice = Utils.numberFormatter(Double.parseDouble(number)*Double.parseDouble(price));
        ExpenseRecord expenseRecord = new ExpenseRecord();
        expenseRecord.setTime(new Date());
        expenseRecord.setNumber(Integer.parseInt(number));
        expenseRecord.setPrice(Double.parseDouble(price));
        expenseRecord.setTotalPrice(totalPrice);
        expenseRecord.setWayToPay(1);
        expenseRecord.setDiscount(1);
        expenseRecord.setTotalPriceAfterDiscount(totalPrice*expenseRecord.getDiscount());
        expenseRecord.setPoint(0);
        expenseRecord.setShop(shopDao.find(Integer.parseInt(shopId)));
        expenseRecord.setProduct(productDao.find(Integer.parseInt(productId)));
        expenseRecord.setStaff(staffDao.find(Integer.parseInt(staffId)));
        expenseRecordDao.save(expenseRecord);
    }

    public void reserveSell(String reservationId, String staffId) {
        Reservation reservation = reservationDao.find(Integer.parseInt(reservationId));
        //record
        ExpenseRecord expenseRecord = new ExpenseRecord();
        expenseRecord.setTime(new Date());
        expenseRecord.setNumber(reservation.getNumber());
        expenseRecord.setPrice(reservation.getPrice());
        expenseRecord.setTotalPrice(reservation.getTotalPrice());
        expenseRecord.setWayToPay(0);
        expenseRecord.setDiscount(reservation.getDiscount());
        expenseRecord.setTotalPriceAfterDiscount(reservation.getTotalPriceAfterDiscount());
        expenseRecord.setPoint(reservation.getPoint());
        expenseRecord.setMember(reservation.getMember());
        expenseRecord.setShop(reservation.getShop());
        expenseRecord.setProduct(reservation.getProduct());
        expenseRecord.setStaff(staffDao.find(Integer.parseInt(staffId)));
        expenseRecordDao.save(expenseRecord);
        reservationDao.delete(Integer.parseInt(reservationId));
    }

    public int getMemberStartAge() {
        List<Member> memberList = memberDao.findAll();
        int startAge = memberList.get(0).getAge();
        for (Member temp : memberList) {
            if (temp.getAge() < startAge) startAge = temp.getAge();
        }
        while (startAge % 10 != 0) --startAge;
        return startAge;
    }

    public int getMemberEndAge() {
        List<Member> memberList = memberDao.findAll();
        int startAge = memberList.get(0).getAge();
        for (Member temp : memberList) {
            if (temp.getAge() > startAge) startAge = temp.getAge();
        }
        while (startAge % 10 != 0) ++startAge;
        return startAge;
    }

    public Map<String, Double> getMemberAgeDistribute() {
        DecimalFormat df = new DecimalFormat("#.##");
        List<Member> memberList = memberDao.findAll();
        int startAge = getMemberStartAge();
        int endAge = getMemberEndAge();
        Map<String, Double> distribute = new HashMap<String, Double>();
        for (int i = startAge/10; i < endAge/10; i ++) {
            double percentage = 0.0;
            for (Member temp : memberList) {
                if (temp.getAge() >= i*10 && temp.getAge() < i*10+10) percentage++;
            }
            percentage = percentage / memberList.size();
            distribute.put(Integer.toString(i*10)+"-"+Integer.toString(i*10+10), Double.parseDouble(df.format(percentage)));
        }
        return distribute;
    }

    public Map<String, Double> getMemberGenderDistribute() {
        Map<String, Double> distribute = new HashMap<String, Double>();
        List<Member> memberList = memberDao.findAll();
        DecimalFormat df = new DecimalFormat("#.##");
        double percentage = 0.0;
        for (Member temp : memberList) {
            if (temp.getGender().equals("male")) percentage++;
        }
        percentage = percentage / memberList.size();
        distribute.put("male", Double.parseDouble(df.format(percentage)));
        distribute.put("female", Double.parseDouble(df.format(1-percentage)));
        return distribute;
    }

    public Map<String, Integer> getMemberCardStateDistribute() {
        Map<String, Integer> distribute = new HashMap<String, Integer>();
        List<Member> memberList = memberDao.findAll();
        int num = 0;
        for (Member temp : memberList) {
            if (temp.getState() == 0) num++;
        }
        distribute.put("normal", num);
        num = 0;
        for (Member temp : memberList) {
            if (temp.getState() == 1) num++;
        }
        distribute.put("paused", num);
        num = 0;
        for (Member temp : memberList) {
            if (temp.getState() == 2) num++;
        }
        distribute.put("stopped", num);
        return distribute;
    }

    public List<Map.Entry<String, Double>> getMemberMonthExpense() {
        DecimalFormat df = new DecimalFormat("#.##");
        List<Member> memberList = memberDao.findAll();
        List<Map.Entry<String, Double>> distribute = new ArrayList<Map.Entry<String, Double>>();
        Map<String, Double> full = new TreeMap<String, Double>();
        List<ExpenseRecord> expenseRecordList = expenseRecordDao.findWithTime(Utils.getMonthStart(), Utils.getMonthEnd());
        for (Member temp : memberList) {
            double expense = 0.0;
            for (ExpenseRecord expenseRecord : expenseRecordList) {
                if (null != expenseRecord.getMember() && temp.getUsername().equals(expenseRecord.getMember().getUsername())) expense += expenseRecord.getTotalPriceAfterDiscount();
            }
            full.put(temp.getUsername(), Double.parseDouble(df.format(expense)));
        }
        List<Map.Entry<String, Double>> fullList = new ArrayList<Map.Entry<String, Double>>(full.entrySet());
        Collections.sort(fullList, new Comparator<Map.Entry<String, Double>>() {

            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                 return o2.getValue().compareTo(o1.getValue());
            }
        });
        int num = 0;
        for (Map.Entry<String, Double> entry : fullList) {
            if (num >= 5) break;
            distribute.add(entry);
            num ++;
        }
        return distribute;
    }

    public Map<String, Map<String, Integer>> getShopReservation() {
        Map<String, Map<String, Integer>> result = new TreeMap<String, Map<String, Integer>>();
        List<Reservation> reservationList = reservationDao.findWithTime(Utils.getMonthStart(), Utils.getMonthEnd());
        List<Shop> shopList = shopDao.findAll();
        for (Shop shop : shopList) {
            Map<String, Integer> productSell = new HashMap<String, Integer>();
            for (Reservation reservation : reservationList) {
                if (shop.getId() == reservation.getShop().getId()) {
                    if (null == productSell.get(reservation.getProduct().getName())) productSell.put(reservation.getProduct().getName(), reservation.getNumber());
                    else productSell.put(reservation.getProduct().getName(), productSell.get(reservation.getProduct().getName())+reservation.getNumber());
                }
            }
            result.put(shop.getName(), productSell);
        }
        return result;
    }

    public Map<String, Map<String, Integer>> getShopSale() {
        Map<String, Map<String, Integer>> result = new TreeMap<String, Map<String, Integer>>();
        List<ExpenseRecord> expenseRecordList = expenseRecordDao.findWithTime(Utils.getMonthStart(), Utils.getMonthEnd());
        List<Shop> shopList = shopDao.findAll();
        for (Shop shop : shopList) {
            Map<String, Integer> productSell = new HashMap<String, Integer>();
            for (ExpenseRecord expenseRecord : expenseRecordList) {
                if (null != expenseRecord.getShop() && shop.getId() == expenseRecord.getShop().getId()) {
                    if (null == productSell.get(expenseRecord.getProduct().getName())) productSell.put(expenseRecord.getProduct().getName(), expenseRecord.getNumber());
                    else productSell.put(expenseRecord.getProduct().getName(), productSell.get(expenseRecord.getProduct().getName())+expenseRecord.getNumber());
                }
            }
            result.put(shop.getName(), productSell);
        }
        return result;
    }

    public List<Map.Entry<String, Integer>> getHotProduct() {
        List<Product> productList = productDao.findAll();
        List<Map.Entry<String, Integer>> hotProduct = new ArrayList<Map.Entry<String, Integer>>();
        Map<String, Integer> fullMap = new HashMap<String, Integer>();
        List<ExpenseRecord> expenseRecordList = expenseRecordDao.findWithTime(Utils.getMonthStart(), Utils.getMonthEnd());
        for (Product product : productList) {
            int num = 0;
            for (ExpenseRecord expenseRecord : expenseRecordList) {
                if (null != expenseRecord.getProduct() && expenseRecord.getProduct().getId() == product.getId()) num += expenseRecord.getNumber();
            }
            fullMap.put(product.getName(), num);
        }
        List<Map.Entry<String, Integer>> fullList = new ArrayList<Map.Entry<String, Integer>>(fullMap.entrySet());
        Collections.sort(fullList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int num = 0;
        for (Map.Entry<String, Integer> entry : fullList) {
            if (num >= 5) break;
            hotProduct.add(entry);
            num ++;
        }
        return hotProduct;
    }
}
