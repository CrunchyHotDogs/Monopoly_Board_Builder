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
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            data: jsonObject,
            success: function() {
                fadeImage('#successImage');
                $('#importJsonTextArea').val("");
            },
            error: function() {
                fadeImage('#errorImage');
            }
        });
    });
});

function fadeImage(id) {
    $(id).fadeIn(0).fadeOut(1000);
}