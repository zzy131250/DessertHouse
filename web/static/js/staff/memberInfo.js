/**
 * Created by Justin Chou on 2016/3/17.
 */

$(document).ready(function() {

    $('.searchMember').click(function() {
        var memberCardId = $('#searchForm input[name="memberCard"]').val();
        $.ajax({
            type: "POST",
            url: "/staff/member_info",
            data: {"behavior": "search", "memberCard": memberCardId},
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

    $('.btnSell').click(function() {
        var tds = $(this).closest('tr').children();
        var reservationId = $(tds[0]).text();
        $.ajax({
            type: "POST",
            url: "/staff/member_info",
            data: {"behavior": "sell", "reservationId": reservationId},
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

});