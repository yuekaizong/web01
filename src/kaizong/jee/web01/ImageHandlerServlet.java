
package kaizong.jee.web01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageHandlerServlet extends HttpServlet {

    private DataSource ds = null;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement("select data from uploadfile where id = ?");
            pstmt.setInt(1, 1);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                File file = new File(rs.getString("data"));
                if (!file.exists()){
                    RequestDispatcher rd = request.getRequestDispatcher("upload2.html");
                    rd.forward(request, response);
                    return;
                }
                InputStream is = new FileInputStream(file);

                // 通过JPEG图像数据输入流创建JPEG数据流解码器
                JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
                // 解码当前的JPEG数据流，返回BufferedImage对象
                BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
                // 得到Graphics对象，用于在BUfferedImage对象上绘图和输出文字
                Graphics g = buffImg.getGraphics();
                // 创建ImageIcon对象，logo.gif作为水印图片
                ImageIcon imgIcon = new ImageIcon("D:/develop/tools/extools/eclipse/spring-tool-suite/workspace/Web01/WebContent/icon.jpg");
                // 得到Image对象
                Image img = imgIcon.getImage();
                // 将水印绘制到图片上
                g.drawImage(img, 80, 80, null);
                // 设置图形上下文的当前颜色为红色
                g.setColor(Color.RED);
                // 创建新地铁字体
                Font font = new Font("Courier New", Font.BOLD, 20);
                // 设置图形上下文的字体为指定字体
                g.setFont(font);
                // 在图片上绘制文件
                g.drawString("596760835@qq.com", 10, 20);
                // 释放图形上下文使用的系统变量
                g.dispose();

                response.setContentType("image/jpeg");
                ServletOutputStream sos = response.getOutputStream();
                // 创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流
                JPEGImageEncoder jpgEncoder = JPEGCodec.createJPEGEncoder(sos);
                // 编码BUfferedImage对象到JPEG数据输出流.
                jpgEncoder.encode(buffImg);
                is.close();
                sos.close();

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                pstmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
        }
    }
}
