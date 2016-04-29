import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Tetris implements ActionListener{
  
  gameBoard game = new gameBoard();
  JButton smileyButton;
  
  
  JTextField timeDisplay;
  JLabel timeLabel;
  JLabel mineLabel;
  
  //add a menu bar
  public JMenuBar createMenuBar() 
  {
    JMenuBar menuBar;    //menu bar that will hold menu items
    JMenu menu;    //for menu items in column of options
    
    menuBar = new JMenuBar();
    menu = new JMenu("Options");    //top options
    menuBar.add(menu);
    
    //restart menu-1 option
    JMenuItem resetmenuItem = new JMenuItem("Start New Game  (R)", KeyEvent.VK_R);
    resetmenuItem.setMnemonic(KeyEvent.VK_R);
    resetmenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // RESET GAME
        JOptionPane.showMessageDialog(null, "Game restarted!", "New Game", JOptionPane.PLAIN_MESSAGE);
      }
    });
    menu.add(resetmenuItem);
    
    //quit in menu-1 option
    JMenuItem quitGame = new JMenuItem("Exit  (Q)", KeyEvent.VK_Q);
    quitGame.setMnemonic(KeyEvent.VK_Q);
    quitGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    menu.add(quitGame);
    
    
    menu = new JMenu("Help");    //new menu column for the about/help info
    menuBar.add(menu);
    
    //about in menu-2 option
    JMenuItem about = new JMenuItem("About  (A)", KeyEvent.VK_A);
    about.setMnemonic(KeyEvent.VK_A);
    about.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "This program was written by Lubna Mirza and Aiwan Hazari. \nIt is the 5th programming assignment for CS 342. The \npurpose of this program was to use the Java Swing Library \nto implement the game Tetris.", "About", JOptionPane.PLAIN_MESSAGE);
      }
    });
    menu.add(about);
    
    //help in menu-2 option
    JMenuItem helpOpt = new JMenuItem("Help  (H)", KeyEvent.VK_H);
    helpOpt.setMnemonic(KeyEvent.VK_H);
    helpOpt.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Tetris Game: The goal of this game is to wipe out as many rows as possible by \naligning blocks together to fill an entire row. You can rotate the blocks \nas they come down with the left and right arrow keys, and move them up \nand down with the up and down arrow keys (all keys are on your keyboard). \nThere are 4 menu options in this program: Start, Quit, Help, and About. \nStart will start/restart a new game\nQuit will exit the game (closing this entire panel with the red X will also quit the game) \nAbout will tell you more information on the authors and purpose of this program. \n\nMore game information can be found on this page: https://en.wikipedia.org/wiki/Tetris.", "Help", JOptionPane.PLAIN_MESSAGE);
      }
    });
    menu.add(helpOpt);
    
    return menuBar;
  }
  
  
  
  public static void main (String args[])
  {
    new Tetris();
  } 
  public Tetris()
  {
    //overarching frame
    JFrame f = new JFrame();
    //labels
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    
    //ADDING MINE NUMBER
    mineLabel = new JLabel();
    mineLabel.setText("" + game.mineFlag);
    panel3.add(mineLabel);
    f.getContentPane().add(panel3,BorderLayout.NORTH);
    
    //Adding RESET button
    Icon smiley = new ImageIcon("CS342 Project 2 Minesweeper Images/smile_button.gif");
    smileyButton = new JButton(smiley); 
    smileyButton.setPreferredSize(new Dimension(24, 24));
    smileyButton.addActionListener(this);
    panel1.add(smileyButton);
    f.getContentPane().add(panel1,BorderLayout.NORTH);    
    
    
    //adding counter
    JLabel countDownLabel = new JLabel();
    countDownLabel.setText("" + Seconds.seconds);
    panel2.add(countDownLabel);
    f.getContentPane().add(panel2,BorderLayout.NORTH);
    //Starting counter
    CountDown countDown = new CountDown(countDownLabel);
    Timer timer = new Timer(1000, countDown);
    timer.start();
    
    
    JMenuBar menuBar = new JMenuBar();
    
    f.setJMenuBar(createMenuBar());
    f.setLayout(new FlowLayout());
    f.getContentPane().add(game);
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );     
    f.pack();
    f.setSize(400,600);
    f.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    
  }
  
  class CountDown implements ActionListener {
    private JLabel countDownLabel;
    
    
    public CountDown(JLabel countDownLabel) {
      this.countDownLabel = countDownLabel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      Seconds.seconds++;
      this.countDownLabel.setText("Secs Elapsed: " + Seconds.seconds);
      if (game.mineFlag != 0)
        mineLabel.setText("Mines Left: " + game.mineFlag);
      if (game.mineFlag <= 0)
      {
        mineLabel.setText("0");
      }
      
      
      if (game.gameCompletedFlag == 1 )
      {
        game.secondsElapsed = Seconds.seconds;
      }
    }
  }
}

class Seconds {
  public static int seconds = 0;
}
