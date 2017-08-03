/**
 * Created by Justin on 2016/2/25.
 */

$(document).ready(function() {

    $('.updateShop').click(function() {
        var tds = $(this).closest('tr').children();
        $('#updateShopModal input[name="id"]').val($(tds[0]).text());
        $('#updateShopModal input[name="name"]').val($(tds[1]).text());
        $('#updateShopModal input[name="address"]').val($(tds[2]).text());
    });

    $('.deleteShop').click(function() {
        var shopId = $(this).closest('tr').find('.shopId').text();
        $.ajax({
            type: "POST",
            url: "/admin/shop",
            data: {"behavior": "delete", "id": shopId},
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