package com.ohn;

import java.util.ArrayList;

/**
 * Created by c4q-george on 3/20/15.
 */
public class Projects {
    /* This is where we build our projects
    * Projects many times will be student's homework, this will be an easier way to organize their thoughts
    * and with organization comes formatting, how it appears on printed paper and editing the sequence the importance.
    * I was thinking we need a TITLE,
    * a description with different KEYWORDS,
    * a TEAM that is made of
    * different CLASS of people engineers, artists, free thinkers.
    * Team also holds EXPERTS that have a leading role in everything.
    * BUDGET that rises with the materials needed
    * how much to build a lab, build an item.
    * MATERIALS
    * DESIGN for efficiency, for looks, for preference
    * RESEARCH theory and practise
    * that contains video, images, text and special sequences of image, video,text that
    * describe a thought flow. Also needed and performed experiments
    * THREADS must sort through these projects, this is important stuff
    * */

    private String title = "";
    private int cost = 0;

    public Projects(String title){
        this.title=title;
    }

    public String getTitle(){
        return this.title;
    }

    public int getCost(){
        return this.cost;
    }

    public void setTitle(String updatedTitle){
        this.title = updatedTitle;
    }

    public void setCost(int cost){
        this.cost = cost;
    }


}
