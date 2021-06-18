package ru.ikusov.training.trash;

import ru.ikusov.training.utils.Console;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static ru.ikusov.training.utils.Console.p;
import static ru.ikusov.training.utils.Linguistic.generateRussianName;
import static ru.ikusov.training.utils.MyMath.r;

public class MainClass {
    public static void main(String... args) throws IOException {
        Path    source = Paths.get("c:\\windows"),
                file = Paths.get("c:\\windows\\system32\\accessor.dll"),
                target = Paths.get("d:\\tmp");
        System.out.println("source = " + source);
        System.out.println("file = " + file);
        System.out.println("target = " + target);
        System.out.println("target.resolve(file.getFileName()) = " + target.resolve(file.getFileName()));
        System.out.println("file.relativize(source) = " + file.relativize(source));
        System.out.println("source.relativize(file) = " + source.relativize(file));
        System.out.println("target.resolve(file.relativize(source)) = " + target.resolve(file.relativize(source)));
        System.out.println("target.resolve(source.relativize(file)) = " + target.resolve(source.relativize(file)));
    }
}