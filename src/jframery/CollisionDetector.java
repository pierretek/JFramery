package jframery;

/*
 * Project: JFramery
 * Program: CollisionDetector.java
 * Programmer: Pierre Awad
 * Date: June 4 2023
 */

public class CollisionDetector {

    //Declaring Fields
    private int obstacleX;
    private int obstacleY;
    private int playerX;
    private int playerY;

    //Initializing the No-Arg Contructor
    CollisionDetector() {
        obstacleX = 0;
        obstacleY = 0;
        playerX = 0;
        playerY = 0;
    }

    //Initializing the Argument Based Constructor
    CollisionDetector(int obX, int obY, int plX, int plY) {
        obstacleX = obX;
        obstacleY = obY;
        playerX = plX;
        playerY = plY;
    }

    /* 
     * Method: calcCollision
     * This Method Calculates Whether a Collision Between the Player and an Obstacle Has Occured
     * @return collision (Whether the User Collided or Not)
     */
    public boolean calcCollision() {
        
        //Creating the Collision Variable
        boolean collision = false;

        //If the User Is Within 150 Pixels Of an Obstacle
        if ((Math.abs(playerX - obstacleX) <= 150) && (Math.abs(playerY - obstacleY) <= 150)) {
            
            //A Collision Has Occured
            collision = true;
        }
        
        //Returning the Collision State
        return collision;
    }

}
