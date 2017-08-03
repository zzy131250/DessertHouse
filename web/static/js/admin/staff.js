/**
 * Created by Justin on 2016/2/26.
 */

$(document).ready(function() {

    $('#addStaff').click(function() {
        $.ajax({
            type: "POST",
            url: "/admin/get_shop_json",
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(shops) {
                var html = '';
                shops.shopList.forEach(function(shop) {
                    html += '<option value ="' + shop.id + '">' + shop.name +'</option>';
                });
                document.getElementById('addModalShopId').innerHTML = html;
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('.updateStaff').click(function() {
        var tds = $(this).closest('tr').children();
        $('#updateStaffModal input[name="id"]').val($(tds[0]).text());
        var role = $(tds[1]).text();
        var shop = $(tds[2]).text();
        $('#updateStaffModal select[name="role"] option').each(function() {
            if($(this).text() == role) $(this).attr("selected", "selected");
        });
        $.ajax({
            type: "POST",
            url: "/admin/get_shop_json",
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(shops) {
                var html = '';
                shops.shopList.forEach(function(shop) {
                    html += '<option value ="' + shop.id + '">' + shop.name +'</option>';
                });
                document.getElementById('updateModalShopId').innerHTML = html;
                $('#updateStaffModal select[name="shopId"] option').each(function() {
                    if ($(this).text() == shop) $(this).attr("selected", "selected");
                });
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('.deleteStaff').click(function() {
        var shopId = $(this).closest('tr').find('.staffId').text();
        $.ajax({
            type: "POST",
            url: "/admin/staff",
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