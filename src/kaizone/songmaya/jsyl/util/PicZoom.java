
package kaizone.songmaya.jsyl.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class PicZoom {

    public static BufferedImage zoom(String srcFileName, int outputWidth, int outputHeight) {
        ImageIcon imgIcon = new ImageIcon(srcFileName);
        Image img = imgIcon.getImage();
        return zoom(img, outputWidth, outputHeight);
    }

    public static BufferedImage zoom(Image srcImage, int outputWidth, int outputHeight) {
        BufferedImage buffImg = new BufferedImage(outputWidth, outputHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = buffImg.createGraphics();

        g.setColor(Color.WHITE);

        g.fillRect(0, 0, outputWidth, outputHeight);

        g.drawImage(srcImage, 0, 0, outputWidth, outputHeight, null);

        g.dispose();

        return buffImg;
    }

}
