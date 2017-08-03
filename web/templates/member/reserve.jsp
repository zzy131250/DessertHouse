<%--
  Created by IntelliJ IDEA.
  User: Justin Chou
  Date: 2016/3/11
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Reserve Product</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="static/css/member/reserve.css" />
</rapid:override>

<rapid:override name="content">

    <!-- Modal -->
    <div id="reserveModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Reserve Information</h4>
                </div>
                <div class="modal-body">
                    <form id="confirmReserve" action="/reserve" method="post">
                        <div class="form-group">
                            <input type="hidden" name="behavior" value="reserve" />
                            <input type="hidden" name="productId" value="<%=request.getParameter("productId")%>" readonly />
                            <label for="productName">Product Name</label>
                            <input type="text" class="form-control" name="productName" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="price">Price</label>
                            <input type="text" class="form-control" name="price" readonly />
                        </div>
                        <div class="form-group">
                            <label for="purchaseDate">Purchase Date</label>
                            <input type="text" class="form-control" name="purchaseDate" readonly />
                        </div>
                        <div class="form-group">
                            <label for="purchaseShop">Purchase Shop</label>
                            <input type="hidden" name="purchaseShopId" />
                            <input type="text" class="form-control" name="purchaseShop" readonly />
                        </div>
                        <div class="form-group">
                            <label for="number">Number</label>
                            <input type="text" class="form-control" name="number" readonly />
                        </div>
                        <div class="form-group">
                            <label for="totalPrice">Total Price</label>
                            <input type="text" class="form-control" name="totalPrice" readonly />
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" name="address" />
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary" style="background-color: #000; color: #fff">Confirm</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                        <div class="alert alert-danger" role="alert" style="display: none">Insufficient balance.</div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <s:if test="#session.username == null">
        <h4><a href="/login" style="text-decoration: underline;">Login in</a> now and enjoy the reservation!</h4>
    </s:if>
    <s:else>
        <div id="left">
            <img src="/static/image/product/<%=request.getParameter("productId")%>.jpg" width="300" height="370" /><br>
            <span id="productId">Product Id : <%=request.getParameter("productId")%></span>
        </div>
        <div id="right">
            <span id="productName"><strong><%=session.getAttribute("productName")%></strong></span><br>
            <span id="price">Price : ¥ 10 - 50</span>
            <form>
                <div class="form-group">
                    <span>Purchase Date :</span>
                    <input type="date" class="form-control" id="purchaseDate" name="purchaseDate" />
                </div>
                <div class="form-group">
                    <span>Purchase Shop :</span>
                    <select class="form-control" id="purchaseShop" name="purchaseShop"></select>
                </div>
                <div class="form-group">
                    <span>Number : <i id="minus" class="fa fa-minus-square-o fa-fw"></i>
                        <input type="text" class="form-control" name="number" value="1" />
                        <i id="plus" class="fa fa-plus-square-o fa-fw"></i></span>
                    <span style="margin-left: 8%">Inventory : <span id="inventory"></span></span>
                </div>
                <div class="form-group">
                    <button type="button" id="btnReserve" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#reserveModal">Reserve</button>
                    <button type="button" id="btnAddtoShoppingCart" class="btn btn-danger btn-lg">Add To Shopping Cart</button>
                </div>
            </form>
        </div>
    </s:else>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="/static/js/member/reserve.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>