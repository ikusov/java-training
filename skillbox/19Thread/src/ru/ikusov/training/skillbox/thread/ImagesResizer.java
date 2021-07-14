package ru.ikusov.training.skillbox.thread;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.ikusov.training.utils.Console.p;
import static ru.ikusov.training.utils.Images.getResizedImage;
import static ru.ikusov.training.utils.MyMath.r;
import static ru.ikusov.training.utils.MyMath.rb;

public class ImagesResizer implements Runnable {
    List<File> files;
    String outputPath;
    Boolean useImgScalrLib;

    ImagesResizer(File[] files, String outputPath, Boolean useImgScalrLib) {
        this.files = Arrays.asList(files);
        this.outputPath = outputPath;
        this.useImgScalrLib = useImgScalrLib;
    }

    ImagesResizer(File file, String outputPath, Boolean useImgScalrLib) {
        this.files = new ArrayList<>();
        files.add(file);
        this.outputPath = outputPath;
        this.useImgScalrLib = useImgScalrLib;
    }

    @Override
    public void run() {
        files.forEach(file -> {
            BufferedImage image;
            try {
                image = ImageIO.read(file);
                if (image != null) {
                    ImageIO.write(getResizedImage(image, r(image.getWidth()), r(image.getHeight()), rb(), useImgScalrLib),
                            "jpg",
                            new File(outputPath + "/" + file.getName()));
                }
            } catch (IOException e) {
                p("Error reading image " + e.getMessage());
            }
        });
    }
}
