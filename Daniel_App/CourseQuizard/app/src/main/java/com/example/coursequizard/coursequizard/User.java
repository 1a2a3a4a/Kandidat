package com.example.coursequizard.coursequizard;

import android.app.Application;

/**
 * Created by daniel.hellgren on 2016-04-21.
 */
public class User extends Application {
    private String name = "";
    private int wins = 0;
    private int losses = 0;


    public User(String name, int wins, int losses){
        this.name = name;
        this.wins = wins;
        this.losses = losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }
}
