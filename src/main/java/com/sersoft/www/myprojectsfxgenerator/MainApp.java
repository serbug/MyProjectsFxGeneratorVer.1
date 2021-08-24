package com.sersoft.www.myprojectsfxgenerator;




//import com.sersoft.www.myprojectsfxgenerator.media.PlayerMusic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//import javax.sound.sampled.AudioFormat;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent parent = loader.load();

    // plays.setVisible(false);


        Scene scene = new Scene(parent);
          scene.getStylesheets().add("/styles/labelStyle.css");
          scene.getStylesheets().add("/styles/myDialogs.css");
        scene.getStylesheets().add("/styles/LoginStyles.css");
       

        stage.getIcons().add(new Image("/fxml/iconapp.png"));
        stage.setTitle("LOGIN");
      stage.setResizable(false);
        stage.setScene(scene);
       
       stage.hide();
        stage.show();
     //  PlayerMusic.playerInto();
//      PlayerMusic.playerTest();

  

    }

 

    public static void main(String[] args) {
        launch(args);
        
    }
    public void playSound() {

			
    }
}
