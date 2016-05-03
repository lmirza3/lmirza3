/*
 * File: Class file to make our TetrominoFactory class. Adds in a random shape accordingly.
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

import java.util.Random;
//Tetromino factory class
public class TetrominoFactory {
  //One method in this class that will return a random block every time.
  public Tetromino randomPiece(Block[][] grid) {
    Random rand = new Random();
    int n = rand.nextInt(7);
    
    if (n == 0) 
      return new IBlock(grid);
    else if (n == 1) 
      return new JBlock(grid);
    else if (n == 2) 
      return new LBlock(grid);
    else if (n == 3)
      return new OBlock(grid);
    else if (n == 4)
      return new SBlock(grid);
    else if (n == 5)
      return new ZBlock(grid);
    else
      return new TBlock(grid);
  }
}