// CS101 Lab 9
// Name:
// Lab Section:
// Email:
// Grid.java

import javax.swing.JComponent;
import java.awt.*;

public class Grid extends JComponent {
 int contents[][];  // each rows is an array of integers
 
 public Grid(int[][] contents) {
  this.contents = contents;
  Dimension d = new Dimension(getColumns()*Tetris.SQUARE_SIZE,
                              getRows()*Tetris.SQUARE_SIZE);
  setSize(d);
  setPreferredSize(d);
  setOpaque(false);
 }
  
 public int getColumns() {
  return contents[0].length;
 }

 public int getRows() {
  return contents.length;
 } 
  
 void paintSquare(int row, int col, Graphics g) {
  if (contents[row][col] != 0)
   g.fillRect(Tetris.SQUARE_SIZE*col+1,
      Tetris.SQUARE_SIZE*row+1,
      Tetris.SQUARE_SIZE-2,
      Tetris.SQUARE_SIZE-2);
 }
 
 public void paintComponent(Graphics g) {
  super.paintComponent(g);
  for (int row = 0; row < contents.length; row++) {
   for (int col = 0; col < contents[row].length; col++) {
     paintSquare(row,col,g);
   }    
  }
 }
}
