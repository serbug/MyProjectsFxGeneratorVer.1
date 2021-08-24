/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sersoft.www.myprojectsfxgenerator.controllers;


import com.sersoft.www.myprojectsfxgenerator.security.FileEncryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Anonyms
 */
public class EncryptController implements Initializable {
 File file,file1;
 String pass;
    @FXML
    private Button buttonEncrypt;
    @FXML
    private Button buttonOriginalText;
    @FXML
    private Button buttonSaveEncrypting;
    @FXML
    private TextField txtFieldPasswordEncrypt;
    private TextField txtFieldPasswordDecrypt;
    @FXML
    private Button buttonDelete;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void handlebuttonEncrypt(ActionEvent event) {
   
        pass=txtFieldPasswordEncrypt.getText();
     String[] args = null;
        
     try {
         FileEncryption.main(args, file,file1,pass);
     } catch (Exception ex) {
         Logger.getLogger(EncryptController.class.getName()).log(Level.SEVERE, null, ex);
     }
      
    }

    
    @FXML
    private void handlebuttonOriginalText(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();

        Stage stage = new Stage();
        fileChooser.setTitle("Open File");
         fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ File.separator + "Desktop"));
        fileChooser.getExtensionFilters().addAll(
               // new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("All", "*.*")
        );
        file = fileChooser.showOpenDialog(stage);
    }

    @FXML
    private void handlebuttonSaveEncrypting(ActionEvent event) {
         Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ File.separator + "Desktop"));
     
              file1 = fileChooser.showSaveDialog(secondaryStage);
     
    }

    

    @FXML
    private void handleButtonDeleteOriginal(ActionEvent event) {
        file.delete();
    }

  
}
