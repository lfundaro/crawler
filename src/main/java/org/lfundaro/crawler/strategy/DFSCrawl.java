package org.lfundaro.crawler.strategy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import org.lfundaro.crawler.content.ContentExtractor;

/**
 * Crawls the web using a Depth-First Search strategy.
 * @author Lorenzo
 */
public class DFSCrawl extends ContentExtractor implements CrawlStrategy {

    /**
     * Emulates the stack of a recursive DFS.
     */
    private Stack<URL> toVisit;

    public DFSCrawl() {
        toVisit = new Stack();
    }

    /**
     * Crawls the web using a DFS strategy. 
     * @param from starting point.
     * @param maxVisit max visit.
     * @param visited table containing the nodes already visited.
     */
    public void crawl(URL from, int maxVisit, Map<Integer, String> visited) {
        toVisit.push(from);
        int visitedCount = 0;
        while (visitedCount < maxVisit && !toVisit.isEmpty()) {
            URL _url = (URL) toVisit.pop();
            if (visited.containsKey(_url.hashCode())) {
                continue;
            }
            String content = getURLcontent(_url);
            visitedCount++;
            System.out.println("Count = [" + visitedCount + "], "
                    + "URL = [" + _url.toExternalForm() + "]");
            visited.put(_url.hashCode(), _url.toExternalForm());
            ArrayList<URL> extractedUrls = extractURLs(content);
            for (URL exURL : extractedUrls) {
                toVisit.add(exURL);
            }
        }
    }
}
