/*
 * @author Kyle Crossman
 * @file createBoard.js
 */


/*
 * Global Variables
 */
var jsonObject = "{";
var board = new Board();
var imageFLAG = true;
var formData;

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
                //$('#imageNextButton').prop('disabled', true);
                $('#errorMessage').html(errorMessage);
                $('#imageUploadDialog').dialog('open');
            }
            else {
                //$('#imageNextButton').prop('disabled', false);
            }
        };
        image.onerror = function() {
            alert('Invalid file type: '+ file.type);
        };      
    };
}

function fileOnload(e) {
    var $img = $('<img>', { src: e.target.result });
    var canvas = $('#imageCanvas')[0];
    var context = canvas.getContext('2d');

    $img.load(function() {
        context.drawImage(this, 0, 0);
    });
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
    
    window.scrollTo(0, 0);
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
        retrievePropertyInfo();
        showNextTab('formChance', 'chanceTab');
    });
    $('#chanceNextButton').click(function () {
        retrieveChanceInfo();
        showNextTab('formCommunityChest', 'communityChestTab');
    });
    $('#communityChestNextButton').click(function () {
        retrieveCommunityChestInfo();
        showNextTab('formImage', 'imageTab');
    });
    $('#imageNextButton').click(function () {
        retrieveImage();
        showNextTab('formBoardName', 'boardNameTab');
    });
    $('#boardNameNextButton').click(function () {
        retrieveBoardName(); 
        //showNextTab('formFinished', '');
    });
    
    
    $('#myFile').change(function(e) {
        var file = e.target.files[0],
            imageType = /image.*/;
        
        if (!file.type.match(imageType))
            return;
        
        var reader = new FileReader();
        reader.onload = fileOnload;
        reader.readAsDataURL(file);
        
    });
    $('#previewImageButton').click(function() {
        $('#imageDialog').dialog('open');
    });
    
    
    $('#imageDialog').dialog({
        autoOpen: false,
        width: 850,
        height: 850
    });
    $('#imageUploadDialog').dialog({
        autoOpen: false
    });
    $('#cardDescDialog').dialog({
        autoOpen: false,
        width: 400,
        height: 300
    });
}

function retrievePropertyInfo() {
    var properties = [];
        
    $('#formProperty').find('div.oneProperty').each(function (index, element) {
        properties[index] = new Property();
        
        $(this).find('input').each(function (innerIndex, innerElement) {
            switch (innerIndex) {
                case 0:
                    if ($(this).val() !== "") {
                        properties[index].setName($(this).val());
                    }
                    else {
                        properties[index].setName($(this).attr('placeholder'));
                    }
                    break;
                case 1:
                    var regex = new RegExp("^\d*[0-9](,\d*[0-9]){5}$");
                    if ($(this).val() !== "" && regex.test($(this).val())) {
                        properties[index].setTax($(this).val()); 
                    }
                    else {
                        properties[index].setTax($(this).attr('placeholder')); 
                    }
                    break;
                case 2:
                    if ($(this).val() !== "" && $.isNumeric($(this).val())) {
                        properties[index].setCost($(this).val());
                    }
                    else {
                        properties[index].setCost($(this).attr('placeholder'));
                    }
                    break;
                case 3:
                    if ($(this).val() !== "" && $.isNumeric($(this).val())) {
                        properties[index].setHouse($(this).val());
                    }
                    else {
                        properties[index].setHouse($(this).attr('placeholder'));
                    }
                    break;
                case 4:
                    properties[index].setType($(this).attr('placeholder'));
                    break;
            }
        });
    });
    
    jsonObject += "\"property\" : [";
    
    for (var i = 0; i < properties.length; i++) {
        jsonObject += properties[i].toJson();
        if (i !== (properties.length - 1)) {
            jsonObject += ",";
        }
    }
    jsonObject += "],";
}

function retrieveChanceInfo() {
    var chanceCards = [];
    
    $('#formChance').find('div.oneChanceCard').each(function (index, element) {
        chanceCards[index] = new Card();
        
        $(this).find('input').each(function (innerIndex, innerElement) {
            switch (innerIndex) {
                case 0:
                    if ($(this).val() !== "") {
                        chanceCards[index].setName($(this).val());
                    }
                    else {
                        chanceCards[index].setName($(this).attr('placeholder'));
                    }
                    break;
                case 1:
                    chanceCards[index].setType($(this).attr('placeholder'));
                    break;
                case 2:
                    if ($(this).val() !== "") {
                        chanceCards[index].setDescription($(this).val());
                    }
                    else {
                        chanceCards[index].setDescription($(this).attr('placeholder'));
                    }
                    break;
            }
        });
    });
    
    jsonObject += "\"chanceCard\" : [";
    
    for (var i = 0; i < chanceCards.length; i++) {
        jsonObject += chanceCards[i].toJson();
        if (i !== (chanceCards.length - 1)) {
            jsonObject += ",";
        }
    }
    jsonObject += "],";
}

function retrieveCommunityChestInfo() {
    var communityChest = [];
    
    $('#formCommunityChest').find('div.oneCommunityChest').each(function (index, element) {
        communityChest[index] = new Card();
        
        $(this).find('input').each(function (innerIndex, innerElement) {
            switch (innerIndex) {
                case 0:
                    if ($(this).val() !== "") {
                        communityChest[index].setName($(this).val());
                    }
                    else {
                        communityChest[index].setName($(this).attr('placeholder'));
                    }
                    break;
                case 1:
                    communityChest[index].setType($(this).attr('placeholder'));
                    break;
                case 2:
                    if ($(this).val() !== "") {
                        communityChest[index].setDescription($(this).val());
                    }
                    else {
                        communityChest[index].setDescription($(this).attr('placeholder'));
                    }
                    break;
            }
        });
    });
    
    jsonObject += "\"communityChest\" : [";
    
    for (var i = 0; i < communityChest.length; i++) {
        jsonObject += communityChest[i].toJson();
        if (i !== (communityChest.length - 1)) {
            jsonObject += ",";
        }
    }
    jsonObject += "],";
}

function retrieveImage() {
    var fileSelect = document.getElementById('myFile');
    var files = fileSelect.files;
    
    formData = new FormData();
    
    if (files.length > 0) {
        var file = files[0];
        formData.append('boardImage', file);
    }
    else {
        imageFLAG = false;
    }
}

function retrieveBoardName() {
    
    //Will be moving everything into the success portion of the image upload. The image upload will return a random string that will be
    //used to set the image url.
    //
    
    $.ajax({
        type: 'POST',
        url: "./imageUpload",
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function () {
            console.log('Form Submitted!');
        },
        error: function() {
            console.log("FAILURE");
        }
    });       
    
    if (imageFLAG === true) {
        board.setUrl("http://localhost:8080/MonopolyBoardBuilder/Gameboards/" + (new Date).getTime() + ".jpg");
    }
    else {
        board.setUrl("http://localhost:8080/MonopolyBoardBuilder/Gameboards/Gameboard.jpg");
    }
    
    if ($('#boardName').val() !== "") {
        board.setName($('#boardName').val());
    }
    else {
        board.setName($('#boardName').attr('placeholder'));
    }
    
    jsonObject += "\"board\" : [";
    jsonObject += board.toJson();
    jsonObject += "]}";
    
    //Will become part of the success of another ajax call. The new ajax call will be for uploading the image.
    /*$.ajax({
        type: 'POST',
        url: "./boards/boardUpload",
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        dataType: "json",
        data: jsonObject
    });*/
}