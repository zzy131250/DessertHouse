<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/2/11
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Staff</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="/static/css/admin/staff.css" />
</rapid:override>

<rapid:override name="head">
    <ul class="nav navbar-nav link">
        <li><a href="/admin/shop">Shops</a></li>
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
    <div id="addStaffModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Add Staff Modal</h4>
                </div>
                <div class="modal-body">
                    <form action="/admin/staff" method="post">
                        <div class="form-group">
                            <input type="hidden" name="behavior" value="add" />
                            <select class="form-control" name="role">
                                <option value="1">Head Staff</option>
                                <option value="2">Branch Staff</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <select id="addModalShopId" class="form-control" name="shopId"></select>
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
    <div id="updateStaffModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Update Staff Modal</h4>
                </div>
                <div class="modal-body">
                    <form action="/admin/staff" method="post">
                        <div class="form-group">
                            <input type="hidden" name="behavior" value="update" />
                            <input type="hidden" name="id" />
                            <select class="form-control" name="role">
                                <option value="0">Manager</option>
                                <option value="1">Head Staff</option>
                                <option value="2">Branch Staff</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <select id="updateModalShopId" class="form-control" name="shopId"></select>
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
            <button id="addStaff" type="button" class="btn btn-info" data-toggle="modal" data-target="#addStaffModal">
                Add Staff
            </button>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <th>Role</th>
                <th>Shop</th>
                <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="staffList">
                <tr>
                    <td class="staffId"><s:property value="id"></s:property></td>
                    <td><s:if test="role == 0">Manager</s:if><s:elseif test="role == 1">Head Staff</s:elseif><s:elseif test="role == 2">Branch Staff</s:elseif><s:elseif test="role == 3">Admin</s:elseif></td><!--td不能有空格-->
                    <td><s:property value="shop.name"></s:property></td>
                    <td><button class="updateStaff btn btn-info" type="button" data-toggle="modal" data-target="#updateStaffModal">
                        Update
                    </button>  <button class="deleteStaff btn btn-danger">Delete</button></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="/static/js/admin/staff.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>
