import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gameBoard extends JPanel implements ActionListener
{
  int width = 10;
  int height = 20;
  
  JLabel score;
  boolean gamePause = false;
  boolean gameStart = false;
  
  public gameBoard(Tetris inherited)
  {
    score = inherited.updateScore();
    addKeyListener(new TAdapter());
  }
  
  public void actionPerformed(ActionEvent e) {
  }
  
  class TAdapter extends KeyAdapter {
  }
}
