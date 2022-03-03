$(document).ready(function () {
    $("#btn-login").click(function () {
        var email = $("#email").val();
        var password = $("#password").val();
        $.ajax({
            url: "/fashtionshop_war/api/checkLogin",
            type: "get",
            data: {
                email: email,
                password: password,
            },
            success: function (value) {
                if (value == 'true') {
                    var locationLogin = window.location.href;
                    var locationHomePage = locationLogin.replace("login/", "");
                    window.location = locationHomePage;
                } else {
                    $("#result").append("Login fail!")
                    $("#block-success-login").css("display", "block");
                }
            }
        })
    });

    $(".close-btn").click(function () {
        $("#block-success-login").css("display", "none");
    })

    $("#login").click(function () {
        $(this).addClass("actived");
        $("#signup").removeClass("actived");
        $(".container-login-form").show();
        $(".container-signup-form").css("display", "none");
    });

    $("#signup").click(function () {
        $(this).addClass("actived");
        $("#login").removeClass("actived");
        $(".container-login-form").css("display", "none");
        $(".container-signup-form").show();
    })

    $("#btn-signup").click(function () {
        $("#block-success-login").css("display", "block");
    })

    $(".btn-cart").click(function () {
        var idDetailProduct = $(this).attr("data-idDetailProduct");
        var idProduct = $("#name-product").attr("data-idProduct");
        var idSize = $(this).closest("tr").find(".size").attr("data-idSize");
        var idColor = $(this).closest("tr").find(".color").attr("data-idColor");
        var nameProduct = $("#name-product").text();
        var price = $("#price").text();
        var nameColor = $(this).closest("tr").find(".color").text();
        var nameSize =  $(this).closest("tr").find(".size").text();
        var quantity = $(this).closest("tr").find(".quantity").text();
        $.ajax({
            url: "/fashtionshop_war/api/addCart",
            type: "get",
            data: {
                idDetailProduct:idDetailProduct,
                idProduct:idProduct,
                idSize:idSize,
                idColor:idColor,
                nameProduct:nameProduct,
                price:price,
                nameColor:nameColor,
                nameSize:nameSize,
                quantity:quantity
            },
            success:function (value) {

            }
        }).done(function (){
            $.ajax({
                url:"/fashtionshop_war/api/getQuantityInCart",
                type:"get",
                success:function (value){
                    $("#cart").find("div").addClass("circle-quantity");
                    $("#cart").find("div").html("<span>" + value + "</span>");
                }
            })
        })
    })

})