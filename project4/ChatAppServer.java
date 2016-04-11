import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ChatAppServer extends JFrame implements ActionListener{
  
  // GUI items
  JButton ssButton;
  JLabel machineInfo;
  JLabel portInfo;
  JTextArea history;
  private boolean running;
  
  public static Vector<Client> names = new Vector<Client>();
  //public static HashSet<String> names = new HashSet<String>();
  
  // Network Items
  boolean serverContinue;
  ServerSocket serverSocket;
  
  // set up GUI
  public ChatAppServer()
  {
    super( "Echo Server" );
    
    // get content pane and set its layout
    Container container = getContentPane();
    container.setLayout( new FlowLayout() );
    
    // create buttons
    running = false;
    ssButton = new JButton( "Start Listening" );
    ssButton.addActionListener( this );
    container.add( ssButton );
    
    String machineAddress = null;
    try
    {  
      InetAddress addr = InetAddress.getLocalHost();
      machineAddress = addr.getHostAddress();
    }
    catch (UnknownHostException e)
    {
      machineAddress = "127.0.0.1";
    }
    machineInfo = new JLabel (machineAddress);
    container.add( machineInfo );
    portInfo = new JLabel (" Not Listening ");
    container.add( portInfo );
    
    history = new JTextArea ( 10, 40 );
    history.setEditable(false);
    container.add( new JScrollPane(history) );
    
    setSize( 500, 250 );
    setVisible( true );
    
    //Vector<Integer> names = new Vector<Integer>();
    //HashSet<String> names = new HashSet<String>();
    
  } // end CountDown constructor
  
  public static void main( String args[] )
  { 
    ChatAppServer application = new ChatAppServer();
    application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  }
  
  // handle button event
  public void actionPerformed( ActionEvent event )
  {
    if (running == false)
    {
      running = true;
      new ConnectionThread (this);
    }
    else
    {
      serverContinue = false;
      running = false;
      ssButton.setText ("Start Listening");
      portInfo.setText (" Not Listening ");
    }
  }
  
  
} // end class EchoServer3


class ConnectionThread extends Thread
{
  ChatAppServer gui;
  
  public ConnectionThread (ChatAppServer es3)
  {
    gui = es3;
    start();
  }
  
  public void run()
  {
    gui.serverContinue = true;
    
    try 
    { 
      gui.serverSocket = new ServerSocket(0); 
      gui.portInfo.setText("Listening on Port: " + gui.serverSocket.getLocalPort());
      System.out.println ("Connection Socket Created");
      try { 
        while (gui.serverContinue)
        {
          System.out.println ("Waiting for Connection");
          gui.ssButton.setText("Stop Listening");
          new CommunicationThread (gui.serverSocket.accept(), gui); 
        }
      } 
      catch (IOException e) 
      { 
        System.err.println("Accept failed."); 
        System.exit(1); 
      } 
    } 
    catch (IOException e) 
    { 
      System.err.println("Could not listen on port: 10008."); 
      System.exit(1); 
    } 
    finally
    {
      try {
        gui.serverSocket.close(); 
      }
      catch (IOException e)
      { 
        System.err.println("Could not close port: 10008."); 
        System.exit(1); 
      } 
    }
  }
}


class CommunicationThread extends Thread
{ 
  //private boolean serverContinue = true;
  private Socket clientSocket;
  private ChatAppServer gui;
  
  
  
  public CommunicationThread (Socket clientSoc, ChatAppServer ec3)
  {
    clientSocket = clientSoc;
    gui = ec3;
    start();
    
  }
  
  public Client getClient(Vector<Client> names, String clientName)
  {
    for(Client c : gui.names)
    {
      if(c.getName().equals(clientName))
      {
        return c;
      }
    }
    //System.out.println(clientName);
    System.err.println("client is not connected to Server");
    return new Client();
  }
  
  
  public void run()
  {
    System.out.println ("New Communication Thread Started");
    
    try { 
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                        true); 
      BufferedReader in = new BufferedReader( 
                                             new InputStreamReader( clientSocket.getInputStream())); 
      
      Client connectedClient = new Client(out, in);
      String name = connectedClient.getName();
      
      //gui.clientList.addElement(connectedClient.clientNo);
      connectedClient.getOut().println("You are Client " + connectedClient.getClientNo());
      gui.history.insert ("There are " +
                          connectedClient.getNumClients() +
                          " clients currently connected." +
                          "\n", 0);
      
      
      
      if (!gui.names.contains(connectedClient)) {
        gui.names.add(connectedClient);
      }
      
      //Update clientList in Client
      String clients = "All ";
      for (Client c1 : gui.names) 
      {
        clients = clients + " " + c1.getName();  
      }
      for (Client c : gui.names)
      {
        c.getOut().println(clients);
      }
      
      gui.history.insert(gui.names+"\n", 0);
      System.out.println(gui.names);
      
      String inputLine; 
      
      while ((inputLine = in.readLine()) != null) 
      { 
        //Remove disconnected clients
        if(inputLine.startsWith("Remove: "))
        {
          
          for (int i = 0; i < gui.names.size(); i++)
          {
            System.out.println(gui.names.get(i).clientName);
            System.out.println(inputLine.split(" ")[1]);
            if (gui.names.get(i).clientName.equals(inputLine.split(" ")[1]))
            {
              gui.names.remove(i);
              connectedClient.decrementClientCount();
            }
          }
          
          //Update clientList in Client
          clients = "All ";
          for (Client c1 : gui.names) 
          {
            clients = clients + " " + c1.getName();  
          }
          for (Client c : gui.names)
          {
            c.getOut().println(clients);
          }
        }
        
        //Change ClientName
        if(inputLine.startsWith("Name: "))
        {
          connectedClient.setName(inputLine.split(" ")[1]);
          
          //Update clientList in Client
          clients = "All ";
          for (Client c1 : gui.names) 
          {
            clients = clients + " " + c1.getName();  
          }
          for (Client c : gui.names)
          {
            c.getOut().println(clients);
          }
          
          connectedClient.getOut().println("From Server: You are now " + inputLine.split(" ")[1]);
        }
        
        //Send Message
        if(inputLine.startsWith("Message: "))
        {
          System.out.println("Message "+inputLine);
          String[] sendList = inputLine.split(" ");
          String messageTo = in.readLine();
          
          
          System.out.println("Message is " + messageTo);
          
          for(String c : sendList)
          {
            
            if(c.equals("Message:"))
            {
              continue;
            }
            if(c.equals("All")){
              for (Client cc : gui.names)
              {
                System.out.println("For client " + cc.getName());
                cc.getOut().println("From " + connectedClient.getName() + ": " + messageTo);
                connectedClient.getOut().println("Me: " + messageTo);
              }
              break;
            }
            else{
              System.out.println("For client " + getClient(gui.names, c).getName());
              getClient(gui.names, c).getOut().println("From " + connectedClient.getName() + ": " + messageTo);
              connectedClient.getOut().println("Me: " + messageTo);
              break;
            }
          }
          
          gui.history.insert(messageTo + "\n", 0);
        }
        
        //System.out.println ("Server: " + inputLine); 
        //gui.history.insert (inputLine + "\n", 0);
        //out.println(inputLine); 
        
        if (inputLine.equals("Bye.")) 
          break; 
        
        
        if (inputLine.equals("End Server.")) 
          gui.serverContinue = false; 
      } 
      /*******************************************************************************************/
      //gui.names.remove(name);
      connectedClient.connected = false;
      
      for (Client c : gui.names){
        c.getOut().println(clients);
      }
      
      out.close(); 
      in.close(); 
      clientSocket.close(); 
    } 
    catch (IOException e) 
    {  
      System.err.println("Problem with Communication Server");
      //System.exit(1); 
    } 
  }
}

class Client 
{
  private PrintWriter output;
  private BufferedReader input;
  protected int clientNo;
  protected String clientName;
  private static int numClients = 0;
  public boolean connected;
  
  public Client (PrintWriter out, BufferedReader in){
    output = out;
    input = in;
    clientNo = numClients;
    ++numClients;
    connected = true;
    clientName = "Client" + clientNo;
  }
  
  public Client(){
    connected = false;
    clientName = "garbageClient";
  }
  
  public void decrementClientCount(){
    --numClients;
  }
  
  public void setName(String name){
    clientName = name;
  }
  
  public String getName(){
    return clientName;
  }
  
  public PrintWriter getOut(){
    return output;
  }
  
  public BufferedReader getIn(){
    return input;
  }
  
  public int getClientNo(){
    return clientNo;
  }
  
  public int getNumClients(){
    return numClients;
  }
  
  public boolean isConnected(){
    return connected;
  }
  
  public void toggleConnected(){
    connected = !connected;
  }
}