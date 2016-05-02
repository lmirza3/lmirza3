/*
 * File: Class file to make our Tetris class. This handles the GUI of the board.
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
import java.io.*;
import java.util.Random;
import java.lang.Math;

import java.awt.Color;
import java.awt.BorderLayout;


public class Tetris extends JFrame implements ActionListener{
 
  private static Tetris instance;
 // public static gameBoard game = new gameBoard();
  public static final int HEIGHT = 600, WIDTH = 500;
  private JTextField timeDisplay;
  public static JLabel countDownLabel ;
  public static JLabel scoreLabel;
  public static gameBoard board;
  public static Timer timer;
  
  //add a menu bar
  public JMenuBar createMenuBar() 
  {
    JMenuBar menuBar;    //menu bar that will hold menu items
    JMenu menu;    //for menu items in column of options
    
    menuBar = new JMenuBar();
    menu = new JMenu("Options");    //top options
    menuBar.add(menu);
    
    //restart menu-1 option
    JMenuItem resetmenuItem = new JMenuItem("Start New Game  (R)", KeyEvent.VK_R);
    resetmenuItem.setMnemonic(KeyEvent.VK_R);
    resetmenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // RESET GAME
        JOptionPane.showMessageDialog(null, "Game restarted!", "New Game", JOptionPane.PLAIN_MESSAGE);
      }
    });
    menu.add(resetmenuItem);
    
    //quit in menu-1 option
    JMenuItem quitGame = new JMenuItem("Exit  (Q)", KeyEvent.VK_Q);
    quitGame.setMnemonic(KeyEvent.VK_Q);
    quitGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    menu.add(quitGame);
    
    
    menu = new JMenu("Help");    //new menu column for the about/help info
    menuBar.add(menu);
    
    //about in menu-2 option
    JMenuItem about = new JMenuItem("About  (A)", KeyEvent.VK_A);
    about.setMnemonic(KeyEvent.VK_A);
    about.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "This program was written by Lubna Mirza and Aiwan Hazari. \nIt is the 5th programming assignment for CS 342. The \npurpose of this program was to use the Java Swing Library \nto implement the game Tetris.", "About", JOptionPane.PLAIN_MESSAGE);
      }
    });
    menu.add(about);
    
    //help in menu-2 option
    JMenuItem helpOpt = new JMenuItem("Help  (H)", KeyEvent.VK_H);
    helpOpt.setMnemonic(KeyEvent.VK_H);
    helpOpt.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Tetris Game: The goal of this game is to wipe out as many rows as possible by \naligning blocks together to fill an entire row. You can rotate the blocks \nas they come down with the left and right arrow keys, and move them up \nand down with the up and down arrow keys (all keys are on your keyboard). \nThere are 4 menu options in this program: Start, Quit, Help, and About. \nStart will start/restart a new game\nQuit will exit the game (closing this entire panel with the red X will also quit the game) \nAbout will tell you more information on the authors and purpose of this program. \n\nMore game information can be found on this page: https://en.wikipedia.org/wiki/Tetris.", "Help", JOptionPane.PLAIN_MESSAGE);
      }
    });
    menu.add(helpOpt);
    
    return menuBar;
  }

 
  private Tetris()
  {
    //ADDING score 
    scoreLabel = new JLabel();
    scoreLabel.setText("Score: " + board.scoreFlag);
    
    //adding counter
    countDownLabel = new JLabel();
    countDownLabel.setText("" + Seconds.seconds);
    //Starting counter
    CountDown countDown = new CountDown(countDownLabel);
    timer = new Timer(1000, countDown);
    timer.start();
    
    //menu
    setJMenuBar(createMenuBar());
    add(countDownLabel, BorderLayout.NORTH);
    add(scoreLabel, BorderLayout.SOUTH);
    
    board = new gameBoard(this);
    add(board);
    board.start();

    setSize(WIDTH,HEIGHT);
    setTitle("Tetris_Hazari-Mirza");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  /* public static void main (String args[])
  {
    instance =  new Tetris();
    //Tetris main = new Tetris();
    instance.setVisible(true);
  } */
   
   
   //Get the only Tetris object available
   public static Tetris getInstance()
   {
     instance =  new Tetris();
     System.out.println("Tetris used a singleton design pattern.");
     instance.setVisible(true);
      return instance;
   }
   
   
  public void actionPerformed(ActionEvent e) {
    
  }
  
 
  
  class CountDown implements ActionListener {
    private JLabel countDownLabel;
    
    
    public CountDown(JLabel countDownLabel) {
      this.countDownLabel = countDownLabel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      Seconds.seconds++;
      this.countDownLabel.setText("Secs Elapsed: " + Seconds.seconds);
      if (board.scoreFlag <= 0)
      {
        board.scoreFlag = 0;
        scoreLabel.setText("Score: " + board.scoreFlag);
      }
         
      if (board.gameCompletedFlag == 1 )
      {
        board.secondsElapsed = Seconds.seconds;
        timer.stop();
      }
    }
  }
}

class Seconds 
{
  public static int seconds = 0;
}

 /* public void init()
  {
    Thread runGame = new Thread(this);
    runGame.setPriority(Thread.MAX_PRIORITY);
    runGame.start();
  }
  
  public void run()
  {
    boolean gameRun = true;
    while(gameRun)
    {
      updateGame();
      /*BufferStrategy b = this.getBufferStrategy();
      if(b == null)   //if game has to be initialized
      {
        createBufferStrategy(2);
        continue;
      }
      //otherwise keep updating the pictures/board
      Graphics2D g = (Graphics2D) b.getDrawGraphics();
      render(g);
      b.show();
    }
  }
  
  public void updateGame()
  {
    /*try
    {
      curPiece = game.blue;
    }
    catch(IOException e)
    {
      System.out.println("Error getting shape");
      System.exit(1);
    }
  }
  public void render(Graphics2D graphCols)
  {
    //graphCols.setColor(Color.black);
    //graphCols.fillRect(0,0,WIDTH,HEIGHT);
   // graphCols.drawImage(curPiece, 100, 100, 25, 25, null);
  }
  
   /*  //pick a random shape to drop
  public static void dropRandShape()
  {
    Random r = new Random();
    int shapeVal = Math.abs(r.nextInt()) % 7 + 1;
    //call function accordingly
    switch(shapeVal)
    {
      case 1:
        //box
        System.out.println("1");
        shape = new box();
        game.isFalling = true;
        break;
      case 2:
        System.out.println("2");
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
  
  */
  