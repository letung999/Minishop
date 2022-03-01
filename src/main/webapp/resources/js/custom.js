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

    $("#btn-signup").click(function (){
        $("#block-success-login").css("display", "block");
    })

})