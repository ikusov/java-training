package ru.ikusov.training.skillbox.forkjoinpool;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;

import static ru.ikusov.training.utils.MyMath.r;

//class for site link tree recursive task
public class LinksProcessor extends RecursiveTask<Set<String>> {
    private final String mainURL;   //root url of the site
    private final String url;       //current working url
    private final Set<String> linkSet;  //set of found links

    public LinksProcessor(String url, Set<String> linkSet, String mainURL){
        this.mainURL = mainURL;
        this.url = url;
        this.linkSet = linkSet;
    }

    @Override
    protected Set<String> compute() {
        List<LinksProcessor> subTasks = new LinkedList<>();
        List<String> linksOnThePage;    //current found links

        try {
            linksOnThePage = getLinksFromURL(url);
            Thread.sleep(100+r(100));
            for (String link : linksOnThePage) {

                //if found unique link
                if (!linkSet.contains(link)) {
                    //add link to link set
                    linkSet.add(link);

                    //start new task for the link
                    LinksProcessor task = new LinksProcessor(link, linkSet, mainURL);
                    task.fork();
                    subTasks.add(task);
                }
            }
            //todo: comment, some test and git!
            //add tasks results to link set
            for (LinksProcessor task : subTasks) {
                linkSet.addAll(task.join());
            }
        } catch (IOException | InterruptedException ignored) {
        }
        return linkSet;
    }

    //get links from the page url
    public List<String> getLinksFromURL(String url) throws IOException {
        return Jsoup.connect(url).get()
                .select("a")
                .eachAttr("abs:href")
                .stream()
                .filter(link -> link.contains(mainURL) && !link.contains("#"))
                .map(link -> {
                    StringBuilder sb = new StringBuilder(link);
                    while (sb.charAt(sb.length()-1)=='/') sb.deleteCharAt(sb.length()-1);
                    return sb.toString();
                })
                .toList();
    }
}
