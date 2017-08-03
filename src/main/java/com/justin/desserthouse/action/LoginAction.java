package com.justin.desserthouse.action;

import com.justin.desserthouse.model.CartItem;
import com.justin.desserthouse.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 2016/2/3.
 */

@Repository
public class LoginAction extends BaseAction {

    @Autowired
    private LoginService loginService;

    private String message;

    public String login() {
        if(request.getMethod().equalsIgnoreCase("get")) return "login";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String result = loginService.checkLogin(username, password);
        if (result.equals("error")) {
            session.put("message", "Username or password is wrong");
        } else {
            session.put("username", username);
            session.put("role", result);
            if (result.equals("member")) MemberAction.shoppingCart = new ArrayList<CartItem>();
        }
        return result;
    }

    public String logout() {
        session.clear();
        MemberAction.shoppingCart = null;
        return "logout";
    }

    public String checkRegister() {
        String username = request.getParameter("username");
        message = loginService.checkRegister(username);
        return "message";
    }

    public String register() {
        if(request.getMethod().equalsIgnoreCase("get")) return "register";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String livingPlace = request.getParameter("livingPlace");
        String bankcard = request.getParameter("bankcard");
        loginService.register(username, password, age, gender, livingPlace, bankcard);
        session.put("username", username);
        session.put("role", "member");
        return "success";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
