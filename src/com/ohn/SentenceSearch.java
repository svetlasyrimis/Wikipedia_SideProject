package com.ohn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by c4q-george on 3/6/15.
 */
public class SentenceSearch {
    public static void main(String[] args) throws IOException
    {

        Document fetchedHTML = Jsoup.connect("www.google.com").get();

        String webPage = fetchedHTML.text();

        int grandPage = webPage.length();
        int lastPeriod = webPage.lastIndexOf(". ");

        int searchFrom = grandPage - webPage.length();

        String lastPiece = webPage.substring(lastPeriod);



    }
}
