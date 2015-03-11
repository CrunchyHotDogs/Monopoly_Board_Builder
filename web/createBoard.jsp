<%-- 
    Document   : createBoard
    Created on : Mar 5, 2015, 2:36:53 PM
    Author     : Kyle
--%>

<%@page import="boardObjects.Property"%>
<%
    //All the variables used for the placeholders.
    Property properties[] = {   new Property("Go",new int[]{-200,-200,-200,-200,-200,-200},0,0,"G"),
                                new Property("Mediterranean Avenue", new int[]{2,10,30,90,160,250},60,50,"P"),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, ""),
                                new Property("", new int[]{0,0,0,0,0,0}, 0, 0, "")};
                            
    String propertyNames[] = {  "Go", "Mediterranean Avenue", "Community Chest", "Baltic Avenue",
                                                        "Income Tax", "Reading Railroad", "Oriental Avenue", "Chance", "Vermont Avenue",
                                                        "Connecticut Avenue", "Jail", "St. Charles Place", "Electric Company", "States Avenue",
                                                        "Virginia Avenue", "Pennsylvania Railroad", "St. James Place", "Community Chest",
                                                        "Tennessee Avenue", "New York Avenue", "Free Parking", "Kentucky Avenue", "Chance", "Indiana Avenue",
                                                        "Illinois Avenue", "B. & O. Railroad", "Atlantic Avenue", "Ventnor Avenue", "Water Works", "Marvin Gardens",
                                                        "Go To Jail", "Pacific Avenue", "North Carolina Avenue", "Community Chest", "Pennsylvania Avenue",
                                                        "Short Line", "Chance", "Park Place", "Luxury Tax", "Boardwalk"};
                            
    String propertyTypes[] = {  "G", "P", "CC", "P", "IT", "RR", "P", "C", "P", "P", "J", "P", "U", "P", "P", "RR", "P",
                                                        "CC", "P", "P", "FP", "P", "C", "P", "P", "RR", "P", "P", "U", "P", "GTJ", "P", "P", "CC",
                                                        "P", "RR", "C", "P", "LT", "P"};
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
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Build Board</title>
    </head>
    <body>
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
                        <div class="propertyHeaderDiv">
                            <span class="propertyHeader">
                                Name  
                                <span class="pointerCursor" title="The name that will show up when the property is landed on in the game.">
                                    &ofcir;
                                </span>
                            </span>
                            <span class="propertyHeader">
                                Tax  
                                <span class="pointerCursor" title="The amount that is required to be payed when another player lands on your property. Enter five numbers (used for tax when you have houses) seperated by commas.">
                                    &ofcir;
                                </span>
                            </span>
                            <span class="propertyHeader">
                                Cost  
                                <span class="pointerCursor" title="The cost that the property will be in order to buy it.">
                                    &ofcir;
                                </span>
                            </span>
                            <span class="propertyHeader">
                                House  
                                <span class="pointerCursor" title="The cost that each house on this property will be.">
                                    &ofcir;
                                </span>
                            </span>
                            <span class="propertyHeader">
                                Type
                                <span class="pointerCursor" title="The type of property.&#013;P = Property&#013;RR = Railroad&#013;J = Jail&#013;GOT = Go To Jail&#013;G = Go&#013;C = Chance&#013;CC = Community Chest&#013;U = Utility&#013;LT = Luxury Tax&#013;IT = Income Tax&#013;FP = Free Parking">
                                    &ofcir;
                                </span>
                            </span>
                        </div>
                        <% 
                            for (int i = 0; i < properties.length; i++) { 
                        %>
                        <div class="oneProperty">
                            <input id="propertyName<%=i%>" type="text" class="formTextField margin20px" placeholder="<%=properties[i].getName()%>" />
                            <input id="propertyTax<%=i%>" type="text" class="formTextField margin20px" placeholder="<%=properties[i].getTaxString()%>" />
                            <input id="propertyPrice<%=i%>" type="text" class="formTextField margin20px" placeholder="<%=properties[i].getCost()%>" />
                            <input id="propertyHouse<%=i%>" type="text" class="formTextField margin20px" placeholder="<%=properties[i].getHouse()%>" />
                            <input id="propertyType<%=i%>" type="text" class="formTextField margin20px" disabled="disabled" placeholder="<%=properties[i].getType()%>" />
                            <button>Map</button>
                        </div>
                        <% } %>
                    
                        <button id="propertyNextButton" class="submitButton margin20px">Chance Cards</button>
                    </div>
                    
                    <div id="formChance" class="infoForms">
                        <% 
                            for (int i = 0; i < 16; i++) {    
                        %>
                        <div class="oneChanceCard">
                            <input id="chanceName<%=i%>" type="text" class="formTextField margin20px" placeholder="testName" />
                            <input id="chanceLetter<%=i%>" type="text" class="formTextField margin20px" placeholder="A" disabled="disabled" />
                            <input id="chanceDesc<%=i%>" type="text" class="formTextField margin20px" placeholder="testDesc" />
                        </div>
                        <%
                            }
                        %>
                        
                        <button id="chanceNextButton" class="submitButton margin20px">Community Chest Cards</button>
                    </div>
                    
                        
                        
                    <div id="formCommunityChest" class="infoForms">
                        <% 
                            for (int i = 0; i < 18; i++) {    
                        %>
                        <div class="oneCommunityChest">
                            <input id="chestName<%=i%>" type="text" class="formTextField margin20px" placeholder="testName" />
                            <input id="chestLetter<%=i%>" type="text" class="formTextField margin20px" placeholder="A" disabled="disabled" />
                            <input id="chestDesc<%=i%>" type="text" class="formTextField margin20px" placeholder="testDesc" />
                        </div>
                        <%
                            }
                        %>
                        
                        <button id="communityChestNextButton" class="submitButton margin20px">Game Image</button>
                    </div>
                    
              
                    <div id="formImage" class="infoForms">
                        <input id="myFile" type="file" accept=".jpg" />
                        
                        <button id="imageNextButton" class="submitButton margin20px">Submit</button>
                    </div>
                </div>
            </div>
        </div>    
    </body>
</html>
