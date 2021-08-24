package com.sersoft.www.myprojectsfxgenerator.controllers;

import com.sersoft.www.myprojectsfxgenerator.entites.DateSite;
import com.sersoft.www.myprojectsfxgenerator.services.ControllerManager;
import com.sersoft.www.myprojectsfxgenerator.services.DateSiteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author ANONYM
 */
public class AddDateSiteController implements Initializable {

    ControllerManager controllerManager;
    DateSiteService dateSiteService;

    @FXML
    private TextField textFieldSite;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private TextField textFieldPasswordSize;
    @FXML
    private TextField textFieldGeneratePassword;
    @FXML
    private Button buttonGenerate;
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonExit;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button buttonAddDate;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controllerManager = ControllerManager.getInstance();
        dateSiteService = DateSiteService.getInstance();
//      
   }

    @FXML
    private void handleButtonGenerate(ActionEvent event) {
        
      if((textFieldPasswordSize == null || textFieldPasswordSize.getText().trim().isEmpty())){
       Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("Password Size is EMPTY");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if(emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                textFieldPasswordSize.requestFocus();
            }
      
      }
      
       String pass = "";
        Random random = new Random();
        String alfabet = "QWERTYUIOPASDFGHJKLZXCVBNM?!_.-qwertyuiopasdfghjklzxcvbnm1234567890";

        int mar = Integer.parseInt(textFieldPasswordSize.getText());
        if (mar < 8) {  Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("Password Size min. > 8");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if(emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                textFieldPasswordSize.requestFocus();
            }
      
      
      
        } else {
            for (int i = 0; i < mar; i++) {
                char c = alfabet.charAt(random.nextInt(alfabet.length()));
                pass += c;

            }
        }
        textFieldGeneratePassword.setText(pass);
        pass = "";
    
      
       
    }

    @FXML
    private void handleButtonAddDate(ActionEvent event) throws IOException {
         if(isValidInput(event)){
        DateSite dateSite = new DateSite();

        dateSite.setSite(textFieldSite.getText());
        dateSite.setLogin(textFieldLogin.getText());
        dateSite.setPasswordSize(Integer.parseInt(textFieldPasswordSize.getText()));
        dateSite.setPasswordGenerate(textFieldGeneratePassword.getText());

        DateSiteService.add(dateSite);
        ControllerManager.getMainController().populateUsersTable();
         }
    }

    @FXML
    private void handleButtonClear(ActionEvent event) {
        textFieldSite.clear();
        textFieldLogin.clear();
        textFieldPasswordSize.clear();
        textFieldGeneratePassword.clear();
    }

    @FXML
    private void handleButtonExit(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
 private boolean isValidInput(ActionEvent event) {
 Boolean validInput = true;

        if(textFieldSite== null || textFieldSite.getText().trim().isEmpty() ) {
            validInput = false;
            Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("Name Site/App name is EMPTY");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if(emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                textFieldSite.requestFocus();
            }
        }
        if(textFieldLogin == null || textFieldLogin.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyLastName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyLastName.setContentText("Login is EMPTY");
            emptyLastName.initModality(Modality.APPLICATION_MODAL);
            emptyLastName.initOwner(owner);
            emptyLastName.showAndWait();
            if (emptyLastName.getResult() == ButtonType.OK) {
                emptyLastName.close();
                textFieldLogin.requestFocus();
            }
        }
//  if(!textFieldPasswordSize.getText().matches("[0-9]")|| textFieldPasswordSize.getText().trim().isEmpty()){
 if(textFieldPasswordSize == null || textFieldPasswordSize.getText().trim().isEmpty()){
          Alert emptyUIN = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyUIN.setContentText("Password Size is EMPTY or contains character ! ");
            emptyUIN.initModality(Modality.APPLICATION_MODAL);
            emptyUIN.initOwner(owner);
            emptyUIN.showAndWait();
            if (emptyUIN.getResult() == ButtonType.OK) {
                emptyUIN.close();
                textFieldPasswordSize.requestFocus();
            }
        
        }
            if(textFieldGeneratePassword == null || textFieldGeneratePassword.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyNetID = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyNetID.setContentText("Password is EMPTY");
            emptyNetID.initModality(Modality.APPLICATION_MODAL);
            emptyNetID.initOwner(owner);
            emptyNetID.showAndWait();
            if (emptyNetID.getResult() == ButtonType.OK) {
                emptyNetID.close();
                textFieldGeneratePassword.requestFocus();
            }
        }
         return validInput;
          }
}
