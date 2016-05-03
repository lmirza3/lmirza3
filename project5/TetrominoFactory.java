import java.util.Random;

public class TetrominoFactory {
  
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