import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class gameBoard extends JPanel{
  private MSButton buttons[][];
  private Container container;
  private GridLayout grid;
  private int numCleared;
  private int markedM;
  private File fileName = new File("topTen.txt");  //This file must be in project folder for topTen functionality to work.
  private String[] topTen = new String[11];
  /***************************************Button Icons************************************/
  private Icon background = new ImageIcon( "bricks/blacksquare.jpg" );
  private Icon button1 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_1.gif" );
  private Icon button2 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_2.gif" );
  private Icon button3 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_3.gif" );
  private Icon button4 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_4.gif" );
  private Icon button5 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_5.gif" );
  private Icon button6 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_6.gif" );
  private Icon button7 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_7.gif" );
  private Icon button8 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_8.gif" );
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
    
    buttons = new MSButton[10][20];
    this.numCleared = 0;
    this.markedM = 0;
    fileName = new File("topTen.txt");
    topTen = new String[11];
    mineFlag = 10;
    secondsElapsed = 0;
    gameCompletedFlag = 0;
    
    for(int i = 0; i < 10; ++i)
    {
      for(int j = 0; j < 20; ++j)
      {   
        buttons[i][j] = new MSButton(i, j, "normal", background);
        buttons[i][j].setSize(16,16);
        this.add(buttons[i][j]);
      }
    }
    
    int randRow;
    int randCol;
    for(int i = 0; i < 10; ++i)
    {
      do
      {
        randRow = (int) (Math.random() * 9);
        randCol = (int) (Math.random() * 9);
      }while(buttons[randRow][randCol].isMine());
      
      buttons[randRow][randCol].toggleMine(true); 
    }    
  }
  /***************************************************************************************/
  
  
  /***************************************************************************************/
  
  /***********************************Grid Resetter***************************************/
  public void resetGrid()
  {
    for(int i = 0; i < 10; ++i)
    {
      for(int j = 0; j < 10; ++j)
      {   
        buttons[i][j].setEnabled(true);
        buttons[i][j].setState("normal");
        buttons[i][j].toggleMine(false);
        buttons[i][j].setIcon(background);
      }
    }
    
    int randRow;
    int randCol;
    for(int i = 0; i < 10; ++i)
    {
      do
      {
        randRow = (int) (Math.random() * 9);
        randCol = (int) (Math.random() * 9);
      }while(buttons[randRow][randCol].isMine());
      
      buttons[randRow][randCol].toggleMine(true); 
    }
    
    numCleared = 0;
    
    try
    {
      Scanner input = new Scanner(fileName);
      
      int i = 1;
      while(input.hasNextLine())
      {
        topTen[i] = input.nextLine();
        ++i;
      }
      input.close();
    }
    catch(Exception ex)
    {
      JOptionPane.showMessageDialog (null, "File topTen.txt could not be read.");
    }
  }  
  /***************************************************************************************/
  
  /*************************************Calculate Adjacent Mines**************************/
  public int calcAdjMines(MSButton curr)
  {
    int currRow = curr.getRow();
    int currCol = curr.getCol();
    int adjMines = 0;
    
    //NW-Button
    if((currRow > 0) && (currCol > 0))
    {
      if(buttons[currRow-1][currCol-1].isMine())
        ++adjMines;
    }
    //N-Button
    if(currRow > 0)
    {
      if(buttons[currRow-1][currCol].isMine())
        ++adjMines;
    }
    //NE-Button
    if((currRow > 0) && (currCol < 9))
    {
      if(buttons[currRow-1][currCol+1].isMine())
        ++adjMines;
    }
    //W-Button
    if(currCol > 0)
    {
      if(buttons[currRow][currCol-1].isMine())
        ++adjMines;
    }
    //SW-Button
    if((currRow < 9) && (currCol > 0))
    {
      if(buttons[currRow+1][currCol-1].isMine())
        ++adjMines;
    }
    //S-Button
    if(currRow < 9)
    {
      if(buttons[currRow+1][currCol].isMine())
        ++adjMines;
    }
    //SE-Button
    if((currRow < 9) && (currCol < 9))
    {
      if(buttons[currRow+1][currCol+1].isMine())
        ++adjMines;
    }
    //E-Button
    if(currCol < 9)
    {
      if(buttons[currRow][currCol+1].isMine())
        ++adjMines;
    }
    
    return adjMines;
  }  
  /***************************************************************************************/

  
  /**************************************MSButton Class***********************************/
  private class MSButton extends JButton 
  {
    private int row;
    private int col;
    private String state;
    private Icon icon;
    private boolean mineStatus;
    
    public MSButton(int r, int c, String s, Icon i)
    {
      super("", i);
      this.state = s;
      this.row = r;
      this.col = c;
      this.mineStatus = false;
    }
    
    public void toggleMine(boolean state)
    {
      this.mineStatus = state;
    } 
    
    public boolean isMine()
    {
      return this.mineStatus;
    }  
    
    public void setState(String s)
    {
      this.state = s;
    }  
    
    public String getState()
    {
      return this.state;
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
