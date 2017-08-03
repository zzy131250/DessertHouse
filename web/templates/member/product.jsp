<%--
  Created by IntelliJ IDEA.
  User: Justin
  Date: 2016/2/11
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<rapid:override name="title">Product</rapid:override>

<rapid:override name="loadCssFile">
    <link rel="stylesheet" href="static/css/member/product.css" />
</rapid:override>

<rapid:override name="content">
    <div class="page-header">
        <h1>List of all desserts</h1>
    </div>
    <s:iterator id="rowList" value="productList">
        <div class="row dessert_row">
            <s:iterator value="#rowList">
                <div class="col-md-3 dessert_item">
                    <img class="img-responsive center-block" src="/static/image/product/<s:property value="id"></s:property>.jpg"/>
                    <span><s:property value="name"></s:property></span><br>
                    <a href="/reserve?productId=<s:property value="id"></s:property>">Reserve Now !</a>
                </div>
            </s:iterator>
        </div>
    </s:iterator>
</rapid:override>

<%@ include file="/base.jsp" %>
