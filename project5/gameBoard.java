import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class gameBoard extends JPanel{
  private Tile buttons[][];
  private Container container;
  private GridLayout grid;
  private int linesCleared;
  /***************************************Button Icons************************************/
  private Icon black = new ImageIcon( "bricks/blacksquare.jpg" );
  private Icon blue = new ImageIcon( "bricks/bluesquare.jpg" );
  private Icon cyan = new ImageIcon( "bricks/cyansquare.jpg" );
  private Icon green = new ImageIcon( "bricks/greensquare.jpg" );
  private Icon magenta = new ImageIcon( "bricks/magentasquare.jpg" );
  private Icon white = new ImageIcon( "bricks/whitesquare.jpg" );
  private Icon red = new ImageIcon( "bricks/redsquare.jpg" );
  private Icon orange = new ImageIcon( "bricks/orangesquare.jpg" );
  private Icon yellow = new ImageIcon( "bricks/yellowsquare.jpg" );
  private Icon gray = new ImageIcon( "bricks/graysquare.jpg" );
  /***************************************************************************************/
  public int mineFlag;
  public int secondsElapsed;
  public int gameCompletedFlag;
  /***********************************Game Grid Constructor********************************/
  public gameBoard(){
    
    this.setBackground(Color.RED);
    this.setPreferredSize(new Dimension(200,400));
    grid = new GridLayout(20,20,0,0);
    this.setLayout(grid);
    
    buttons = new Tile[10][20];
    this.linesCleared = 0;
    mineFlag = 10;
    secondsElapsed = 0;
    gameCompletedFlag = 0;
    
    for(int i = 0; i < 10; ++i)
    {
      for(int j = 0; j < 20; ++j)
      {          
        buttons[i][j] = new Tile(i, j, black);
        buttons[i][j].setSize(16,16);
        this.add(buttons[i][j]);
      }
    }
  }
  /***************************************************************************************/
  
  /***************************************************************************************/
  
  /**************************************Tetris Tile Class***********************************/
  private class Tile extends JButton 
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
      super("", i);
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
