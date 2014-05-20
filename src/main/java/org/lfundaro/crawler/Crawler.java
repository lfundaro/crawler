package org.lfundaro.crawler;

import org.lfundaro.crawler.strategy.CrawlStrategy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Crawls the web using a user defined strategy. 
 * @author Lorenzo
 */
public class Crawler {

    /**
     * Starting URL.
     */
    private URL from;
    /**
     * Max amount of explorations.
     */
    private int maxVisit;
    /**
     * Visited nodes table.
     */
    private Map<Integer, String> visited = new HashMap();
    /**
     * User defined strategy. Could be any that implements 
     * the CrawlStrategy interface.
     */
    private CrawlStrategy crawlStrategy;

    /**
     * Constructs a new Crawler.
     * @param maxVisit max amount of visits.
     * @param from starting point.
     * @param crawlStrategy user defined strategy.
     */
    public Crawler(int maxVisit, URL from, CrawlStrategy crawlStrategy) {
        this.maxVisit = maxVisit;
        this.from = from;
        this.crawlStrategy = crawlStrategy;
    }

    /**
     * Runs the crawler with a given strategy.
     */
    public void run() {
        crawlStrategy.crawl(from, maxVisit, visited);
    }

    public int getMaxVisit() {
        return maxVisit;
    }

    public URL getFrom() {
        return from;
    }

    public Map<Integer, String> getVisited() {
        return visited;
    }
}
