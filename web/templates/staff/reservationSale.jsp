<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/3/2
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Reservation / Sale</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="/static/css/staff/reservationSale.css" />
</rapid:override>

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

<rapid:override name="content">
    <div id="shopSale" style="width:100%; height:500px;"></div>
    <div id="line"></div>
    <div id="shopReservation" style="width:100%; height:500px;"></div>
</rapid:override>

<rapid:override name="loadJsFile">
    <script>
        $(document).ready(function() {
            $('#shopSale').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Monthly Shop Sale',
                },
                xAxis: {
                    categories: [
                        <s:iterator value="productList">
                        '<s:property value="name"></s:property>',
                        </s:iterator>
                    ],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Number'
                    }
                },
                series: [
                    <s:iterator id="shop" value="shopSale">
                    {name: '<s:property value="#shop.key"></s:property>',
                        data: [
                            <s:iterator id="product" value="productList">
                                <s:iterator value="#shop.value">
                                    <s:if test="key == #product.name">
                                        <s:property value="value"></s:property>,
                                    </s:if>
                                </s:iterator>
                            </s:iterator>
                        ]
                    },
                    </s:iterator>
                ]
            });

            $('#shopReservation').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Monthly Shop Reservation',
                },
                xAxis: {
                    categories: [
                        <s:iterator value="productList">
                        '<s:property value="name"></s:property>',
                        </s:iterator>
                    ],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Number'
                    }
                },
                series: [
                    <s:iterator id="shop" value="shopReservation">
                        {name: '<s:property value="#shop.key"></s:property>',
                            data: [
                                <s:iterator id="product" value="productList">
                                    <s:iterator value="#shop.value">
                                        <s:if test="key == #product.name">
                                            <s:property value="value"></s:property>,
                                        </s:if>
                                    </s:iterator>
                                </s:iterator>
                            ]
                        },
                    </s:iterator>
                ]
            });
        });
    </script>
</rapid:override>

<%@ include file="/base.jsp" %>
