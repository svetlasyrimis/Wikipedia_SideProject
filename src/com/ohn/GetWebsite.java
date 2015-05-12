package com.ohn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GetWebsite {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String webPageURL;//we need this
        String searchTerms;//we need this
        String fileName;//we need this
        String webPage;//we need this
        String fullPage;//we need this
        int grandPage;//we need this

        System.out.println("Welcome to your OHN search engine...");
        System.out.println("I log onto any webpage you give me and I search it's sentences for your keywords");
        System.out.println("You can then save these sentences or expand the selection by typing context.");
        System.out.println("Where shall we start(enter URL)?");
        webPageURL = sc.nextLine();
        System.out.println("Enter your keyword:");
        System.out.println("Tip: It's best to use the root of a word for example electr would select a wide range of electricity themed words.");
        searchTerms = sc.nextLine();
        System.out.println("What should I call the file I'm saving your selections to?");
        fileName = sc.nextLine() + ".txt";

        Document fetchedHTML = Jsoup.connect(webPageURL).get();
        webPage = fetchedHTML.text();
        fullPage = fetchedHTML.text();
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");

        grandPage = fullPage.length(); //we need this for tracking index position
        int searchFrom = grandPage-webPage.length();//we need this to move the search index


        while (true) {
            int beginSentence;//we need this
            int wordIndex;//we need this
            int endSentence;//we need this
            String textBeforeWord;//we need this
            String sentence;//we need this
/*-------------------------------------------------------------------------------*/
            wordIndex = fullPage.indexOf(searchTerms,searchFrom);
            if (wordIndex == -1) {
                break;
            }
/*-------------------------------------------------------------------------------*/
            endSentence = fullPage.indexOf(". ", wordIndex);
            if (endSentence == -1) {
                endSentence = webPage.length();
            } else {
                endSentence++;
            }
/*-------------------------------------------------------------------------------*/
            textBeforeWord = fullPage.substring(0, wordIndex);
            beginSentence = textBeforeWord.lastIndexOf(". ") + 2;
            sentence = fullPage.substring(beginSentence, endSentence);
/*-------------------------------------------------------------------------------*/
            System.out.println(sentence);
            System.out.println("Is this sentence interesting? [ Yes / No ] ");
            String response = sc.nextLine();

            if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                System.out.println("SAVED");
                writer.println(sentence);
                writer.println();
            }
            else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                System.out.println("Deleted");
            }

            else if (response.equalsIgnoreCase("context")) {
                int backtrack = beginSentence - 202;
                int fortrack = endSentence + 198;

                if ((fortrack <= grandPage) && (backtrack >= 0)) {
                    String context;
                    context = fullPage.substring(backtrack, fortrack);
                    System.out.println(context);
                    System.out.println("Is this better now?[ Yes / No ]");
                    response = sc.nextLine();

                    if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                        System.out.println("SAVED");
                        writer.println(context);
                        writer.println();
                    }

                    else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                        System.out.println(sentence);
                        System.out.println("How about now? [ Yes / No ]");
                        response = sc.nextLine();

                        if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                            System.out.println("SAVED");
                            writer.println(sentence);
                            writer.println();
                        }
                        else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                            System.out.println("Deleted");
                        }
                    }

                }
                if((fortrack > grandPage) || (backtrack < 0)){
                    System.out.println("The is no more context to show.");
                    System.out.println(sentence);
                    System.out.println("Do you want to keep this sentence instead? [ Yes / No ] ");
                    response = sc.nextLine();
                    if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                        System.out.println("SAVED");
                        writer.println(sentence);
                        writer.println();
                    }
                    else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                        System.out.println("Deleted");
                    }

                }

            }
/*-------------------------------------------------------------------------------*/
            webPage=fullPage.substring(endSentence);
            searchFrom = grandPage-webPage.length();
        }
        writer.close();

    }
}
