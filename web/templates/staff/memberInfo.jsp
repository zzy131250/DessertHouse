<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/3/2
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Member Info</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="/static/css/staff/memberInfo.css" />
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
        <h1>Member Info</h1>
    </div>
    <form id="searchForm">
        <input type="text" class="form-control" name="memberCard" placeholder="Member Card" />&nbsp;&nbsp;
        <button type="button" class="searchMember btn btn-primary" style="background-color: #000; color: #fff">Search</button>
    </form>
    <div class="page-header">
        <h3>Basic Info</h3>
    </div>
    <form id="basicInfo">
        <s:if test="%{member}">
            <div class="form-group">
                <label for="username">Username :</label>
                <input type="text" class="form-control" name="username" value="<s:property value="%{member.username}"></s:property>" readonly />
            </div>
            <div class="form-group">
                <label for="age">Age :</label>
                <input type="text" class="form-control" name="age" value="<s:property value="%{member.age}"></s:property>" readonly />
            </div>
            <div class="form-group">
                <label for="gender">Gender :</label>
                <input type="text" class="form-control" name="gender" value="<s:property value="%{member.gender}"></s:property>" readonly />
            </div>
            <div class="form-group">
                <label for="livingPlace">Living Place :</label>
                <input type="text" class="form-control" name="livingPlace" value="<s:property value="%{member.livingPlace}"></s:property>" readonly />
            </div>
        </s:if>
    </form>
    <div class="page-header">
        <h3>Reservation Info</h3>
    </div>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Product</th>
            <th>Number</th>
            <th>Price</th>
            <th>Total Price</th>
            <th>Total Price (after discount)</th>
            <th>Reserve Date</th>
            <th>Purchase Date</th>
            <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="reservationList">
            <tr>
                <td class="reservationId"><s:property value="id"></s:property></td>
                <td><s:property value="product.name"></s:property></td>
                <td><s:property value="number"></s:property></td>
                <td><s:property value="price"></s:property></td>
                <td><s:property value="totalPrice"></s:property></td>
                <td><s:property value="totalPriceAfterDiscount"></s:property></td>
                <td><s:date name="reserveDate" format="yyyy-MM-dd"></s:date></td>
                <td><s:date name="purchaseDate" format="yyyy-MM-dd"></s:date></td>
                <td><button class="btnSell btn btn-danger">Sell</button></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <div class="page-header">
        <h3>Expense Info</h3>
    </div>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Product</th>
            <th>Number</th>
            <th>Price</th>
            <th>Total Price</th>
            <th>Total Price (after discount)</th>
            <th>Time</th>
            <th>Point</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="expenseRecordList">
            <tr>
                <td><s:property value="id"></s:property></td>
                <td><s:property value="product.name"></s:property></td>
                <td><s:property value="number"></s:property></td>
                <td><s:property value="price"></s:property></td>
                <td><s:property value="totalPrice"></s:property></td>
                <td><s:property value="totalPriceAfterDiscount"></s:property></td>
                <td><s:date name="time" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
                <td><s:property value="point"></s:property></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <div class="page-header">
        <h3>Payment Info</h3>
    </div>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Time</th>
            <th>Money</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="paymentRecordList">
            <tr>
                <td><s:property value="id"></s:property></td>
                <td><s:date name="time" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
                <td><s:property value="money"></s:property></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="/static/js/staff/memberInfo.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>
