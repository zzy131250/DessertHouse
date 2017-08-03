package com.justin.desserthouse.service.impl;

import com.justin.desserthouse.dao.StaffDao;
import com.justin.desserthouse.dao.TestDao;
import com.justin.desserthouse.dao.UserDao;
import com.justin.desserthouse.model.Staff;
import com.justin.desserthouse.model.Test;
import com.justin.desserthouse.model.User;
import com.justin.desserthouse.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Justin on 2016/1/27.
 */

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private StaffDao staffDao;
    @Autowired
    private UserDao userDao;

    public String addTest() {
        User user = new User();
        user.setPassword(Utils.md5("123"));
        Staff staff = new Staff();
        staff.setRole(3);
        staff.setUser(user);
        userDao.save(user);
        staffDao.save(staff);

        user = new User();
        user.setPassword(Utils.md5("123"));
        staff = new Staff();
        staff.setRole(0);
        staff.setUser(user);
        userDao.save(user);
        staffDao.save(staff);
        return "success";
    }

}
