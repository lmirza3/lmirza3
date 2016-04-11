import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ChatAppClient extends JFrame implements ActionListener, WindowListener
{  
  //Identifier
  int clientNum;
  String clientName;
  
  
  // GUI items
  JButton sendButton;
  JButton connectButton;
  JTextField machineInfo;
  JTextField portInfo;
  JTextField message;
  JTextArea history;
  JList clientList;
  DefaultListModel list;
  
  // Network Items
  boolean connected;
  Socket echoSocket;
  PrintWriter out;
  BufferedReader in;
  
  
  // set up GUI
  public ChatAppClient()
  {
    super( "Echo Client" );
    
    // get content pane and set its layout
    Container container = getContentPane();
    container.setLayout (new BorderLayout ());
    
    // set up the North panel
    JPanel upperPanel = new JPanel ();
    upperPanel.setLayout (new GridLayout (4,2));
    container.add (upperPanel, BorderLayout.NORTH);
    
    // create buttons
    connected = false;
    
    upperPanel.add ( new JLabel ("Message/Name Change: ", JLabel.RIGHT) );
    message = new JTextField ("");
    message.addActionListener( this );
    upperPanel.add( message );
    
    sendButton = new JButton( "Send Message" );
    sendButton.addActionListener( this );
    sendButton.setEnabled (false);
    upperPanel.add( sendButton );
    
    connectButton = new JButton( "Connect to Server" );
    connectButton.addActionListener( this );
    upperPanel.add( connectButton );
    
    upperPanel.add ( new JLabel ("Server Address: ", JLabel.RIGHT) );
    machineInfo = new JTextField ("127.0.0.1");
    upperPanel.add( machineInfo );
    
    upperPanel.add ( new JLabel ("Server Port: ", JLabel.RIGHT) );
    portInfo = new JTextField ("");
    upperPanel.add( portInfo );
    
    history = new JTextArea ( 5, 20 );
    history.setEditable(false);
    container.add( new JScrollPane(history) ,  BorderLayout.CENTER);
    
    list = new DefaultListModel();
    clientList = new JList(list);
    container.add( new JScrollPane(clientList) ,  BorderLayout.EAST);
    
    setSize( 600, 350 );
    setVisible( true );
    addWindowListener(this);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    
    /*WindowListener listener = new WindowAdapter(){
     public void windowClosing(WindowEvent w){
     if(connected){
     JOptionPane.showMessageDialog(frame, "Please Disconnect From Server Before Closing Window.");
     
     }
     }
     }*/
    
  } // end CountDown constructor
  
  public static void main( String args[] )
  { 
    ChatAppClient application = new ChatAppClient();
    
    //application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  }
  
  // handle button event
  public void actionPerformed( ActionEvent event )
  {
    if ( connected && 
        (event.getSource() == sendButton || 
         event.getSource() == message ) )
    {
      
      java.util.List checkList = clientList.getSelectedValuesList();
      
      if(checkList.isEmpty())
      {
        history.insert("You need to select a recipient!\n", 0);
      }
      else
        doSendMessage();
    }
    else if (event.getSource() == connectButton)
    {
      doManageConnection();
    }
  }
  @Override
  public void windowClosing(WindowEvent e) {
    if(connected){
      JOptionPane.showMessageDialog(this, "Please Disconnect From Server Before Closing Window.");
    }
    else{
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //System.exit(0);  // Terminate the program
    }
  }
  
  // Not Used, but need to provide an empty body
  @Override
  public void windowOpened(WindowEvent e) { }
  @Override
  public void windowClosed(WindowEvent e) { }
  @Override
  public void windowIconified(WindowEvent e) { }
  @Override
  public void windowDeiconified(WindowEvent e) { }
  @Override
  public void windowActivated(WindowEvent e) { }
  @Override
  public void windowDeactivated(WindowEvent e) { }
  
  public void doSendMessage()
  {
    try
    {
      //Change Client Name
      if(message.getText().startsWith("Name: ")) 
      {
        if (message.getText().indexOf(' ', 6) == -1 && !message.getText().equals("Name: "))
        {
          out.println(message.getText());
          clientName = message.getText().split(" ")[1];
          return;
        }
        else
          JOptionPane.showMessageDialog(this, "Name must not contain any spaces.");
      }
      
      java.util.List sendList = clientList.getSelectedValuesList();
      
      if(sendList.get(0).toString().equals("All "))
      {
        sendList.clear();
        int size = clientList.getModel().getSize();
        
        for(int i = 1; i < size; ++i)
        {
          sendList.add(clientList.getModel().getElementAt(i));
        }
      }
      
      String messageInfo = "Message: ";
      
      for(Object item : sendList)
      {
        messageInfo = messageInfo + item.toString() + " ";
      }
      
      //System.out.println(messageInfo);
      out.println(messageInfo);
      
      out.println(message.getText());
      //history.insert ("From Server: " + in.readLine() + "\n" , 0);
      //out.println(message.getText());
      //clientList.insert ("Client: " + in.readLine() + "\n" , 0);
    }
    catch (Exception e) 
    {
      history.insert ("Error in processing message \n", 0);
    }
  }
  
  public void doManageConnection()
  {
    if (connected == false)
    {
      String machineName = null;
      int portNum = -1;
      try {
        machineName = machineInfo.getText();
        portNum = Integer.parseInt(portInfo.getText());
        echoSocket = new Socket(machineName, portNum );
        out = new PrintWriter(echoSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(
                                                      echoSocket.getInputStream()));
        
        //Get Client number
        try
        {
          String clientLine = in.readLine();
          String clientInfo[] = clientLine.split(" ");
          clientNum = Integer.parseInt(clientInfo[3]);
          clientName = "Client" + clientNum;
          System.out.println("This is client " + clientNum);
          history.insert ("From Server: " + clientLine + "\n" , 0);
          
        }
        catch (IOException e) 
        {
          history.insert ("Error in processing message ", 0);
        }
        
        sendButton.setEnabled(true);
        connected = true;
        connectButton.setText("Disconnect from Server");
      } catch (NumberFormatException e) {
        history.insert ( "Server Port must be an integer\n", 0);
      } catch (UnknownHostException e) {
        history.insert("Don't know about host: " + machineName , 0);
      } catch (IOException e) {
        history.insert ("Couldn't get I/O for "
                          + "the connection to: " + machineName , 0);
      }
      new ListeningThread (out,in);
    }
    else
    {
      try 
      {
        //send info to server to remove the client
        out.println("Remove: " + clientName);
        DefaultListModel model = (DefaultListModel) clientList.getModel();
        model.remove(clientNum);
        
        
        //fix the indices in the list. 
        out.close();
        in.close();
        echoSocket.close();
        sendButton.setEnabled(false);
        connected = false;
        connectButton.setText("Connect to Server");
        history.insert ("disconnected from server...\n" , 0);
        //this.setVisible(false); //you can't see me!
        //this.dispose();
        //list.removeAllElements();
      }
      catch (IOException e) 
      {
        history.insert ("Error in closing down Socket ", 0);
      }
    }
    
    
  }
  
  
  class ListeningThread extends Thread
  {
    private PrintWriter out;
    private BufferedReader in;
    public ListeningThread (PrintWriter output, BufferedReader input) 
    {
      out = output;
      in = input; 
      start();
    }
    
    public void run()
    {
      String inputLine;
      try {
        while ((inputLine = in.readLine()) != null) 
        { 
          //Update ClientList
          if (inputLine.startsWith("All "))
          {
            System.out.println ("Server: " + inputLine); 
            list.clear();
            for (String name : inputLine.split(" "))
            {
              list.addElement(name);
            }  
          }
          
          //Print Messages from Server
          if (inputLine.startsWith("From Server: "))
          {
            if(inputLine.startsWith("From Server: You are now "))
            {
              clientName = inputLine.split(" ")[5];
            }
            System.out.println (inputLine);
            history.insert(inputLine + "\n", 0);
          }
          else if (inputLine.startsWith("From ") || inputLine.startsWith("Me: ")) //Print Incoming Messages
          {
            System.out.println (inputLine); 
            history.insert(inputLine + "\n", 0);
          }
        }
      }
      catch (IOException e) {
        System.out.println("Error");
      }
    }
  }
  
} // end class EchoServer3
