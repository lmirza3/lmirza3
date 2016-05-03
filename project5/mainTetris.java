/*
 * File: Main file to run our program with. Also used singleton Design 
 *       Pattern to privately call an instance of Tetris.
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

public class mainTetris {
   public static void main(String[] args) 
   {
      //Call Tetris instance 
      Tetris.getInstance();
   }
}