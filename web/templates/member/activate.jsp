<%--
  Created by IntelliJ IDEA.
  User: Justin Chou
  Date: 2016/3/14
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Activation</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="static/css/member/activate.css" />
</rapid:override>

<rapid:override name="content">
    <s:if test="#session.state == \"normal\"">
        <h4>You don't have to activate your membership, enjoy it !</h4>
    </s:if>
    <s:else>
        <div class="page-header">
            <h1>Activate Now <small>Enjoy Superior Membership</small></h1>
        </div>
        <span>Bankcard Id : <span><%=session.getAttribute("bankcardId")%></span></span><br>
        <span>Member Card Id : <span><%=session.getAttribute("memberCardId")%></span></span><br>
        <span>Transfer Amount : </span>
        <ul>
            <li><button type="button" class="btn btn-primary" value="200">¥ 200</button></li>
            <li><button type="button" class="btn btn-primary" value="500">¥ 500</button></li>
            <li><button type="button" class="btn btn-primary" value="800">¥ 800</button></li>
        </ul>
        <button type="button" id="activate" class="btn btn-primary">Activate</button>
    </s:else>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="/static/js/member/activate.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>
