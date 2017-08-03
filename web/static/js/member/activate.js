/**
 * Created by Justin Chou on 2016/3/15.
 */

$(document).ready(function() {

    $('li button').click(function() {
        $('li button').removeClass("active");
        $(this).addClass("active");
    });

    $('#activate').click(function() {
        var money = $('li .active').val();
        $.ajax({
            type: "POST",
            url: "/activate",
            data: {"behavior": "query bankcard money", "money": money},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(data) {
                var message = data.message;
                if (message == 'no') alert("Your bankcard balance is not enough !");
                else activateFunc(money);
            },
            error: function() {
                alert('Error');
            }
        });
    });

    var activateFunc = function(money) {
        $.ajax({
            type: "POST",
            url: "/activate",
            data: {"behavior": "activate", "money": money},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(data) {
                if (data.message == 'ok') {
                    alert('You have activate your account !');
                    location.href = "http://localhost:8080/product";
                }
            },
            error: function() {
                alert('Error');
            }
        });
    }

});