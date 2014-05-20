package org.lfundaro.crawler.main;

import org.lfundaro.crawler.strategy.BFSCrawl;
import org.lfundaro.crawler.strategy.DFSCrawl;
import org.lfundaro.crawler.strategy.CrawlStrategy;
import java.net.MalformedURLException;
import java.net.URL;
import org.lfundaro.crawler.Crawler;

/**
 * This is a simple Crawler prototype using 
 * two exploring strategies: BFS & DFS.
 *
 * To run this program you should do:
 * java -jar crawler.jar <startURL> <BFS|DFS> <maxVisit> 
 */
public class App {

    public static void main(String[] args) {
        try {
            URL url = new URL(args[0]);
            CrawlStrategy strategy;
            if (args[1].equals("BFS")) {
                strategy = new BFSCrawl();
            } else if (args[1].equals("DFS")) {
                strategy = new DFSCrawl();
            } else {
                System.out.println("No such crawl strategy: "+args[1]);
                usage();
                return;
            }
            Crawler crawler = new Crawler(Integer.parseInt(args[2]), url, strategy);
            crawler.run();
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
            usage();
        } catch (NumberFormatException ex) {
            System.out.println("Not a valid max number of visits");
            usage();
        }
    }
    
    private static void usage() {
        System.out.println("Usage: java -jar Crawler-version "
                + "<startURL> <BFS|DFS> <maxVisit>");
    }
}
