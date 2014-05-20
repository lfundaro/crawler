/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lfundaro.crawler.content;

import org.lfundaro.crawler.main.App;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lorenzo
 */
public abstract class ContentExtractor {

    private static final Pattern aTagPattern =
            Pattern.compile("<a\\b[^>]*href=\"https?[^>]*>(.*?)</a>");
    private static final Pattern linkPattern =
            Pattern.compile("href=\"[^>]*\">");

    public ArrayList<URL> extractURLs(String content) {
        Matcher tagMatcher = aTagPattern.matcher(content);
        ArrayList<URL> urlList = new ArrayList();
        while (tagMatcher.find()) {
            try {
                Matcher linkMatcher = linkPattern.matcher(tagMatcher.group());
                if (linkMatcher.find()) {
                    String link = linkMatcher.group().replaceFirst("href=\"", "")
                            .replaceFirst("\">", "")
                            .replaceFirst("\"[\\s]*\\w+=\".*", "");
                    urlList.add(new URL(link));
                }
            } catch (MalformedURLException ex) {
                continue;
            }
        }
        return urlList;
    }

    public String getURLcontent(URL url) {
        HttpURLConnection con = null;
        BufferedReader in = null;
        StringBuilder strBuff = new StringBuilder();
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(20000);
            int resCode = con.getResponseCode();
            if (resCode != 200) {
                return "";
            }
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                strBuff.append(currentLine);
            }
        } catch (ProtocolException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            System.out.println("No such host: " + url.toExternalForm());
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return strBuff.toString();
    }
}
