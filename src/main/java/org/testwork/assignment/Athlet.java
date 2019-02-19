package org.testwork.assignment;

public class Athlet {

    private String name;

    private double[] decathlon;

    private int score;

    private String place;

    public Athlet(String name, double[] decathlon) {
        this.name = name;
        this.decathlon = decathlon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getDecathlon() {
        return decathlon;
    }

    public void setDecathlon(double[] decathlon) {
        this.decathlon = decathlon;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
