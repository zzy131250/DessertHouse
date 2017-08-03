/**
 * Created by Justin on 2016/3/3.
 */

$(document).ready(function() {

    $('#addPlan').click(function() {
        $.ajax({
            type: "POST",
            url: "/staff/get_product_list_json",
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(products) {
                var html = '';
                products.productList.forEach(function(product) {
                    html += '<option value ="' + product.id + '">' + product.name +'</option>';
                });
                document.getElementById('addModalProductId').innerHTML = html;
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('.updatePlan').click(function() {
        var tds = $(this).closest('tr').children();
        $('#updateProductPlanModal input[name="id"]').val($(tds[0]).text());
        $('#updateProductPlanModal input[name="date"]').val($(tds[3]).text());
        $('#updateProductPlanModal input[name="saleNumber"]').val($(tds[4]).text());
        $('#updateProductPlanModal input[name="price"]').val($(tds[5]).text());
        $.ajax({
            type: "POST",
            url: "/staff/get_product_list_json",
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-Request-Source", "Dessert House");
            },
            success: function(products) {
                var html = '';
                products.productList.forEach(function(product) {
                    html += '<option value ="' + product.id + '">' + product.name +'</option>';
                });
                document.getElementById('updateModalProductId').innerHTML = html;
                $('#updateProductPlanModal select[name="productId"] option').each(function() {
                    if ($(this).text() == shop) $(this).attr("selected", "selected");
                });
            },
            error: function() {
                alert('Error');
            }
        });
    });

    $('.deletePlan').click(function() {
        var productPlanId = $(this).closest('tr').find('.productPlanId').text();
        $.ajax({
            type: "POST",
            url: "/staff/product_plan",
            data: {"behavior": "delete", "id": productPlanId},
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

    $('#addPlanModal input[name="saleNumber"]').blur(function() {
        if (isNaN($(this).val())) {
            alert("Number must be a number");
            $(this).val(1);
        }
        if ($(this).val() % 1 != 0) {
            alert("Number must be an integer");
            $(this).val(1);
        }
    });

    $('#addPlanModal input[name="price"]').blur(function() {
        if (isNaN($(this).val())) {
            alert("Price must be a number");
            $(this).val(1);
        }
    });

    $('#updateProductPlanModal input[name="saleNumber"]').blur(function() {
        if (isNaN($(this).val())) {
            alert("Number must be a number");
            $(this).val(1);
        }
        if ($(this).val() % 1 != 0) {
            alert("Number must be an integer");
            $(this).val(1);
        }
    });

    $('#updateProductPlanModal input[name="price"]').blur(function() {
        if (isNaN($(this).val())) {
            alert("Price must be a number");
            $(this).val(1);
        }
    });

});