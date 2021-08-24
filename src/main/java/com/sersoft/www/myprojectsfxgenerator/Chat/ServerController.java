package com.sersoft.www.myprojectsfxgenerator.Chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerController implements Initializable {

    private static final Logger LOG = Logger.getLogger(ServerController.class.getName());
    
    
   ArrayList clientOutputStreams;
   ArrayList<String> users;
    
    @FXML
    private Label label;
    @FXML
    private TextField textFieldPort;
    @FXML
    private TextArea textArea;
    @FXML
    private Button buttonServer;
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonOnline;
    @FXML
    private Button buttonStop;
    
//int port=Integer.parseInt(textFieldPort.getText());
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
 
    }    

    @FXML
    private void handleButtonServer(ActionEvent event) {
        try {
           InetAddress IP=InetAddress.getLocalHost();
           textArea.appendText("IP of my system is := "+IP.toString()+":"+Integer.parseInt(textFieldPort.getText()));
       } catch (UnknownHostException ex) {
           Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
           
       }
  Thread starter = new Thread(new ServerStart());
        starter.start();
        

     //   System.out.println("IP of my system is := "+IP.getHostAddress());
        textArea.appendText("\n Server started...\n ");
      
    }

    @FXML
    private void handleButtonClear(ActionEvent event) {
        
        textArea.clear();

    }

    @FXML
    private void handleButtonOnline(ActionEvent event) {
  textArea.appendText("\n Online users : \n");
        for (String current_user : users)
        {
             textArea.appendText(current_user);
             textArea.appendText("\n");
        }    
        
    }

    @FXML
    private void handleButtonStop(ActionEvent event) {
          try 
        {
            Thread.sleep(500);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        textArea.appendText("Server stopping... \n");
        
        textArea.setText("");

    }
    
       public class ClientHandler implements Runnable	{

       BufferedReader reader;
       Socket sock;
       PrintWriter client;

       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                textArea.appendText("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                textArea.appendText("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                     textArea.appendText(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        tellEveryone(message);
                    } 
                    else 
                    {
                       textArea.appendText("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                textArea.appendText("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }
        public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(Integer.parseInt(textFieldPort.getText()));

                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);

				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				 textArea.appendText("Got a connection. \n");
                }
            }
            catch (Exception ex)
            {
             textArea.appendText("Error making a connection. \n");
            }
        }
    }
    
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
       textArea.appendText("Before " + name + " added. \n");
        users.add(name);
        textArea.appendText("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		textArea.appendText("Sending: " + message + "\n");
                writer.flush();
                textArea.positionCaret(textArea.getLength());

            } 
            catch (Exception ex) 
            {
		textArea.appendText("Error telling everyone. \n");
            }
        } 
    }
}
