package com.justin.desserthouse.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created by Justin on 2016/2/13.
 */
public class ManagerInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String role = (String) actionInvocation.getInvocationContext().getSession().get("role");
        if (null != role && role.equals("manager")) return actionInvocation.invoke();
        return "403";
    }

}
