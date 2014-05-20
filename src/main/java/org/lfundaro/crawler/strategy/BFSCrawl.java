package org.lfundaro.crawler.strategy;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import org.lfundaro.crawler.content.ContentExtractor;

/**
 * Crawls the web using a Breath-First Search strategy. 
 * @author Lorenzo
 */
public class BFSCrawl extends ContentExtractor implements CrawlStrategy {

    private LinkedList<URL> queue;

    public BFSCrawl() {
        queue = new LinkedList<URL>();
    }

    /**
     * Crawls the web using a BFS strategy. 
     * @param from starting point.
     * @param maxVisit max visit.
     * @param visited table containing the nodes already visited.
     */
    public void crawl(URL from, int maxVisit, Map<Integer, String> visited) {
        queue.add(from);
        int visitedCount = 0;
        visited.put(from.hashCode(), from.toExternalForm());
        while (visitedCount < maxVisit && !queue.isEmpty()) {
            URL _url = queue.poll();
            String content = getURLcontent(_url);
            visitedCount++;
            System.out.println("Count = ["+visitedCount+"], "
                    + "URL = ["+_url.toExternalForm()+"]");
            ArrayList<URL> extractedUrls = extractURLs(content);
            for (URL exURL : extractedUrls) {
                if (visited.containsKey(exURL.hashCode())) {
                    continue;
                } else {
                    visited.put(exURL.hashCode(), exURL.toExternalForm());
                    queue.add(exURL);
                }
            }
        }
    }
}
