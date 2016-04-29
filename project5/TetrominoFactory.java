public class TetrominoFactory {
  
  
  public static final int[][] L =
  {{1,1},
    {0,1},
    {0,1}
  };
  
  public static final int[][] J =
  {{0,1},
    {0,1},
    {1,1}
  };  
  
  public static final int[][] T =
  {{0,1},
    {1,1},
    {0,1}
  };
  
  public static final int[][] BOX =
  {{1,1},
    {1,1}
  };
  
  public static final int[][] I =
  {{1,1,1,1}
  };  
  
  public static final int[][] S =
  {{1,0},
    {1,1},
    {0,1}
  }; 
  
  public static final int[][] Z =
  {{0,1},
    {1,1},
    {1,0}
  };
  
  public static final int[][][] SHAPES = {L,J,T,BOX,I,S,Z};
  
  public static Tetromino createTetromino() {
    int[][] s = SHAPES[(int) (Math.random()*SHAPES.length)];
    switch ((int) (Math.random()*10)) {
      case 0: 
      case 1: 
      case 2:
      case 3: 
      default: return new Piece(s);
    }
  }  
}
