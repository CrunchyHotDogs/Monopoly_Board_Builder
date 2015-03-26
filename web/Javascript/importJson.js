/*
 * @author Kyle Crossman
 * @file importJson.js
 */

var formData;

$(document).ready(function() {
    $("#myFile").change(function (e) {
        if (this.disabled) {
            return alert('File upload not supported!');
        }
        
        var F = this.files;
        
        if(F && F[0]) {
            for (var i = 0; i < F.length; i++) { 
                readImage(F[i]);
            } 
        }
    });
    $('#imageUploadDialog').dialog({
        autoOpen: false
    });
    $('#imageUrlDialog').dialog({
        autoOpen: false
    });
    
    
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

function readImage(file) {
    var reader = new FileReader();
    var image  = new Image();

    reader.readAsDataURL(file);  
    reader.onload = function(_file) {
        image.src = _file.target.result;
        image.onload = function() {
            var errorMessage = "";
        
            if (this.width !== 800) {
                errorMessage += "Picture must be 800px wide.\n";
            }
            if (this.height !== 800) {
                errorMessage += "Picture must be 800px high.\n";
            }
            if (file.type !== "image/jpeg") {
                errorMessage += "Picture must be of the jpg/jpeg format.\n";
            }
            
            if (errorMessage.length > 0) {
                $('#errorMessage').html(errorMessage);
                $('#imageUploadDialog').dialog('open');
            }
            else {
                retrieveImage();
                $('#imageUrlTextarea').val(uploadImage());
                $('#imageUrlDialog').dialog('open');
            }
        };
        image.onerror = function() {
            alert('Invalid file type: '+ file.type);
        };      
    };
}

function retrieveImage() {
    var fileSelect = document.getElementById('myFile');
    var files = fileSelect.files;
    
    formData = new FormData();
    
    if (files.length > 0) {
        var file = files[0];
        formData.append('boardImage', file);
    }
}

function uploadImage() {
    var imageUrl = "";
    $.ajax({
        type: 'POST',
        url: "./imageUpload",
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            imageUrl = location.href.substring(0, location.href.lastIndexOf("/")+1) + "Gameboards/" + data;
        },
        error: function() {
            imageUrl = "FAILED UPLOAD";
        }
    });
    return imageUrl;
}

function fadeImage(id) {
    $(id).fadeIn(0).fadeOut(1000);
}