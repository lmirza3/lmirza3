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
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.*;

public class Tetris implements Runnable {

 private int[] info;// = new int[3];
 private Tetromino[] nextPiece;// = new Tetromino[1];
 JFrame frame;
 private JMenuBar menuBar;
 
 private Tetris()
 {
   info = new int[3];
   nextPiece = new Tetromino[1];
 }
 
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
 
 
 
 public void run() 
 {
  frame = new JFrame();
  
  JPanel list = new JPanel();
  list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
    
  JLabel score = new JLabel();
  score.setText("Score: " + Integer.toString(info[0]));
  
  JLabel level = new JLabel();
  level.setText("Level: " + Integer.toString(info[1]));
  
  JLabel lines = new JLabel();
  lines.setText("Lines: " + Integer.toString(info[2]));

  JLabel timerLabel = new JLabel();
  int seconds = 0;
  lines.setText("Timer: " + seconds);
  
  JLabel blankSpace = new JLabel("   ");
  JLabel instructions1 = new JLabel("Controls: ");
  JLabel instructions2 = new JLabel("Left: Left-Arrow Key");
  JLabel instructions3 = new JLabel("Right: Right-Arrow Key");
  JLabel instructions4 = new JLabel("Rotate: Up-Arrow Key");
  JLabel instructions5 = new JLabel("Down: Space Bar");
  JLabel instructions6 = new JLabel("OR Buttons: ");
  JLabel blankSpace2 = new JLabel("   ");
  
  JButton leftBtn = new JButton("Move Left");
  
  JButton rightBtn = new JButton("Move Right");
  
  JButton rotateBtn = new JButton("Rotate");
  
  JButton downBtn = new JButton("Move Down");
  
  
  final TetrisGrid Tetris = new TetrisGrid(info, score, level, lines,
    nextPiece, timerLabel, seconds, leftBtn, rightBtn, rotateBtn, downBtn);

  final JButton start = new JButton("Start/Restart");
  start.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    Tetris.start();
   }
  });
  
  list.add(start);
  list.add(score);
  list.add(level);
  list.add(lines);
  
  list.add(timerLabel);
  
  //add instructions
  list.add(blankSpace);
  list.add(instructions1);
  list.add(instructions2);
  list.add(instructions3);
  list.add(instructions4);
  list.add(instructions5);
  list.add(blankSpace2);
  list.add(instructions6);
  
  //add buttons
  list.add(leftBtn);
  list.add(rightBtn);
  list.add(rotateBtn);
  list.add(downBtn);
  
  frame.add(Tetris, BorderLayout.CENTER);
  frame.add(list, BorderLayout.EAST);
  frame.setJMenuBar(createMenuBar());
  frame.pack();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);

 }

 /*public static void main(String[] args) {
  
 }*/
 
 //Get the only Tetris object available
   public static void getInstance()
   {
      SwingUtilities.invokeLater(new Tetris());
     //System.out.println("Tetris used a singleton design pattern.");
   }
}