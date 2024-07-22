package com.hyl.qixiao.util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessUtil {

    private static final int TV_FOCUS_WIDTH = 402;
    private static final int  TV_FOCUS_HEIGHT = 226;

    // 2:1 图片 调整到分辨率 402*226
    public static void generateTvFocusImg(File originalFile, File productFile) throws IOException{
        BufferedImage originalImage = ImageIO.read(originalFile);
        double scale = originalImage.getHeight() / (double) originalImage.getWidth();
        int scaledHeight = TV_FOCUS_HEIGHT;
        int scaledWidth = (int)(scaledHeight / scale);
        BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        graphics2D.dispose();
        int cropLeft =  (scaledWidth - TV_FOCUS_WIDTH)/2;
        BufferedImage croppedImage = scaledImage.getSubimage(cropLeft, 0, TV_FOCUS_WIDTH, TV_FOCUS_HEIGHT);
        ImageIO.write(croppedImage, "jpg", productFile);
    }
}
