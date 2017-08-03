<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/3/2
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Hot Product</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="/static/css/staff/hotProduct.css" />
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
    <div id="hotProduct" style="width:100%; height:500px;"></div>
</rapid:override>

<rapid:override name="loadJsFile">
    <script>
        $(document).ready(function() {
            $('#hotProduct').highcharts({
                title: {
                    text: 'Hot Product Monthly Ranking',
                    x: -20 //center
                },
                xAxis: {
                    categories: [
                        <s:iterator id="entry" value="hotProduct">
                        '<s:property value="#entry.key"></s:property>',
                        </s:iterator>
                    ]
                },
                yAxis: {
                    title: {
                        text: 'Number'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: 'Number',
                    data: [
                        <s:iterator id="entry" value="hotProduct">
                        <s:property value="#entry.value"></s:property>,
                        </s:iterator>]
                }]
            });
        });
    </script>
</rapid:override>

<%@ include file="/base.jsp" %>
