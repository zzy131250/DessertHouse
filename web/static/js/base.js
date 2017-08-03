/**
 * Created by J on 2016/6/28.
 */

$(document).ready(function() {

    $('#shoppingCart select[name="cartPurchaseShop"]').click(function() {
        var tr = $(this).closest("tr");
        var g_date = tr.find('.cartTdDate').children(0).val();
        $.ajax({
            type: "POST",
            url: "/reserve",
            data: {"behavior": "query shop", "date": g_date, "productId": tr.find(".cartProductId").val()},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(shops) {
                var html = '<option value="">-- Please Select --</option>';
                var selected = tr.find('.cartTdShop').children(0).val();
                shops.shopList.forEach(function(shop) {
                    if (shop.id == selected) html += '<option value ="' + shop.id + '" selected="selected">' + shop.name +'</option>';
                    else html += '<option value ="' + shop.id + '">' + shop.name +'</option>';
                });
                tr.find('.cartTdShop').children(0).html(html);
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('#shoppingCart input[name="cartPurchaseDate"]').change(function() {
        var date = $(this).val();
        var tr = $(this).closest("tr");
        $.ajax({
            type: "POST",
            url: "/reserve",
            data: {"behavior": "query shop", "date": date},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(shops) {
                var html = '<option value="">-- Please Select --</option>';
                shops.shopList.forEach(function(shop) {
                    html += '<option value ="' + shop.id + '">' + shop.name +'</option>';
                });
                tr.find('.cartTdShop').children(0).html(html);
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('#shoppingCart select[name="cartPurchaseShop"]').change(function() {
        if ($(this).val() != '') {
            var tr = $(this).closest("tr");
            var shopId = $(this).val();
            $.ajax({
                type: "POST",
                url: "/reserve",
                data: {"behavior": "query inventory", "productId": tr.find(".cartProductId").val(), "date": tr.find('.cartTdDate').children(0).val(), "shopId": shopId},
                dataType: "json",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("X-Request-Source", "Dessert House");
                },
                success: function(data) {
                    tr.find(".cartTdPrice").text(parseFloat(data.price));
                },
                error: function() {
                    alert('Error');
                }
            });
        }
    });

    $('.cartPlus').click(function() {
        var tr = $(this).closest("tr");
        var number = tr.find('.cartTdNumber').children(1).val();
        tr.find('.cartTdNumber').children(1).val(++number);
    });

    $('.cartMinus').click(function() {
        var tr = $(this).closest("tr");
        var number = tr.find('.cartTdNumber').children(1).val();
        if (number > 1) tr.find('.cartTdNumber').children(1).val(--number);
    });
    
    $('.btnCartSubmit').click(function() {
        var rows = $('.cartTable').find('tbody tr');
        var isNull = true;
        rows.each(function() {
            var checked = $(this).find('.checkbox');
            if (checked.is(":checked")) {
                isNull = false;
                var number = $(this).find('input[name="number"]').val();
                var price = $(this).find('.cartTdPrice').text();
                var purchaseDate = $(this).find('input[name="cartPurchaseDate"]').val();
                var productId = $(this).find('input[name="cartProductId"]').val();
                var purchaseShop = $(this).find('select[name="cartPurchaseShop"]').val();
                if (purchaseShop == '') {
                    alert("Please select shop");
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: "/reserve",
                    data: {"behavior": "reserve ajax", "number": number, "price": price, "purchaseDate": purchaseDate, "productId": productId, "purchaseShopId": purchaseShop},
                    dataType: "json",
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader("X-Request-Source", "Dessert House");
                    },
                    success: function(data) {
                        if (data.message == 'ok') window.location.href = '/reservation';
                    },
                    error: function() {
                        alert('Error');
                    }
                });
            }
        });
        if (isNull) alert("Please select product");
    });

});