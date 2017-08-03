<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/2/4
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Login</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="static/css/login/login.css" />
</rapid:override>

<rapid:override name="content">
    <div id="wrapper">
        <s:form id="login" action="login" method="POST">
            <i class="fa fa-user fa-fw"></i><input type="text" name="username" placeholder="Username" autofocus required />
            <i class="fa fa-lock fa-fw"></i><input type="password" name="password" placeholder="Password" required />
            <s:if test="#session.message != null">
                <div id="message">
                    <%=session.getAttribute("message")%>
                </div>
            </s:if>
            <input type="submit" value="LOGIN" />
            <div class="register_wrapper">
                <span>No Account?</span>
                <a href="/register">[Register Now]</a>
            </div>
        </s:form>
    </div>
</rapid:override>

<%@ include file="/base.jsp" %>
