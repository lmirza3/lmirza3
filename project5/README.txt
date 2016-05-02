/*********************  RUNNING THE PROGRAM  ************/

To run the program, run the file mainTetris.java

If in terminal/command line: 
1) javac mainTetris.java
2) java mainTetris

——————————————————————————————————————————————————————————————
/*********************************KNOWN ISSUES***********************************/
1. Timer displaying seconds elapsed
2. GUI manual controls

——————————————————————————————————————————————————————————————
/*********************  DESIGN PATTERNS  ************/

*(1) Singleton Design Pattern:

The Singleton Design Pattern is one where we only allow 1
instantiation of an object to be called. 

—————————————————————————
-Implementation of Singleton Design Pattern:

i) First, we make the constructor of an object private, so no other file can 
access it. We did this in the “Tetris.java” file:

//Private Constructor:
private Tetris()
{
   . . . . 
}


ii) We make a function so we can return the instance of this object in “Tetris.java”:

//Function to allow this instance to be called:

public static void main(String[] args) {
  SwingUtilities.invokeLater(new Tetris());
 }


—————————————————————————————————
*(2) Factory Design Pattern:

The Factory Design Pattern allows the program to have an interface
for the programmer and user. This makes creation of objects even more 
private, and thus more efficient. 

—————————————————————————
Implementation of Factory Design Pattern:

i) We first make a shapes interface in the file “ShapesInterface.java” with
all the functions in the file “Shapes.java”:

public interface ShapesInterface 
{
  void makeShape(Shapes.Tetrominoes shape);
  void makeNewX(int ind, int x);
  void makeNewY(int ind, int y);
  int getx(int ind);

  . . . . 
}


ii) Next we made our Shapes class implement this interface in our “Shapes.java” file:

public interface Tetromino {
 public void moveLeft(Block[][] grid);
 public void moveRight(Block[][] grid); 
 public boolean moveDown(Block[][] grid);
 public void hardDrop(Block[][] grid); 
 public void rotate(Block[][] grid);
}
 (since we use inheritance on each shape, we don’t implement the shapes directly)


iii) Next we use our classes that inherit the Tetromino class in order to instantiate different kinds of Tetrominoes.

public class IBlock implements Tetromino {
/////
.....
}




