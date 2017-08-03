package com.justin.desserthouse.service;

import com.justin.desserthouse.model.Member;
import com.justin.desserthouse.model.Product;
import com.justin.desserthouse.model.Shop;

/**
 * Created by Justin on 2016/2/4.
 */
public interface LoginService {

    public String checkLogin(String userId, String password);

    public String checkRegister(String username);

    public void register(String username, String password, String age, String gender, String livingPlace, String bankcardId);

}
