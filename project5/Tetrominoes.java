import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;


interface Tetrominoes {
  Coord[] getLocation();
}

abstract class Tetromino {
  
  Tetromino()
  {
    int orientation;
    orientation = 1;
  }
  
  public Coord[] getLocation() {
    return null;
  }
}

class I extends Tetromino {
  public Coord[] pos;
  
  I() {
    
    pos = new Coord[4];
    pos[0] = new Coord(0,4);
    pos[1] = new Coord(1,4);
    pos[2] = new Coord(2,4);
    pos[3] = new Coord(3,4);
  }
  
  @Override
  public Coord[] getLocation() {
    return pos;
  }
}