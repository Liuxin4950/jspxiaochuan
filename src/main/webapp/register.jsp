<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>登录EmarketMall系统</title>
    <meta name="description" content="EmarketMall系统">
    <% pageContext.setAttribute("ctx", request.getContextPath());%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="${ctx}/static/css/style.css" rel="stylesheet"/>
    <link href="${ctx}/static/css/login.min.css" rel="stylesheet"/>
    <!-- 360浏览器急速模式 -->
    <meta name="renderer" content="webkit">
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="${ctx}/static/favicon.ico"/>
</head>
<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>后台管理系统</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> SpringBoot</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Mybatis</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Shiro</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Thymeleaf</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Bootstrap</li>
                </ul>
                <strong>已经注册过? <a href="${ctx}/login.jsp">直接登录&raquo;</a></strong>
            </div>
        </div>
        <div class="col-sm-5">
            <form id="registerForm" autocomplete="off" method="post">
                <h4 class="no-margins">注册：</h4>
                <p class="m-t-md">When we let go of something, it opens up a little space to grow.<br/>学会放手的同时也学会成长。
                </p>
                <input type="text" name="loginName" class="form-control uname" placeholder="登录名" maxlength="20"/>
                <input type="password" name="password" class="form-control pword" placeholder="密码" maxlength="20" value="123456"/>
                <input type="password" name="confirmPassword" class="form-control pword" placeholder="确认密码"
                       maxlength="20" value="123456"/>
                <input type="text" name="phone" class="form-control uname" placeholder="电话" maxlength="20"
                       value="13912345678"/>
                <br/>
                <div class="checkbox-custom">
                    <input type="checkbox" id="acceptTerm" name="acceptTerm"> <label
                        for="acceptTerm">我已阅读并同意</label>
                </div>
                <button class="btn btn-success btn-block" id="btnSubmit" type="button" onclick="register()"
                        data-loading="正在验证注册，请稍后...">注册
                </button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2019 All Rights Reserved. <br>
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="${ctx}/static/js/jquery.min.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
<!-- 遮罩层 -->
<script src="${ctx}/static/ajax/blockUI/jquery.blockUI.js"></script>
<script src="${ctx}/static/ajax/iCheck/icheck.min.js"></script>
<script src="${ctx}/static/ajax/layer/layer.min.js"></script>
<script src="${ctx}/static/ajax/layui/layui.js"></script>
<script src="${ctx}/static/ajax/easy-ui/common.js?v=4.5.1"></script>
<script src="${ctx}/static/ajax/easy-ui/easy-ui.js?v=4.5.1"></script>
<!-- 验证插件 -->
<script src="${ctx}/static/js/validate/jquery.validate.min.js"></script>
<script src="${ctx}/static/js/validate/messages_zh.min.js"></script>

<script type="text/javascript">
    function register() {
        let loginName = $("input[name='loginName']").val();
        let password = $("input[name='password']").val();
        let phone = $("input[name='phone']").val();

        $.ajax({
            type: "post",
            url: "/signup",
            dataType: "json",
            data: {
                "loginName": loginName,
                "password": password,
                "phone": phone
            },
            success: function (ret) {
                console.log("返回码:", ret.code);
                if (ret.code == 0) {
                    $.modal.alertSuccess("恭喜你，您的账号 " + loginName + " 注册成功！");
                    // 延迟2秒跳转，让用户看到提示
                    setTimeout(function () {
                        location.href = '/login.jsp';
                    }, 1000);
                } else {
                    $.modal.alertError(ret.msg);
                }
            },
            error: function (ret) {
                console.log("返回码:", ret.code);
                $.modal.alertError(ret.msg);
            }
        });
    }
    function validateRule() {
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#registerForm").validate({
                rules: {
                    username: {
                        required: true,
                        minlength: 2
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                    confirmPassword: {
                        required: true,
                        equalTo: "[name='password']"
                    },
                    phone: {
                        required: true,
                        isPhone: true
                    }
                },
                messages: {
                    username: {
                        required: icon + "请输入您的用户名",
                        minlength: icon + "用户名不能小于2个字符"
                    },
                    password: {
                        required: icon + "请输入您的密码",
                        minlength: icon + "密码不能小于5个字符",
                    },
                    confirmPassword: {
                        required: icon + "请再次输入您的密码",
                        equalTo: icon + "两次密码输入不一致"
                    },
                    phone: {
                        required: icon + "请输入手机号",
                        isPhone: icon + "请填写正确的11位手机号"
                    }
                }
            }
        )
    }
</script>
</body>
</html>
