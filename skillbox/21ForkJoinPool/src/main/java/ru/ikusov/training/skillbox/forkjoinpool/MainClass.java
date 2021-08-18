package ru.ikusov.training.skillbox.forkjoinpool;

import ru.ikusov.training.utils.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static ru.ikusov.training.utils.Console.p;


/**
 * skillbox task #21
 * building site map using fork join pool framework
 */
public class MainClass {
    public static final String theURL = "https://www.baeldung.com";
    public static final String theFileName = theURL.substring(8) + ".txt";

    public static void main(String... args) throws IOException, InterruptedException {
        test2();
    }

    public static void test2() throws InterruptedException, IOException {
        //sorted set for found links
        Set<String> linkSet = new TreeSet<>();
        linkSet.add(theURL);

        //create recursive tasks pool
        ForkJoinPool pool = new ForkJoinPool();

        //create recursive task
        LinksProcessor processor = new LinksProcessor(theURL, linkSet, theURL);

        //start time
        long currentTimeMillis = System.currentTimeMillis();

        //starting task and logging its work
        pool.execute(processor);
        while (!processor.isDone()) {
            TimeUnit.MILLISECONDS.sleep(5000);
            p(String.format("Active threads: %d, task count: %d, steal count: %d, links found: %d, time elapsed: %d seconds",
                    pool.getActiveThreadCount(),
                    pool.getQueuedTaskCount(),
                    pool.getStealCount(),
                    linkSet.size(),
                    (System.currentTimeMillis() - currentTimeMillis) / 1000
            ));
        }
        pool.shutdown();

        //format data for file output
        List<String> strings = new ArrayList<>();
        for (String link : linkSet) {
            int slashes = -2;
            for (int i=0; i<link.length()-1; i++) {
                if (link.charAt(i) == '/') {
                    slashes++;
                }
            }
            strings.add("\t".repeat(slashes) + link);
        }
        Files.write(Paths.get(theFileName), strings);
    }
}
