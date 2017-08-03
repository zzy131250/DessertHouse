/**
 * Created by Justin Chou on 2016/3/15.
 */

$(document).ready(function() {

    $('.cancelReservation').click(function() {
        var tds = $(this).closest('tr').children();
        $('#cancelReservationModal input[name="reservationId"]').val($(tds[0]).val());
    });

});