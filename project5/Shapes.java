/*
 * File: Class file to make our Shapes class to intialize, keep track of, and control each shape made.
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

import java.util.Random;
import java.lang.Math;

public class Shapes implements ShapesInterface
{
  //each shape will have max 4 coordinates
    int rowsMax = 4;
    int colsMax = 2;

    enum Tetrominoes {  Blank, Square,Line,T,Z ,S,L ,MirrorL};

    public Tetrominoes curShape;
    private int coords[][];
    private int[][][] coordAll;


    public Shapes() 
    {

        coords = new int[rowsMax][colsMax];
        //initialize with all blank originally
        makeShape(Tetrominoes.Blank);

    }

    //initialize the shapes with their perspective coordinates & fill in our final coordinate table
    @Override
    public void makeShape(Tetrominoes shape) 
    {
         coordAll = new int[][][] {
           {{ 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 }},
           {{ 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 }},
           {{ 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 }},
           {{ -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 }},
           {{ 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 }},
           {{ 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 }},
           {{ -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 }},
           {{ 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 }} };

         
         int makeShape = 0;
        for (int i = 0; i < rowsMax ; i++) 
        {
            for (int j = 0; j < colsMax; ++j) 
            {
                coords[i][j] = coordAll[makeShape][i][j];   //each shape has 4 coordinates
            }
            makeShape++;   //next shape 
        }
        curShape = shape;
    }

    //set new coordinates for y position
    @Override
    public void makeNewX(int ind, int x) 
    { 
      coords[ind][0] = x; 
    }
    
    //set new coordinates for y position
    @Override
    public void makeNewY(int ind, int y) 
    { 
      coords[ind][1] = y; 
    }
    
    //get x position from coordinate table
    @Override
    public int getx(int ind) 
    { 
      return coords[ind][0]; 
    }
    
    //get y position from coordinate table
    @Override
    public int gety(int ind) 
    { 
      return coords[ind][1]; 
    }
    
    // return the current shape we are using
    @Override
    public Tetrominoes newShape()  
    { 
      return curShape; 
    }

    @Override
    public Shapes rotateLeft() 
    {
      //dont need to rotate the square
        if (curShape == Tetrominoes.Square)
            return this;

        Shapes rotated = new Shapes();
        rotated.curShape = curShape;

        for (int i = 0; i < 4; ++i) 
        {
            rotated.makeNewX(i, gety(i));
            rotated.makeNewY(i, -(getx(i)));
        }
        return rotated;
    }

    @Override
    public Shapes rotateRight()
    {
      //dont need to rotate the square
        if (curShape == Tetrominoes.Square)
            return this;

        Shapes rotated = new Shapes();
        rotated.curShape = curShape;

        for (int i = 0; i < 4; ++i) 
        {
            rotated.makeNewX(i, -(gety(i)));
            rotated.makeNewY(i, getx(i));
        }
        return rotated;
    }
    
    //find the highest x coordinate with curr shape
    @Override
    public int minx()
    {
      int highestx = coords[0][0];
      int min = highestx;
      for (int i=0; i < 4; i++) 
      {
        if(min > coords[i][0])   //if curr coordnate is smaller than original
        {
          min = coords[i][0];
        }
      }
      return min;
    }

    //find the highest y coordinate with curr shape
    @Override
    public int minY() 
    {
      int highesty = coords[0][1];
      int min = highesty;
      for (int i=0; i < 4; i++) 
      {
        if(min > coords[i][1])
          min = coords[i][1];
      }
      return min;
    }
    
    @Override
       public void getRandShape()
    {
        Random randomGenerator = new Random();
        int shapeVal = Math.abs(randomGenerator.nextInt()) % 7 + 1; 
        Tetrominoes[] val = Tetrominoes.values(); 
        
        //pick a random enum value to pick random shape
        makeShape(val[shapeVal]);
        displayShape("");
    }
       
    @Override
    public void displayShape(String piece)
    {
      System.out.println("Added a new " + piece + " piece ");
    }

       
}