
package com.sersoft.www.myprojectsfxgenerator.controllers;

import com.sersoft.www.myprojectsfxgenerator.security.FileDecryption;
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
public class DecryptController implements Initializable {

    @FXML
    private Button buttonEncrypting;
    @FXML
    private Button buttonDeleteDecrypt;
    @FXML
    private TextField txtFieldPasswordDecrypt;
    @FXML
    private Button buttonSaveDecrypt;
    @FXML
    private Button buttonDecrypt;
    
    File file2,file3;
    String pass;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlebuttonEncrypting(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();

        Stage stage = new Stage();
        fileChooser.setTitle("Open File");
         fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ File.separator + "Desktop"));
        fileChooser.getExtensionFilters().addAll(
               // new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("All", "*.*")
        );
        file2 = fileChooser.showOpenDialog(stage);
    }

    @FXML
    private void handleButtonDeleteDecrypt(ActionEvent event) {
         file3.delete();
    }

    @FXML
    private void handlebuttonSaveDecrypt(ActionEvent event) {
          Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ File.separator + "Desktop"));
      
              file3 = fileChooser.showSaveDialog(secondaryStage);
    }

    @FXML
    private void handlebuttonDecrypt(ActionEvent event) {
          pass=txtFieldPasswordDecrypt.getText();
        
        
        
     String[] args = null;
        
     try {
         FileDecryption.main(args, file2, file3,pass);
     } catch (Exception ex) {
         Logger.getLogger(EncryptController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
}
