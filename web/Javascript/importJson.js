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
            dataType: "json",
            data: jsonObject
        });
    });
});