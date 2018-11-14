package ohtu;

import java.util.*;

public class Submission {
    private int week;
    private int hours;
    private List<Integer>exercises;
    private String course;

    public void setWeek(int week, int hours, List exercises, String course) {
        this.week = week;
        this.hours = hours;
        this.exercises = exercises;
        this.course= course;
    }

    public int getWeek() {
        return week;
    }
    
    public int getHours() {
        return this.hours;
    }

    public String getCourse() {
        return this.course;
    }
    
    public List getExercises() {
        return this.exercises;
    }
    
    public int getTehtavaMaara() {
        return this.exercises.size();
    }
    
    @Override
    public String toString() {
        String harjoitukset="";
        for (int i=0; i<exercises.size(); i++) {
            if(i == exercises.size()-1) {
                harjoitukset += exercises.get(i);
            } else {
                harjoitukset += exercises.get(i) + ", ";
            }           
        }

        return course + ", viikko " +week + " tehtyjä tehtäviä yhteensä " + exercises.size() + 
                " aikaa kului " + this.hours + " tehdyt tehtävät: " + harjoitukset;
    }
    
}