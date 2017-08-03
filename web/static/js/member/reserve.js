/**
 * Created by Justin Chou on 2016/3/12.
 */

$(document).ready(function() {

    $('#purchaseDate').change(function() {
        var date = $(this).val();
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
                document.getElementById('purchaseShop').innerHTML = html;
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('#purchaseShop').change(function() {
        var date = $('#right input[name="purchaseDate"]').val();
        var shop = $(this).val();
        $.ajax({
            type: "POST",
            url: "/reserve",
            data: {"behavior": "query inventory", "productId": $('input[name="productId"]').val(), "date": date, "shopId": shop},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(data) {
                $('#right #inventory').text(data.inventory);
                $('#right #price').text('Price: ¥ ' + data.price);
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('#plus').click(function() {
        var number = $('#right input[name="number"]').val();
        if (number == $('#right #inventory').text()) alert("Exceed max number !");
        else $('#right input[name="number"]').val(++number);
    });

    $('#minus').click(function() {
        var number = $('#right input[name="number"]').val();
        if (number == 1) alert("Number must be positive !");
        else $('#right input[name="number"]').val(--number);
    });

    $('input[name="number"]').blur(function() {
        var number = $(this).val();
        if (number <= 0) {
            alert("Number must be positive !");
            $(this).val(1);
            return;
        }
        if (number > parseInt($('#right #inventory').text())) {
            alert("Short of inventory !");
            $(this).val(1);
            return;
        }
        if (isNaN(number)) {
            alert("Not a number !");
            $(this).val(1);
            return;
        }
        if (number % 1 != 0) {
            alert("Number must be an integer");
            $(this).val(1);
        }
    });

    $('#btnReserve').click(function() {
        if($('#right input[name="purchaseDate"]').val() == '' || $('#right select[name="purchaseShop"]').val() == '') {
            alert('Incomplete information !');
            return false;
        }
        var username = $('span[name="username"]').text();
        var number = $('#right input[name="number"]').val();
        var price = $('#price').text().substr(9);
        $.ajax({
            type: "POST",
            url: "/reserve",
            data: {"behavior": "query member state and balance", "username": username, "number": number, "price": price},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(data) {
                var message = data.message;
                if (message == 'paused') {
                    document.getElementById('confirmReserve').innerHTML = '<div class="form-group">You have not activate your qualification, <a href="/activate">activate now</a> !</div>';
                } else if (message == 'stopped') {
                    document.getElementById('confirmReserve').innerHTML = 'Your member qualification has been stopped, please contact the work staff !';
                } else {
                    $('#confirmReserve input[name="productName"]').val($('#productName').text());
                    $('#confirmReserve input[name="price"]').val($('#price').text().substr(9));
                    $('#confirmReserve input[name="purchaseDate"]').val($('#right input[name="purchaseDate"]').val());
                    $('#confirmReserve input[name="purchaseShopId"]').val($('#right select[name="purchaseShop"] :selected').val());
                    $('#confirmReserve input[name="purchaseShop"]').val($('#right select[name="purchaseShop"] :selected').text());
                    $('#confirmReserve input[name="number"]').val($('#right input[name="number"]').val());
                    $('#confirmReserve input[name="totalPrice"]').val(parseFloat($('#right input[name="number"]').val())*parseFloat($('#price').text().substr(9)));
                    if (message == 'insufficient balance') {
                        $('div[role="alert"]').attr("style", "");
                        $('#confirmReserve button[type="submit"]').attr('disabled', 'disabled');
                    }
                }
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('#btnAddtoShoppingCart').click(function() {
        if($('#right input[name="purchaseDate"]').val() == '' || $('#right select[name="purchaseShop"]').val() == '') {
            alert('Incomplete information !');
            return false;
        }
        $.ajax({
            type: "POST",
            url: "/shopping_cart",
            data: {"productId": $('#reserveModal input[name="productId"]').val(), "price": $('#price').text().substr(9),
                "purchaseDate": $('#right input[name="purchaseDate"]').val(), "purchaseShopId": $('#right select[name="purchaseShop"] :selected').val(), "number": $('#right input[name="number"]').val()},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function() {
                alert("Added to shopping cart !");
                window.location.href = '/product';
            },
            error: function() {
                alert('Error');
            }
        });
    });

});