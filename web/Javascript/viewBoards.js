/*
 * @author Kyle Crossman
 * @file viewBoards.js
 */

$(document).ready(function() {
    var getBoards = function() {
                    $.getJSON("./boards/boardUpload", function(data) {
                        $('#viewBoardsDiv').html('');
                        for (var i = 0; i < data.length; i++) {
                            insertDiv(data[i].id, data[i].name);
                        }
                    });
                };
    
    getBoards();

    $('.saveNameButton').click(function() {
        console.log($(this).closest("div").find(".hiddenId").val());
    });
    $('.deleteButton').click(function() {
        console.log($(this).closest("div").find(".boardNameValue").val());
    });
});

function insertDiv(boardId, boardName) {
    var HTMLObject = '';
    
    HTMLObject +=   '<div class="viewOneProperty">\n\
                    <input type="text" class="formTextField veryLargeFormTextField marginLeft20p boardNameValue" value="' + boardName + '" />\n\
                    <input type="hidden" class="hiddenId" value="' + boardId + '" />\n\
                    <button class="submitButton marginLeft20px saveNameButton">Save Name</button>\n\
                    <button class="submitButton deleteButton">Delete Board</button>\n\
                    </div>';
    
    $('#viewBoardsDiv').append(HTMLObject);
}