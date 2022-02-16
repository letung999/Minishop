<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
                    <span class="actived">Đăng Nhập</span>/<span>Đăng Ký</span>
                </div>

                <div id="container-login-center">
                    <form>
                        <input class="input-icon-email" type="text" placeholder="Email"><br>
                        <input class="input-icon-password" type="password" placeholder="Password"><br>
                        <input class="material-primary-button" type="submit" value="Đăng Nhập">
                    </form>
                </div>

                <div id="social">
                    <img style="width: 30px; height: 30px;" src='<c:url value="/resources/image/facebook.png"/>'/>
                    <img style="width: 30px; height: 30px;" src='<c:url value="/resources/image/github.png"/>'/>
                    <img style="width: 30px; height: 30px;" src='<c:url value="/resources/image/instagram.png"/>'/>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
