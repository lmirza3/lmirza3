/*********************  FILES  ************/
The final files for this program are: 
1) Tetris.java  - Main file to run program. Includes the GUI here
2) TetrisGrid.java  - File to initialize game board grid
3) Tetromino.java - File to initialize our interface for Tetrominos
4) TetrominoFactory.java - File to use Design Pattern
5) Block-Tetromino classes:
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

To run the program, run the file Tetris.java

If in terminal/command line: 
1) javac Tetris.java
2) java Tetris

——————————————————————————————————————————————————————————————
/*********************  NOT WORKING IN OUR PROGRAM  ************/

1) We were unable to get the Timer functioning on this program.

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
   public static Tetris getInstance()
   {
     instance =  new Tetris();
     System.out.println("Tetris used a singleton design pattern.");
     instance.setVisible(true);
      return instance;
   }


iii) Then in another file, “mainTetris.java”, we call this object: 

//Only able to call this object once & in this file:
public static void main(String[] args) 
   {
      //Call Tetris object 
      Tetris object = Tetris.getInstance();
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

public class Shapes implements ShapesInterface
{
  . . . . 
}
 (since we use inheritance on each shape, we don’t implement the shapes directly)


iii) Next we use a Shapes Factory class in the “ShapeFactory.java” file to call each of these
shapes are are being implemented by our interface:

public class ShapeFactory {
 
   //use getShape method to get object of type shape 
   public Shapes getShape(Shapes.Tetrominoes shapeType)
   {
      if(shapeType == null)
      {
         return null;
      }  
      
      if(shapeType == Shapes.Tetrominoes.Blank)
      {
        return new Blank();
      }
      
      . . . . 
    }




