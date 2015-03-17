package servlets;

import java.io.InputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kyle
 */
@Path("/boardUpload")
public class Boards {
    /*@POST
    @Path("/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@FormDataParam("file") InputStream in, @FormDataParam("file") FormDataContentDisposition fileDetail) {   
        return null;
    }*/
    
    @POST   
    public Response uploadBoard(String boardJson) {
        int uniqueId;
        
        JsonReader reader = Json.createReader(new StringReader(boardJson));
        JsonObject json = reader.readObject();
        
        JsonArray properties = json.getJsonArray("property");
        JsonArray chanceCards = json.getJsonArray("chanceCard");
        JsonArray communutyCards = json.getJsonArray("communityChest");
        JsonArray boardInfo = json.getJsonArray("board");
        
        
        //Insert Board Into Database
        try (Connection conn = credentials.Credentials.getConnection()) {
            //Insert Board Into Database
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO board (board_name, image_url) VALUES ('?','?');",
                                                            Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, boardInfo.getJsonObject(0).getString("name"));
            pstmt.setString(2, boardInfo.getJsonObject(0).getString("url"));
            
            pstmt.executeUpdate();
            
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                uniqueId = keys.getInt(1);
            }
            else {
                uniqueId = -1;
            }
            
            if (uniqueId != -1) {
                //Insert Properties For That Board
                for (int i = 0; i < properties.size(); i++) {
                    JsonObject objects = properties.getJsonObject(i);
                    String name = objects.getString("name");
                    int house = Integer.parseInt(objects.getString("house"));
                    int cost = Integer.parseInt(objects.getString("cost"));
                    String type = objects.getString("type");
                }
        
                //Insert Chance Cards For That Board


                //Insert Community Chest Cards For That Board
            }
         }
         catch (SQLException ex) {
            System.out.println(ex);
         }
        catch (NumberFormatException ex) {
            System.out.println(ex);
        }
        
        return null;
    }
}