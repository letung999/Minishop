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
    });

    parseFloatPrice();
    setTotalPrice();

    function parseFloatPrice() {
        $(".price").each(function() {
            var price = $(this).text();
            var newPrice = parseFloat(price)*1000;
            $(this).html(newPrice);
        });

    }
    function setTotalPrice() {
        var totalPriceProduct = 0;
        $(".price").each(function() {
            var price = $(this).attr("data-price");
            var quantity = $(this).closest("tr").find(".quantity-cart").val();
            var tmp = parseFloat(price) * quantity * 1000;
            totalPriceProduct = totalPriceProduct + tmp/1000;
            $(this).closest("tr").find(".price").html(tmp);
            var formattotalPriceProduct = totalPriceProduct.toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.");
            $("#total").html(formattotalPriceProduct + "VND");
        });
    }

    $(".quantity-cart").change(function (){
        setTotalPrice();
        var quantity = $(this).val();
        var idProduct = $(this).closest("tr").find(".product-cart").attr("data-idProduct");
        var idColor = $(this).closest("tr").find(".color").attr("data-idColor");
        var idSize = $(this).closest("tr").find(".size").attr("data-idSize");

        $.ajax({
            url:"/fashtionshop_war/api/updateCart",
            type:"get",
            data:{
                quantity:quantity,
                idColor:idColor,
                idSize:idSize,
                idProduct:idProduct
            },
            success: function (value){
            }
        })
    })

    $(".btn-delete").click(function (){
        var occur = $(this)
        var idProduct = $(this).closest("tr").find(".product-cart").attr("data-idProduct");
        var idColor = $(this).closest("tr").find(".color").attr("data-idColor");
        var idSize = $(this).closest("tr").find(".size").attr("data-idSize");

        $.ajax({
            url:"/fashtionshop_war/api/deleteCart",
            type:"get",
            data:{
                idColor:idColor,
                idSize:idSize,
                idProduct:idProduct
            },
            success: function (value){
                occur.closest("tr").remove();
                setTotalPrice();
            }
        }).done(function (value){
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


    $("body").on("click", ".paging-item", function (){
        $(".paging-item").removeClass("active");
        $(this).addClass("active");
        var numberPage = $(this).text();
        var startProduct = (numberPage - 1) * 5;
        $.ajax({
            url:"/fashtionshop_war/api/getListProductLimit",
            type:"get",
            data:{
                startProduct: startProduct,
            },
            success:function (value){
                var tbody = $("#table-product").find("tbody");
                tbody.empty();
                tbody.append(value);
            }
        })
    })

    $("#check-all").change(function (){
        if(this.checked){
            $("#table-product input").each(function (){
                $(this).attr("checked", true)
            })
        }
        else{
            $("#table-product input").each(function (){
                $(this).attr("checked", false)
            })
        }
    })

    $("#btn-delete-product").click(function (){
        $("#table-product > tbody input:checked").each(function (){
            var idProduct = $(this).val();
            var thisInput = $(this);

            $.ajax({
                url:"/fashtionshop_war/api/deleteProductById",
                type:"get",
                data:{
                    idProduct:idProduct
                },
                success:function (value){
                    thisInput.closest("tr").remove();
                }
            })
        })
    })


    /* $("#delete-product").click(function() {
         $("#table-product > tbody input:checked").each(function() {
             var idProduct = ($(this).val());
             var This = $(this);
             This.closest("tr").remove();
             $.ajax({

                 url: "/book-shop/api/deleteProduct",
                 type: "GET",
                 data: {
                     idProduct: idProduct
                 },
                 success: function(value) {
                     This.closest("tr").remove();
                 }
             })
         })
     });*/



})