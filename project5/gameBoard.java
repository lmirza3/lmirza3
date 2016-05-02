import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

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
  /***************************************************************************************/
  public int mineFlag;
  public int secondsElapsed;
  public int gameCompletedFlag;
  /***********************************Game Grid Constructor********************************/
  public gameBoard(){
    
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
    {
      for(int j = 0; j < 10; ++j)
      {          
        squares[i][j] = new Tile(i, j, black);
        squares[i][j].setSize(20,20);
        this.add(squares[i][j]);
      }
    }
    
   Tetromino currTetromino; 
   currTetromino = new I();
      
      for (Coord c : currTetromino.getLocation()) {
        squares[c.x][c.y].setIcon(blue);
      }
  }
  /***************************************************************************************/
  
  /***************************************************************************************/
  
  /**************************************Tetris Tile Class***********************************/
  public class Tile extends JLabel 
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
  
  Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
