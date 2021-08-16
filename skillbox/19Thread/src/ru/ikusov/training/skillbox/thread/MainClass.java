package ru.ikusov.training.skillbox.thread;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static ru.ikusov.training.utils.Console.p;
import static ru.ikusov.training.utils.Images.getResizedImage;
import static ru.ikusov.training.utils.MyMath.r;
import static ru.ikusov.training.utils.MyMath.rb;

//@SuppressWarnings("all")
public final class MainClass extends Object {
    private final static Integer cPUCount;
    private final static String inputPath = "D:\\tmp\\in",
                                outputPath = "D:\\tmp\\out";
    private static Boolean useImgScalarLib = false;

    static {
        cPUCount = Runtime.getRuntime().availableProcessors();
    }

    public final static void main(String... iuhfauihfkuahfluhglkushdlkfaoiruwqoiuhrlkajshoiuhflkhaiuhakjnfiuahlkanfluyaewlufkalskfaluheflkuasnflkahdliuahelkgnalkdhlkauhrglkahfkuhaskufhaslkufhailuwheflkasluahflukahlkfhaslkdfhalkhflakjnefluaihrfkuaelkfhalsuehflkauhefkahfkuahsefluha) throws InterruptedException {
        singleThreadImagesResizingMethod();    //37883 millis //42591 millis //36560 millis
        version1OfMultiThreadImagesResizingMethod();    //26845 millis //19093 millis
        version2OfMultiThreadImagesResizingMethod();    //20385 millis //19206 millis //21397 millis
        imgScalrLibraryImagesResizingMethod();    //16460 millis //13980 millis //10097 millis
    }

    //single thread images resizing method
    public static final void singleThreadImagesResizingMethod() {
        final long startTime = System.currentTimeMillis();
        File[] files = new File(inputPath).listFiles();
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                ImageIO.write(getResizedImage(image, r(image.getWidth()), r(image.getHeight()), rb(), useImgScalarLib),
                        "jpg",
                        new File(outputPath + "/" + file.getName()));
            }
        } catch (IOException e) {
            p(e.getMessage());
        }
        p("Processing time " + (System.currentTimeMillis() - startTime) + " millis");
    }

    //version 1 of multi thread images resizing method
    public static final void version1OfMultiThreadImagesResizingMethod() throws InterruptedException {
        final long startTime = System.currentTimeMillis();

        File[] files = new File(inputPath).listFiles();

        //split files array to CPUCOUNT of arrays for processing by independent threads
        File[][] filearray = new File[cPUCount][];

        int fileArraySize = files.length/cPUCount;
        for (int i=0; i<cPUCount; i++) {
            filearray[i] = Arrays.copyOfRange(files,
                    fileArraySize*i,
                    i==cPUCount-1 ? files.length : fileArraySize*(i+1));
        }

        ThreadGroup tg = new ThreadGroup("imageResizers");
        for (File[] filear : filearray) {
            Thread thread = new Thread(tg, new ImagesResizer(filear, outputPath, useImgScalarLib));
            thread.start();
        }

        while (tg.activeCount() > 0)
            Thread.sleep(10);

        p(String.format("Processing time: %d millis", System.currentTimeMillis() - startTime));
    }

    //version 2 of multi-thread images resizing method
    public static final void version2OfMultiThreadImagesResizingMethod() throws InterruptedException {
        final long startTime = System.currentTimeMillis();

        File[] files = new File(inputPath).listFiles();

        ThreadGroup threadGroup = new ThreadGroup("imageResizers");

        //run new thread if one of CPUCOUNT threads has done the job
        for(File file : files) {
            while(true) {
                if (threadGroup.activeCount() < cPUCount) {
                    Thread imageResizer =
                            new Thread(threadGroup, new ImagesResizer(file, outputPath, useImgScalarLib));
                    imageResizer.start();
                    break;
                }
                Thread.sleep(10);
            }
        }

        while (threadGroup.activeCount() > 0)
            Thread.sleep(10);

        p(String.format("Processing time: %d millis", System.currentTimeMillis() - startTime));
    }

    //imgscalr library images resizing method
    public static final void imgScalrLibraryImagesResizingMethod() throws InterruptedException {
        useImgScalarLib = true;
        version2OfMultiThreadImagesResizingMethod();
    }

    //just prints cpu count
    public final static void printCPUCount() {
        p(String.format("The program has been found %d processors!", cPUCount));
    }
}