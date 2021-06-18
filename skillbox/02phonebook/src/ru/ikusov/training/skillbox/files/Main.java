package ru.ikusov.training.skillbox.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static ru.ikusov.training.skillbox.files.FileUtils.CalculateSizeMethod.IO;
import static ru.ikusov.training.skillbox.files.FileUtils.CalculateSizeMethod.NIO;
import static ru.ikusov.training.skillbox.files.FileUtils.copyFolder;
import static ru.ikusov.training.utils.Console.p;

public class Main {
    public static String path = "D:\\!!incoming";

    public static File file;
    public static long duration;
    public static long size;
    public static String readableSize;

    public static Path source;
    public static Path target;

    public static final void main (String... lkjsoifj) throws InterruptedException, IOException {
/*      //Code for HomeTask "DIRECTORY SIZE"
        file = sizePathInput();
        path = file.getPath();

        calculate(NIO);
        calculate(IO);

        file = sizePathInput();
        path = file.getPath();

        calculate(IO);
        calculate(NIO);

 */

        //Code for HomeTask "DIRECTORY COPY"
        copyPathInput();
        copyFolder(source, target);
    }


    private static void copyPathInput() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        for (;;) {
            p("Enter source file or directory name and target directory name:");
            String[] input = scanner.nextLine().split(" ");

            if (input[0].equalsIgnoreCase("exit")) {
                throw new InterruptedException("PROGROM INTORROPTOD BO OSOR!!111");
            }

            if (input.length < 2) {
                p("Not enough arguments!");
            } else try {
                source = Paths.get(input[0]);
                target = Paths.get(input[1]);
                break;
            } catch (InvalidPathException e) {
                p("Invalid path!");
            }
        }
    }

    private static File sizePathInput() throws InterruptedException {
        File file;
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            p("Enter path to file or directory: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                throw new InterruptedException("PROGRAM INTERRUPTOD BUY USOR!!11");
            }
            file = new File(input);
            if (file.exists()) break;
            p("Bad filename!");
        }
        return file;
    }

    private static String readableSize(long size) {
        String[] prefix = {"", "", "K", "M", "G", "T", "P", "E", "Z", "Y"};
        float division = size;
        int i = 0;
        while (division >= 1) {
            division /= 1024;
            i++;
        }
        return String.format("%.2f %sbytes", division*1024, prefix[i]);
    }

    private static void startTimer() {
        duration = System.currentTimeMillis();
    }

    private static void stopTimer() {
        duration = System.currentTimeMillis() - duration;
    }

    private static void calculate(FileUtils.CalculateSizeMethod method) throws IOException {
        startTimer();
        size = FileUtils.calculateSize(file, method);
        stopTimer();

        readableSize = readableSize(size);
        p(String.format("Dir: %s\nSize: %s (%,d bytes)\n%s calculation duration: %,d millis",
                            path, readableSize, size, method,                   duration));
    }

}
