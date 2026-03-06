package jframery;

/*
 * Project: JFramery
 * Program: Player.java
 * Programmer: Pierre Awad
 * Date: June 4 2023
 */
public class Player {

    //Declaring Fields
    private String name;
    private int windowHScore;
    private int windowCScore;
    private int windowSScore;

    //Initializing the No-Arg Contructor
    Player() {
        name = "";
        windowHScore = 0;
        windowCScore = 0;
        windowSScore = 0;
    }

    //Initializing the Argument Based Constructor
    Player(String nam, int windowH, int windowC, int windowS) {
        name = nam;
        windowHScore = windowH;
        windowCScore = windowC;
        windowSScore = windowS;
    }

    /* 
     * Method: setName
     * This Method sets the User's Name
     * @param nam (User's Name)
     */
    public void setName(String nam) {
        name = nam;
    }

    /* 
     * Method: setWindowHunterScore
     * This Method sets the User's Score in the Window Hunter Minigame
     * @param WindowH (User's Score in Window Hunter Minigame)
     */
    public void setWindowHunterScore(int windowH) {
        windowHScore = windowH;
    }

    /* 
     * Method: setWindowChaserScore
     * This Method sets the User's Score in the Window Chaser Minigame
     * @param WindowC (User's Score in Window Chaser Minigame)
     */
    public void setWindowChaserScore(int windowC) {
        windowCScore = windowC;
    }

    /* 
     * Method: setWindowSurvivorScore
     * This Method sets the User's Score in the Window Survivor Minigame
     * @param WindowS (User's Score in Window Survivor Minigame)
     */
    public void setWindowSurvivorScore(int windowS) {
        windowSScore = windowS;
    }

    /* 
     * Method: getName
     * This Method Obtains the User's Name
     * @return name (User's Name)
     */
    public String getName() {
        return name;
    }

    /* 
     * Method: getWindowHunterScore
     * This Method Obtains the User's Score in the Window Hunter Minigame
     * @return windowHScore (User's Current Score the Window Hunter Minigame)
     */
    public int getWindowHunterScore() {
        return windowHScore;
    }

    /* 
     * Method: getWindowChaserScore
     * This Method Obtains the User's Score in the Window Chaser Minigame
     * @return windowCScore (User's Current Score the Window Chaser Minigame)
     */
    public int getWindowChaserScore() {
        return windowCScore;
    }

    /* 
     * Method: getWindowSurvivorScore
     * This Method Obtains the User's Score in the Window Survivor Minigame
     * @return windowSScore (User's Current Score the Window Survivor Minigame)
     */
    public int getWindowSurvivorScore() {
        return windowSScore;
    }

    /* 
     * Method: calcOverallScore
     * This Method Calculates the User's Overall Score According to All Three Minigames
     * @return overallScore (User's Overall Score)
     */
    public int calcOverallScore() {

        //Initializing the Overall Score Variable
        int overallScore = 0;

        //If the User Played All Three Games
        if (windowCScore > 0 && windowHScore > 0 && windowSScore > 0) {

            //Calculating the Overall Score
            overallScore = (int)(2*windowSScore) - ((int) (0.1 * windowCScore) - (int) (0.1 * windowHScore));

            //If the User Has Played At Least One Game
        }else if (windowCScore > 0 || windowHScore > 0 || windowSScore > 0){
            
            //Calculating the Overall Score
            overallScore = (int)(0.2*Math.max(Math.max(windowSScore, windowHScore),windowCScore));
        }

        //Returning the Overall Score Value
        return overallScore;
    }

    /* 
     * Method: toString
     * This Method Returns A String With the User's Name and Corresponding Scores
     * @return state (the State of the Point Instance in (x,y) Form)
     */
    public String toString() {
        //Creating the State Variable
        String state = name + " " + windowHScore + " " + windowCScore + " " + windowSScore;

        //Returning the State
        return state;

    }
}
