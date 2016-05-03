/*
 * File: Class file to make a timer output to the command line (since timer on GUI is not updating)
 * 
 * CS 342, Spring 2016
 * Project 5: Tetris
 * 
 * Group Members: Lubna Mirza and Aiwan Hazari
 * 
 * Program Description: 
 *              This program makes the game of Tetris. In this, the
 *              player has the option to move the blocks down with keyboard
 *              presses. The goal of the game is to wipe out as many lines
 *              as possible on the board and not fill it up (so that it touches
 *              the top of the board).
 */

import java.util.Timer;
import java.util.TimerTask;

public class testTimer
{
  public int sec;
  
  Timer myTimer = new Timer();
  
  TimerTask task = new TimerTask(){
    public void run()
    {
       sec++;
       System.out.println("Timer: " + sec);
    }
  };
  
  public int getTime()
  {
    return sec;
  }
  
  public void start()
  {
    sec = 0;
    myTimer.scheduleAtFixedRate(task, 1000, 1000);
  }  
  /*
  public static void main(String[] args)
  {
    testimer newTimer = new testimer();
    newTimer.start();
  }*/
}