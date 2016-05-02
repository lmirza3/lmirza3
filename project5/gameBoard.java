/*
 * File: Class file to initialize and control our game-board.
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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import java.awt.event.ActionListener;

import java.util.Random;
import java.lang.Math;

<<<<<<< HEAD
public class gameBoard extends JPanel{
  private Tile squares[][];
  private Container container;
  private GridLayout grid;
  private int linesCleared;
  public Tetromino currentPiece;
  /***************************************Button Icons************************************/
  private String names[] = {
    "bricks/blacksquare.jpg", "bricks/bluesquare.jpg" , "bricks/cyansquare.jpg",
    "bricks/greensquare.jpg", "bricks/magentasquare.jpg"
  };
  private Icon black = new ImageIcon( "bricks/blacksquare.jpg" );
  private Icon blue = new ImageIcon( "bricks/bluesquare.jpg" );
  private Icon cyan = new ImageIcon( "bricks/cyansquare.jpg" );
  private Icon green = new ImageIcon( "bricks/greensquare.jpg" );
  private Icon magenta = new ImageIcon( "bricks/magentasquare.jpg" );
  private Icon white = new ImageIcon( "bricks/whitesquare.jpg" );
  private Icon red = new ImageIcon( "bricks/redsquare.jpg" );
  public Icon orange = new ImageIcon( "bricks/orangesquare.jpg" );
  private Icon yellow = new ImageIcon( "bricks/yellowsquare.jpg" );
  private Icon gray = new ImageIcon( "bricks/graysquare.jpg" );
=======
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class gameBoard extends JPanel implements ActionListener
{
  public static JLabel buttons[][];
  public static int buttonFilled[][];   //keep track of which spots in the grid are filled with shapes
  private Container container;
  private GridLayout grid;
  /***************************************Button Icons************************************/
  public static Icon black = new ImageIcon( "bricks/blacksquare.jpg" );
  public static Icon blue = new ImageIcon( "bricks/bluesquare.jpg" );
  public static Icon cyan = new ImageIcon( "bricks/cyansquare.jpg" );
  public static Icon green = new ImageIcon( "bricks/greensquare.jpg" );
  public static Icon magenta = new ImageIcon( "bricks/magentasquare.jpg" );
  public static Icon white = new ImageIcon( "bricks/whitesquare.jpg" );
  public static Icon red = new ImageIcon( "bricks/redsquare.jpg" );
  public static Icon orange = new ImageIcon( "bricks/orangesquare.jpg" );
  public static Icon yellow = new ImageIcon( "bricks/yellowsquare.jpg" );
  public static Icon gray = new ImageIcon( "bricks/graysquare.jpg" );
>>>>>>> origin/master
  /***************************************************************************************/
  public static int scoreFlag;
  public static int secondsElapsed;
  public static int gameCompletedFlag;
  public static boolean gameStarted;
  public static boolean gamePause;
  public static boolean isFalling;
  
  public static int coord[][];
  
 // public static box shape = new box();
 // public static JLabel[][] curBlock;
  
  private static int height, width;
  protected static int linesWiped;
  
  private static Shapes curBlock;
  private static Shapes.Tetrominoes gameboard[];
  
  public static int curx, cury;   
  /***********************************Game Grid Constructor********************************/
  public gameBoard(Tetris p)
  {
    setFocusable(true);
    height = 400;
    width = 200;
    
<<<<<<< HEAD
    this.setBackground(Color.GRAY);
    this.setPreferredSize(new Dimension(200,400));
    grid = new GridLayout(20,10,1,1);
    this.setLayout(grid);
    
    squares = new Tile[20][10];
    this.linesCleared = 0;
    mineFlag = 10;
    secondsElapsed = 0;
    gameCompletedFlag = 0;
    
    for(int i = 0; i < 20; ++i)
=======
   // this.setBackground(Color.WHITE);
    //this.setPreferredSize(new Dimension(200,400));
    
    //initialize new piece
    curBlock = new Shapes();
    
    /*for(int i = 0; i < 10; ++i)
>>>>>>> origin/master
    {
      for(int j = 0; j < 10; ++j)
      {          
<<<<<<< HEAD
        squares[i][j] = new Tile(i, j, black);
        squares[i][j].setSize(20,20);
        this.add(squares[i][j]);
=======
        buttons[i][j] = new JLabel();
        buttonFilled[i][j] = 0;    //no boxes are filled   0-empty, 1-full
        this.add(buttons[i][j]);
>>>>>>> origin/master
      }
    }*/
    
    gameboard = new Shapes.Tetrominoes[height * width];
    addKeyListener(new TAdapter());
    
    newBoard();
  }
 
  public static void newBoard()
  {
    //clear the board before starting
    for(int i = 0; i < height*width; i++)
    {
      gameboard[i] = Shapes.Tetrominoes.Blank;
    }
    
<<<<<<< HEAD
   Tetromino currTetromino; 
   currTetromino = new I();
      
      for (Coord c : currTetromino.getLocation()) {
        squares[c.x][c.y].setIcon(blue);
      }
=======
>>>>>>> origin/master
  }
  
  public static void start()
  {
    if(gamePause == true)
      return;
    
    //else restart game...
    linesWiped = 0;
    scoreFlag = 0;
    gameStarted = true;
    secondsElapsed = 0;
    gameCompletedFlag = 0;
    isFalling = false;
    gamePause = false;
    
    //get a new board
    newBoard();
    
    addRandPiece();
  }
  
  public static void addRandPiece()
  {
    curBlock.getRandShape();
    //add this new piece to the middle of the board
    curx = width/2 + 1;
    cury = height-1 + curBlock.minY();
    
    //if we cant move down one - then reset everything
    if (nextMoveAllowed(curx, cury, curBlock) == false) 
    {
            curBlock.makeShape(Shapes.Tetrominoes.Blank);
            gameStarted = false;
            gamePause = false;
            isFalling = false;
            
            //gameCompletedFlag = 1;   //actionperformed in Tetris will take care of timer and score
            //JOptionPane.showMessageDialog(null,"GAME OVER!");
        }
    }

    
    //see if there is a shape in this x,y coordinate
    public static Shapes.Tetrominoes shapeAt(int x, int y) 
    { 
      return gameboard[(y * width) + x]; 
    }
    
    public static boolean nextMoveAllowed(int updatex, int updatey, Shapes updateBlock)
    {
      //go through each square place to see if we can move to the next spot
        for (int i = 0; i < 4; i++) 
        {
            int x = updatex + updateBlock.getx(i);
            int y = updatey - updateBlock.gety(i);
            //if there is another shape in place
            if (shapeAt(x, y) != Shapes.Tetrominoes.Blank)
                return false;
            
            //check that we are not out of bounds
            if ((x < 0) || (x >= width) || (y < 0) || (y >= height))
                return false;
        }
        
        //else...we are able to move our shape down by 1 
        curBlock = updateBlock;
        curx = updatex;
        cury = updatey;
        return true;
    }
  
    //while game is paused & unpaused
    private void pause()
    {
        if (gameStarted == true)
            return;

        gamePause = !gamePause;
        if (gamePause == true) {
            Tetris.timer.stop();
            JOptionPane.showMessageDialog(null, "GAME PAUSED!");
        } 
        else //Game unpaused
        {
            Tetris.timer.start();
            gameStarted = true;
        }
    }
    
    private void drawSquare(Graphics g, int x, int y, Shapes.Tetrominoes shape)
    {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color color = colors[shape.ordinal()];

        g.setColor(color);
        
        int w = (int)(getSize().getWidth() / width);
        int h = (int)(getSize().getHeight() / height);
        
        g.fillRect(x + 1, y + 1, w - 2, h - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + h - 1, x, y);
        g.drawLine(x, y, x + w - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + h - 1,
                         x + w - 1, y + h - 1);
        g.drawLine(x + w - 1, y + h - 1,
                         x + w - 1, y + 1);
    }
    
    
  /*   public static int squareWidth() 
    { 
      int w = (int)(getSize().getWidth() / width);
      return w;
    }
    
    public static int squareHeight() 
    { 
      int h = (int)(getSize().getHeight() / height);
      return h; 
    }
  */  
    public void paint(Graphics g)
    { 
        super.paint(g);

        Dimension size = getSize();
        int w = (int)(getSize().getWidth() / width);
        int h = (int)(getSize().getHeight() / height);
        int boardTop = (int) size.getHeight() - height * h;


        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                Shapes.Tetrominoes shape = shapeAt(j, height - i - 1);
                if (shape != Shapes.Tetrominoes.Blank)
                {
                    drawSquare(g, 0 + j * w,
                               boardTop + i * h, shape);
                }
            }
        }

        if (curBlock.newShape() != Shapes.Tetrominoes.Blank) {
            for (int i = 0; i < 4; ++i) {
                int x = curx + curBlock.getx(i);
                int y = cury - curBlock.gety(i);
                drawSquare(g, 0 + x * w,
                           boardTop + (height - y - 1) * h,
                           curBlock.newShape());
            }
        }
    }
    
  /* public static void makeShape()
   {
  for(int i = 0; i < 2; i++)
    {
      for(int j = 0; j < 2; j++)
    {
        if(buttonFilled[i][j] == true)
        {
          return;
        }
        buttons[i][j].setIcon(black);
        buttons[i][j].setSize(30,30);
    }
    }
  System.out.println("Added square");
   //repaint();
   }
  
  //test out making a new box
  public static void makeNewBox()
  {
    int x,y;
    x = coord[0][0];
    y = coord[0][1];
    //System.out.println(x + " " + y + " " + buttonFilled[x][y]);
    buttons[x][y].setIcon(black);
    buttonFilled[x][y] = 1;
    
    x = coord[1][0];
    y = coord[1][1];
    buttons[x][y].setIcon(black);
    buttonFilled[x][y] = 1;
    
    x = coord[2][0];
    y = coord[2][1];
    buttons[x][y].setIcon(black);
    buttonFilled[x][y] = 1;
    
    x = coord[3][0];
    y = coord[3][1];
    buttons[x][y].setIcon(black); 
    buttonFilled[x][y] = 1;
  }
 
  public static void makeNewLineShape()
  {
    buttons[1][3].setIcon(green);
    buttonFilled[1][3] = 1;
    buttons[1][4].setIcon(green);
    buttonFilled[1][4] = 1;
    buttons[1][5].setIcon(green);
    buttonFilled[1][5] = 1;
    buttons[1][6].setIcon(green);
    buttonFilled[1][6] = 1;
  }
  
    //pick a random shape to drop
  public static int dropRandShape()
  {
    int maxGetPts = 0;
    while(!isFalling)
    {
    Random r = new Random();
    int shapeVal = Math.abs(r.nextInt()) % 7 + 1;
    //call function accordingly
    switch(shapeVal)
    {
      case 1:
        //make a box
        System.out.println("1");  
        makeNewBox();
        isFalling = true;
        maxGetPts = 3;   //box coordinates end off on the 3rd (0-3) place in coordinates array
        return maxGetPts;
        
      case 2:
        //make a line
        System.out.println("2");
        //makeNewLine();
        //isFalling = true;
        break;
        
      case 3:
        System.out.println("3");
        break;
        
      case 4:
        System.out.println("4");
        break;
        
      case 5:
        System.out.println("5");
        break;
        
      case 6:
        System.out.println("6");
        break;
        
      case 7:
        System.out.println("7");
        break;
    }
    }
    return maxGetPts;
  }
  
 /* public static void blockFalling(int maxCoord, Icon temp)
  {
    //we we hit another block, stop falling
    boolean noTouch = true;
    int downLines = 0;
    int tempMax = maxCoord;
    while((noTouch == true) && (downLines < 18))
    {
      downLines++;   //we are at a new line now
      System.out.println("Down: " + downLines);
      //check if next place to move is empty first
      if(buttonFilled[coord[tempMax][0] + downLines][coord[tempMax][1]] == 1)
      {
        noTouch = false;
        System.out.println("*Stopped at: (" + coord[tempMax][0] + ", " + coord[tempMax + downLines][1] + ")");
        return;
      }
      //check if next place to move is empty first
      if(buttonFilled[coord[tempMax-1][0] + downLines][coord[tempMax-1][1]] == 1)
      {
        noTouch = false;
        System.out.println("**Stopped at: (" + coord[tempMax][0] + ", " + coord[tempMax-1 + downLines][1] + ")");
        return;
      }*/
      /*//check if next place to move is empty first
      if(buttonFilled[coord[tempMax-2 + downLines][0]][coord[tempMax-2 + downLines][1]] == 1)
      {
        noTouch = false;
         System.out.println("***Stopped at: (" + coord[tempMax-2 + downLines][0] + ", " + coord[tempMax-2 + downLines][1] + ")");
        return;
      }
      //check if next place to move is empty first
      if(buttonFilled[coord[tempMax-3 + downLines][0]][coord[tempMax-3 + downLines][1]] == 1)
      {
        noTouch = false;
        System.out.println("****Stopped at: (" + coord[tempMax-4 + downLines][0] + ", " + coord[tempMax-4 + downLines][1] + ")");
        return;
      }*/
  /*
      //set original coordinates to false-filled so we can move it
      buttons[coord[tempMax][0] + downLines-1][coord[tempMax][1]].setIcon(white);
      buttonFilled[coord[tempMax][0] + downLines-1][coord[tempMax][1]] = 0;
      
      buttons[coord[tempMax-1][0]+ downLines-1][coord[tempMax-1][1]].setIcon(white);
      buttonFilled[coord[tempMax-1][0] + downLines-1][coord[tempMax-1][1]] = 0;
      
      buttons[coord[tempMax-2][0]+ downLines-1][coord[tempMax-2][1]].setIcon(white);
      buttonFilled[coord[tempMax-2][0] + downLines-1][coord[tempMax-2][1]] = 0;
      
      buttons[coord[tempMax-3][0]+ downLines-1][coord[tempMax-3][1]].setIcon(white);
      buttonFilled[coord[tempMax-3][0] + downLines-1][coord[tempMax-3][1]] = 0;
      
      //put all 4 coordinates down continuously 
      buttons[coord[tempMax][0] + downLines][coord[tempMax][1]].setIcon(temp);
      buttonFilled[coord[tempMax][0] + downLines][coord[tempMax][1]] = 1;
      
      buttons[coord[tempMax-1][0] + downLines][coord[tempMax-1][1]].setIcon(temp);
      buttonFilled[coord[tempMax-1][0] + downLines][coord[tempMax-1][1]] = 1;
      
      buttons[coord[tempMax-2][0] + downLines][coord[tempMax-2][1]].setIcon(temp);
      buttonFilled[coord[tempMax-2][0] + downLines][coord[tempMax-2][1]] = 1;
      
      buttons[coord[tempMax-3 ][0] + downLines][coord[tempMax-3][1]].setIcon(temp);
      buttonFilled[coord[tempMax-3][0] + downLines][coord[tempMax-3][1]] = 1;
      
    }
  }
  
 
  //check if theres a row we can wipe out
  public static int wipeRow()
  {
    int numRowsWiped = 0;
    //start checking from the bottom row
    for(int x = 19; x > -1; x--)
    {
      if((buttonFilled[x][0] == 0) && (buttonFilled[x][1] == 0) && (buttonFilled[x][2] == 0) && (buttonFilled[x][3] == 0) && (buttonFilled[x][4] == 0) && (buttonFilled[x][5] == 0) && (buttonFilled[x][6] == 0) && (buttonFilled[x][7] == 0) && (buttonFilled[x][8] == 0) && (buttonFilled[x][9] == 0))
      {
        //drop all blocks down -- 
        numRowsWiped++;
      }
    }
    
    return numRowsWiped;
  }*/
  
  class TAdapter extends KeyAdapter {
         public void keyPressed(KeyEvent e) {

             if (!gameStarted || curBlock.newShape() == Shapes.Tetrominoes.Blank) {  
                 return;
             }

             int keycode = e.getKeyCode();

             //user pressed p
             if (keycode == 'p' || keycode == 'P') 
             {
               gamePause = true;
               //pause();
                 return;
             }

             //dont do anything if game is paused
             if (gamePause)
                 return;
             boolean moved = false;

             switch (keycode) {
             case KeyEvent.VK_LEFT:
                 moved = nextMoveAllowed(curx - 1, cury, curBlock);
                 if(moved)
                   System.out.println("Move Left");
                 break;
             case KeyEvent.VK_RIGHT:
                 moved = nextMoveAllowed(curx + 1, cury, curBlock);
                 if(moved)
                   System.out.println("Move Right");
                 break;
             case KeyEvent.VK_DOWN:
               moved = nextMoveAllowed(curx, cury, curBlock.rotateRight());
               if(moved)
                 System.out.println("Move DOWN");
                 break;
             case KeyEvent.VK_UP:
                 moved = nextMoveAllowed(curx, cury, curBlock.rotateLeft());
                 if(moved)
                   System.out.println("Move UP");
                 break;
             case KeyEvent.VK_SPACE:
                 //dropDown();
                 if(moved)  
                   System.out.println("Move SPACE/Drop down");
                 break;
             case 'd':
                 //oneLineDown();
                 if(moved)
                  System.out.println("Move 1 line down");
                 break;
             case 'D':
                 //oneLineDown();
                 if(moved)
                   System.out.println("Move 1 line down");
                 break;
             }

         }
     }
  /***************************************************************************************/
  
  /***************************************************************************************/
  
<<<<<<< HEAD
  /**************************************Tetris Tile Class***********************************/
  public class Tile extends JLabel 
=======
  /**************************************Tetris Tile Class**********************************
  private class Tile extends JButton 
>>>>>>> origin/master
  {
    //x and y positions
    private int row;
    private int col;
    //filled or unfilled
    private Icon icon;
    //if true, then tetromino at tile
    private boolean tileStatus;
    
    public Tile(int r, int c, Icon i)
    {
      super(i);
      this.row = r;
      this.col = c;
      this.tileStatus = false;
    }
    
    //toggle whether there is a tetromino piece at said tile or not
    public void toggleTile(boolean state)
    {
      this.tileStatus = state;
    } 
    
    public boolean isTetromino()
    {
      return this.tileStatus;
    }  
    
    public int getRow()
    {
      return this.row;
    }  
    public int getCol()
    {
      return this.col;
    }  
    
  }
  /***************************************************************************************/  
}

class Coord {
  
  public int x;
  public int y;
  
<<<<<<< HEAD
  Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
=======
  private void removeFullLines()
    {
        int numFullLines = 0;

        for (int i = height - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < width; ++j) {
                if (shapeAt(j, i) == Shapes.Tetrominoes.Blank) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < height - 1; ++k) {
                    for (int j = 0; j < width; ++j)
                         gameboard[(k * width) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (numFullLines > 0) {
            linesWiped += numFullLines;

            isFalling = true;
            curBlock.makeShape(Shapes.Tetrominoes.Blank);
            repaint();
        }
     }
  
  private void pieceDrop()
    {
        for (int i = 0; i < 4; ++i) {
            int x = curx + curBlock.getx(i);
            int y = cury - curBlock.gety(i);
            gameboard[(y * width) + x] = curBlock.newShape();
        }

        removeFullLines();

        if (isFalling == false)
            addRandPiece();
    }
  
  private void downLine()
    {
        if (nextMoveAllowed(curx, cury-1, curBlock) == false)
            pieceDrop();
    }
  
  public void actionPerformed(ActionEvent e) 
  {
    //every increment either adds a new piece to the board or drops a piece down by 1
      if(isFalling == false)
      {
        addRandPiece();
      }
      else
      {
        downLine();
      }
   }

}
>>>>>>> origin/master
