package com.justin.desserthouse.service.impl;

import com.justin.desserthouse.dao.*;
import com.justin.desserthouse.model.*;
import com.justin.desserthouse.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Justin on 2016/2/4.
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StaffDao staffDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BankcardDao bankcardDao;
    @Autowired
    private MemberCardDao memberCardDao;

    public String checkLogin(String username, String password) {
        if (username.length() == 6 && Utils.isNumber(username)) {    //staff
            Staff staff = staffDao.find(Integer.parseInt(username));
            if (null == staff) return "error";
            User user = staff.getUser();
            if (!user.getPassword().equals(Utils.md5(password))) return "error";
            //根据职位返回index, staff username 即 id
            String role = "";
            switch (staff.getRole()) {
                case 0:
                    role = "manager";
                    break;
                case 1:
                    role = "headStaff";
                    break;
                case 2:
                    role = "branchStaff";
                    break;
                case 3:
                    role = "admin";
                    break;
            }
            return role;
        } else {    //member
            Member member = memberDao.find(username);
            if (null == member) return "error";
            User user = member.getUser();
            if (!user.getPassword().equals(Utils.md5(password))) return "error";
            return "member";
        }
    }

    public String checkRegister(String username) {
        Member member = memberDao.find(username);
        if (null == member) return "ok";
        return "no";
    }

    public void register(String username, String password, String age, String gender, String livingPlace, String bankcardId) {
        User user = new User();
        user.setPassword(Utils.md5(password));
        BankCard bankCard = bankcardDao.find(Integer.parseInt(bankcardId));
        MemberCard memberCard = new MemberCard();
        Member member = new Member();
        member.setUsername(username);
        member.setAge(Integer.parseInt(age));
        member.setGender(gender);
        member.setLivingPlace(livingPlace);
        member.setPoint(0);
        member.setState(1);
        member.setUser(user);
        member.setBankCard(bankCard);
        member.setMemberCard(memberCard);
        userDao.save(user);
        memberCardDao.save(memberCard);
        memberDao.save(member);
    }

}
