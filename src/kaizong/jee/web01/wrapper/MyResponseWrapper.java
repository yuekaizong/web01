
package kaizong.jee.web01.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream baos;
    private ByteArrayServletOutputStream basos;
    private PrintWriter pw;

    public MyResponseWrapper(HttpServletResponse response) {
        super(response);
        baos = new ByteArrayOutputStream();
        basos = new ByteArrayServletOutputStream(baos);
        pw = new PrintWriter(baos);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return pw;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return basos;
    }

    public byte[] toByteArray() {
        return baos.toByteArray();
    }

}
