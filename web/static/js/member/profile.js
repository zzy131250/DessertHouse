/**
 * Created by Justin Chou on 2016/3/17.
 */

$(document).ready(function() {

    $('.btnExchange').click(function() {
        $.ajax({
            type: "POST",
            url: "/profile",
            data: {"behavior": "exchange"},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function() {
                location.reload();
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('.btnStop').click(function() {
        $.ajax({
            type: "POST",
            url: "/profile",
            data: {"behavior": "stop"},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function() {
                window.location.href = "http://localhost:8080";
            },
            error: function() {
                alert('Error');
            }
        });
    });

});