<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/3/1
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Product Plan</rapid:override>

<rapid:override name="head">
    <ul class="nav navbar-nav link">
        <li><a href="/staff/product_plan">Product Plan</a></li>
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
    <div id="addPlanModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Add Product Plan Modal</h4>
                </div>
                <div class="modal-body">
                    <form action="/staff/product_plan" method="post">
                        <div class="form-group">
                            <input type="hidden" name="behavior" value="add" />
                            <input type="hidden" name="shopId" value="<%=session.getAttribute("shop")%>" />
                            <select id="addModalProductId" class="form-control" name="productId"></select>
                        </div>
                        <div class="form-group">
                            <input type="date" class="form-control" name="date" required />
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="saleNumber" placeholder="Number" required />
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="price" placeholder="Price" required />
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
    <div id="updateProductPlanModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Update Product Plan Modal</h4>
                </div>
                <div class="modal-body">
                    <form action="/staff/product_plan" method="post">
                        <div class="form-group">
                            <input type="hidden" name="behavior" value="update" />
                            <input type="hidden" name="id" />
                            <input type="hidden" name="shopId" value="<%=session.getAttribute("shop")%>" />
                            <select id="updateModalProductId" class="form-control" name="productId"></select>
                        </div>
                        <div class="form-group">
                            <input type="date" class="form-control" name="date" required />
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="saleNumber" placeholder="Number" required />
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="price" placeholder="Price" required />
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

    <form action="/staff/product_plan" method="post" style="float: left">
        <input type="hidden" name="behavior" value="query" />
        <span>Start Date: </span>
        <input type="date" name="start" />&nbsp;&nbsp;&nbsp;
        <span>End Date: </span>
        <input type="date" name="end" />&nbsp;&nbsp;&nbsp;
        <button type="submit" class="btn btn-primary" style="background-color: #000; color: #fff">Find</button>
    </form>
    <button type="button" id="addPlan" class="btn btn-primary" data-toggle="modal" data-target="#addPlanModal" style="background-color: #000; color: #fff; float: right;">Add Plan</button>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Shop</th>
            <th>Product</th>
            <th>Date</th>
            <th>Number</th>
            <th>Price(¥)</th>
            <th>State</th>
            <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="productPlanList">
            <tr>
                <td class="productPlanId"><s:property value="id"></s:property></td>
                <td><s:property value="shop.name"></s:property></td>
                <td><s:property value="product.name"></s:property></td>
                <td><s:date name="date" format="yyyy-MM-dd"></s:date></td>
                <td><s:property value="saleNumber"></s:property></td>
                <td><s:property value="price"></s:property></td>
                <td><s:if test="state == 0">To Be Checked</s:if><s:elseif test="state == 1">Passed</s:elseif><s:elseif test="state == 2">Not Passed</s:elseif></td><!--td不能有空格-->
                <td><button class="updatePlan btn btn-info" type="button" data-toggle="modal" data-target="#updateProductPlanModal" <s:if test="state == 1">disabled="disabled"</s:if>>
                    Update
                </button>  <button class="deletePlan btn btn-danger" <s:if test="state == 1">disabled="disabled"</s:if>>Delete</button></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</rapid:override>

<rapid:override name="loadJsFile">
    <script src="/static/js/staff/productPlan.js"></script>
</rapid:override>

<%@ include file="/base.jsp" %>
