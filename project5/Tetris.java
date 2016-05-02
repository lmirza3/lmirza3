import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.*;

public class Tetris implements Runnable {

 private int[] gameStats = new int[3];
 private Tetromino[] nextPiece = new Tetromino[1];
 JFrame frame;
 private JMenuBar menuBar;
 
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
 
 
 
 public void run() {
   
   
  frame = new JFrame();
  
  JPanel list = new JPanel();
  list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
    
  JLabel score = new JLabel();
  score.setText("Score: " + Integer.toString(gameStats[0]));
  
  JLabel level = new JLabel();
  level.setText("Level: " + Integer.toString(gameStats[1]));
  
  JLabel lines = new JLabel();
  lines.setText("Lines: " + Integer.toString(gameStats[2]));

  final TetrisGrid Tetris = new TetrisGrid(gameStats, score, level, lines,
    nextPiece);

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
  frame.add(Tetris, BorderLayout.CENTER);
  frame.add(list, BorderLayout.EAST);
  frame.setJMenuBar(createMenuBar());
  frame.pack();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);

 }

 public static void main(String[] args) {
  SwingUtilities.invokeLater(new Tetris());
 }
}
