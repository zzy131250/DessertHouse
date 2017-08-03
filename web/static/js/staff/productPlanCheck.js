/**
 * Created by Justin Chou on 2016/3/9.
 */

$(document).ready(function() {

    $('.approve').click(function() {
        var tds = $(this).closest('tr').children();
        var productPlanId = $(tds[0]).text();
        $.ajax({
            type: "POST",
            url: "/staff/product_plan_check",
            data: {"behavior": "approve", "id": productPlanId},
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

    $('.reject').click(function() {
        var tds = $(this).closest('tr').children();
        var productPlanId = $(tds[0]).text();
        $.ajax({
            type: "POST",
            url: "/staff/product_plan_check",
            data: {"behavior": "reject", "id": productPlanId},
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