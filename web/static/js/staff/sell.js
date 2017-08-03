/**
 * Created by Justin Chou on 2016/3/16.
 */

$(document).ready(function() {

    $('#btnSell').click(function() {
        $('#sellModal input[name="number"]').val($('#right input[name="number"]').val());
    });

    $('.btnSearch').click(function() {
        var memberCard = $('#sellModal input[name="memberCard"]').val();
        $.ajax({
            type: "POST",
            url: "/staff/sell",
            data: {"behavior": "choose member", "memberCard": memberCard},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(data) {
                var html = '';
                if (data.message == "Wrong Id") {
                    html = '<h5>Wrong Member Card Id !</h5>';
                    document.getElementById('messageArea').innerHTML = html;
                } else {
                    $.each(data, function(key, value) {
                        html += '<div class="form-group">' +
                            '<label for=\"' + key + '\">' + key + ' :</label>' +
                        '<input type="text" class="form-control" name="' + key + '" value="' + value + '" readonly/>' +
                        '</div>';
                    });
                    html += '<div class="form-group">' + '' +
                        '<label for="totalPrice">Total Price : </label>' +
                            '<input type="text" class="sellTotal form-control" name="totalPrice" value="" readonly/>'
                        '</div>';
                    document.getElementById('messageArea').innerHTML = html;
                    $('.sellTotal').val(parseFloat($('#sellModal input[name="number"]').val()*parseFloat($('#right #price').text().substr(9))));
                    $('.btnConfirm').attr("style", "background-color: #000; color: #fff; float: right;");
                }
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

    $('#btnTriggerCashSell').click(function() {
        var number = $('#right input[name="number"]').val();
        var price = $('#sellModal input[name="price"]').val();
        $('#sellWithCashModal input[name="totalPrice"]').val(parseFloat(number)*parseFloat(price));
    });

    $('#btnCashSell').click(function() {
        var number = $('#right input[name="number"]').val();
        var price = $('#sellModal input[name="price"]').val();
        var productId = $('#sellModal input[name="productId"]').val();
        $.ajax({
            type: "POST",
            url: "/staff/sell",
            data: {"behavior": "cash sell", "number": number, "price": price, "productId": productId},
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(data) {
                if(data == 'success') {
                    alert('success');
                    window.location.href = '/staff/sale';
                }
            },
            error: function() {
                alert('Error');
            }
        });
    });

});