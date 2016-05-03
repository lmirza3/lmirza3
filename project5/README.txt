/*********************  FILES  ************/
The final files for this program are: 
1) mainTetris.java - Main file to run the program. Also uses Singleton
                     Design Pattern here.
2) Tetris.java  - File that makes the GUI
3) TetrisGrid.java  - File to initialize game board grid
4) Tetromino.java - File to initialize our interface for Tetrominos
5) TetrominoFactory.java - File to use Factory Design Pattern
6) testTime.java - File to show time elapsed since game started/level
		    increased (time shown on command line)
7) Blocks/Tetromino classes:
   LBlock.java
   Block.java
   IBlock.java
   JBlock.java
   OBlock.java
   SBlock.java
   TBlock.java
   ZBlock.java

——————————————————————————————————————————————————————————————
/*********************  RUNNING THE PROGRAM  ************/

To run the program, run the file mainTetris.java

If in terminal/command line: 
1) javac mainTetris.java
2) java mainTetris

——————————————————————————————————————————————————————————————
/*********************  KNOWN ISSUES  ************/
1. Timer displaying seconds elapsed not functioning

——————————————————————————————————————————————————————————————
/*********************  DESIGN PATTERNS  ************/

*(1) Singleton Design Pattern:

The Singleton Design Pattern is one where we only allow one,
private instantiation of an object to be called. 

—————————————————————————
-Implementation of Singleton Design Pattern:

i) First, we make the constructor of an object private, so no other file can 
access it. We did this in the “LBlock.java” file:

//Tetris class:
private Tetris()
 {
   info = new int[3];
   nextPiece = new Tetromino[1];
 }


ii) We make a function so we can return the instance of this object in “Tetris.java”:

//Function to allow this instance to be initialized:
   public static void getInstance()
   {
      SwingUtilities.invokeLater(new Tetris());
     //System.out.println("Tetris used a singleton design pattern.");
   }


iii) Then in another file, “mainTetris.java”, we call this object: 

//in mainTetris class
public class mainTetris 
{
   public static void main(String[] args) 
   {
      //Call Tetris instance 
      Tetris.getInstance();
   }
}

—————————————————————————————————
*(2) Factory Design Pattern:

The Factory Design Pattern allows the program to have an interface
for the programmer and user. This makes creation of objects even more 
private, and thus more efficient. 

—————————————————————————
Implementation of Factory Design Pattern:

i) We first make a tetromino interface in the file “Tetromino.java” that contains
all the default functions that will be implemented in each class. The class looks as follows:

//Tetromino class
public interface Tetromino 
{
 public void moveLeft(Block[][] grid);
 public void moveRight(Block[][] grid); 
 public boolean moveDown(Block[][] grid);
 public void hardDrop(Block[][] grid); 
 public void rotate(Block[][] grid);
}

=======
ii) Next, we implement classes for each of the different types of blocks that will inherit the Tetromino class, and they each have their own functions for how each block handles the functions such as moving, dropping, rotating, etc. An example is
shown below:

//IBlock class
public class IBlock implements Tetromino {
 private int rotation;
 private int[] pOne = new int[2];
 private int[] pTwo = new int[2];
 private int[] pThree = new int[2];
 private int[] pFour = new int[2];

 private Block one = new Block(Color.CYAN);
   ....
   ....
   ....
}
 (since we use inheritance on each shape, we don’t implement the shapes directly
  from the interface)

=======
iii) Then, we have a TetrominoFactory class file which will randomly return one of the created tetrominoes.

//Tetromino factory class
public class TetrominoFactory {
  //One method in this class that will return a random block every time.
  public Tetromino randomPiece(Block[][] grid) {
    Random rand = new Random();
    int n = rand.nextInt(7);
    
    if (n == 0) 
      return new IBlock(grid);
    else if (n == 1) 
      return new JBlock(grid);
    else if (n == 2) 
      return new LBlock(grid);
    else if (n == 3)
      return new OBlock(grid);
    else if (n == 4)
      return new SBlock(grid);
    else if (n == 5)
      return new ZBlock(grid);
    else
      return new TBlock(grid);
  }
}

=======
iv) Then, an instance of the TetrominoFactory class gets called in class TetrisGrid, and it is used to 
access the random function (From Tetromino Factory) that will return a different shape every time:

//TetrisGrid class
TetrominoFactory tetFactory = new TetrominoFactory();
