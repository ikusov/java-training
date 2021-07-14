package ru.ikusov.training.utils;

import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;

/**
 * Uninstanceable uninheritable static image utilities class.
 */
public final class Images {
    private Images() {}

    public final static BufferedImage getResizedImage(BufferedImage sourceImage, int width, int height, boolean keepProportions, boolean useImgScalrLib) {
        return useImgScalrLib ?
                getScalrResizedImage(sourceImage, width, height, keepProportions) :
                getPlainResizedImage(sourceImage, width, height, keepProportions);
    }

    private final static BufferedImage getScalrResizedImage(BufferedImage sourceImage, int width, int height, boolean keepProportions) {
        return Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, width, height);
    }

    private final static BufferedImage getPlainResizedImage(BufferedImage sourceImage, int width, int height, boolean keepProportions) {
        int     oldWidth = sourceImage.getWidth(),
                oldHeight = sourceImage.getHeight(),

                newWidth = keepProportions ?
                        Math.max(width, height * oldWidth / oldHeight) :
                        width,
                newHeight = keepProportions ?
                        Math.max(height, width * oldHeight / oldWidth) :
                        height;

        double  wStep = (double)oldWidth / (double)newWidth,
                hStep = (double)oldHeight / (double)newHeight;

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        for (int x=0; x<newWidth; x++)
            for (int y=0; y<newHeight; y++) {
                newImage.setRGB(x, y, sourceImage.getRGB((int)(x*wStep), (int)(y*hStep)));
            }

        return newImage;
    }


}
