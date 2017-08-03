<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/2/11
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Reservation</rapid:override>

<rapid:override name="content">
    <!-- Modal -->
    <div id="cancelReservationModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Confirm Cancel Reservation</h4>
                </div>
                <div class="modal-body">
                    <form action="/reservation" method="post">
                        <div class="form-group">
                            <input type="hidden" name="behavior" value="cancel" />
                            <input type="hidden" name="reservationId" />
                        </div>
                        <div class="form-group">
                            <h4>You are going to cancel your reservation, you have to pay 10% of the total price for it, are you sure ?</h4>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary" style="background-color: #000; color: #fff">I'm Sure</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <s:if test="#session.username == null">
        <h4><a href="/login" style="text-decoration: underline;">Login in</a> now and enjoy the reservation!</h4>
    </s:if>
    <s:else>
        <div class="page-header">
            <h1>My Reservation</h1>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Reserve Date</th>
                <th>Purchase Date</th>
                <th>Shop</th>
                <th>Product</th>
                <th>Number</th>
                <th>Price</th>
                <th>TotalPrice(After Discount)</th>
                <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="reservationList">
                <tr>
                    <input type="hidden" name="id" value="<s:property value="id"></s:property>" />
                    <td><s:date name="reserveDate" format="yyyy-MM-dd"></s:date></td>
                    <td><s:date name="purchaseDate" format="yyyy-MM-dd"></s:date></td>
                    <td><s:property value="shop.name"></s:property></td>
                    <td><s:property value="product.name"></s:property></td>
                    <td><s:property value="number"></s:property></td>
                    <td><s:property value="price"></s:property></td>
                    <td><s:property value="totalPriceAfterDiscount"></s:property></td>
                    <td><button class="cancelReservation btn btn-danger" data-toggle="modal" data-target="#cancelReservationModal">Cancel</button></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </s:else>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="/static/js/member/reservation.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>
