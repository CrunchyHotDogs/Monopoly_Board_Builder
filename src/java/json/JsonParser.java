package json;

import boardObjects.Card;
import boardObjects.Property;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Kyle
 */
public class JsonParser {
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
                    .add("desctiption", chance.getString("description"))
                    .add("type", chance.getString("type"));
            jsonArray.add(singleJson);
        }
        
        fullJson.add("chanceCard", jsonArray);
        
        while (communty.next()) {
            singleJson.add("name", communty.getString("name"))
                    .add("desctiption", communty.getString("description"))
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
    
    public static Property parseProperty(String jsonString) {
        return new Property();
    }
    
    public static Card parseCard(String jsonString) {
        return new Card();
    }
}
