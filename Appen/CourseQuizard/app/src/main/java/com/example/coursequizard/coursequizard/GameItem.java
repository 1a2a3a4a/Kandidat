package com.example.coursequizard.coursequizard;

/**
 * Created by Simon on 2016-04-26.
 */
public class GameItem {
    private String opponentName;
    private int opponentScore;
    private int userScore;
    private int gameID;
    private String courseName;
    private int state;
    private String stateMessage;

    public void GameItem(){
        this.opponentName = "";
        this.opponentScore=0;
        this.userScore =0;
        this.gameID = -1;
        this.courseName ="";
        this.state = 0;
        this.stateMessage ="";
    }
    public String getOpponentName(){
        return this.opponentName;
    }
    public String getCourseName(){
        return this.courseName;
    }
    public String getStateMessae(){
        return this.stateMessage;
    }
    public int getOpponentScore(){
        return this.opponentScore;
    }
    public int getUserScore(){
        return this.userScore;
    }
    public int getGameID(){
        return this.gameID;
    }
    public int getState(){
        return this.state;
    }
    public void setOpponentName(String opName){
        this.opponentName=opName;
    }
    public void setCourseName(String cName){
    this.courseName =cName;
    }
    public void setStateMessae(String sMessage){
        this.stateMessage =sMessage;
    }
    public void setOpponentScore(int opScore){
        this.opponentScore=opScore;
    }
    public void setUserScore(int uScore){
      this.userScore =uScore;
    }
    public void setGameID(int gID){
        this.gameID=gID;
    }
    public void getState(int gState){
        this.state =gState;
    }
}
