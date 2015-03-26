package json;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * A static class that is used to either return json, check json, or parse through json.
 * @author Kyle
*/


public class JsonParser {
    
    /**
     * Runs through all of the info about a board and return a complete json object for that board.
     * @param board A result set of the board info (board name and image url).
     * @param property A result set of the properties.
     * @param chance A result set of the chance cards.
     * @param communty A result set of the community chest cards.
     * @return A json object represented as a string.
     * @throws SQLException 
     */
    public static String getSpecificBoardJson(ResultSet board, ResultSet property, ResultSet chance, ResultSet communty) throws SQLException {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        JsonObjectBuilder singleJson = Json.createObjectBuilder();
        JsonObjectBuilder fullJson = Json.createObjectBuilder();
        
        while (property.next()) {
            singleJson.add("name", property.getString("name"))
                    .add("tax", property.getString("tax"))
                    .add("house", property.getString("house_cost"))
                    .add("cost", property.getString("cost"))
                    .add("type", property.getString("type"));
            jsonArray.add(singleJson);
        }
        
        fullJson.add("property", jsonArray);
        
        while (chance.next()) {
            singleJson.add("name", chance.getString("name"))
                    .add("description", chance.getString("description"))
                    .add("type", chance.getString("type"));
            jsonArray.add(singleJson);
        }
        
        fullJson.add("chanceCard", jsonArray);
        
        while (communty.next()) {
            singleJson.add("name", communty.getString("name"))
                    .add("description", communty.getString("description"))
                    .add("type", communty.getString("type"));
            jsonArray.add(singleJson);
        }
        
        fullJson.add("communityChest", jsonArray);
        
        while (board.next()) {
            singleJson.add("name", board.getString("board_name"))
                    .add("url", board.getString("image_url"));
            jsonArray.add(singleJson);
        }
        
        fullJson.add("board", jsonArray);
        
        JsonObject completeJson = fullJson.build();
        return completeJson.toString();
    }
    
    public static String getAllBoards(ResultSet info) throws SQLException {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        JsonObjectBuilder json = Json.createObjectBuilder();


        while (info.next()) {
            json.add("id", info.getInt("board_id"))
                    .add("name", info.getString("board_name"));
            jsonArray.add(json);
        }

        JsonArray completeJson = jsonArray.build();
        
        return completeJson.toString();
    }
    
    /**
     * Runs through a JsonObject and checks to see if it is valid according to the
     * format used for boards. Must have 40 properties, 16 chance cards, 17 community
     * chest cards, and the board info.
     * @param json The JsonObject used to hold the board json.
     * @return A true or false value saying if the json is valid.
     */
    public static boolean validJsonCheck(JsonObject json) {
        boolean validFLAG = true;
        int Counter = 0;
        String errorMessage = "";
        
        JsonArray properties = json.getJsonArray("property");
        JsonArray chanceCards = json.getJsonArray("chanceCard");
        JsonArray communutyCards = json.getJsonArray("communityChest");
        JsonArray boardInfo = json.getJsonArray("board");
        
        try {
            for (int i = 0; i < properties.size(); i++) {
                JsonObject objects = properties.getJsonObject(i);
                if (chkS(objects, "name") && chkS(objects, "tax") && chkS(objects, "house") && chkS(objects, "cost") && chkS(objects, "type")) {
                    Counter += 1;
                }
            }
            if (Counter != 40) {
                errorMessage += "Problem with the properties!\n";
                validFLAG = false;
            } 
            
            
            Counter = 0;
            for (int i = 0; i < chanceCards.size(); i++) {
                JsonObject objects = chanceCards.getJsonObject(i);
                if (chkS(objects, "name") && chkS(objects, "description") && chkS(objects, "type")) {
                    Counter += 1;
                }
            }
            if (Counter != 16) {
                errorMessage += "Problem with the chance cards!\n";
                validFLAG = false;
            }
            
            
            Counter = 0;
            for (int i = 0; i < communutyCards.size(); i++) {
                JsonObject objects = communutyCards.getJsonObject(i);
                if (chkS(objects, "name") && chkS(objects, "description") && chkS(objects, "type")) {
                    Counter += 1;
                }
            }
            if (Counter != 17) {
                errorMessage += "Problem with the community chest cards!\n";
                validFLAG = false;
            }
            
            Counter = 0;
            for (int i = 0; i < boardInfo.size(); i++) {
                JsonObject objects = boardInfo.getJsonObject(i);
                if (chkS(objects, "name") && chkS(objects, "url")) {
                    Counter += 1;
                }
            }
            if (Counter != 1) {
                errorMessage += "Problem with the board info!\n";
                validFLAG = false;
            }
        }
        catch (NullPointerException ex) {
            System.out.println("Oh no! An Error! " + ex.getMessage());
            validFLAG = false;
        }
        
        System.out.println(errorMessage);
        return validFLAG;
    }
    
    /**
     * Simple method to determine if the variable is part of the JsonObject. Checks for strings.
     * @param objects The json object to check in.
     * @param string The variable to look for.
     * @return Returns true if the variable is in the json object.
     * @throws NullPointerException 
     */
    private static boolean chkS(JsonObject objects, String string) throws NullPointerException {
        String str = objects.getString(string);
        return true;
    }
}
