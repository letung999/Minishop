<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <meta charset="UTF-8">
    <title>FashionShop</title>
</head>
<body>
<div class="container-fluid">
    <nav class="navbar navbar-expand-lg none_nav">
        <a class="navbar-brand" href="fashionShop/">
            <h2>FashionShop</h2>
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto navbar-center">
                <li class="nav-item active"><a class="nav-link" href='<c:url value ="/"/>'>TRANG CHỦ <span
                        class="sr-only">(current)</span></a></li>

                <li class="nav-item dropdown ">
                    <a
                            class="nav-link dropdown-toggle" href="" id="navbarDropdown"
                            role="button" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                        SẢN PHẨM
                    </a>

                    <div class="dropdown-menu open" aria-labelledby="navbarDropdown">

                        <c:forEach var="category" items="${listCategory}">
                            <a class="dropdown-item" href='<c:url value="/category/${category.idCategory}/${category.nameCategory}"/>'>
                                    ${category.getNameCategory()}
                            </a>
                            <div class="dropdown-divider"></div>
                        </c:forEach>

                    </div>
                </li>

                <li class="nav-item"><a class="nav-link" href="#">DỊCH VỤ</a></li>

                <li class="nav-item"><a class="nav-link" href="#">LIÊN HỆ</a></li>


            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <c:choose>
                        <c:when test="${firstWord != null }">
                            <a class="nav-link circle-login" href="login/">${firstWord}</a>

                        </c:when>

                        <c:otherwise>
                            <a class="nav-link" href='<c:url value ="/login/"/>'>ĐĂNG NHẬP</a>
                        </c:otherwise>

                    </c:choose>
                </li>

                <li id="cart">
                    <a href='<c:url value ="/cart/"/>'><img id="image-cart"
                                                            src='<c:url value="/resources/image/shopping-cart.png" />'/>
                        <c:if test="${quantity > 0}">
                            <div class="circle-quantity">
                                <span>${quantity}</span>
                            </div>
                        </c:if>

                        <c:if test="${quantity <= 0 || quantity == null}">
                            <div>
                                <span>${quantity}</span>
                            </div>
                        </c:if>
                    </a>
                </li>

            </ul>
        </div>
    </nav>
</div>

<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-sm-2 col-md-2">
            <h3 style="font-family: Roboto">Danh Mục</h3>
            <ul class="main-menu" style="padding: 1px">
                <c:forEach var="category" items="${listCategory}">
                    <li><a href="#">${category.getNameCategory()}</a></li>
                </c:forEach>
            </ul>

        </div>

        <div id="title-product" class="col-sm-10 col-md-10">
            <div class="container">
                <span>${nameCategory}</span>
                <div class="row product">
                    <c:forEach var="productByCategory" items="${listProductByCategory}">
                        <div class="col-12 col-sm-6 col-md-3 card block-img wow animate__zoomIn" data-wow-duration="2s"
                             style="width: 18rem;">
                            <a class="product-img" href='<c:url value="/detail/${productByCategory.getIdProduct()}"/>'>
                                <img class="card-img-top" src='<c:url value = "/resources/image/nameStuff/${productByCategory.photo}"/>'
                                     alt="Card image cap">
                            </a>
                            <div class="card-body">
                                <h5 class="card-title">${productByCategory.nameProduct}</h5>
                                <p class="card-text">${productByCategory.price}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
</div>

<div id="footer" class="container-fluid">
    <div class="row">
        <div class="col-sm-4 col-md-4 wow animate__headShake" data-wow-duration="2s">
            <p><span class="title-footer">THÔNG TIN SHOP</span></p>
            <div class="description-footer">
					<span>
						FashionShop là một của hàng thời trang hiện đại, luôn cam kết những trang sách chất lượng với giá
						cả hợp lý, đặc biệt là cửa hàng luôn có những cuốn sách gắn liền với dòng thời gian của tuổi thơ
					</span>
            </div>


        </div>
        <div class="col-sm-4 col-md-4 wow animate__headShake" data-wow-duration="2s">
            <p><span class="title-footer">LIÊN HỆ</span></p>
            <div class="description-footer">
                <span>80/9A Lã Xuân Oai Phường Tăng Nhơn Phú A Tp Thủ Đức</span>
                <span>bookshop@gmail.com</span><br>
                <span>0338257409</span>
            </div>

        </div>
        <div class="col-sm-4 col-md-4 wow animate__headShake" data-wow-duration="2s">
            <p><span class="title-footer">GÓP Ý</span>
            <p>
            <div class="description-footer">
                <form action="" method="post">
                    <input name="nameProduct" style="margin-bottom: 5px; width: 100%" type="text" placeholder="Email"/>
                    <textarea name="price" rows="4" cols="50" placeholder="Nội Dung"></textarea>
                    <button style="margin-top: 5px;">Đồng Ý</button>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>

</html>

