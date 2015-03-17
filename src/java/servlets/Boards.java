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
        JsonReader reader = Json.createReader(new StringReader(boardJson));
        JsonObject json = reader.readObject();
        
        JsonArray properties = json.getJsonArray("property");
        JsonArray chanceCards = json.getJsonArray("chanceCard");
        JsonArray communutyCards = json.getJsonArray("communityChest");
        JsonArray boardInfo = json.getJsonArray("board");
        
        
        //Insert Board Into Database
        try (Connection conn = credentials.Credentials.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO board (board_name, image_url) VALUES ('3', '32');");
            pstmt.executeUpdate();
            
         }
         catch (SQLException ex) {
             
         }
        
        //Insert Properties For That Board
        
        
        //Insert Chance Cards For That Board
        
        
        //Insert Community Chest Cards For That Board
        
        for (int i = 0; i < properties.size(); i++) {
            JsonObject objects = properties.getJsonObject(i);
            System.out.println(objects.getString("name"));
        }
        
        
        
        return null;
    }
}
