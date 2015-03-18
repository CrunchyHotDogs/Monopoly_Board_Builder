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
    
    var getBoards = function() {
                    $.getJSON("./boards/boardUpload", function(data) {
                        $('#viewBoardsDiv').html('');
                        for (var i = 0; i < data.length; i++) {
                            insertDiv(data[i].id, data[i].name);
                        }
                        
                        $('.saveNameButton').click(function() {
                            var jsonObject = new Object();
                            jsonObject.id = $(this).closest("div").find(".hiddenId").val();;
                            jsonObject.name = $(this).closest("div").find(".boardNameValue").val();
                            
                            $.ajax({
                                url: "./boards/boardUpload",
                                headers: { 
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json' 
                                },
                                method: "PUT",
                                dataType: "json",
                                data: JSON.stringify(jsonObject),
                                success: getBoards
                            });
                        });
                        $('.deleteButton').click(function() {
                            var url = "./boards/boardUpload/" + $(this).closest("div").find(".hiddenId").val();
                            $.ajax({
                               url: url,
                               method: "DELETE",
                               success: getBoards
                            });
                        });
                        $('.exportJsonButton').click(function() {
                            var url = "./boards/boardUpload/" + $(this).closest("div").find(".hiddenId").val();
                            $.ajax({
                                url: url,
                                method: "GET",
                                success: function(data) {
                                    $('#exportJsonTextArea').val(JSON.stringify(data));
                                    $('#exportJsonDialog').dialog('open');
                                }
                            });
                        });
                    });
                };
                
    getBoards();
    
});

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