/*
 * File: Class file to make our empty piece.
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

public class Block {
 private Color fillColor;
 
 public Block(Color color) {
  this.fillColor = color;
 }
 
 public boolean isEmpty() {
  return (fillColor.equals(Color.BLACK));
 }

 public void draw(int x, int y, Graphics g) {
  g.setColor(fillColor);
  g.fillRect(x*25, (y-1)*25, 25, 25);
 }
}

class EmptyBlock extends Block {
 
 public EmptyBlock(Color color) {
  super(color);
 }
 
 public void draw(int x, int y, Graphics g) {
  g.setColor(Color.BLACK);
  g.fillRect(x*25, (y-1)*25, 25, 25);
 }
}
