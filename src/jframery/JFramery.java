package jframery;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.JFrame;

/*
 * Project: JFramery
 * Program: JFramery.java
 * Programmer: Pierre Awad
 * Date: May 30 2023
 * Description: A Program With Various JFrame Based Minigames
 */

public class JFramery {

    public static void main(String[] args) {

        //Initializing the Variables Needed for the Games
        int menuChoice;
        int score;
        int ranking;
        boolean flag;
        String name;
        Player temp;
        String[] tokens;

        //Initializing Array Lists To Store Players
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> savedPlayers = new ArrayList<>();

        //Initializing Decimal Formatter
        DecimalFormat dF = new DecimalFormat("###,##0.##");
        Scanner scanN = new Scanner(System.in);

        //Declaring the Scanner, File, and Print Writers
        PrintWriter pw;
        PrintWriter pfw;
        FileWriter fw;
        Scanner scanFile;

        //Initialzing the Score File
        File nf1 = new File("scores.txt");

        //Starting the Try Catch For the InterruptedException
        try {

            //Printing the Title
            System.out.println("Welcome To...");
            Thread.sleep(1000);
            System.out.println("\tJFRAMERY");
            Thread.sleep(1000);

            //Using a Do-While Loop to Repeat the Main Menu
            do {

                //Printing the Main Menu
                System.out.println("\n\t-Main Menu-"
                        + "\n\t1 - View Minigame Insructions"
                        + "\n\t2 - Play Window Hunter"
                        + "\n\t3 - Play Window Chaser"
                        + "\n\t4 - Play Window Survivor"
                        + "\n\t5 - See Scores"
                        + "\n\t6 - Erase All Scores"
                        + "\n\t7 - End Program");

                //Prompting and Storing the User's Menu Choice
                System.out.print("\nYour Choice: ");
                menuChoice = scanN.nextInt();

                //Using a Switch Statement to Excecute The User's Desired Menu Option
                switch (menuChoice) {

                    //If the User Chose to View the Instructions
                    case 1: {

                        //Calling the Instructions Method
                        instructions();
                    }
                    break;

                    //If the User Chose to Play the Window Hunter Minigame
                    case 2: {

                        //Calling the Name Getter Method and Storing the Name
                        name = nameGetter().toUpperCase();

                        //Calling the Minigame Method and Storing the Score
                        score = windowHunter(name);

                        //Creating a New Player Object to Store the Name and Score
                        players.add(new Player(name, score, 0, 0));

                        //Opening the Try Catch Statement
                        try {

                            //Initiazlizing the FileWriter and PrintWriter
                            fw = new FileWriter(nf1, true);
                            pfw = new PrintWriter(fw);

                            //Printing the Latest Player's Information to the Scores File
                            pfw.println(players.get(players.size() - 1).toString());

                            //Closing the FileWriter and PrintWriter
                            pfw.close();
                            fw.close();

                            //Catching the IOException and Printing the Error Message
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    break;

                    //If the User Chose to Play the Window Chaser Minigame
                    case 3: {

                        //Calling the Name Getter Method and Storing the Name
                        name = nameGetter().toUpperCase();

                        //Calling the Minigame Method and Storing the Score
                        score = windowChaser(name);

                        //Creating a New Player Object to Store the Name and Score
                        players.add(new Player(name, 0, score, 0));

                        //Opening the Try Catch Statement
                        try {

                            //Initiazlizing the FileWriter and PrintWriter
                            fw = new FileWriter(nf1, true);
                            pfw = new PrintWriter(fw);

                            //Printing the Latest Player's Information to the Scores File
                            pfw.println(players.get(players.size() - 1).toString());

                            //Closing the FileWriter and PrintWriter
                            pfw.close();
                            fw.close();

                            //Catching the IOException and Printing the Error Message
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    break;

                    //If the User Chose to Play the Window Survivor Minigame
                    case 4: {

                        //Calling the Name Getter Method and Storing the Name
                        name = nameGetter().toUpperCase();

                        //Calling the Minigame Method and Storing the Score
                        score = windowSurvivor(name);

                        //Creating a New Player Object to Store the Name and Score
                        players.add(new Player(name, 0, 0, score));

                        //Opening the Try Catch Statement
                        try {

                            //Initiazlizing the FileWriter and PrintWriter
                            fw = new FileWriter(nf1, true);
                            pfw = new PrintWriter(fw);

                            //Printing the Latest Player's Information to the Scores File
                            pfw.println(players.get(players.size() - 1).toString());

                            //Closing the FileWriter and PrintWriter
                            pfw.close();
                            fw.close();

                            //Catching the IOException and Printing the Error Message
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                    //If User Chose to View All Previous Scores
                    case 5: {

                        //Setting the Ranking Variable to One
                        ranking = 1;

                        //Starting the Try Catch For the InterruptedException
                        try {

                            //Initializing the FileWriter and PrintWriter
                            scanFile = new Scanner(nf1);

                            //If the Scores File is Empty
                            if (!scanFile.hasNext()) {

                                //Telling the User There are No Previous Scores
                                System.out.println("\n-Sorry, No Previous Scores Were Found-");

                                //If the Scores File is Not Empty
                            } else {

                                //Printing the Title and Collums For the Scores Table
                                System.out.println("\n\t\t-All Previous Scores-");
                                System.out.println("Name\tWindow Hunter\tWindow Chaser\tWindow Survivor\t Overall Score");

                                //While There are More Lines in the Scores File
                                while (scanFile.hasNext()) {

                                    //Tokenizing the Player Information Stored in the Latest Line of the Scores File
                                    tokens = scanFile.nextLine().split(" ");

                                    //Creating a New Saved Player Using the Information from the File
                                    savedPlayers.add(new Player(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));

                                    //Combining the Scores of Duplicate Players To Ensure They Have the Highest Score In Each Game
                                    for (int i = 0; i < savedPlayers.size(); i++) {

                                        //If There Is a Duplicate Name
                                        if (savedPlayers.get(savedPlayers.size() - 1).getName().equalsIgnoreCase(savedPlayers.get(i).getName())) {

                                            //If the Score In the Window Hunter Minigame is Greater Than A Previous One
                                            if (savedPlayers.get(savedPlayers.size() - 1).getWindowHunterScore() > savedPlayers.get(i).getWindowHunterScore()) {

                                                //Replacing the Previous Window Hunter Score with the Greater One
                                                savedPlayers.get(i).setWindowHunterScore(savedPlayers.get(savedPlayers.size() - 1).getWindowHunterScore());
                                            }

                                            //If the Score In the Window Chaser Minigame is Greater Than A Previous One
                                            if (savedPlayers.get(savedPlayers.size() - 1).getWindowChaserScore() > savedPlayers.get(i).getWindowChaserScore()) {

                                                //Replacing the Previous Window Chaser Score with the Greater One
                                                savedPlayers.get(i).setWindowChaserScore(savedPlayers.get(savedPlayers.size() - 1).getWindowChaserScore());
                                            }

                                            //If the Score In the Window Survivor Minigame is Greater Than A Previous One
                                            if (savedPlayers.get(savedPlayers.size() - 1).getWindowSurvivorScore() > savedPlayers.get(i).getWindowSurvivorScore()) {

                                                //Replacing the Previous Window Survivor Score with the Greater One
                                                savedPlayers.get(i).setWindowSurvivorScore(savedPlayers.get(savedPlayers.size() - 1).getWindowSurvivorScore());
                                            }
                                        }
                                    }
                                }

                                //Exchange Sorting the Players By Descending Overall Score
                                for (int i = 0; i < savedPlayers.size(); i++) {
                                    for (int j = i + 1; j < savedPlayers.size(); j++) {

                                        //If the Overall Score of the Next Player is Greater Than the Last
                                        if (savedPlayers.get(i).calcOverallScore() < savedPlayers.get(j).calcOverallScore()) {

                                            //Storing One of the Players in a Temporary Variable
                                            temp = savedPlayers.get(i);

                                            //Overwriting the Lesser Player with the Greater
                                            savedPlayers.set(i, savedPlayers.get(j));

                                            //Replacing the Greater Player with the Temporary Variable
                                            savedPlayers.set(j, temp);
                                        }
                                    }
                                }

                                //Removing Players With Duplicate Names
                                for (int i = 0; i < savedPlayers.size(); i++) {
                                    for (int k = 0; k < savedPlayers.size(); k++) {

                                        //If There Is a Duplicate Name
                                        if (savedPlayers.get(i).getName().equalsIgnoreCase(savedPlayers.get(k).getName())) {

                                            //Removing All Other Players With the Same Name 
                                            for (int f = 0; f < savedPlayers.size(); f++) {

                                                //If There Is a Duplicate Name Other Than The First One
                                                if (savedPlayers.get(i).getName().equalsIgnoreCase(savedPlayers.get(f).getName()) && i != f) {
                                                    //Removing The Player
                                                    savedPlayers.remove(f);
                                                }

                                            }
                                        }
                                    }
                                }

                                //Printing the Names and Scores of the Players
                                for (int i = 0; i < savedPlayers.size(); i++) {

                                    //Flag Is False if There is No Duplicate
                                    flag = false;

                                    //Checking For Duplicate Names
                                    for (int k = 0; k < savedPlayers.size(); k++) {

                                        //If the Duplicate is Found After the Original
                                        if (k > i) {

                                            //If the Duplicate Player Has the Same Name
                                            if (savedPlayers.get(i).getName().equalsIgnoreCase(savedPlayers.get(k).getName())) {

                                                //Flag is True
                                                flag = true;
                                            }
                                        }
                                    }

                                    //Printing the Names and Scores of the Players
                                    for (int k = 0; k < savedPlayers.size(); k++) {

                                        //If the Flag is False
                                        if (flag == false) {

                                            //Printing Out the Table Using System.out.format()
                                            System.out.format("%-12s%9s%17s%18s%16s%n",
                                                    savedPlayers.get(i).getName().substring(0, Math.min(8, savedPlayers.get(i).getName().length())),
                                                    dF.format(savedPlayers.get(i).getWindowHunterScore()) + "ms",
                                                    dF.format(savedPlayers.get(i).getWindowChaserScore()) + "ms",
                                                    dF.format(savedPlayers.get(i).getWindowSurvivorScore()) + "ms",
                                                    dF.format(savedPlayers.get(i).calcOverallScore()) + "(#" + ranking + ")");

                                            //Increasing the Number Ranking For the Next Player on the List
                                            ranking++;

                                            //Setting the Flag to True So the Same Name Wont Be Printed Again
                                            flag = true;
                                        }
                                    }
                                }

                                //Clearing the Saved Players Array List
                                savedPlayers.clear();
                            }

                            //Closing the Scanner
                            scanFile.close();

                            //Catching the IOException and Printing the Error Message
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                    //If the User Chose to Erase All Previous Scores
                    case 6: {

                        //Opening Try Catch Statement
                        try {

                            //Clearing The Score File
                            pw = new PrintWriter(nf1);
                            pw.print("");

                            //Closing the PrintWriter
                            pw.close();

                            //Telling the User The Scores Were Deleted
                            System.out.println("\n-All Scores Deleted-");

                            //Catching the IOException and Printing the Error Message
                        } catch (IOException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                    //If the User Chose to End the Game
                    case 7: {

                        //Printing a Goodbye Message to the User
                        System.out.println("\nThanks For Playing!");
                    }
                    break;

                    //Error Trapping the User Using the Default Case in the Do-While Loop
                    default: {

                        //Printing Out an Error Message and Asking User to Try Again
                        System.out.println("\nSorry, " + menuChoice + " Is Not a Valid Option");
                        System.out.println("Please Enter a Number from 1-7");
                    }
                    break;
                }

                //While the User Doesn't End the Game
            } while (menuChoice != 7);

            //Catching the InterruptedException and Printing the Error Message
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
    *Method: windowHunter
    *Description: Excecutes the Window Hunter Minigame and Returns the Score
    *@param name (The Name of the User)
    *@return score (The Minigame Score the User Has Achieved)
     */
    public static int windowHunter(String name) {

        //Creating the Array of Jframes
        JFrame[] windows = new JFrame[64];

        //Creating the Decimal Formatter For the Score
        DecimalFormat dF = new DecimalFormat("###,##0");

        //Initializing the Variables Needed for the Game
        int score = 0;
        int windowCount = 0;

        //Starting the Try Catch For the InterruptedException
        try {

            //While the Number Of Windows Is Under the Window Array Limit
            for (int i = 0; i < windows.length; i++) {

                //Creating a New Window Titled "[CLOSE ME]"
                windows[i] = new JFrame("[CLOSE ME]");
                windows[i].setVisible(true);
                windows[i].setResizable(false);
                windows[i].setLocation((int) (300 + Math.random() * (600)), (int) (50 + Math.random() * (450)));
                windows[i].setSize(300, 300);
                windows[i].setAlwaysOnTop(true);

                //Delaying the Game for 600 Milliseconds 
                Thread.sleep(700);

                //Increasing the Score for Every Millisecond
                score += 700;

                //increasing the Window Count
                windowCount++;

                //If the Latest Window is Closed
                if (!windows[i].isShowing()) {

                    //Closing All the Previous Windows
                    for (int m = 0; m < windowCount; m++) {
                        windows[m].dispose();
                    }

                    //Ending the Game
                    break;
                }
            }

            //Catching the InterruptedException and Printing the Error Message
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        //Closing All the Windows If the Windows Exceeded the limit
        for (int m = 0; m < windowCount; m++) {
            windows[m].dispose();
        }

        //If the Windows Have Reached the Limit
        if (windowCount == windows.length) {

            //Printing Out a Loss Message to the User
            System.out.println("\n" + name + " Has Lost! :(");

            //Returning a Score of 0
            return 0;

           //If the User Has Managed to Close the Windows Before they Reach the Limit
        } else {

            //Printing Out a Win Message to the User
            System.out.println("\n" + name + " Has Won in " + dF.format(score) + " Milliseconds!");

            //Returning the Achieved Score
            return score;
        }
    }

    /*
    *Method: windowChaser
    *Description: Excecutes the Window Chaser Minigame and Returns the Score
    *@param name (The Name of the User)
    *@return score (The Minigame Score the User Has Achieved)
     */
    public static int windowChaser(String name) {

        //initializing the Variables Needed for the Game
        int score = 0;
        int mouseX;
        int mouseY;
        double distanceX;
        double distanceY;
        double distanceTotal;

        //Initializing the Decimal Formatter for the Score
        DecimalFormat dF = new DecimalFormat("###,##0");

        //Creating a New Window Titled "[CLOSE ME]"
        JFrame window = new JFrame("[CLOSE ME]");
        window.setSize(300, 300);
        window.setVisible(true);
        window.setAlwaysOnTop(true);
        window.setLocation(1000, 500);
        window.setResizable(false);

        //Starting the Try Catch For the InterruptedException
        try {

            //While the Window Is Not Closed
            while (window.isShowing()) {

                //Storing the Current X and Y Position of the Mouse
                mouseX = MouseInfo.getPointerInfo().getLocation().x;
                mouseY = MouseInfo.getPointerInfo().getLocation().y;

                //Getting the Distance Between the Window and the Cursor in the X and Y Direction
                distanceX = window.getLocation().x + 50 - mouseX;
                distanceY = window.getLocation().y + 50 - mouseY;

                //Creating the Distance Vector Between the Cursor and the Window
                distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

                //Normalizing the Distance Vector
                distanceX /= distanceTotal;
                distanceY /= distanceTotal;

                //Scaling the Distance Vector to 30
                distanceX *= 30;
                distanceY *= 30;

                //If the Cursor is Closer than 400 Pixels
                if (distanceTotal < 400) {

                    //Moving the Window Away From the Cursor By the Maginitude of the Distance Vector
                    window.setLocation(window.getLocation().x + (int) distanceX, window.getLocation().y + (int) distanceY);
                }

                //If The Window Reaches the Left of the Screen
                if (window.getLocation().x + 50 < 0) {

                    //Moving the Window Away From the Left Screen Border
                    window.setLocation(50, window.getLocation().y);
                }

                //If The Window Reaches the Right of the Screen
                if (window.getLocation().x + 50 > Toolkit.getDefaultToolkit().getScreenSize().width) {

                    //Moving the Window Away From the Right Screen Border
                    window.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - 150, window.getLocation().y);
                }

                //If The Window Reaches the Top of the Screen
                if (window.getLocation().y + 50 < 0) {

                    //Moving the Window Away From the Top Screen Border
                    window.setLocation(window.getLocation().x, 50);
                }

                //If The Window Reaches the Bottom of the Screen
                if (window.getLocation().y + 50 > Toolkit.getDefaultToolkit().getScreenSize().height) {

                    //Moving the Window Away From the Bottom Screen Border
                    window.setLocation(window.getLocation().x, Toolkit.getDefaultToolkit().getScreenSize().height - 100);
                }

                //Delaying the Game to Allow the Player to Reach the X Button
                Thread.sleep(50);

                //Increasing the Score for Every Millisecond
                score += 50;
            }

            //Catching the InterruptedException and Printing the Error Message
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        //Printing Out a Win Message to the User
        System.out.println("\n" + name + " Has Won in " + dF.format(score) + " Milliseconds!");

        //Returning the Score
        return score;
    }

    /*
    *Method: windowSurvivor
    *Description: Excecutes the Window Survivor Minigame and Returns the Score
    *@param name (The Name of the User)
    *@return score (The Minigame Score the User Has Achieved)
     */
    public static int windowSurvivor(String name) throws InterruptedException {

        //Initilizing the Variables Needed for the Game
        int score;
        int windowSpeed = 100;
        int totalWindowCount = 0;
        int activeWindows = 0;
        boolean collision = false;

        //Initializing the Decimal Formatter
        DecimalFormat dF = new DecimalFormat("###,##0");

        //Initializing the Array Lists for the Obstacles and Collision Detectors
        ArrayList<JFrame> obstacles = new ArrayList<>();
        ArrayList<CollisionDetector> colDetect = new ArrayList<>();

        //Creating the Player Window
        JFrame player = new JFrame("Player");
        player.setSize(100, 100);
        player.setVisible(true);
        player.setAlwaysOnTop(true);
        player.getContentPane().setBackground(new java.awt.Color(255, 0, 0));

        //Starting the Try Catch For the InterruptedException
        try {
            
            //While the Player Has Not Touched the Obstacle 
            while (collision == false) {

                //Setting the Player Window At the Cursor Location
                player.setLocation(MouseInfo.getPointerInfo().getLocation().x - 50, MouseInfo.getPointerInfo().getLocation().y - 50);

                //If the Number of Windows is a Multiple of the Window Speed
                if (totalWindowCount % windowSpeed == 0) {

                    //Creating a New Obstacle Window
                    obstacles.add(0, new JFrame());
                    obstacles.get(0).setVisible(true);
                    obstacles.get(0).setAlwaysOnTop(true);
                    obstacles.get(0).setResizable(false);
                    obstacles.get(0).setSize(200, 200);
                    
                    //Setting the Obstacle Window at a Random X Value Across the Top of the Screen
                    obstacles.get(0).setLocation((int) (Math.random() * Toolkit.getDefaultToolkit().getScreenSize().width), -100);

                    //Increasing the Active Windows Counter
                    activeWindows++;

                    //Gradually Decreasing the Interval Between Obstacles Until It Reaches 5 Milliseconds
                    if (windowSpeed > 5) {
                        windowSpeed -= 5;
                    }
                }

                //Checking Every Active Obstacle Window On the Screen
                for (int k = 0; k < activeWindows; k++) {

                    //Gradually Lowering Every Active Obstacle Window Down the Screen
                    obstacles.get(k).setLocation(obstacles.get(k).getLocation().x, obstacles.get(k).getLocation().y + 10);

                    //If the Obstacle Window Reaches the Bottom of the Screen
                    if (obstacles.get(k).getLocation().y > Toolkit.getDefaultToolkit().getScreenSize().height - 100) {

                        //Closing the Obstacle Window
                        obstacles.get(k).dispose();
                    }

                    //If the Collison Between Obstacle and Player is False
                    if (collision == false) {

                        //Creating A New Collison Detector Object With the Location Information Of the Obstacle and Player
                        colDetect.add(0, new CollisionDetector(obstacles.get(k).getLocation().x + 100, obstacles.get(k).getLocation().y + 100, player.getLocation().x + 50, player.getLocation().y + 50));

                        //Using the calcCollision Method to Determine Whether a Collision Has Occured
                        collision = colDetect.get(0).calcCollision();
                    }
                }

                //Delaying the Game for 10 Milliseconds 
                Thread.sleep(10);

                //Increasing the Total Window Counter
                totalWindowCount++;
                
                //Clearing the Collision Detectors
                colDetect.clear();
            }

            //Catching the InterruptedException and Printing the Error Message
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        //Deleting the Player Window
        player.dispose();

        //Deleting All the Obstacle Windows
        for (int k = 0; k < activeWindows; k++) {
            obstacles.get(k).dispose();
        }

        //Calculating Score
        score = 10 * totalWindowCount;

        //Printint Out a Win Message To the User
        System.out.println("\n" + name + " Has Survived For " + dF.format(score) + " Milliseconds!");

        //Returning the Score
        return score;
    }

    /*
    *Method: nameGetter
    *Description: Prompts the User for Their Name and Returns their Name
    *@return name (The User's Name)
     */
    public static String nameGetter() {

        //Declaring the Name Variable
        String name;

        //Intialzing the Scanner
        Scanner scanS = new Scanner(System.in);

        //Prompting the User for Their Name
        System.out.println("\nPlease Enter Your Name");
        System.out.print("Your Name: ");

        //Scanning and Storing the Name While Removing any Spaces
        name = scanS.nextLine().replaceAll("\s+", "");

        //Printing Out a Starting Game Message
        System.out.println("\n-Starting Game-");

        //Returing the Name
        return name;
    }

    /*
    *Method: instructions
    *Description: Simply Prints the Instructions for Each Minigame
     */
    public static void instructions() {

        //Printing Simple Instructions for Each Game
        System.out.println("\nGAME 1: WINDOW HUNTER");
        System.out.println("> Close the Newest Window Before Another Pops Up!");
        System.out.println("> Tip: Ignore Previous Windows and Focus On Closing the Latest One");

        System.out.println("\nGAME 2: WINDOW CHASER");
        System.out.println("> Chase the Window Across Your Screen and Try to Close It!");
        System.out.println("> Tip: The Window Runs Away From Your Cursor!");

        System.out.println("\nGAME 3: WINDOW SURVIVOR");
        System.out.println("> Navigate the Red Window Between the Falling Cascade of Obstacles!");
        System.out.println("> Tip: Avoid the White Windows At All Costs! ");

        System.out.println("\nBONUS TIP: Use a Mouse!");
    }
}
