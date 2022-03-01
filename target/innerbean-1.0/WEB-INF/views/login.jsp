<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <jsp:include page="header.jsp"/>
</head>
<body id="body-login">
    <div id="body-flex-login">
        <div id="container-login">
            <div id="container-login-left">
                <div id="header-top-left" class="header-login">
                    <span id="text-logo">Welcome</span><br>
                    <span id="hint-text-logo">Hãy Tạo Nên Phong Cách Của Bạn</span>
                </div>

                <div id="header-bottom-left">
                    <p><img style="width: 15px" height="15px" src='<c:url value="/resources/image/circle.png"/>'/><span>Luôn Cập Nhập Phong Cách Mới Nhất</span></p>
                    <p><img style="width: 15px" height="15px" src='<c:url value="/resources/image/circle.png"/>'/><span>Chất Lượng Tốt Nhất</span></p>
                    <p><img style="width: 15px" height="15px" src='<c:url value="/resources/image/circle.png"/>'/><span>Dịch Vụ Chu Đáo Tận Tình</span></p>
                </div>
            </div>


            <div id="container-login-right">
                <div id="header-top-right" class="header-login">
                    <span id="login" class="actived">Đăng Nhập</span>/<span id="signup">Đăng Ký</span>
                </div>

                <div id="container-login-center">
                    <div class="container-login-form">
                        <input id="email" name="email" class="input-icon-email" type="text" placeholder="Email"><br>
                        <input id="password" name="password" class="input-icon-password" type="password" placeholder="Password"><br>
                        <input id="btn-login" class="material-primary-button" type="submit" value="Đăng Nhập">
                    </div>

                    <form method="post" action="">
                        <div class="container-signup-form">
                            <input id="email" name="email" class="input-icon-email" type="text" placeholder="Email"><br>
                            <input id="password" name="password" class="input-icon-password" type="password" placeholder="Password"><br>
                            <input id="repassword" name="confirmPassword" class="input-icon-password" type="password" placeholder="Confirm Password"><br>
                            <input id="btn-signup" class="material-primary-button" type="submit" value="Đăng Ký">
                            <span>${signupResult}</span>
                        </div>

                    </form>

                </div>

                <div id="block-success-login">
                    <div class="center">
                        <input type="checkbox" id="click">
                        <label for="click" class="click-me">click me</label>
                        <div class="content-success">
                            <div class="header-success">
                                <h2>Notification!</h2>
                                <label for="click" class="fas fa-times"></label>
                            </div>
                            <label for="click" class="fas fa-exclamation-triangle"></label>
                            <p id="result"></p>
                            <label for="click" class="close-btn">Close</label>
                        </div>
                    </div>
                </div>

                <div id="social">
                    <img style="width: 30px; height: 30px;" src='<c:url value="/resources/image/facebook.png"/>'/>
                    <img style="width: 30px; height: 30px;" src='<c:url value="/resources/image/github.png"/>'/>
                    <img style="width: 30px; height: 30px;" src='<c:url value="/resources/image/instagram.png"/>'/>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
