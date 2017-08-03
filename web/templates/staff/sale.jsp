<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/3/2
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Sale</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="/static/css/staff/sale.css" />
</rapid:override>

<rapid:override name="head">
    <ul class="nav navbar-nav link">
        <li><a href="/staff/sale">Sale</a></li>
        <li><a href="/staff/member_info">Member Info</a></li>
    </ul>

    <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("username")%> <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/logout">Logout</a></li>
            </ul>
        </li>
    </ul>
</rapid:override>

<rapid:override name="content">
    <div class="page-header">
        <h1>List of all desserts</h1>
    </div>
    <s:iterator id="rowList" value="productListUi">
        <div class="row dessert_row">
            <s:iterator value="#rowList">
                <div class="col-md-3 dessert_item">
                    <img class="img-responsive center-block" src="/static/image/product/<s:property value="id"></s:property>.jpg"/>
                    <span><s:property value="name"></s:property></span><br>
                    <a href="/staff/sell?productId=<s:property value="id"></s:property>">Sell</a>
                </div>
            </s:iterator>
        </div>
    </s:iterator>
</rapid:override>

<%@ include file="/base.jsp" %>
