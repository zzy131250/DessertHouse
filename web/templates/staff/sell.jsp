<%--
  Created by IntelliJ IDEA.
  User: Justin Chou
  Date: 2016/3/16
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Sale</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="/static/css/staff/sell.css" />
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
    <!-- Modal -->
    <div id="sellModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Choose Member</h4>
                </div>
                <div class="modal-body">
                    <form id="sellMemberForm" action="/staff/sell" method="post">
                        <div class="form-group">
                            <input type="hidden" name="behavior" value="member sell" />
                            <input type="hidden" name="productId" value="<%=request.getParameter("productId")%>" />
                            <input type="hidden" name="price" value="<%=session.getAttribute("price")%>" />
                            <input type="hidden" name="number" value="" />
                            <label for="memberCard">Member Card :</label>
                            <input type="text" class="form-control" name="memberCard" required/>
                        </div>
                        <div id="messageArea"></div>
                        <div class="form-group">
                            <input type="button" class="btnSearch btn btn-primary" value="Search" style="background-color: #000; color: #fff" />
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <input type="submit" class="btnConfirm btn btn-primary" value="Confirm" style="background-color: #000; color: #fff; float: right; display: none" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="sellWithCashModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Confirm Sale</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="totalPrice">Total Price</label>
                            <input type="text" class="form-control" name="totalPrice" readonly />
                        </div>
                        <div class="form-group">
                            <input type="button" id="btnCashSell" class="btn btn-primary" value="Confirm" style="background-color: #000; color: #fff" />
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div id="left">
        <img src="/static/image/product/<%=request.getParameter("productId")%>.jpg" width="300" height="370" /><br>
        <span id="productId">Product Id : <%=request.getParameter("productId")%></span>
    </div>
    <div id="right">
        <span id="productName"><strong><%=session.getAttribute("productName")%></strong></span><br>
        <span id="price">Price : ¥ <%=session.getAttribute("price")%></span>
        <form>
            <div class="form-group">
                    <span>Number : <i id="minus" class="fa fa-minus-square-o fa-fw"></i>
                        <input type="text" class="form-control" name="number" value="1" />
                        <i id="plus" class="fa fa-plus-square-o fa-fw"></i></span>
                <span style="margin-left: 8%">Inventory : <span id="inventory"><%=session.getAttribute("inventory")%></span></span>
            </div>
            <div class="form-group">
                <button type="button" id="btnSell" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#sellModal">Sell</button>
                <button type="button" id="btnTriggerCashSell" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#sellWithCashModal">SellWithCash</button>
            </div>
        </form>
    </div>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="/static/js/staff/sell.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>
