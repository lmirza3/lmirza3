import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class gameBoard extends JPanel{
  private JButton buttons[][];
  private Container container;
  private GridLayout grid;
  private int linesCleared;
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
    
    buttons = new JButton[10][20];
    this.linesCleared = 0;
    mineFlag = 10;
    secondsElapsed = 0;
    gameCompletedFlag = 0;
    
    for(int i = 0; i < 10; ++i)
    {
      for(int j = 0; j < 20; ++j)
      {   
        buttons[i][j] = new JButton(background);
        buttons[i][j].setPreferredSize(new Dimension(16,16));
        this.add(buttons[i][j]);
      }
    }
    
   /* int randRow;
    int randCol;
    for(int i = 0; i < 10; ++i)
    {
      do
      {
        randRow = (int) (Math.random() * 9);
        randCol = (int) (Math.random() * 9);
      }while(buttons[randRow][randCol].isMine());
      
      buttons[randRow][randCol].toggleMine(true); 
    }*/    
  }
  /***************************************************************************************/
  
  
  /***************************************************************************************/
  
  /***********************************Grid Resetter***************************************/
 /* public void resetGrid()
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
    
    linesCleared = 0;
  }*/
  /***************************************************************************************/
  
  
  /***************************************************************************************/
  
}
