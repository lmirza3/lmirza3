/*
 * File: Class file to make our S-Shaped piece.
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

import java.awt.*;

public class SBlock implements Tetromino {
 private int rotation;
 private int[] pOne = new int[2];
 private int[] pTwo = new int[2];
 private int[] pThree = new int[2];
 private int[] pFour = new int[2];

 private Block one = new Block(Color.GREEN);
 private Block two = new Block(Color.GREEN);
 private Block three = new Block(Color.GREEN);
 private Block four = new Block(Color.GREEN);

 public SBlock(Block[][] grid) {
  grid[3][0] = one;
  grid[4][0] = two;
  grid[4][0] = three;
  grid[5][0] = four;

  pOne[0] = 3;
  pOne[1] = 1;
  pTwo[0] = 4;
  pTwo[1] = 1;
  pThree[0] = 4;
  pThree[1] = 0;
  pFour[0] = 5;
  pFour[1] = 0;

  rotation = 0;

 }

 public void moveLeft(Block[][] grid) {

  int[][] arr = {
    {pOne[0]-1,pOne[1]}, {pTwo[0]-1,pTwo[1]}, 
    {pThree[0]-1,pThree[1]}, {pFour[0]-1,pFour[1]},
  };
  if (!checkBlocks(grid, arr)) {
   return;
  }
  
  grid[pOne[0]][pOne[1]] = new EmptyBlock(Color.BLACK);
  grid[pTwo[0]][pTwo[1]] = new EmptyBlock(Color.BLACK);
  grid[pThree[0]][pThree[1]] = new EmptyBlock(Color.BLACK);
  grid[pFour[0]][pFour[1]] = new EmptyBlock(Color.BLACK);

  grid[pOne[0] - 1][pOne[1]] = one;
  grid[pTwo[0] - 1][pTwo[1]] = two;
  grid[pThree[0] - 1][pThree[1]] = three;
  grid[pFour[0] - 1][pFour[1]] = four;

  pOne[0] -= 1;
  pTwo[0] -= 1;
  pThree[0] -= 1;
  pFour[0] -= 1;
 }

 public void moveRight(Block[][] grid) {
  
  int[][] arr = {
    {pOne[0]+1,pOne[1]}, {pTwo[0]+1,pTwo[1]}, 
    {pThree[0]+1,pThree[1]}, {pFour[0]+1,pFour[1]},
  };
  if (!checkBlocks(grid, arr)) {
   return;
  }
  
  grid[pOne[0]][pOne[1]] = new EmptyBlock(Color.BLACK);
  grid[pTwo[0]][pTwo[1]] = new EmptyBlock(Color.BLACK);
  grid[pThree[0]][pThree[1]] = new EmptyBlock(Color.BLACK);
  grid[pFour[0]][pFour[1]] = new EmptyBlock(Color.BLACK);

  grid[pOne[0] + 1][pOne[1]] = one;
  grid[pTwo[0] + 1][pTwo[1]] = two;
  grid[pThree[0] + 1][pThree[1]] = three;
  grid[pFour[0] + 1][pFour[1]] = four;

  pOne[0] += 1;
  pTwo[0] += 1;
  pThree[0] += 1;
  pFour[0] += 1;
 }

 public boolean moveDown(Block[][] grid) {

  int[][] arr = {
    {pOne[0],pOne[1]+1}, {pTwo[0],pTwo[1]+1}, 
    {pThree[0],pThree[1]+1}, {pFour[0],pFour[1]+1},
  };
  if (!checkBlocks(grid, arr)) {
   return false;
  }

  grid[pOne[0]][pOne[1]] = new EmptyBlock(Color.BLACK);
  grid[pTwo[0]][pTwo[1]] = new EmptyBlock(Color.BLACK);
  grid[pThree[0]][pThree[1]] = new EmptyBlock(Color.BLACK);
  grid[pFour[0]][pFour[1]] = new EmptyBlock(Color.BLACK);

  grid[pOne[0]][pOne[1] + 1] = one;
  grid[pTwo[0]][pTwo[1] + 1] = two;
  grid[pThree[0]][pThree[1] + 1] = three;
  grid[pFour[0]][pFour[1] + 1] = four;

  pOne[1] += 1;
  pTwo[1] += 1;
  pThree[1] += 1;
  pFour[1] += 1;

  return true;
 }

 public void hardDrop(Block[][] grid) {
  while (moveDown(grid)) {
   moveDown(grid);
  }
 }

 public void rotate(Block[][] grid) {

  if (rotation == 0) {
   int[][] arr = {
     {pOne[0]+1,pOne[1]-1}, {pTwo[0],pTwo[1]}, 
     {pThree[0]+1,pThree[1]+1}, {pFour[0],pFour[1]+2},
   };
   if (!checkBlocks(grid, arr)) {
    return;
   }

   grid[pOne[0]][pOne[1]] = new EmptyBlock(Color.BLACK);
   grid[pTwo[0]][pTwo[1]] = new EmptyBlock(Color.BLACK);
   grid[pThree[0]][pThree[1]] = new EmptyBlock(Color.BLACK);
   grid[pFour[0]][pFour[1]] = new EmptyBlock(Color.BLACK);

   grid[pOne[0]+1][pOne[1]-1] = one;
   grid[pTwo[0]][pTwo[1]] = two;
   grid[pThree[0]+1][pThree[1]+1] = three;
   grid[pFour[0]][pFour[1]+2] = four;

   pOne[0] += 1;
   pOne[1] -= 1;
   pTwo[0] += 0;
   pTwo[1] += 0;
   pThree[0] += 1;
   pThree[1] += 1;
   pFour[0] -= 0;
   pFour[1] += 2;

   rotation = 1;
  }
  else if (rotation == 1) {
   int[][] arr = {
     {pOne[0]+1,pOne[1]+1}, {pTwo[0],pTwo[1]}, 
     {pThree[0]-1,pThree[1]+1}, {pFour[0]-2,pFour[1]},
   };
   if (!checkBlocks(grid, arr)) {
    return;
   }

   grid[pOne[0]][pOne[1]] = new EmptyBlock(Color.BLACK);
   grid[pTwo[0]][pTwo[1]] = new EmptyBlock(Color.BLACK);
   grid[pThree[0]][pThree[1]] = new EmptyBlock(Color.BLACK);
   grid[pFour[0]][pFour[1]] = new EmptyBlock(Color.BLACK);

   grid[pOne[0]+1][pOne[1]+1] = one;
   grid[pTwo[0]][pTwo[1]] = two;
   grid[pThree[0]-1][pThree[1]+1] = three;
   grid[pFour[0]-2][pFour[1]] = four;

   pOne[0] += 1;
   pOne[1] += 1;
   pTwo[0] += 0;
   pTwo[1] += 0;
   pThree[0] -= 1;
   pThree[1] += 1;
   pFour[0] -= 2;
   pFour[1] -= 0;

   rotation = 2;
  }
  else if (rotation == 2) {
   int[][] arr = {
     {pOne[0]-1,pOne[1]+1}, {pTwo[0],pTwo[1]}, 
     {pThree[0]-1,pThree[1]-1}, {pFour[0],pFour[1]-2},
   };
   if (!checkBlocks(grid, arr)) {
    return;
   }

   grid[pOne[0]][pOne[1]] = new EmptyBlock(Color.BLACK);
   grid[pTwo[0]][pTwo[1]] = new EmptyBlock(Color.BLACK);
   grid[pThree[0]][pThree[1]] = new EmptyBlock(Color.BLACK);
   grid[pFour[0]][pFour[1]] = new EmptyBlock(Color.BLACK);

   grid[pOne[0]-1][pOne[1]+1] = one;
   grid[pTwo[0]][pTwo[1]] = two;
   grid[pThree[0]-1][pThree[1]-1] = three;
   grid[pFour[0]][pFour[1]-2] = four;

   pOne[0] -= 1;
   pOne[1] += 1;
   pTwo[0] -= 0;
   pTwo[1] += 0;
   pThree[0] -= 1;
   pThree[1] -= 1;
   pFour[0] += 0;
   pFour[1] -= 2;

   rotation = 3;
  }
  else if (rotation == 3) {
   int[][] arr = {
     {pOne[0]-1,pOne[1]-1}, {pTwo[0],pTwo[1]}, 
     {pThree[0]+1,pThree[1]-1}, {pFour[0]+2,pFour[1]},
   };
   if (!checkBlocks(grid, arr)) {
    return;
   }

   grid[pOne[0]][pOne[1]] = new EmptyBlock(Color.BLACK);
   grid[pTwo[0]][pTwo[1]] = new EmptyBlock(Color.BLACK);
   grid[pThree[0]][pThree[1]] = new EmptyBlock(Color.BLACK);
   grid[pFour[0]][pFour[1]] = new EmptyBlock(Color.BLACK);

   grid[pOne[0]-1][pOne[1]-1] = one;
   grid[pTwo[0]][pTwo[1]] = two;
   grid[pThree[0]+1][pThree[1]-1] = three;
   grid[pFour[0]+2][pFour[1]] = four;

   pOne[0] -= 1;
   pOne[1] -= 1;
   pTwo[0] += 0;
   pTwo[1] -= 0;
   pThree[0] += 1;
   pThree[1] -= 1;
   pFour[0] += 2;
   pFour[1] += 0;

   rotation = 0;
  }
 }

 private boolean checkBlocks(Block[][] grid, int[][] arr) {
  for (int x = 0; x < arr.length; x++) {
   if (arr[x][0] >= grid.length ||
     arr[x][0] < 0 ||
     arr[x][1] >= grid[x].length ||
     arr[x][1] < 0 ||
     !(grid[arr[x][0]][arr[x][1]].isEmpty() || 
       grid[arr[x][0]][arr[x][1]].equals(one) || 
       grid[arr[x][0]][arr[x][1]].equals(two) || 
       grid[arr[x][0]][arr[x][1]].equals(three) || 
       grid[arr[x][0]][arr[x][1]].equals(four))) {
    return false;
   }
  }

  return true;
 }
}