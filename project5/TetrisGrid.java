import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class TetrisGrid extends JComponent {
 private Tetromino currentPiece; 
 private Tetromino nextPiece;
 private Block[][] grid; 
 private int[] gameStats; 
 private boolean gameStarted; 
 private JLabel score; 
 private JLabel level;
 private JLabel lines;
 private Tetromino[] nextArray;

 private int interval = 750;
 private Timer timer;       

 final int WIDTH  = 250; 
 final int HEIGHT = 450; 

 public TetrisGrid(int[] gameStats, JLabel score, JLabel level, JLabel lines,
   Tetromino[] nextArray) {
  setBorder(BorderFactory.createLineBorder(Color.BLACK));
  setFocusable(true);

  this.gameStarted = false;
  this.gameStats = gameStats;
  this.score = score;
  this.level = level;
  this.lines = lines;
  this.nextArray = nextArray;

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
  
  currentPiece = randomPiece();
  nextPiece = randomPiece();
  updateNextPiece();

  addKeyListener(new KeyAdapter() {
   public void keyPressed(KeyEvent e) {
    if (gameStarted) {
     if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      currentPiece.moveLeft(grid);
     }
     else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      currentPiece.moveRight(grid);
     }
     else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      currentPiece.hardDrop(grid);
      clearLines();
      if (checkGameEnd()) {
       gameStarted = false;
      }
      currentPiece = randomPiece();
      nextPiece = randomPiece();
      updateNextPiece();
     }
     else if (e.getKeyCode() == KeyEvent.VK_UP) {
      currentPiece.rotate(grid);
     }
     repaint();
    }
   }

  });
 }

 public void start() {
  gameStarted = true;
  clearGame();
  requestFocusInWindow();
 }

 void tick() {
  if (gameStarted) {
   if (!currentPiece.moveDown(grid)) {
    clearLines();
    if (checkGameEnd()) {
     gameStarted = false;
    }
    currentPiece = randomPiece();
    nextPiece = randomPiece();
    updateNextPiece();
   }
   repaint(); 
  }
 }

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
  return new Dimension(WIDTH, HEIGHT);
 }
 
 // Checks to see if the game is over each time a block falls
 private boolean checkGameEnd() {
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
 
 // Clears lines that are completed
 private void clearLines() {
  int combo = 0;
  for (int y = 1; y < 19; y++) {
   int countFilled = 0;
   for (int x = 0; x < 10; x++) {
    if (!grid[x][y].isEmpty()) {
     countFilled++;
    }
   }
   // Pushes blocks down into the open space
   if (countFilled == 10) {
    combo++;
    for (int x = 0; x < 10; x++) {
     for (int y2 = y; y2 > 2; y2--) {
      grid[x][y2] = grid[x][y2-1];
     }
    }
   }
  }
  // Updates score
  if (combo == 1) {
   gameStats[0] += 40;
  }
  else if (combo == 2) {
   gameStats[0] += 100;
  }
  else if (combo == 3) {
   gameStats[0] += 300;
  }
  else if (combo == 4) {
   gameStats[0] += 1200;
  }
  // Updates lines
  gameStats[2] += combo;
  // Updates level
  if (combo > 0 && gameStats[2] != 0 && gameStats[2] % 10 == 0) {
   gameStats[1] += 1;
   levelUp();
  }
  lines.setText("Lines: " + Integer.toString(gameStats[2]));
  score.setText("Score: " + Integer.toString(gameStats[0]));
 }
 
 // Resets the game
 private void clearGame() {
  for (int x = 0; x < 10; x++) {
   for (int y = 0; y < 19; y++) {
    grid[x][y] = new EmptyBlock(Color.BLACK);
   }
  }
  gameStats[0] = 0;
  gameStats[1] = 1;
  gameStats[2] = 0;
  score.setText("Score: " + Integer.toString(gameStats[0]));
  level.setText("Level: " + Integer.toString(gameStats[1]));
  lines.setText("Lines: " + Integer.toString(gameStats[2]));
  currentPiece = randomPiece();
 }
 
 // Decreases the interval between drops by updating the level
 private void levelUp() {
  level.setText("Level: " + Integer.toString(gameStats[1]));
  if (interval > 50) {
   timer.stop();
   interval -= 10;
   timer = new Timer(interval, new ActionListener() {
    public void actionPerformed(ActionEvent e) { tick(); }});
   timer.start(); 
  }
 }
 
 // Randomly selects a tetromino
 private Tetromino randomPiece() {
  Random rand = new Random();
  int n = rand.nextInt(7);
  if (n == 0) {
   return new IBlock(grid);
  }
  else if (n == 1) {
   return new JBlock(grid);
  }
  else if (n == 2) {
   return new LBlock(grid);
  }
  else if (n == 3) {
   return new OBlock(grid);
  }
  else if (n == 4) {
   return new SBlock(grid);
  }
  else if (n == 5) {
   return new ZBlock(grid);
  }
  else {
   return new TBlock(grid);
  }
 }
 
 private void updateNextPiece() {
  nextArray[0] = nextPiece;
 }
}
