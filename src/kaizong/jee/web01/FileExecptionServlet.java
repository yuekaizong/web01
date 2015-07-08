package kaizong.jee.web01;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileExecptionServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        FileInputStream fis = new FileInputStream("config.inc");
        Properties pps = new Properties();
        pps.load(fis);
        
        fis.close();
    }

}
