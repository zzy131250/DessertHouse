<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/2/11
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Shop</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="/static/css/admin/shop.css" />
</rapid:override>

<rapid:override name="head">
    <ul class="nav navbar-nav link">
        <li><a href="/admin/shop">Shop</a></li>
        <li><a href="/admin/staff">Staff</a></li>
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
    <div id="addShopModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Add Shop Modal</h4>
                </div>
                <div class="modal-body">
                    <form action="/admin/shop" method="post">
                        <div class="form-group">
                            <input type="hidden" name="behavior" value="add" />
                            <input type="text" class="form-control" name="name" placeholder="Name" required/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="address" placeholder="Address" required />
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary" style="background-color: #000; color: #fff">Add</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="updateShopModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Update Shop Modal</h4>
                </div>
                <div class="modal-body">
                    <form action="/admin/shop" method="post">
                        <div class="form-group">
                            <input type="hidden" name="behavior" value="update" />
                            <input type="hidden" name="id" />
                            <input type="text" class="form-control" name="name" placeholder="Name" required/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="address" placeholder="Address" required />
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary" style="background-color: #000; color: #fff">Update</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="panel panel-primary">
        <div class="panel-heading">All Shops
            <!-- Button trigger modal -->
            <button id="addShop" type="button" class="btn btn-info" data-toggle="modal" data-target="#addShopModal">
                Add Shop
            </button>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Address</th>
                <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="shopList">
                <tr>
                    <td class="shopId"><s:property value="id"></s:property></td>
                    <td><s:property value="name"></s:property></td>
                    <td><s:property value="address"></s:property></td>
                    <td><button class="updateShop btn btn-info" type="button" data-toggle="modal" data-target="#updateShopModal">
                        Update
                    </button>  <button class="deleteShop btn btn-danger">Delete</button></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="/static/js/admin/shop.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>
