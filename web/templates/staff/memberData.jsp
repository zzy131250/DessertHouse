<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/3/2
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Member Data</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="/static/css/staff/memberData.css" />
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
    <div id="ageDistribute" style="width:100%; height:500px;"></div>
    <div id="line"></div>
    <div id="genderDistribute" style="width:100%; height:500px;"></div>
    <div id="line"></div>
    <div id="cardStateDistribute" style="width:100%; height:500px;"></div>
    <div id="line"></div>
    <div id="expenseDistribute" style="width:100%; height:500px;"></div>
</rapid:override>

<rapid:override name="loadJsFile">
    <script>
        $(document).ready(function() {

            $('#ageDistribute').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: 'Members\' Age Distribution'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    name: 'Percentage',
                    colorByPoint: true,
                    data: [<s:iterator value="memberAgeDistribute">
                        {name: "<s:property value="key"></s:property>",
                            y: <s:property value="value"></s:property>},
                        </s:iterator>]
                }]
            });

            $('#genderDistribute').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Members\' Gender Distribution'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    title: {
                        text: 'Percentage'
                    }

                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.1f}%'
                        }
                    }
                },

                tooltip: {
                    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
                },

                series: [{
                    name: 'Gender',
                    colorByPoint: true,
                    data: [<s:iterator value="memberGenderDistribute">
                        {name: "<s:property value="key"></s:property>",
                            y: <s:property value="value"></s:property>*100,
                            drilldown: "<s:property value="key"></s:property>"
                        },
                        </s:iterator>]
                }]
            });

            $('#cardStateDistribute').highcharts({
                chart: {
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45
                    }
                },
                title: {
                    text: 'Member Card State Distribution'
                },
                plotOptions: {
                    pie: {
                        innerSize: 100,
                        depth: 45
                    }
                },
                series: [{
                    name: 'Number',
                    data: [
                        <s:iterator value="memberCardStateDistribute">
                        ["<s:property value="key"></s:property>",
                          <s:property value="value"></s:property>],
                        </s:iterator>
                    ]
                }]
            });

            $('#expenseDistribute').highcharts({
                title: {
                    text: 'Member Monthly Expense Ranking',
                    x: -20 //center
                },
                xAxis: {
                    categories: [
                        <s:iterator id="entry" value="memberExpenseDistribute">
                            '<s:property value="#entry.key"></s:property>',
                        </s:iterator>
                    ]
                },
                yAxis: {
                    title: {
                        text: 'Expense (RMB)'
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
                    name: 'Expense',
                    data: [
                        <s:iterator id="entry" value="memberExpenseDistribute">
                            <s:property value="#entry.value"></s:property>,
                        </s:iterator>]
                }]
            });
        });
    </script>
</rapid:override>

<%@ include file="/base.jsp" %>
