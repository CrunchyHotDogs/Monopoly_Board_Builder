<%-- 
    Document   : createBoard
    Created on : Mar 5, 2015, 2:36:53 PM
    Author     : Kyle
--%>

<%@page import="boardObjects.Card"%>
<%@page import="boardObjects.Property"%>
<%
    //All the variables used for the placeholders.
    Property properties[] = {   new Property("Go", new int[]{-200,-200,-200,-200,-200,-200}, 0, 0, "G"),
                                new Property("Mediterranean Avenue", new int[]{2,10,30,90,160,250},60,50,"P"),
                                new Property("Community Chest", new int[]{0,0,0,0,0,0}, 0, 0, "CC"),
                                new Property("Baltic Avenue", new int[]{4,20,60,180,320,450}, 60, 50, ""),
                                new Property("Income Tax", new int[]{0,0,0,0,0,0}, 200, 0, "IT"),
                                new Property("Reading Railroad", new int[]{25,25,25,25,25,25}, 200, 0, "RR"),
                                new Property("Oriental Avenue", new int[]{6,30,90,270,400,550}, 100, 50, "P"),
                                new Property("Chance", new int[]{0,0,0,0,0,0}, 0, 0, "C"),
                                new Property("Vermont Avenue", new int[]{6,30,90,270,400,550}, 100, 50, "P"),
                                new Property("Connecticut Avenue", new int[]{8,40,100,300,450,600}, 120, 50, "P"),
                                new Property("Jail", new int[]{0,0,0,0,0,0}, 0, 0, "J"),
                                new Property("St. Charles Place", new int[]{10,50,150,450,625,750}, 140, 100, "P"),
                                new Property("Electric Company", new int[]{0,0,0,0,0,0}, 150, 0, "U"),
                                new Property("States Avenue", new int[]{10,50,150,450,625,750}, 140, 100, "P"),
                                new Property("Virginia Avenue", new int[]{12,60,180,500,700,900}, 160, 100, "P"),
                                new Property("Pennsylvania Railroad", new int[]{25,25,25,25,25,25}, 200, 0, "RR"),
                                new Property("St. James Place", new int[]{14,70,200,550,750,950}, 180, 100, "P"),
                                new Property("Community Chest", new int[]{0,0,0,0,0,0}, 0, 0, "CC"),
                                new Property("Tennessee Avenue", new int[]{14,70,200,550,750,950}, 180, 100, "P"),
                                new Property("New York Avenue", new int[]{16,80,220,600,800,1000}, 200, 100, "P"),
                                new Property("Free Parking", new int[]{0,0,0,0,0,0}, 0, 0, "FP"),
                                new Property("Kentucky Avenue", new int[]{16,90,250,700,875,1050}, 220, 150, "P"),
                                new Property("Chance", new int[]{0,0,0,0,0,0}, 0, 0, "C"),
                                new Property("Indiana Avenue", new int[]{18,90,250,700,875,1050}, 220, 150, "P"),
                                new Property("Illinois Avenue", new int[]{20,100,300,750,925,1100}, 240, 150, "P"),
                                new Property("B. & O. Railroad", new int[]{25,25,25,25,25,25}, 200, 0, "RR"),
                                new Property("Atlantic Avenue", new int[]{22,110,330,800,975,1150}, 260, 150, "P"),
                                new Property("Ventnor Avenue", new int[]{22,110,330,800,975,1150}, 260, 150, "P"),
                                new Property("Water Works", new int[]{0,0,0,0,0,0}, 150, 0, "U"),
                                new Property("Marvin Gardens", new int[]{24,120,360,850,1025,1200}, 280, 150, "P"),
                                new Property("Go To Jail", new int[]{0,0,0,0,0,0}, 0, 0, "GTJ"),
                                new Property("Pacific Avenue", new int[]{26,130,390,900,1100,1275}, 300, 200, "P"),
                                new Property("North Carolina Avenue", new int[]{26,130,390,900,1100,1275}, 300, 200, "P"),
                                new Property("Community Chest", new int[]{0,0,0,0,0,0}, 0, 0, "CC"),
                                new Property("Pennsylvania Avenue", new int[]{28,150,450,1000,1200,1400}, 320, 200, "P"),
                                new Property("Short Line", new int[]{25,25,25,25,25,25}, 200, 0, "RR"),
                                new Property("Chance", new int[]{0,0,0,0,0,0}, 0, 0, "C"),
                                new Property("Park Place", new int[]{35,175,500,1100,1300,1500}, 350, 200, "P"),
                                new Property("Luxury Tax", new int[]{0,0,0,0,0,0}, 100, 0, "LT"),
                                new Property("Boardwalk", new int[]{50,200,600,1400,1700,2000}, 400, 200, "P")};

    Card chanceCards[] = {  new Card("Advance To Go", "[DESCRIPTION]<br/>[PLAYER_NAME] has advanced to go and collected $200.", "A"),
                            new Card("Advance To Illinois Ave.", "[DESCRIPTION]<br/>[PLAYER_NAME] goes straight to Illinois Ave. Receive $200 if you pass go.", "B"),
                            new Card("Advance To St. Charles Place", "[DESCRIPTION]<br/>[PLAYER_NAME] goes straight to St. Charles Place. Receive $200 if you pass go.", "C"),
                            new Card("Advance To Nearest Utility", "[DESCRIPTION]<br/>[PLAYER_NAME] goes straight to the nearest Utility. You may buy the property if unowned, else pay the owner 10x your roll.", "D"),
                            new Card("Advance To Nearest Railroad", "[DESCRIPTION]<br/>[PLAYER_NAME] goes straight to the nearest Railroad.", "E"),
                            new Card("Bank Pays You Dividend", "[DESCRIPTION]<br/>[PLAYER_NAME] receives $50 from the bank.", "F"),
                            new Card("Get Out of Jail Free Card", "[DESCRIPTION]<br/>[PLAYER_NAME] can now escape from jail.", "G"),
                            new Card("Go Back 3 Spaces", "[DESCRIPTION]<br/>[PLAYER_NAME] must move back 3 spaces. Sorry!", "H"),
                            new Card("Go To Jail", "[DESCRIPTION]<br/>[PLAYER_NAME] goes directly to jail, no go money.", "I"),
                            new Card("Make General Repairs On Your Property", "[DESCRIPTION]<br/>[PLAYER_NAME] must pay $25 per house, $100 for each hotel.", "J"),
                            new Card("Pay Poor Tax", "[DESCRIPTION]<br/>[PLAYER_NAME] must pay $15 tax.", "K"),
                            new Card("Take A Trip To Reading Railroad", "[DESCRIPTION]<br/>[PLAYER_NAME] receives $200 if they pass go. Toot toot!", "L"),
                            new Card("Take A Walk On Broadwalk", "[DESCRIPTION]<br/>[PLAYER_NAME] takes a walk on Broadwalk, better hope no one lives here!", "M"),
                            new Card("You Have Been Elected Chairman of the Board", "[DESCRIPTION]<br/>[PLAYER_NAME] pays each player $50.", "N"),
                            new Card("Your Building Loan Matures", "[DESCRIPTION]<br/>[PLAYER_NAME] receives $150.", "O"),
                            new Card("You Have Won A Crossword Competition", "[DESCRIPTION]<br/>[PLAYER_NAME] receives $100.", "P")};

    Card communityChestCards[] = {  new Card("Advance To Go", "[DESCRIPTION]<br/>[PLAYER_NAME] has advanced to go and collected $200.", "A"),
                                    new Card("Bank Error In Your Favor", "[DESCRIPTION]<br/>A bank error has occured in your favor! Collect $200, [PLAYER_NAME].", "B"),
                                    new Card("Doctor's Fee", "[DESCRIPTION]<br/>Oh no! You have to pay a doctor's fee! Pay the doctor $50, [PLAYER_NAME].", "C"),
                                    new Card("From Sale of Stock", "[DESCRIPTION]<br/>Gain $50, [PLAYER_NAME].", "D"),
                                    new Card("Get Out Of Jail Free Card", "[DESCRIPTION]<br/>[PLAYER_NAME] has received a get out of jail free card! WOO!", "E"),
                                    new Card("Go To Jail", "[DESCRIPTION]<br/>Head right on over to the jail!", "F"),
                                    new Card("Grand Opera Night", "[DESCRIPTION]<br/>Every player must purchase tickets. Each player must pay [PLAYER_NAME] $50.", "G"),
                                    new Card("Holiday Fund Matures", "[DESCRIPTION]<br/>Holiday fund time, cheers! Receive $100, [PLAYER_NAME].", "H"),
                                    new Card("Income Tax Refund", "[DESCRIPTION]<br/>Income tax refund. Receive $20, [PLAYER_NAME].", "I"),
                                    new Card("It Is Your Birthday", "[DESCRIPTION]<br/>Happy birthday, [PLAYER_NAME]! Receive $10 from each player.", "J"),
                                    new Card("Life Insurance Matures", "[DESCRIPTION]<br/>Your life insurance has matured. Receive $100, [PLAYER_NAME].", "K"),
                                    new Card("Pay Hospital Fees", "[DESCRIPTION]<br/>Looks like someone had an accident. Pay $100, [PLAYER_NAME].", "L"),
                                    new Card("Pay School Fees", "[DESCRIPTION]<br/>Education is very important. Pay $150, [PLAYER_NAME].", "M"),
                                    new Card("Receives Consultancy Fee", "[DESCRIPTION]<br/>Receive $25, [PLAYER_NAME].", "N"),
                                    new Card("You Are Assessed For Street Repairs", "[DESCRIPTION]<br/>Street repair time, [PLAYER_NAME]. $25 per house, $115 per hotel.", "O"),
                                    new Card("You Have Won Second Prize In A Beauty Contest", "[DESCRIPTION]<br/>Only second place? [PLAYER_NAME] receives $10 for trying.", "P"),
                                    new Card("You Inherit Money", "[DESCRIPTION]<br/>[PLAYER_NAME] inherits $100.", "Q")};
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/cssPage.css" type="text/css"/>
        <script src="Javascript/jquery-2.1.3.min.js"></script>
        <script src="Javascript/Property.js"></script>
        <script src="Javascript/Card.js"></script>
        <script src="Javascript/createBoard.js"></script>
        <link rel="stylesheet" href="Javascript/jquery-ui-1.11.4/jquery-ui.css" type="text/" />
        <script src="Javascript/jquery-ui-1.11.4/jquery-ui.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Build Board</title>
    </head>
    <body>
        <div id="imageDialog" title="Image Preview" class="hidden">
            <canvas id="imageCanvas" width="850" height="850">
            </canvas>
        </div>
        <div id="imageUploadDialog" title="Errors With Image" class="hidden">
            <p id="errorMessage"></p>
        </div>
        <div id="cardDescDialog" title="Card Description" class="hidden">
            
        </div>
        
        <div class="createBoardPageDiv">
            <a href="index.html"><button class="mainMenuButton buttonDisign">Main Menu</button></a>
            <div class="createNewBoardLogoDiv">
                <img class="logoImage" src="Images/createNewBoardLogo.png" alt="Create New Board" />
            </div>
            
            <div class="newBoardForm">
                <div id="spans">
                    <span id="propertyTab" class="tabSpan selectedTab">Properties</span>
                    <span id="chanceTab" class="tabSpan notSelectedTab">Chance Cards</span>
                    <span id="communityChestTab" class="tabSpan notSelectedTab">Community Chest Cards</span>
                    <span id="imageTab" class="tabSpan notSelectedTab">Game Board Image</span>
                </div>
                
                <div id="forms" class="formsDiv">
                    <div id="formProperty" class="infoForms">
                        <div class="headerDiv">
                            <span class="propertyHeader largeHeader">
                                Name  
                                <span class="pointerCursor" title="The name that will show up when the property is landed on in the game.">
                                    &ofcir;
                                </span>
                            </span>
                            <span class="propertyHeader largeHeader">
                                Tax  
                                <span class="pointerCursor" title="The amount that is required to be payed when another player lands on your property. Enter six numbers (used for tax when you have houses) seperated by commas.">
                                    &ofcir;
                                </span>
                            </span>
                            <span class="propertyHeader smallHeader">
                                Cost  
                                <span class="pointerCursor" title="The cost that the property will be in order to buy it.">
                                    &ofcir;
                                </span>
                            </span>
                            <span class="propertyHeader smallHeader">
                                House  
                                <span class="pointerCursor" title="The cost that each house on this property will be.">
                                    &ofcir;
                                </span>
                            </span>
                        </div>
                        <% 
                            for (int i = 0; i < properties.length; i++) { 
                        %>
                        <div class="oneProperty">
                            <input id="propertyName<%=i%>" type="text" class="formTextField largeFormTextField margin20px" placeholder="<%=properties[i].getName()%>" />
                            <input id="propertyTax<%=i%>" type="text" class="formTextField largeFormTextField margin20px" placeholder="<%=properties[i].getTaxString()%>" />
                            <input id="propertyPrice<%=i%>" type="text" class="formTextField smallFormTextField margin20px" placeholder="<%=properties[i].getCost()%>" />
                            <input id="propertyHouse<%=i%>" type="text" class="formTextField smallFormTextField margin20px" placeholder="<%=properties[i].getHouse()%>" />
                            <input id="propertyType<%=i%>" type="hidden" class="formTextField margin20px" placeholder="<%=properties[i].getType()%>" />
                            <button class="submitButton mapButton">Map</button>
                        </div>
                        <% } %>
                    
                        <button id="propertyNextButton" class="submitButton margin20px">Chance Cards</button>
                    </div>
                    
                    <div id="formChance" class="infoForms">
                        <div class="headerDiv">
                            <span class="propertyHeader largeHeader">
                                Name  
                                <span class="pointerCursor" title="The name that will show up when the chance card is drawn.">
                                    &ofcir;
                                </span>
                            </span>
                            <span class="propertyHeader">
                                Description  
                                <span class="pointerCursor" title="A description of what will happen when the card is drawn. You may use '[PLAYER_NAME]' to display the current turn's player name. You may use '[DESCRIPTION]' to display the name of the card.">
                                    &ofcir;
                                </span>
                            </span>
                        </div>
                        
                        <% 
                            for (int i = 0; i < chanceCards.length; i++) {    
                        %>
                        <div class="oneChanceCard">
                            <input id="chanceName<%=i%>" type="text" class="formTextField largeFormTextField margin20px" placeholder="<%=chanceCards[i].getName()%>" />
                            <input id="chanceLetter<%=i%>" type="hidden" class="formTextField margin20px" placeholder="<%=chanceCards[i].getType()%>" disabled="disabled" />
                            <input id="chanceDesc<%=i%>" type="text" class="formTextField veryLargeFormTextField margin20px" placeholder="<%=chanceCards[i].getDescription()%>" />
                            <button class="cardInfo">What Happens</button>
                        </div>
                        <%
                            }
                        %>
                        
                        <button id="chanceNextButton" class="submitButton margin20px">Community Chest Cards</button>
                    </div>
                    
                        
                        
                    <div id="formCommunityChest" class="infoForms">
                        <div class="headerDiv">
                            <span class="propertyHeader largeHeader">
                                Name  
                                <span class="pointerCursor" title="The name that will show up when the community chest card is drawn.">
                                    &ofcir;
                                </span>
                            </span>
                            <span class="propertyHeader">
                                Description  
                                <span class="pointerCursor" title="A description of what will happen when the card is drawn. You may use '[PLAYER_NAME]' to display the current turn's player name. You may use '[DESCRIPTION]' to display the name of the card.">
                                    &ofcir;
                                </span>
                            </span>
                        </div>
                        
                        <% 
                            for (int i = 0; i < communityChestCards.length; i++) {    
                        %>
                        <div class="oneCommunityChest">
                            <input id="chestName<%=i%>" type="text" class="formTextField largeFormTextField margin20px" placeholder="<%=communityChestCards[i].getName()%>" />
                            <input id="chestLetter<%=i%>" type="hidden" class="formTextField margin20px" placeholder="<%=communityChestCards[i].getType()%>" disabled="disabled" />
                            <input id="chestDesc<%=i%>" type="text" class="formTextField veryLargeFormTextField margin20px" title="<%=communityChestCards[i].getDescription()%>" placeholder="<%=communityChestCards[i].getDescription()%>" />
                            <button class="cardInfo">What Happens</button>
                        </div>
                        <%
                            }
                        %>
                        
                        <button id="communityChestNextButton" class="submitButton margin20px">Game Image</button>
                    </div>
                    
              
                    <div id="formImage" class="infoForms">
                        <div class="headerDiv">
                            <span class="propertyHeader">
                                Game Board Image
                                <span class="pointerCursor" title="The image that will be used as the game board. The image must be 850x850px and jpg/jpeg format.">
                                    &ofcir;
                                </span>
                            </span>
                        </div>
                        
                        <div class="upload margin20px">
                            <input id="myFile" type="file" accept=".jpg" />
                        </div>
                        <button id="previewImageButton" class="submitButton">Preview Image</button>
                        
                        <button id="imageNextButton" class="submitButton margin20px">Submit</button>
                    </div>
                </div>
            </div>
        </div>    
    </body>
</html>
