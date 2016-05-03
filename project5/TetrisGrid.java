/*
 * File: Class file to make our gameboard class. This handles the controls of the game
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
import java.util.Random;

import javax.swing.*;

public class TetrisGrid extends JComponent {
 private Tetromino currentPiece; 
 private Tetromino nextPiece;
 private Block[][] grid; 
 private int[] info; 
 public boolean gameStarted; 
 private JLabel score; 
 private JLabel level;
 private JLabel lines;
 private JLabel timerLabel;
 private Tetromino[] nextArray;
 private Seconds secs = new Seconds();
 TetrominoFactory tetFactory = new TetrominoFactory();
  private testTimer timerTest;

 private int interval = 750;
 private Timer timer;       
 
//grid that handles the game and gameplay
 public TetrisGrid(int[] info, JLabel score, JLabel level, JLabel lines,
   Tetromino[] nextArray, JButton leftBtn, JButton rightBtn, JButton rotateBtn, JButton downBtn) 
  {
  setBorder(BorderFactory.createLineBorder(Color.BLACK));
  setFocusable(true);

  this.gameStarted = false;
  this.info = info;
  this.score = score;
  this.level = level;
  this.lines = lines;
  this.nextArray = nextArray;

  leftBtn.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    moveLeft();
   }
  });
  
  rightBtn.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    moveRight();
   }
  });
  
  downBtn.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    dropDownAllTheWay();
   }
  });
  
  rotateBtn.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    rotatePiece();
   }
  });

  timer = new Timer(interval, new ActionListener() {
   public void actionPerformed(ActionEvent e) {
     tick(); 
   }
  });
  timer.start(); 

  grid = new Block[10][19]; 
  

  for (int x = 0; x < grid.length; x++) {
   for (int y = 0; y < grid[0].length; y++) {
    grid[x][y] = new EmptyBlock(Color.BLACK);
   }
  }
  
  currentPiece = tetFactory.randomPiece(grid);
  nextPiece = tetFactory.randomPiece(grid);
  updateNextPiece();
  
//adding key listeners for game controls
  addKeyListener(new KeyAdapter() {
   public void keyPressed(KeyEvent e) {
    if (gameStarted) {
     if (e.getKeyCode() == KeyEvent.VK_LEFT) {
       moveLeft();
     }
     else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
       moveRight();
     }
     else if (e.getKeyCode() == KeyEvent.VK_SPACE) 
     {
       dropDownAllTheWay();
     }
     else if (e.getKeyCode() == KeyEvent.VK_UP) {
       rotatePiece();
     }
     repaint();
    }
   }

  });
 }
  
  private void moveLeft()
  {
    currentPiece.moveLeft(grid);
  }
  
  private void moveRight()
  {
    currentPiece.moveRight(grid);
  }
  
  private void rotatePiece()
  {
    currentPiece.rotate(grid);
  }
  
  private void dropDownAllTheWay()
  {
    currentPiece.hardDrop(grid);
      clearLines();
      if (checkIfFinished()) {
       gameStarted = false;
      }
      currentPiece = tetFactory.randomPiece(grid);
      nextPiece = tetFactory.randomPiece(grid);
      updateNextPiece();
  }
  
//start the game
 public void start() {
  gameStarted = true;
  clearGame();
  
  //timer in command line
  timerTest = new testTimer();
  timerTest.start();
     
  requestFocusInWindow();
 }
 
//gameplay
 void tick() {
  if (gameStarted) {
   if (!currentPiece.moveDown(grid)) {
    clearLines();
    if (checkIfFinished()) {
     gameStarted = false;
    }
    currentPiece = tetFactory.randomPiece(grid);
    nextPiece = tetFactory.randomPiece(grid);
    updateNextPiece();
   }
   repaint(); 
  }
 }
//paint
 @Override
 public void paintComponent(Graphics g) {
  super.paintComponent(g);
  for (int x = 0; x < grid.length; x++) {
   for (int y = 1; y < grid[0].length; y++) {
    grid[x][y].draw(x, y, g);
   }
  }
  
  g.setColor(Color.LIGHT_GRAY);
  for (int i = 1; i <= 10; i++) {
   g.drawLine(25*i,0,25*i,450);
  }
  for (int i = 1; i <= 18; i++) {
   g.drawLine(0,25*i,250,25*i);
  }
 }

 @Override
 public Dimension getPreferredSize() {
  return new Dimension(250, 450);
 }
 //has the game ended?
 private boolean checkIfFinished() {
  for (int x = 0; x < 10; x++) {
   if (!grid[x][0].isEmpty() &&
     !grid[x][1].isEmpty() &&
     !nextPiece.moveDown(grid) &&
     !currentPiece.moveDown(grid)) {
    return true;
   }
  }
  return false;
 }
 
 // clear line
 private void clearLines() {
  int combo = 0;
  for (int y = 1; y < 19; y++) {
   int numFilled = 0;
   for (int x = 0; x < 10; x++) {
    if (!grid[x][y].isEmpty()) {
     numFilled++;
    }
   }
   // fall down
   if (numFilled == 10) {
    combo++;
    for (int x = 0; x < 10; x++) {
     for (int y2 = y; y2 > 2; y2--) {
      grid[x][y2] = grid[x][y2-1];
     }
    }
   }
  }
  // Update score
  if (combo == 1) {
   info[0] += 40;
  }
  else if (combo == 2) {
   info[0] += 100;
  }
  else if (combo == 3) {
   info[0] += 300;
  }
  else if (combo == 4) {
   info[0] += 1200;
  }
  // Updates lines
  info[2] += combo;
  // Updates level
  if (combo > 0 && info[2] != 0 && info[2] % 10 == 0) {
   info[1] += 1;
   levelUp();
  }
  lines.setText("Lines: " + Integer.toString(info[2]));
  score.setText("Score: " + Integer.toString(info[0]));
 }
 
 // Resets the game
 private void clearGame() {
  for (int x = 0; x < 10; x++) {
   for (int y = 0; y < 19; y++) {
    grid[x][y] = new EmptyBlock(Color.BLACK);
   }
  }
  info[0] = 0;
  info[1] = 1;
  info[2] = 0;
  score.setText("Score: " + Integer.toString(info[0]));
  level.setText("Level: " + Integer.toString(info[1]));
  lines.setText("Lines: " + Integer.toString(info[2]));
  
<<<<<<< HEAD
=======
  //timerLabel.setText("Timer: " + 0);
>>>>>>> origin/master
  currentPiece = tetFactory.randomPiece(grid);
 }
 
//updating the level
 private void levelUp() {
  level.setText("Level: " + Integer.toString(info[1]));
  if (interval > 50) 
  {
    int timestop = timerTest.getTime();
    System.out.println("Level increased at time: " + timestop + " seconds");
   timer.stop();
   interval -= 10;
   timer = new Timer(interval, new ActionListener() {
    public void actionPerformed(ActionEvent e) { tick(); }});
   timer.start(); 
   
   timerTest.sec = 0;   //restart time;
  }
 }

 private void updateNextPiece() {
  nextArray[0] = nextPiece;
 }
}