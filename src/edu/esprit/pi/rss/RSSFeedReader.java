/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.rss;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * It Reads and prints any RSS feed type.
 */
public class RSSFeedReader {

    private String baseUrl = "http://www.lamoooche.com/getRSS.php?idnews=6746";
    private SyndFeed feed;

    // <editor-fold defaultstate="collapsed" desc=" Getters and Setters ">
    public SyndFeed getFeed() {
        return feed;
    }

    public void setFeed(SyndFeed feed) {
        this.feed = feed;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String url) {
        this.baseUrl = url;
    }

    // </editor-fold>
    
    public List<Event> getItems(String url){
        baseUrl = url;
        System.out.println(url);
        List<Event> events = new ArrayList<>();
        try {
            URL feedUrl = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(feedUrl));
            Iterator entryIter = feed.getEntries().iterator();
            while (entryIter.hasNext()) {
                SyndEntry entry = (SyndEntry) entryIter.next();
                Event event = new Event(entry.getTitle(), entry.getDescription().getValue(), entry.getLink());
                events.add(event);
            }
        } catch (IOException ex) {
            ex.getCause();
            //Logger.getLogger(RSSFeedReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            ex.getCause();
            //Logger.getLogger(RSSFeedReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FeedException ex) {
            ex.getCause();
            //Logger.getLogger(RSSFeedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events;
    }
}