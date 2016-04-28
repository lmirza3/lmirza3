import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tetris extends JFrame //implements Runnable
{
  
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

    public JLabel score;
    
    public Tetris()
    {
      super("Tetris - Hazari_Mirza");
      
      setLayout(new BorderLayout());
      
      score = new JLabel("Score: 0");
      add(score, BorderLayout.NORTH);
      
      setSize(400, 600);
      setLocationRelativeTo(null);
      setJMenuBar(createMenuBar());

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
      //set up the board 
      gameBoard board = new gameBoard(this);    //inherit the board
    }
    
    public JLabel updateScore()
    {
      return score;
    }
    
    public static void main(String[] args) 
    {
      new Tetris();
    } 
}