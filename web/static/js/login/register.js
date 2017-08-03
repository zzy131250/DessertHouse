/**
 * Created by Justin on 2016/2/12.
 */

$(document).ready(function() {

    $('input[name="username"]').blur(function() {
        var username = $(this).val();
        if(username != '') {
            $.ajax({
                type: "POST",
                url: "/check_register",
                data: {"username": username},
                dataType: "json",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("X-Request-Source", "Dessert House");
                },
                success: function(data) {
                    if(data['message'] == 'ok') {
                        $('#check_login_img').attr("src", "/static/image/login/ok.png");
                        $('#check_login_img').attr("style", "");
                    } else {
                        $('#check_login_img').attr("src", "/static/image/login/no.png");
                        $('#check_login_img').attr("style", "");
                    }
                },
                error: function() {
                    alert('Error');
                }
            });
        }
    });

    $('input[name="confirm"]').blur(function() {
        if($(this).val() != $('#password').val()) {
            alert('Please input consistent password');
            $(this).val('');
        }
    });

    $('input[name="age"]').blur(function() {
        var age = $(this).val();
        if (isNaN(age)) {
            alert('Not a number');
            $(this).val(1);
            return;
        }
        if (age % 1 != 0) {
            alert('Not an integer');
            $(this).val(1);
        }
    });

});
