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

                        <a class="dropdown-item" href="#">Action</a>

                        <div class="dropdown-divider"></div>

                        <a class="dropdown-item" href="#">Another action</a>

                        <div class="dropdown-divider"></div>

                        <a class="dropdown-item" href="#">Something else here</a>
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

                <li><a href='<c:url value ="/cart/"/>'><img id="image-cart"
                                                            src='<c:url value="/resources/image/shopping-cart.png" />'/></a>
                </li>
            </ul>
        </div>
    </nav>
</div>

<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-sm-2 col-md-2">
            <h3>Danh Mục</h3>
            <ul class="main-menu" style="padding: 1px">
                <li><a>Quần Jean</a></li>
                <li><a>Quần Jean</a></li>
                <li><a>Quần Jean</a></li>
                <li><a>Quần Jean</a></li>
            </ul>

        </div>

        <div class="col-sm-8 col-md-8">
            <div class="row">
                <div class="col-sm-4 col-md-4 card block-img wow animate__zoomIn" data-wow-duration="2s"
                     style="width: 18rem;">
                    <a class="product-img">
                        <img class="card-img-top"
                             src='<c:url value = "/resources/image/nameStuff/${productById.getPhoto()}"/>'
                             alt="Card image cap">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title">${productById.nameProduct}</h5>
                        <p class="card-text">${productById.price}</p>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">
                    <h4>${productById.getNameProduct()}</h4>
                    <h5>${productById.getPrice()}</h5>

                    <table class="table table-sm">
                        <thead>
                        <tr class="bg-warning">
                            <th scope="col">Màu Sản Phẩm</th>
                            <th scope="col">Size</th>
                            <th scope="col">Số Lượng</th>
                            <th scope="col">Thêm Sản Phẩm</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="productDetail" items="${productById.getListDetailProduct()}">
                            <tr>
                                <td>${productDetail.getColorProduct().getNameColor()}</td>
                                <td>${productDetail.getSize().getNameSize()}</td>
                                <td>${productDetail.getQuantity()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-sm-2 col-md-2">
            <h3>Mô Tả</h3>
            <span>
                ${productById.getDescription()}
            </span>
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
