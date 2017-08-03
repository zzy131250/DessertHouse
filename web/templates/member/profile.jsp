<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/2/11
  Time: 16:161
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Profile</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="/static/css/member/profile.css" />
</rapid:override>

<rapid:override name="content">
    <!-- Modal -->
    <div id="editProfileModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Change Profile</h4>
                </div>
                <div class="modal-body">
                    <form action="/profile" method="post">
                        <input type="hidden" name="behavior" value="edit" />
                        <div class="form-group">
                            <label for="age">Age :</label>
                            <input type="text" class="form-control" name="age" value="<s:property value="%{member.age}"></s:property>" />
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender :</label>
                            <input type="text" class="form-control" name="gender" value="<s:property value="%{member.gender}"></s:property>" />
                        </div>
                        <div class="form-group">
                            <label for="livingPlace">Living Place :</label>
                            <input type="text" class="form-control" name="livingPlace" value="<s:property value="%{member.livingPlace}"></s:property>" />
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary" style="background-color: #000; color: #fff">Submit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="page-header">
        <h3>Basic Info</h3>
    </div>
    <form id="basicInfo">
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
        <div class="form-group">
            <label for="point">Point :</label>
            <input type="text" class="form-control" name="point" value="<s:property value="%{member.point}"></s:property>" readonly />
        </div>
        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#editProfileModal">Edit</button>
        <button type="button" class="btnExchange btn btn-primary">Exchange</button>
        <button type="button" class="btnStop btn btn-danger">Stop Member</button>
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
    <script src="/static/js/member/profile.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>
