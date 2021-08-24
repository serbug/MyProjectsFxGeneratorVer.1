/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sersoft.www.myprojectsfxgenerator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Anonyms
 */
public class HelpController implements Initializable {

    @FXML
    private Hyperlink hyperLink;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleHyperLink(ActionEvent event) {
          if(Desktop.isDesktopSupported())
    {
        try {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=SCUwEy2CkHU"));
            } catch (URISyntaxException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    }
    
}
