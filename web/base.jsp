<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/2/3
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    <%--用于格式化日期，数字--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    <%--工具函数--%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1, width=device-width">
        <meta name="role" content="<%=session.getAttribute("role")%>">
        <link rel="shortcut icon" type="image/icon" href="favicon.ico" />
        <title>Dessert House - <rapid:block name="title"></rapid:block></title>

        <link rel="stylesheet" href="/static/vendor/bootstrap/bootstrap.min.css" />
        <link rel="stylesheet" href="/static/vendor/font-awesome/css/font-awesome.min.css" />
        <link rel="stylesheet" href="/static/css/base.css" />
        <rapid:block name="loadCssFile"></rapid:block>
    </head>
    <body>
        <header id="header">
            <nav class="navbar navbar-inverse navbar-static-top">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <a class="navbar-brand" href="/"><strong>Dessert House</strong></a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse">
                        <rapid:block name="head">
                            <ul class="nav navbar-nav link">
                                <li><a href="/product">Product</a></li>
                                <li><a href="/reservation">Reservation</a></li>
                                <li><a href="/contact">Contact</a></li>
                            </ul>

                            <s:if test="#session.username != null">
                                <ul class="nav navbar-nav navbar-right">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span name="username"><%=session.getAttribute("username")%></span> <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="/profile">Profile</a></li>
                                            <li role="separator" class="divider"></li>
                                            <li><a href="#" data-toggle="modal" data-target="#shoppingCart">Shopping Cart</a></li>
                                            <li role="separator" class="divider"></li>
                                            <li><a href="/logout">Logout</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </s:if>
                            <s:else>
                                <ul class="nav navbar-nav navbar-right link">
                                    <li><a href="" data-toggle="modal" data-target="#loginModal">Log in</a></li>
                                    <li><a class="logup" href="/register">Log up</a></li>
                                </ul>
                            </s:else>
                        </rapid:block>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </header>
        <div id="container">

            <!-- Modal -->
            <div id="loginModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Login Modal</h4>
                        </div>
                        <div class="modal-body">
                            <form id="formLogin" action="/login" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="username" placeholder="Username" required/>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" name="password" placeholder="Password" required />
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary" style="background-color: #000; color: #fff">Login</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div id="shoppingCart" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Shopping Cart</h4>
                        </div>
                        <div class="modal-body">
                            <table class="cartTable table table-striped">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>Name</th>
                                    <th>Date</th>
                                    <th>Shop</th>
                                    <th>Number</th>
                                    <th>Price</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="shoppingCart">
                                        <tr>
                                            <input type="hidden" class="cartProductId" name="cartProductId" value="<s:property value="product.id" />" />
                                            <td><input type="checkbox" class="checkbox" /></td>
                                            <td><s:property value="product.name"></s:property></td>
                                            <td class="cartTdDate"><input type="date" class="form-control" name="cartPurchaseDate" value="<s:date name="date" format="yyyy-MM-dd"></s:date>" required/></td>
                                            <td class="cartTdShop">
                                                <select class="form-control" name="cartPurchaseShop" required>
                                                    <option value="<s:property value="shop.id"></s:property>"><s:property value="shop.name"></s:property></option>
                                                </select>
                                            </td>
                                            <td class="cartTdNumber">
                                                <i class="cartMinus fa fa-minus-square-o fa-fw"></i>
                                                <input type="text" class="form-control" name="number" value="<s:property value="number"></s:property>" readonly />
                                                <i class="cartPlus fa fa-plus-square-o fa-fw"></i>
                                            </td>
                                            <td class="cartTdPrice"><s:property value="price"></s:property></td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                            <button type="button" class="btnCartSubmit btn btn-primary" style="background-color: #000; color: #fff">Submit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>

            <rapid:block name="content"></rapid:block>
            <div id="push"></div>
        </div>
        <div id="footer">
            <p>&#169; 2016 Dessert House - Designed by Justin</p>
        </div>
    </body>
</html>

<script src="/static/vendor/bootstrap/jquery-1.11.3.min.js"></script>
<script src="/static/vendor/bootstrap/bootstrap.min.js"></script>
<script src="/static/vendor/highcharts/highcharts.js"></script>
<script src="/static/vendor/highcharts/highcharts-3d.js"></script>
<script src="/static/js/base.js"></script>
<rapid:block name="loadJsFile"></rapid:block>
