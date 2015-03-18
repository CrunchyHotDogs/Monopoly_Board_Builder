package servlets;

import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
    @GET
    @Produces("application/json")
    public Response getAllBoards() {
        String returnString = "";
        try (Connection conn = credentials.Credentials.getConnection()) {
            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
            JsonObjectBuilder json = Json.createObjectBuilder();
            PreparedStatement pstmt = conn.prepareStatement("SELECT board_id, board_name FROM board;");
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                json.add("id", rs.getInt("board_id"))
                        .add("name", rs.getString("board_name"));
                jsonArray.add(json);
            }
            
            JsonArray completeJson = jsonArray.build();
            returnString = completeJson.toString();
        }
        catch(SQLException ex) {
            
        }
        
        return Response.ok(returnString).build();
    }
    
    @POST
    @Path("/image")
    @Consumes("image/jpeg")
    public Response uploadImage() {   
        return null;
    }
    
    @POST
    @Consumes("application/json")
    public Response uploadBoard(String boardJson) {
        int uniqueId;
        Response response;
        
        JsonReader reader = Json.createReader(new StringReader(boardJson));
        JsonObject json = reader.readObject();
        
        JsonArray properties = json.getJsonArray("property");
        JsonArray chanceCards = json.getJsonArray("chanceCard");
        JsonArray communutyCards = json.getJsonArray("communityChest");
        JsonArray boardInfo = json.getJsonArray("board");
        
        
        //Insert Board Into Database
        try (Connection conn = credentials.Credentials.getConnection()) {
            //Insert Board Into Database
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO board (board_name, image_url) VALUES (?, ?);",
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
                    String tax = objects.getString("tax");
                    int house = Integer.parseInt(objects.getString("house"));
                    int cost = Integer.parseInt(objects.getString("cost"));
                    String type = objects.getString("type");
                    
                    pstmt = conn.prepareStatement("INSERT INTO property (property_id, name, tax, house_cost, cost, type, board_id) VALUES (?, ?, ?, ?, ?, ?, ?);");

                    pstmt.setInt(1, i);
                    pstmt.setString(2, name);
                    pstmt.setString(3, tax);
                    pstmt.setInt(4, house);
                    pstmt.setInt(5, cost);
                    pstmt.setString(6, type);
                    pstmt.setInt(7, uniqueId);
                    
                    pstmt.execute();
                }
        
                //Insert Chance Cards For That Board
                for (int i = 0; i < chanceCards.size(); i++) {
                    JsonObject objects = chanceCards.getJsonObject(i);
                    String name = objects.getString("name");
                    String description = objects.getString("description");
                    String type = objects.getString("type");
                    
                    pstmt = conn.prepareStatement("INSERT INTO chance (chance_id, name, description, type, board_id) VALUES (?, ?, ?, ?, ?);");

                    pstmt.setInt(1, i);
                    pstmt.setString(2, name);
                    pstmt.setString(3, description);
                    pstmt.setString(4, type);
                    pstmt.setInt(5, uniqueId);
                    
                    pstmt.execute();
                }

                //Insert Community Chest Cards For That Board
                for (int i = 0; i < communutyCards.size(); i++) {
                    JsonObject objects = communutyCards.getJsonObject(i);
                    String name = objects.getString("name");
                    String description = objects.getString("description");
                    String type = objects.getString("type");
                    
                    pstmt = conn.prepareStatement("INSERT INTO communitychest (chest_id, name, description, type, board_id) VALUES (?, ?, ?, ?, ?);");

                    pstmt.setInt(1, i);
                    pstmt.setString(2, name);
                    pstmt.setString(3, description);
                    pstmt.setString(4, type);
                    pstmt.setInt(5, uniqueId);
                    
                    pstmt.execute();
                }
            }
        }
        catch (SQLException | NumberFormatException ex) {
            System.out.println(ex);
            return Response.status(500).build();
        }
        
        return Response.ok().build();
    }
    
    @PUT
    @Consumes("application/json")
    public Response updateBoard(String board) {
        JsonReader reader = Json.createReader(new StringReader(board));
        JsonObject json = reader.readObject();
        
        
        try (Connection conn = credentials.Credentials.getConnection()) {
            String id = json.getString("id");
            String name = json.getString("name");
        
            PreparedStatement pstmt = conn.prepareStatement("UPDATE board SET board_name = ? WHERE board_id = ?;");
            pstmt.setString(1, name);
            pstmt.setInt(2, Integer.parseInt(id));
            
            pstmt.execute();
        }
        catch(SQLException ex) {
            return Response.status(500).build();
        }
        
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteBoard(@PathParam("id") int id) {
        try (Connection conn = credentials.Credentials.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM board WHERE board_id = ?");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
        catch (SQLException ex) {
            return Response.status(500).build();
        }
        return Response.ok().build();
    }
}
