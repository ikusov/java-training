package ru.ikusov.training.skillbox.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

public class FileUtils {

    public enum CalculateSizeMethod {
        IO, NIO
    }

    public static void copyFolder(Path source, Path target) throws IOException {
        /*
        Is source exists? If not - must throw bad command or filename.

        Is target exists? If yes - must throw existing filename, we can not copy to existing files. Or can we?

        Is source file or directory?
            If yes - just copy and return.
            If no - go on.

        Walk file tree.




         */


            if (!Files.exists(source)) {
                throw new FileNotFoundException("Source file not found!");
            }

            if (Files.exists(target) && (!Files.isDirectory(target) || (Files.isDirectory(target) && Files.list(target).count() != 0))) {
                throw new FileAlreadyExistsException("Target file exists or target directory is not empty!");
            }

            if (Files.isDirectory(source) && Files.notExists(target)) {
                    Files.createDirectory(target);
            }

        try {
            if (!Files.isDirectory(source)) {
                Files.copy(source, target.resolve(source.getFileName()));
            } else {
                Files.walkFileTree(source, new HashSet<>(), Integer.MAX_VALUE, new SimpleFileVisitor<>() {
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
                        try {
                            Files.copy(dir, target.resolve(source.relativize(dir)));
                        } catch (FileAlreadyExistsException e) {
                        } catch (IOException e) {
                            System.err.printf("IO Exception excepted while copying dir %s!", dir);
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                        try {
                            Files.copy(file, target.resolve(source.relativize(file)));
                        } catch (IOException e) {
                            System.err.printf("IO Exception excepted while copying file %s!", file);
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            }

        } catch (IOException e) {
            throw new IOException("Something wrong when copying files!");
        }
    }

    public static long calculateSizeIO(File file) {
        long size = 0;
        if (!file.isDirectory()) {
            try {
                size = file.length();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            File[] dir = null;
            try {
                dir = file.listFiles();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (dir != null)
                for (File f : dir) {
                    size += calculateSizeIO(f);
                }
        }
        return size;
    }

    public static long calculateSizeNIO(File file) throws IOException {
        final long[] size = {0};

        Path path = Paths.get(file.getPath());
        if (!Files.isDirectory(path)) {
            try {
                size[0] = Files.size(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Files.walkFileTree(path, new HashSet<>(), Integer.MAX_VALUE,
                    new FileVisitor<>() {
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            try {
                                size[0] += Files.size(file);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }
                    });
        }

        return size[0];
    }

    public static long calculateSize(File file, CalculateSizeMethod method) throws IOException {
        return method == CalculateSizeMethod.IO ? calculateSizeIO(file) : calculateSizeNIO(file);
    }
}
