package ru.ikusov.training.skillbox.jsoup;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLParserMain {

    public static final String resourceURL = "https://lenta.ru";
    public static final String imgDir = "d:\\Temp\\imgcache\\";
    public static final java.util.function.Consumer<String> save = HTMLParserMain::saveImageToFile;
    public static final java.util.function.Consumer<String> print = System.out::println;

    public static final void main(String... lkjsfoeij) throws IOException {
        getImagesFromURL(resourceURL).forEach(save);
    }

    public static final List<String> getImagesFromURL(String URL) throws IOException {
        return Jsoup.connect(URL)
                .get().select("img")
                .stream()
                .map(img -> img.attr("abs:src"))
                .collect(Collectors.toList());
    }

    public static final void saveImageToFile(String imgURL) {
        String fileName = imgDir + getFileNameFromURL(imgURL);

        Path path = Paths.get(fileName);

        if (Files.exists(path)) {
            return;
        }

        try {
            Files.copy(new URL(imgURL).openStream(), path);
        } catch (IOException e) {
            System.err.println(String.format("Loading error: %s", imgURL));
        }
    }

    public static final String getFileNameFromURL(String fileURL) {
        int begin = fileURL.lastIndexOf("/") + 1;
        int end = fileURL.indexOf("?");
        end = end == -1 ? fileURL.length() : end;

        return fileURL.substring(begin, end);
    }
}
