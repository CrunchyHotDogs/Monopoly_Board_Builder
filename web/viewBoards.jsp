<%-- 
    Document   : viewBoards
    Created on : Mar 17, 2015, 3:52:44 PM
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
        <script src="Javascript/viewBoards.js"></script>
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Boards</title>
    </head>
    <body>
        <div id="exportJsonDialog" title="Export Json">
            <textarea id="exportJsonTextArea" class="noDrag" rows="25" cols="77"></textarea>
        </div>
        <img src="Images/greenCheckmark.png" alt="Success Change" class="centered hidden" id="successImage" />
        <img src="Images/redX.png" alt="Failed Change" class="centered hidden" id="errorImage" />
        
        <div class="pageDiv">
            <a href="index.html"><button class="mainMenuButton buttonDisign">Main Menu</button></a>
            <div class="pageLogos">
                <img class="logoImage" src="Images/viewBoardsLogo.png" alt="View Boards" />
            </div>
            
            <div class="newBoardForm">
                <div id="spans">
                    <span id="propertyTab" class="tabSpan selectedTab">View Boards</span>
                </div>
                
                <div id="viewBoardsDiv" class="formsDiv">
                    <p class="infoLabel marginLeft20px">Loading Boards. Please Wait.</p>
                </div>
            </div>
        </div>
    </body>
</html>
