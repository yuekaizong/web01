
package kaizong.jee.web01.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class ByteArrayServletOutputStream extends ServletOutputStream {

    ByteArrayOutputStream baos;

    public ByteArrayServletOutputStream(ByteArrayOutputStream boas) {
        this.baos = boas;
    }

    @Override
    public void write(int data) throws IOException {
        baos.write(data);
    }

}
