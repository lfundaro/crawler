package org.lfundaro.crawler.strategy;

import java.net.URL;
import java.util.Map;

/**
 * Strategy interface.
 * @author Lorenzo
 */
public interface CrawlStrategy {
    
    /**
     * Crawl the web using a user defined strategy.
     * @param from starting point.
     * @param maxVisit max nodes to visit.
     * @param visited table containing the nodes already visited.
     */
    public void crawl(URL from, int maxVisit, Map<Integer, String> visited);
    
}
