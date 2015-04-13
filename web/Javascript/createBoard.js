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

//Sets the first tab to be visible once the page loads.
$(document).ready(function () {
    showNextTab('formProperty', 'propertyTab');
     
    initializeElements();
});

//When an image is placed into the input, check to see if it is valid.
function readImage(file) {
    var reader = new FileReader();
    var image  = new Image();

    reader.readAsDataURL(file);  
    reader.onload = function(_file) {
        image.src = _file.target.result;
        image.onload = function() {
            var errorMessage = "";
        
            //Checks to see if the image is 800px wide.
            if (this.width !== 800) {
                errorMessage += "Picture must be 800px wide.\n";
            }
            //Checks to see if the image is 800px high.
            if (this.height !== 800) {
                errorMessage += "Picture must be 800px high.\n";
            }
            //Checks to see if the image is jpg.
            if (file.type !== "image/jpeg") {
                errorMessage += "Picture must be of the jpg/jpeg format.\n";
            }
            
            //If there was any errors, show them to the user.
            if (errorMessage.length > 0) {
                imageFLAG = false;
                $('#errorMessage').html(errorMessage);
                $('#imageUploadDialog').dialog('open');
            }
            else {
                imageFLAG = true;
            }
        };
        image.onerror = function() {
            alert('Invalid file type: '+ file.type);
        };      
    };
}

//When the user uploads an image to the input, load the image they uploaded 
//into a canvas so they can preview it.
function fileOnload(e) {
    var $img = $('<img>', { src: e.target.result });
    var canvas = $('#imageCanvas')[0];
    var context = canvas.getContext('2d');

    $img.load(function() {
        context.drawImage(this, 0, 0);
    });
}

//Shows the next form, and hides the rest of the forms.
//Switches which tab is selected.
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

//Initialize all of the onclicks, dialogs, and input changes.
function initializeElements() {
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
        
        var file = e.target.files[0],
            imageType = /image.*/;
        
        if (!file.type.match(imageType))
            return;
        
        var reader = new FileReader();
        reader.onload = fileOnload;
        reader.readAsDataURL(file); 
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
        showNextTab('formFinished', '');
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
        height: 200
    });
    $('#boardMapDialog').dialog({
       autoOpen: false,
       width: 460,
       height: 475
    });
}

//Retrieves all of the info out of property form and builds a JsonArray out of the info.
function retrievePropertyInfo() {
    var properties = [];
        
    //Loops through all of the input sections.
    $('#formProperty').find('div.oneProperty').each(function (index, element) {
        //Creates a new property for each input section.
        properties[index] = new Property();
        
        //Loops through each of the inputs in the input section.
        $(this).find('input').each(function (innerIndex, innerElement) {
            //Checks what input is being looked at.
            //If the info the user inputted is incorrectly formatted or the wrong type
            //use the placeholder value instead.
            switch (innerIndex) {
                //The properties name.
                case 0:
                    if ($(this).val() !== "") {
                        properties[index].setName($(this).val());
                    }
                    else {
                        properties[index].setName($(this).attr('placeholder'));
                    }
                    break;
                //The properties tax.
                case 1:
                    var regex = new RegExp("^\d*[0-9](,\d*[0-9]){5}$");
                    if ($(this).val() !== "" && regex.test($(this).val())) {
                        properties[index].setTax($(this).val()); 
                    }
                    else {
                        properties[index].setTax($(this).attr('placeholder')); 
                    }
                    break;
                //The properties cost.
                case 2:
                    if ($(this).val() !== "" && $.isNumeric($(this).val())) {
                        properties[index].setCost($(this).val());
                    }
                    else {
                        properties[index].setCost($(this).attr('placeholder'));
                    }
                    break;
                //The properties house cost.
                case 3:
                    if ($(this).val() !== "" && $.isNumeric($(this).val())) {
                        properties[index].setHouse($(this).val());
                    }
                    else {
                        properties[index].setHouse($(this).attr('placeholder'));
                    }
                    break;
                //The properties type.
                case 4:
                    properties[index].setType($(this).attr('placeholder'));
                    break;
            }
        });
    });
    //Starts the properties JsonArray.
    jsonObject += "\"property\" : [";
    
    //Loops through all of the properties and build JsonObjects for them.
    for (var i = 0; i < properties.length; i++) {
        jsonObject += properties[i].toJson();
        if (i !== (properties.length - 1)) {
            jsonObject += ",";
        }
    }
    //End the properties JsonArray.
    jsonObject += "],";
}

//Retrieves all of the info out of the chance card form and builds a JsonArray out of the info.
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

//Retrieves all of the info out of the community chest card form and builds a JsonArray out of the info.
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

//Checks to see if the user inserted an image. If they did add it to the FormData.
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

//Retrieves the board name and uploads the board to the database.
function retrieveBoardName() {
    if ($('#boardName').val() !== "") {
        board.setName($('#boardName').val());
    }
    else {
        board.setName($('#boardName').attr('placeholder'));
    }
    
    //If the user did upload an image, try to upload the image to the server.
    if (imageFLAG === true) {
        ajaxImageCall();  
    }
    else {
        //Set the image url to the default game board and skip trying to upload the image.
        board.setUrl(location.href.substring(0, location.href.lastIndexOf("/")+1) + "Gameboards/Gameboard.jpg");
        ajaxBoardInfoCall();
    }
}

//Tries to upload the image to the server.
function ajaxImageCall() {
    $.ajax({
        type: 'POST',
        url: "./imageUpload",
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            board.setUrl(location.href.substring(0, location.href.lastIndexOf("/")+1) + "Gameboards/" + data);
        },
        error: function() {
            board.setUrl(location.href.substring(0, location.href.lastIndexOf("/")+1) + "Gameboards/Gameboard.jpg");
        }
    });  
    ajaxBoardInfoCall();
}

//Creates the JsonArray for the board and tries to upload the board to the database.
function ajaxBoardInfoCall() {
    jsonObject += "\"board\" : [";
    jsonObject += board.toJson();
    jsonObject += "]}";

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
}

//Draws a rectangle over a default game board, showing where each property is.
//Called from a button click to show that properties location.
function drawRectangle(x, y, width, height) {
    var c = document.getElementById("mapCanvas");
    var mapCanvas = c.getContext('2d');
    
    //Clear the board of all other rectangles.
    mapCanvas.clearRect(0, 0, 450, 450);
    
    //Draws the rectangle.
    mapCanvas.beginPath();
    mapCanvas.strokeStyle = 'red';
    mapCanvas.lineWidth = 5;
    mapCanvas.rect(x, y, width, height);
    mapCanvas.stroke();
    
    //Open the dialog box.
    $('#boardMapDialog').dialog('open');
}

//Shows a hint for what each card does.
function showHint(details) {
    $('#detailsParagraph').html(details);
    $('#cardDescDialog').dialog('open');
}