/*
 * @author Kyle Crossman
 * @file viewBoards.js
 */

$(document).ready(function() {
    $('#exportJsonDialog').dialog({
        autoOpen: false,
        width: 750,
        height: 600,
        open: function (event, ui) {
            $('#exportJsonDialog').css('overflow', 'hidden');
        }
    });
    
    //Checks to see if the database responded.
    var successFLAG = false;
    
    //Gets all of the boards in the database, and displays them. Adds onclick listeners for the buttons involving each board.
    var getBoards = function() {
                    $.getJSON("./boards/boardUpload", function(data) {
                        successFLAG = true;
                        var counter = 0;
                        $('#viewBoardsDiv').html('');
                        for (var i = 0; i < data.length; i++) {
                            insertDiv(data[i].id, data[i].name);
                            counter += 1;
                        }
                        
                        
                        if (counter > 0) {
                            $('.saveNameButton').click(function() {
                                var jsonObject = new Object();
                                jsonObject.id = $(this).closest("div").find(".hiddenId").val();;
                                jsonObject.name = $(this).closest("div").find(".boardNameValue").val();

                                //Tries to update the boards name.
                                $.ajax({
                                    url: "./boards/boardUpload",
                                    headers: { 
                                        'Content-Type': 'application/json' 
                                    },
                                    method: "PUT",
                                    data: JSON.stringify(jsonObject),
                                    success: function() {
                                        fadeImage('#successImage');
                                        getBoards();
                                    },
                                    error: function (data) {
                                        fadeImage('#errorImage');
                                    }
                                });
                            });
                            $('.deleteButton').click(function() {
                                var url = "./boards/boardUpload/" + $(this).closest("div").find(".hiddenId").val();
                                //Tries to delete a board.
                                $.ajax({
                                   url: url,
                                   method: "DELETE",
                                   success: function() {
                                       fadeImage('#successImage');
                                       getBoards();
                                   },
                                   error: function(data) {
                                       fadeImage('#errorImage');
                                       console.log(data);
                                   }
                                });
                            });
                            $('.exportJsonButton').click(function() {
                                var url = "./boards/boardUpload/" + $(this).closest("div").find(".hiddenId").val();
                                //Tries to export the json for a board.
                                $.ajax({
                                    url: url,
                                    method: "GET",
                                    success: function(data) {
                                        $('#exportJsonTextArea').val(JSON.stringify(data));
                                        $('#exportJsonDialog').dialog('open');
                                    }
                                });
                            });
                        }
                        else {
                            $('#viewBoardsDiv').append('<p class="infoLabel marginLeft20px">Sorry, there are no boards in the database currently.</p>');
                        }
                    });
                };
    
    setTimeout(function () {
        if (successFLAG === false) {
            insertErrorMessage();
        }
    }, 3000);
    
    getBoards();
});

//Insert a div containing the board and buttons.
function insertDiv(boardId, boardName) {
    var HTMLObject = '';
    
    HTMLObject +=   '<div class="viewOneProperty">\n\
                    <input type="text" class="formTextField boardNameTextField marginLeft20p boardNameValue" value="' + boardName + '" />\n\
                    <input type="hidden" class="hiddenId" value="' + boardId + '" />\n\
                    <button class="submitButton marginLeft20px saveNameButton">Save Name</button>\n\
                    <button class="submitButton deleteButton">Delete Board</button>\n\
                    <button class="submitButton exportJsonButton">Export Json</button>\n\
                    </div>';
    
    $('#viewBoardsDiv').append(HTMLObject);
}

//Inserts a small error message if the program fails to get any boards.
function insertErrorMessage() {
    var HTMLObject = '';
    
    HTMLObject += '<p class="infoLabel marginLeft20px">Sorry, there seems to be an error with our database. Please contact IT.</p>';
    
    $('#viewBoardsDiv').html(HTMLObject);
}

//Shows and hides an image based on if the action was successful or not.
function fadeImage(id) {
    $(id).fadeIn(0).fadeOut(1000);
}
