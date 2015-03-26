/*
    Borrowed example from http://www.codejava.net/java-ee/servlet/java-file-upload-example-with-servlet-30-api
*/

package servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * A webservice used to upload an image to the server.
 * @author Kyle
 */
@WebServlet("/imageUpload")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class ImageUpload extends HttpServlet {
    private static final String directoryPath = "Gameboards";
    
    /**
     * Tries to write the image onto the server.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        String fileName;
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();
        
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + directoryPath;
        
        File fileSaveDir = new File(savePath);
        
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        for (Part part : request.getParts()) {
            fileName = new Date().getTime() + ".jpg";
            try {
                out = new FileOutputStream(new File(savePath + File.separator + fileName));
                filecontent = part.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                writer.println(fileName);
            } 
            catch (FileNotFoundException fne) {
                System.out.println("<br/> ERROR: " + fne.getMessage());
            }
            finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }
}
