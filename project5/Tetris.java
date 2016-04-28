import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Tetris implements ActionListener{
  
  gameBoard game = new gameBoard();
  JButton smileyButton;
  JMenuItem topTen;
  JMenuItem resetTopTen;
  JMenuItem reset;
  JMenuItem exit;
  
  JMenuItem help;
  JMenuItem about;
  
  JTextField timeDisplay;
  JLabel timeLabel;
  JLabel mineLabel;
  
  public static void main (String args[])
  {
    new Tetris();
  }
  
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
    
    JMenu gameMenu = new JMenu("Game");
    gameMenu.setMnemonic(KeyEvent.VK_A);
    menuBar.add(gameMenu);
    
    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic(KeyEvent.VK_A);
    menuBar.add(helpMenu);
    
    //adding menu items for Game
    reset = new JMenuItem("Reset", KeyEvent.VK_T);
    reset.setMnemonic(KeyEvent.VK_T); //used constructor instead
    reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    reset.addActionListener(this);
    
    topTen = new JMenuItem("Top Ten", KeyEvent.VK_T);
    topTen.setMnemonic(KeyEvent.VK_T); //used constructor instead
    topTen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    topTen.addActionListener(this);
    
    resetTopTen = new JMenuItem("Reset Top Ten", KeyEvent.VK_T);
    resetTopTen.setMnemonic(KeyEvent.VK_T); //used constructor instead
    resetTopTen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    resetTopTen.addActionListener(this);
    
    exit = new JMenuItem("Exit", KeyEvent.VK_T);
    exit.setMnemonic(KeyEvent.VK_T); //used constructor instead
    exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    exit.addActionListener(this);
    
    //adding menu items for help
    help = new JMenuItem("Help", KeyEvent.VK_T);
    help.setMnemonic(KeyEvent.VK_T); //used constructor instead
    help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    help.addActionListener(this);
    
    about = new JMenuItem("About", KeyEvent.VK_T);
    about.setMnemonic(KeyEvent.VK_T); //used constructor instead
    about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    about.addActionListener(this);
    
    gameMenu.add(reset);
    gameMenu.add(topTen);
    gameMenu.add(resetTopTen);
    gameMenu.add(exit);
    
    helpMenu.add(help);
    helpMenu.add(about);
    
    f.setJMenuBar(createMenuBar());
    f.setLayout(new FlowLayout());
    f.getContentPane().add(game);
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );     
    f.pack();
    f.setSize(400,600);
    f.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    //TOP TEN
    if (e.getSource() == topTen)
    {
      String fileName = "topTen.txt";
      String line = null;
      
      try {
            FileReader fr = new FileReader(fileName);

            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null) {
                System.out.println(line);
            }   
            br.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");
        }
    }
    //ABOUT
    if(e.getSource() == about){
      System.out.println("Development Team:");
      System.out.println("Lubna Mirza - lmirza3");
      System.out.println("Yordan Machin - ymachi2");
      System.out.println("CS 342 MineSweeper Assignment");
    }
    //RESET
    if (e.getSource() == reset || e.getSource() == smileyButton)
    {
      game.resetGrid();
      Seconds.seconds = 0;
      game.mineFlag = 10;
    }
    //HELP
    if (e.getSource() == help)
    {
      System.out.println("The rules in Minesweeper are simple:");
      System.out.println("- Uncover a mine, and the game ends.");
      System.out.println("- Uncover an empty square, and you keep playing.");
      System.out.println("- Uncover a number, and it tells you how many mines lay hidden in the eight surrounding squares");
    }
    //EXIT
    if (e.getSource() == exit)
    {
      System.exit(0);
    }
    
  }
  
  class CountDown implements ActionListener {
    private JLabel countDownLabel;
    
    
    public CountDown(JLabel countDownLabel) {
      this.countDownLabel = countDownLabel;
    }
    
    //public CountDown(JLabel 
    
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
