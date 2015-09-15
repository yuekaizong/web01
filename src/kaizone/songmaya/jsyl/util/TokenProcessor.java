
package kaizone.songmaya.jsyl.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yuelibiao
 */
public class TokenProcessor {

    static final String TOKEN_KEY = "kaizone.songmaya.token";

    private static TokenProcessor instance = new TokenProcessor();

    public static TokenProcessor getInstance() {
        return instance;
    }

    private long privious; // 最近一次生产令牌的时间戳

    /**
     * 判断请求参数中的令牌值是否有效
     * 
     * @param request
     * @return
     */
    public synchronized boolean isToKenValid(HttpServletRequest request) {
        // 得到请求的当前Session对象
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        // 从Session中取出保存的令牌值
        String saved = (String) session.getAttribute(TOKEN_KEY);
        if (saved == null) {
            return false;
        }

        // 清除Session中的令牌值
        resetToken(request);

        // 得到请求参数中的令牌值
        String token = request.getParameter(TOKEN_KEY);
        if (token == null) {
            return false;
        }

        return saved.equals(token);

    }

    /**
     * 清除Session中的令牌值
     * 
     * @param request
     */
    public synchronized void resetToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(TOKEN_KEY);
    }

    public void saveToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = generateToken(request);
        if (token != null) {
            session.setAttribute(TOKEN_KEY, token);
        }
    }

    public synchronized String generateToken(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            byte[] id = session.getId().getBytes();
            long current = System.currentTimeMillis();
            if (current == privious) {
                current++;
            }

            privious = current;
            byte[] now = new Long(current).toString().getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(id);
            md.update(now);
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 将一个字节数组转换为一个十六禁止数字的字符串
     * 
     * @param buffer
     * @return
     */
    public String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
        }
        return sb.toString();
    }

    public synchronized String getToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null == session)
            return null;
        String token = (String) session.getAttribute(TOKEN_KEY);
        if (null == token) {
            token = generateToken(request);
            if (token != null) {
                session.setAttribute(TOKEN_KEY, token);
                return token;
            } else {
                return null;
            }
        } else
            return token;
    }

}
