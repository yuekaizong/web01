package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaizone.songmaya.jsyl.util.TokenProcessor;

public class HandlerServlet extends HttpServlet{
    
    int count = 0;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=GBK");
        PrintWriter out = resp.getWriter();
        
        TokenProcessor processor = TokenProcessor.getInstance();        
        if(processor.isToKenValid(req)){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("submit:"+count);
            if(count%2==1)
                count =0;
            else
                count++;
            out.println("success!!!");
        }else{
            processor.saveToken(req);
        }
        
        out.close();
    }

}
