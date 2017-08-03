<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/2/5
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Register</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="static/css/login/register.css" />
</rapid:override>

<rapid:override name="content">
    <div id="wrapper">
        <div class="page-header">
            <h1>Register Now <small>  Be a Foodie</small></h1>
        </div>
        <s:form id="register" action="register" method="POST">
            <input type="text" class="form-control" name="username" placeholder="Username" style="display: inline-block;" required />
            <img id="check_login_img" src="" height="25" width="25" style="display: none" />
            <input type="password" class="form-control" id="password" name="password" placeholder="Password" required />
            <input type="password" class="form-control" name="confirm" placeholder="Confirm Password" required />
            <input type="text" class="form-control" name="age" placeholder="Age" required />
            <select name="gender" class="form-control" >
                <option value="male">male</option>
                <option value="female">female</option>
            </select>
            <input type="text" class="form-control" name="livingPlace" placeholder="LivingPlace" required />
            <input type="text" class="form-control" name="bankcard" placeholder="Bankcard" required />
            <input type="submit" class="btn btn-default btn-lg" value="Register" />
            <div class="login_wrapper">
                <span>Have Account?</span>
                <a href="/login">[Login Now]</a>
            </div>
        </s:form>
    </div>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="static/js/login/register.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>