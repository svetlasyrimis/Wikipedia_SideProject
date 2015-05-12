package com.ohn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/* So this is the Main class and I think I'm going to turn this into the main menu of this whole thing. I'm going to try and slice this beast up into components and if it's really it's own thing then Ill transfer it to a new class. */

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);









        System.out.println("Welcome to your OHN search engine...");
        System.out.println("I log onto any webpage you give me and I search it's sentences for your keywords");
        System.out.println("You can then save these sentences or expand the selection by typing context.");

        System.out.println("Where shall we start(enter URL)?");
        String webPageURL = sc.nextLine();
        // DEPRECATED
        System.out.println("Enter your keyword:");
        System.out.println("Tip: It's best to use the root of a word for example electr would select a wide range of electricity themed words.");
        String searchTerms = sc.nextLine();

        // Update so save to different variables and arrays, and save based on packet size. 10Mb max or 1Gb max
        System.out.println("What should I call the file I'm saving your selections to?");
        String fileName = sc.nextLine() + ".txt";

        // Getting the webpage
        Document fetchedHTML = Jsoup.connect(webPageURL).get();
        String webPage = fetchedHTML.text();
        String fullPage = fetchedHTML.text();

        PrintWriter writer = new PrintWriter(fileName, "UTF-8");/* PrintWriter to write some files*/
        
        int grandPage = fullPage.length();
        int lastPeriod = webPage.lastIndexOf(". ");
        int searchFrom = grandPage - webPage.length();
        
        System.out.println("grandPage: " + grandPage);
        System.out.println("Last Period at: " + lastPeriod);

        /* Ok so before we start looping and getting all confused in our flow. What we know is grandPage is an integer that is as big as the length of the fetchedHTML and that we have a String called fullPage that has the full document. We also know that by default in this program, webPage gets shorter. We need to manage the numbers of beginSentence and endSentence better. Have them point to the larger document. */
        
        while (true) {
            /*This is extremely long I want to make it shorter*/
            int beginSentence;
            int wordIndex;
            int endSentence;

            String textBeforeWord;
            String sentence;

            wordIndex = fullPage.indexOf(searchTerms,searchFrom);
            if (wordIndex == -1) {
                break;
            }

            endSentence = fullPage.indexOf(". ", wordIndex);
            if (endSentence == -1) {
                endSentence = webPage.length();
            } else {
                endSentence++;
            }

            textBeforeWord = fullPage.substring(0, wordIndex);
            beginSentence = textBeforeWord.lastIndexOf(". ") + 2;

            sentence = fullPage.substring(beginSentence, endSentence);
            System.out.println("Is this sentence interesting? [ Yes / No ] ");
            String response = sc.nextLine();

            if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                System.out.println("SAVED");
                writer.println(sentence);
                writer.println();
            }
            if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                System.out.println("Deleted");
            }

            if (response.equalsIgnoreCase("context")) {
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

                    if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                        System.out.println(sentence);
                        System.out.println("How about now? [ Yes / No ]");
                        response = sc.nextLine();

                        if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                            System.out.println("SAVED");
                            writer.println(sentence);
                            writer.println();
                        }
                        if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
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
                    if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                        System.out.println("Deleted");
                    }
                }

            }
            webPage=fullPage.substring(endSentence);
            searchFrom = grandPage-webPage.length();

        }
            writer.close();


    }
}
