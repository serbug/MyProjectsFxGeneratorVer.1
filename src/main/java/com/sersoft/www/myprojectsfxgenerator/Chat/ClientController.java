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
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField textFieldAddress;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textFieldPort;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private TextField textFieldMessage;
    @FXML
    private Button buttonAnonym;
    @FXML
    private Button buttonConnect;
    @FXML
    private Button buttonDisconnect;
    @FXML
    private Button buttonSend;

    String username =null;

   

    ArrayList<String> users = new ArrayList();

   

    Boolean isConnected = false;

    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//       port=Integer.parseInt(textFieldPort.getText());
    }

    @FXML
    private void handleButtonAnonym(ActionEvent event) {
             textFieldUsername.setText("");
        if (isConnected == false) 
        {
            String anon="anonym-";
            Random generator = new Random(); 
            int i = generator.nextInt(999) + 1;
            String is=String.valueOf(i);
            anon=anon.concat(is);
            username=anon;
            
            textFieldUsername.setText(anon);
            textFieldUsername.setEditable(false);

            try 
            {
//                sock = new Socket(address, port);
                sock = new Socket(textFieldAddress.getText(), Integer.parseInt(textFieldPort.getText()));
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                textArea.appendText("Cannot Connect! Try Again. \n");
                textFieldUsername.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            textArea.appendText("You are already connected. \n");
        }        
    }

    @FXML
    private void handleButtonConnect(ActionEvent event) {
           if (isConnected == false) 
        {
            username = textFieldUsername.getText();
            textFieldUsername.setEditable(false);

            try 
            {
                
                sock = new Socket(textFieldAddress.getText(), Integer.parseInt(textFieldPort.getText()));
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
             
            } 
            catch (Exception ex) 
            {
                textArea.appendText("Cannot Connect! Try Again. \n");
                textFieldUsername.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            textArea.appendText("You are already connected. \n");
        }
            //label.setText("Sunteti connectat!");
             // textArea.appendText("Sunteti connectat! \n");
    }

    @FXML
    private void handleButtonDisconnect(ActionEvent event) {
         sendDisconnect();
        Disconnect();
    }

    @FXML
    private void handleButtonSend(ActionEvent event) {
          apasa();
    }
     @FXML
    private void handleTextFieldMessage(ActionEvent event) {
        apasa();
    }
    void apasa(){
          String nothing = "";
        if ((textFieldMessage.getText()).equals(nothing)) {
            textFieldMessage.setText("");
            textFieldMessage.requestFocus();
        } else {
            try {
               writer.println(username + ":" + textFieldMessage.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                textArea.appendText("Message was not sent. \n");
            }
            textFieldMessage.setText("");
            textFieldMessage.requestFocus();
        }

        textFieldMessage.setText("");
        textFieldMessage.requestFocus();
    
    }
      public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    //--------------------------//
    
    public void userRemove(String data) 
    {
         textArea.appendText(data + " is now offline.\n");
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             users.add(token + "\n");
         }
    }
    
    //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
             textArea.appendText("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
              textArea.appendText("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
              textArea.appendText("Failed to disconnect. \n");
        }
        isConnected = false;
        textFieldUsername.setEditable(true);

    }

   
     public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        textArea.appendText(data[0] + ": " + data[1] + "\n");
                        textArea.positionCaret(textArea.getLength());  //ta_chat.getDocument()
                     } 
                     else if (data[2].equals(connect))
                     {
                        textArea.clear();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }

}
