package json;

import boardObjects.Card;
import boardObjects.Property;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Kyle
 */
public class JsonParser {
    public static String getSpecificBoardJson(ResultSet info) {
        return "";
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
