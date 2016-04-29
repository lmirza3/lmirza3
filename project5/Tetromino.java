public class Tetromino extends gameBoard {
  
  int currentX;     // current X location on  the board
  double currentY;  // current Y location on the board
  
  public Tetromino(int shape[][]) {
    super(shape);
    currentX = 7;
    currentY = 0;
    updateLocation();
  }
  
  void updateLocation() {
    setLocation(Tetris.SQUARE_SIZE*currentX,
                (int) (Tetris.SQUARE_SIZE*currentY));
  }
}