package com.justin.desserthouse.action;

import com.justin.desserthouse.model.Test;
import com.justin.desserthouse.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Justin on 2016/1/28.
 */

@Repository
public class TestAction extends BaseAction {

    @Autowired
    private TestService testService;

    @Override
    public String execute() throws Exception {
        return testService.addTest();
    }

}
