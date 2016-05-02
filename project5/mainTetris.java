/*
 * File: Main file used to run the program - calls an instance of our GUI and Game-Board. The purpose of this file is to use Singleton Design Pattern efficiently.
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
 * 
 * Items not working (Also detailed in README file);
 * 1) Showing the Tetromino blocks on our GUI screen —> Even though we were
 * initially able to show these shapes on the GUI, we tried a new implementation 
 * (when we cleaned up our code) and with this, we were unable to make it work.
 * We were able to get the logic down to actually make the shapes and control them
 * as well, but showing them on the GUI just wasn’t working.
 * 
 * 2) Adding levels to the game so it goes fast as more lines are removed
 * by the player. 
 * 
 * 3) Adding the play buttons onto the GUI itself
 * 
 * 4) When wiping out multiple lines in a row, adding extra to the score
 * 
 */

public class mainTetris {
   public static void main(String[] args) 
   {
      //Call Tetris object 
      Tetris object = Tetris.getInstance();
   }
}