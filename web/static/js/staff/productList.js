/**
 * Created by Justin on 2016/3/1.
 */

$(document).ready(function() {

    $('.updateProduct').click(function() {
        var tds = $(this).closest('tr').children();
        $('#updateProductModal input[name="id"]').val($(tds[0]).text());
        $('#updateProductModal input[name="name"]').val($(tds[1]).text());
    });

    $('.deleteProduct').click(function() {
        var productId = $(this).closest('tr').find('.productId').text();
        $.ajax({
            type: "POST",
            url: "/staff/product_list",
            data: {"behavior": "delete", "id": productId},
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

    $('.uploadPhoto').click(function() {
        var productId = $(this).closest('tr').find('.productId').text();
        $('#uploadPhotoModal input[name="productId"]').val(productId);
    });

});