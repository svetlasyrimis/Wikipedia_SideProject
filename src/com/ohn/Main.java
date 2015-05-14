package com.ohn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/* So this is the Main class and I think I'm going to turn this into the main menu of this whole thing. I'm going to try and slice this beast up into components and if it's really it's own thing then Ill transfer it to a new class. */

public class Main {


    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to your OHN search engine...");
        System.out.println("I log onto any webpage you give me and I search it's sentences for your keywords");
        System.out.println("You can then save these sentences or expand the selection by typing context.");
        System.out.println("Where shall we start?(enter URL)");
        String sWebURL = userInput.nextLine();
        System.out.println("Enter your keyword:");
        System.out.println("Tip: It's best to use the root of a word for example electr would select a wide range of electricity themed words.");
        String sKeyword = userInput.nextLine();
        System.out.println("What should I call the file I'm saving your selections to?");
        String fileName = userInput.nextLine() + ".txt";
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        Document docRaw = Jsoup.connect(sWebURL).get();
        // sSolid will ALWAYS be used to build our string
        String sSolid = docRaw.text();
        // sShrinking is used to move the iContinueFrom pointer
        String sShrinking = docRaw.text();
        int iSolidLength = sSolid.length();
        int iLastPeriod = sShrinking.lastIndexOf(". ");
        System.out.println("The whole page is " + iSolidLength+" long.");
        System.out.println("Last Period at: " + iLastPeriod);
        int iContinueFrom = iSolidLength - sShrinking.length();
        while (true) {
            int iKeywordPosition = sSolid.indexOf(sKeyword,iContinueFrom);
            if (iKeywordPosition == -1) {
                break;
            }
            int iEndSentence = sSolid.indexOf(". ", iKeywordPosition);
            if (iEndSentence == -1) {
                iEndSentence = sSolid.length();
            } else {
                iEndSentence++;
            }
            String sBeforePosition = sSolid.substring(0, iKeywordPosition);
            int iStartSentence = sBeforePosition.lastIndexOf(". ") + 2;
            String sSentence = sSolid.substring(iStartSentence, iEndSentence);
            System.out.println(sSentence);
            System.out.println("Is this sentence interesting? [ Yes / No ] ");
            String sResponse = userInput.nextLine();
            if (sResponse.equalsIgnoreCase("yes") || sResponse.equalsIgnoreCase("y")) {
                System.out.println("SAVED");
                writer.println(sSentence);
                writer.println();
            }
            if (sResponse.equalsIgnoreCase("no") || sResponse.equalsIgnoreCase("n")) {
                System.out.println("Deleted");
            }
            if (sResponse.equalsIgnoreCase("context")) {
                int iNewStartSentence =getNewStart(iStartSentence);
                int iNewEndSentence = getNewEnd(iSolidLength,iEndSentence);
                    String sContext = sSolid.substring(iNewStartSentence, iNewEndSentence);
                    System.out.println(sContext);
                    System.out.println("Is this better now?[ Yes / No ]");
                    sResponse = userInput.nextLine();
                    if (sResponse.equalsIgnoreCase("yes") || sResponse.equalsIgnoreCase("y")) {
                        System.out.println("SAVED");
                        writer.println(sContext);
                        writer.println();
                    }
                    if (sResponse.equalsIgnoreCase("no") || sResponse.equalsIgnoreCase("n")) {
                        System.out.println(sSentence);
                        System.out.println("How about now? [ Yes / No ]");
                        sResponse = userInput.nextLine();
                        if (sResponse.equalsIgnoreCase("yes") || sResponse.equalsIgnoreCase("y")) {
                            System.out.println("SAVED");
                            writer.println(sSentence);
                            writer.println();
                        }
                        if (sResponse.equalsIgnoreCase("no") || sResponse.equalsIgnoreCase("n")) {
                            System.out.println("Deleted");
                        }
                    }
            }
            sShrinking = sSolid.substring(iEndSentence);
            iContinueFrom = iSolidLength - sShrinking.length();
        }
            writer.close();
    }
    public static int getNewStart(int iStart){
        return (202>iStart) ? 0:iStart-202;
    }
    public static int getNewEnd(int max, int iEnd){
        return (iEnd+198>max)? max:iEnd+198;
    }
}
