package servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kyle
 */
@Path("/boardUpload")
public class Boards {
    
    @GET
    public void doGet() {
        System.out.println("Hello World!");
    }
    
    @POST
    @Path("/image")
    public Response uploadImage() {   
        return null;
    }
    
    @POST   
    public Response uploadBoard(String boardJson) {
        /*try (Connection conn = credentials.Credentials.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO board (board_name, image_url) VALUES ('3', '32');");
            pstmt.executeUpdate();
            
         }
         catch (SQLException ex) {
             
         }*/
        
        System.out.println(boardJson);
        return null;
    }
}
