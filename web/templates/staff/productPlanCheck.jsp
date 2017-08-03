<%--
  Created by IntelliJ IDEA.
  User: Justin Chou
  Date: 2016/3/9
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Product Plan Check</rapid:override>

<rapid:override name="head">
    <ul class="nav navbar-nav link">
        <li><a href="/staff/product_plan_check">Product Plan Check</a></li>
        <li><a href="/staff/member_data">Member Data</a></li>
        <li><a href="/staff/reservation_sale">Reservation / Sale</a></li>
        <li><a href="/staff/hot_product">Hot Product</a></li>
        <li><a href="/staff/product_list">Product List</a></li>
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
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Shop</th>
            <th>Product</th>
            <th>Date</th>
            <th>Number</th>
            <th>Price(¥)</th>
            <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="productPlanCheckList">
            <tr>
                <td><s:property value="id"></s:property></td>
                <td><s:property value="shop.name"></s:property></td>
                <td><s:property value="product.name"></s:property></td>
                <td><s:date name="date" format="yyyy-MM-dd"></s:date></td>
                <td><s:property value="saleNumber"></s:property></td>
                <td><s:property value="price"></s:property></td>
                <td><button class="approve btn btn-info">Approve</button>  <button class="reject btn btn-danger">Reject</button></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="/static/js/staff/productPlanCheck.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>

