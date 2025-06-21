<%@ page import="org.example.emarketmall.entity.ProductInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EmarketMall系统">
    <title>商品管理-详情</title>
    <script type="text/javascript" src="../static/js/common/common-css.js"></script>

    <% pageContext.setAttribute("ctx", request.getContextPath());%>
    <%-- 建议使用EL方式或者把变量保存到pageContext中的方式读取保存在request和session中的变量 --%>
    <% pageContext.setAttribute("product", request.getAttribute("product")); %>
    <!-- 360浏览器急速模式 -->
    <meta name="renderer" content="webkit">
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body class="white-bg">
<div class="wrapper wrapper-content animated fadeInRight ibox-content">
    <form class="form-horizontal m" id="form-product-edit">
        <input name="id" type="hidden" value="${product.id}">
        <div class="form-group">
            <label class="col-sm-3 control-label is-required">商品编码：</label>
            <div class="col-sm-8">
                <input name="productCode" class="form-control" value="${product.productCode}" type="text" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label is-required">商品名称：</label>
            <div class="col-sm-8">
                <input name="productName" class="form-control" value="${product.productName}" type="text" readonly>
            </div>
        </div>
        <!-- 密码经过MD5加密，需要重新设置，无法在个人信息中修改 -->
        <div class="form-group">
            <label class="col-sm-3 control-label">商品价格：</label>
            <div class="col-sm-8">
                <input name="price" class="form-control" value="${product.price}" type="text" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">生产日期：</label>
            <div class="col-sm-8">
                <input name="productionDate" class="form-control" value="${product.productionDate}" type="text" readonly>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="../static/js/common/common-js.js"></script>
</body>
</html>
