package com.justin.desserthouse.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Justin on 2016/3/3.
 */

public class AjaxInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        final ActionContext context = actionInvocation.getInvocationContext ();
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        if (null != request && "Dessert House".equals(request.getHeader("X-Request-Source")))
            return actionInvocation.invoke();
        return "ajax_error";
    }
}
