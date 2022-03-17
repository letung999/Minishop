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
        <div class="col-md-6 col-sm-12">
            <h2>Danh Sách Sản Phẩm Trong Giỏ Hàng</h2>
            <table class="table table-sm">
                <thead>
                <tr class="bg-warning">
                    <th scope="col">Tên Sản Phẩm</th>
                    <th scope="col">Màu Sản Phẩm</th>
                    <th scope="col">Size</th>
                    <th scope="col">Số Lượng</th>
                    <th scope="col">Giá Tiền</th>
                    <th scope="col">Xóa Sản Phẩm</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cart" items="${carts}">
                    <tr>
                        <td class="product-cart" data-idProduct = "${cart.getIdProduct()}">${cart.nameProduct}</td>
                        <td class="color" data-idColor="${cart.idColor}">${cart.nameColor}</td>
                        <td class="size" data-idSize="${cart.idSize}">${cart.nameSize}</td>
                        <td class="quantity"><input class="quantity-cart" type="number" min="1" value="${cart.quantity}" style="width: 50px"></td>
                        <td class="price" data-price=${cart.price}>${cart.price}</td>
                        <td>
                            <button data-idDetailProduct="${productDetail.getIdDetailProduct()}"
                                    class="btn btn-danger btn-delete">
                                xóa
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <h3>Tổng Tiền: <span id="total" style="color: red" ;></span></h3>

        </div>

        <div class="col-md-6 col-sm-12">
            <h3>Thông Tin Nhận Hàng</h3>
            <div class="form-group">
                <form action="" method="post">
                    <label for="namePerson">Tên Người Mua</label>
                    <input class="form-control" id="namePerson" name="nameCustomer"><br>

                    <label for="phone">Điện Thoại Liên Lạc</label>
                    <input class="form-control" id="phone" name="phoneNumber"><br>

                    <div class="radio">
                        <label><input type="radio" name="deliveryBy" checked value="Giao Hàng Tận Nơi">Giao Hàng
                            Tận Nơi</label>
                    </div>

                    <div class="radio">
                        <label><input type="radio" name="deliveryBy" value="Nhận Trực Tiếp Tại Của Hàng">Nhận Trực
                            Tiếp Tại Của Hàng</label>
                    </div>


                    <label for="address">Địa Chỉ Nhận Hàng</label>
                    <input class="form-control" id="address" name="address"><br>

                    <label for="note">Ghi Chú</label>
                    <textarea class="form-control" id="note" rows="3" name="note"></textarea><br>

                    <input type="submit" class="btn btn-success" value="Mua Hàng"/>
                </form>
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
						cả hợp lý, đặc biệt là cửa hàng luôn có những sản phẩm chất lượng giá cả hợp lý
					</span>
            </div>


        </div>
        <div class="col-sm-4 col-md-4 wow animate__headShake" data-wow-duration="2s">
            <p><span class="title-footer">LIÊN HỆ</span></p>
            <div class="description-footer">
                <span>80/9A Lã Xuân Oai Phường Tăng Nhơn Phú A Tp Thủ Đức</span>
                <span>fashionshop@gmail.com</span><br>
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

