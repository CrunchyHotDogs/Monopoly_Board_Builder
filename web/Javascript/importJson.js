/*
 * @author Kyle Crossman
 * @file importJson.js
 */

$(document).ready(function() {
    $('#importJsonSubmit').click(function() {
        var jsonObject = $('#importJsonTextArea').val();
        $.ajax({
            type: 'POST',
            url: "./boards/boardUpload",
            dataType: "json",
            data: jsonObject
        });
    });
});