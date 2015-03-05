/*
 * @author Kyle Crossman
 * @file createBoard.js
 */


$(document).ready(function () {
    showNextTab('formProperty', 'propertyTab');
     
    initializeElements();
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
                $('#imageNextButton').prop('disabled', true);
                alert(errorMessage);
            }
            else {
                $('#imageNextButton').prop('disabled', false);
                alert("Image is good.");
            }
        };
        image.onerror = function() {
            alert('Invalid file type: '+ file.type);
        };      
    };
}

function showNextTab(nextForm, nextTab) {
    $('#forms').find("div[id^='form']").each(function (index, element) {
       $(this).hide();
    });
    
    $('#spans').find("span").each(function (index, element) {
        $(this).addClass("notSelectedTab");
        $(this).removeClass("selectedTab");
    });
    
    $('#' + nextForm).show();
    $('#' + nextTab).removeClass("notSelectedTab");
    $('#' + nextTab).addClass("selectedTab");
}

function initializeElements() {
    $("#myFile").change(function (e) {
        if (this.disabled) {
            return alert('File upload not supported!');
        }
        
        var F = this.files;
        
        if(F && F[0]) {
            for (var i = 0; i < F.length; i++) { 
                readImage( F[i] );
            } 
        }
    });
    
    $('#propertyNextButton').click(function () {
        showNextTab('formChance', 'chanceTab');
    });
    $('#chanceNextButton').click(function () {
        showNextTab('formCommunityChest', 'communityChestTab');
    });
    $('#communityChestNextButton').click(function () {
        showNextTab('formImage', 'imageTab');
    });
    $('#imageNextButton').click(function () {
        showNextTab('formProperty', 'propertyTab');
    });
}