<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/1/27
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Index</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="static/css/member/index.css" />
</rapid:override>

<s:if test="#session.role == \"manager\"">
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
</s:if>
<s:elseif test="#session.role == \"headStaff\"">
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
</s:elseif>
<s:elseif test="#session.role == \"branchStaff\"">
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
</s:elseif>
<s:elseif test="#session.role == \"admin\"">
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
</s:elseif>

<rapid:override name="content">
    <div class="jumbotron">
        <div id="back_wrapper">
            <span>MEET DELICACY</span>
            <s:if test="#session.role == \"member\"">
                <a href="/product">Shop Now</a>
            </s:if>
            <s:elseif test="#session.role == null">
                <a href="/login">Shop Now</a>
            </s:elseif>
            <s:else>
                <a href="#">Shop Now</a>
            </s:else>
        </div>
    </div>
    <div id="grey_line">
        <p>
            <span>DELICIOUS DESSERT WORLDWIDE</span>
        </p>
    </div>
    <div id="center">
        <div id="center_title">
            <p>
                <span>ALL OVER THE WORLD</span>
            </p>
        </div>
        <div id="line"></div>
        <div id="center_content">
            <p>
                <span>Must Have a Try</span>
            </p>
        </div>
    </div>
    <div id="image_list">
        <div class="image_left">
            <div class="in_image">
                <p>
                    <span>Selected Raw Material</span>
                </p>
            </div>
        </div>
        <div class="image_center">
            <div class="in_image">
                <p>
                    <span>Skilled Craft</span>
                </p>
            </div>
        </div>
        <div class="image_right">
            <div class="in_image">
                <p>
                    <span>Ultimate Enjoyment</span>
                </p>
            </div>
        </div>
    </div>
</rapid:override>

<%@ include file="base.jsp" %>