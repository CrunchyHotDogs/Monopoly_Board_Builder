<%-- 
    Document   : importJson
    Created on : Mar 17, 2015, 3:52:57 PM
    Author     : Kyle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/cssPage.css" type="text/css"/>
        <script src="Javascript/jquery-2.1.3.min.js"></script>
        <link rel="stylesheet" href="Javascript/jquery-ui-1.11.4/jquery-ui.css" type="text/" />
        <script src="Javascript/jquery-ui-1.11.4/jquery-ui.js"></script>
        <script src="Javascript/importJson.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Import Json</title>
    </head>
    <body>
        <div id="imageUrlDialog" class="hidden" title="Image URL">
            <p>Copy the text field to retrieve your image url.</p>
            <input id="imageUrlTextarea" type="text" class="formTextField largeFormTextField" />
        </div>
        <div id="imageUploadDialog" title="Errors With Image" class="hidden">
            <p id="errorMessage"></p>
        </div>
        <img src="Images/greenCheckmark.png" alt="Success Change" class="centered hidden" id="successImage" />
        <img src="Images/redX.png" alt="Failed Change" class="centered hidden" id="errorImage" />
        
        <div class="pageDiv">
            <a href="index.html"><button class="mainMenuButton buttonDisign">Main Menu</button></a>
            <div class="pageLogos">
                <img class="logoImage" src="Images/importJsonLogo.png" alt="Import Json" />
            </div>
            
            <div class="newBoardForm">
                <div id="spans">
                    <span id="propertyTab" class="tabSpan selectedTab">Json Data</span>
                </div>
                
                <div class="formsDiv">
                    <div class="headerDiv marginTop20px">
                        <span class="propertyHeader largeHeader">
                            How This Works  
                            <span class="pointerCursor" title="This page is used for people who know how JSON data works. You can export a board's json data from the view boards page and simply edit any of the information. You must use the exported json as a starting block or create the json in the exact same format. JSONLint.com may make the json easier to read after exporting it.">
                                &ofcir;
                            </span>
                        </span>
                    </div>
                    
                    <textarea id="importJsonTextArea" class="marginLeft20px jsonTextArea noDrag" placeholder="Json data...." cols="125" rows="25"></textarea>
                    <div class="marginLeft20px marginBottom20px">
                        <div class="upload">
                            <input id="myFile" type="file" accept=".jpg" />
                        </div>
                        <button id="importJsonSubmit" class="submitButton">Submit</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
