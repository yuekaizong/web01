
package kaizong.jee.web01.tag;

import java.io.UnsupportedEncodingException;

public class MyFuncs {

    public static String toGBK(String str, String charset) throws UnsupportedEncodingException {
        return new String(str.getBytes(charset), "GBK");
    }

}
