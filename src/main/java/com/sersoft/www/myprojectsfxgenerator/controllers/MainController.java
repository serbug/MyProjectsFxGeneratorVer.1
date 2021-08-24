package com.sersoft.www.myprojectsfxgenerator.controllers;


import com.sersoft.www.myprojectsfxgenerator.IO.FileIO;
import com.sersoft.www.myprojectsfxgenerator.entites.DateSite;
import com.sersoft.www.myprojectsfxgenerator.services.ControllerManager;
import com.sersoft.www.myprojectsfxgenerator.services.DateSiteService;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sersoft.www.myprojectsfxgenerator.services.DateSiteService.dateSite;



/**
 * FXML Controller class
 *
 * @author ANONYM
 */
public class MainController implements Initializable {

    ControllerManager controllerManager;
    DateSiteService dateSiteService;
  

    @FXML
    private Button buttonMainAdd;
    @FXML
    private Button buttonMainDelete;
    @FXML
    private Button buttonPlay;
    @FXML
    private Button buttonPause;
    @FXML
    private TextField textFieldMainFilterTable;

    @FXML
   private TableView<DateSite> tableView;
    @FXML
    private TableColumn<DateSite, String> site;
    @FXML
    private TableColumn<DateSite, String> login;
    @FXML
    private TableColumn<DateSite, Integer> passwordSize;
    @FXML
    private TableColumn<DateSite, String> passwordGenerate;
    @FXML
    private MenuBar fileMenu;
    @FXML
    private MenuItem MenuItemOpen;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
      File file = null;

 
 
    private Stage primaryStage;
    @FXML
    private MenuItem MenuItemServer;
    @FXML
    private MenuItem menuItemClient;
    @FXML
    private MenuItem MenuItemSave;
    @FXML
    private MenuItem MenuItemEncrypt;
    @FXML
    private MenuItem MenuItemDecrypt;
    @FXML
    private MenuItem MenuItemAbout;
    @FXML
    private MenuItem MenuItemHelp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        controllerManager = ControllerManager.getInstance();
        dateSiteService = DateSiteService.getInstance();

        textFieldMainFilterTable.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterSiteList((String) oldValue, (String) newValue);

            }

        });

      
        tableView.setEditable(true);
        site.setOnEditCommit(e -> site_OnEditCommit(e));
        login.setOnEditCommit(e -> login_OnEditCommit(e));
        passwordSize.setOnEditCommit(e -> passwordSize_OnEditCommit(e));
        passwordGenerate.setOnEditCommit(e -> passwordGenerat_OnEditCommit(e));

       tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        site.setCellFactory(TextFieldTableCell.forTableColumn());
        login.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordSize.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        passwordGenerate.setCellFactory(TextFieldTableCell.forTableColumn());
        

         
        buttonMainDelete.setDisable(true);
       tableView.setItems(dateSite);
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setPlaceholder(new Label("Your Table is Empty"));

        tableView.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (tableView.isFocused()) {
                    buttonMainDelete.setDisable(false);
                }
            }
        });
 //tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    @FXML
    private void handlebuttonMainAdd(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/addDateSite.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
         scene.getStylesheets().add("/styles/myDialogs.css");
        scene.getStylesheets().add("/styles/AddUserStyles.css");

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("ADD NEW DATE");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handlebuttonMainDelete(ActionEvent event) {
        if (!dateSite.isEmpty() && tableView.getSelectionModel().getSelectedItems()!=null) {
         
                        Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.YES, ButtonType.NO);

            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Are you sure you want to delete this?\n\nTHIS CANNOT BE UNDONE.");
            
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if (deleteAlert.getResult() == ButtonType.YES) {
            
dateSite.removeAll( tableView.getSelectionModel().getSelectedItems());
          
               tableView.getSelectionModel().clearSelection();
               populateUsersTable();
  

            } else {
                deleteAlert.close();
            }
        }

     // tableView.setItems(FXCollections.observableArrayList(dateSiteService.findAll()));

    }

    public  void populateUsersTable() {
        site.setCellValueFactory(new PropertyValueFactory<>("site"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordSize.setCellValueFactory(new PropertyValueFactory<>("passwordSize"));
        passwordGenerate.setCellValueFactory(new PropertyValueFactory<>("passwordGenerate"));
      
    }

    @FXML
    private void handleMenuItemOpen(ActionEvent event) throws IOException {
        dateSite.clear();
          FileChooser fileChooser = new FileChooser();

        Stage stage = new Stage();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
               // new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("All", "*.*")
        );
        file = fileChooser.showOpenDialog(stage);
          
        FileIO.citeste(dateSite,file, tableView);
        
       populateUsersTable();

    }

    @FXML
    private void handleMenuItemSave(ActionEvent event) throws IOException {
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ File.separator + "Desktop"));
        if (dateSite.isEmpty()) {
            secondaryStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "EMPTY TABLE", ButtonType.OK);
            emptyTableAlert.setContentText("You have nothing to save");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(this.fileMenu.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if (emptyTableAlert.getResult() == ButtonType.OK) {
                emptyTableAlert.close();
            }
        } else {
              file = fileChooser.showSaveDialog(secondaryStage);
       
   
            if (file != null) {
               // scrie(dateSite, file);
                FileIO.scrie(dateSite, file);
            }
        }
       
        // File inputFile = new File("document.txt");
      
      
     
    }

    @FXML
    private void handleMenuItemExit(ActionEvent event) {
  Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.YES, ButtonType.NO);
        Stage stage = (Stage) fileMenu.getScene().getWindow();
        exitAlert.setContentText("Are you sure you want to exit?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();

        if(exitAlert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
        else {
            exitAlert.close();
        }
    }

    public void showMain() {
        primaryStage.show();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleButtonPLay(ActionEvent event) {
        
    //    PlayerMusic.player.play();
    }

    @FXML
    private void handleButtonPause(ActionEvent event) {
      //  PlayerMusic.player.pause();
    }

    public void site_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<DateSite, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DateSite, String>) e;
        DateSite dateSite = cellEditEvent.getRowValue();
        dateSite.setSite(cellEditEvent.getNewValue());
    }

    public void login_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<DateSite, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DateSite, String>) e;
        DateSite dateSite = cellEditEvent.getRowValue();
        dateSite.setLogin(cellEditEvent.getNewValue());
    }

    public void passwordSize_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<DateSite, Integer> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DateSite, Integer>) e;
        DateSite dateSite = cellEditEvent.getRowValue();
        dateSite.setPasswordSize(cellEditEvent.getNewValue());
    }

    public void passwordGenerat_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<DateSite, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DateSite, String>) e;
        DateSite dateSite = cellEditEvent.getRowValue();
        dateSite.setPasswordGenerate(cellEditEvent.getNewValue());
    }

    public void filterSiteList(String oldValue, String newValue) {
        ObservableList<DateSite> filteredList = FXCollections.observableArrayList();
        if (textFieldMainFilterTable == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tableView.setItems(dateSite);
        } else {
            newValue = newValue.toUpperCase();
            for (DateSite students : tableView.getItems()) {
                String filterFirstName = students.getSite();
                String filterLastName = students.getLogin();
                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filteredList.add(students);
                }
            }
            tableView.setItems(filteredList);
        }
    }

    @FXML
    private void handleMenuItemServer(ActionEvent event) {
          Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Server.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
         scene.getStylesheets().add("/styles/myDialogs.css");
        scene.getStylesheets().add("/styles/ServerStyles.css");

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("SERVER CHAT");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void handleMenuItemClient(ActionEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Client.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
         scene.getStylesheets().add("/styles/myDialogs.css");
        scene.getStylesheets().add("/styles/ClientStyle.css");

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("CHAT CLIENT");
        stage.setScene(scene);
        stage.showAndWait();
    }

   
    @FXML
    private void handleMenuItemDecrypt(ActionEvent event)  {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/decrypt.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/DecryptStyle.css");

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Decrypt File");
        stage.setScene(scene);
        stage.showAndWait();

//FileChooser fileChooser = new FileChooser();
//
//        Stage stage = new Stage();
//        fileChooser.setTitle("Open File");
//         fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ File.separator + "Desktop"));
//        fileChooser.getExtensionFilters().addAll(
//               // new FileChooser.ExtensionFilter("CSV", "*.csv"),
//                new FileChooser.ExtensionFilter("All", "*.*")
//        );
//        file = fileChooser.showOpenDialog(stage);
// SecretKey key =null;
//     
//  //creating file input stream to read from file
//  try(FileInputStream fis = new FileInputStream(file)){
//   //creating object input stream to read objects from file
//   ObjectInputStream ois = new ObjectInputStream(fis);
//   key = (SecretKey)ois.readObject();  //reading key used for encryption
//    
//   Cipher aesCipher = Cipher.getInstance("AES");  //getting cipher for AES
//   aesCipher.init(Cipher.DECRYPT_MODE, key);  //initializing cipher for decryption with key
//   //creating file output stream to write back original contents
//   try(FileOutputStream fos = new FileOutputStream(file+".txt")){
//    //creating cipher input stream to read encrypted contents
//    try(CipherInputStream cis = new CipherInputStream(fis, aesCipher)){
//     int read;
//     byte buf[] = new byte[4096];
//     while((read = cis.read(buf)) != -1)  //reading from file
//      fos.write(buf, 0, read);  //decrypting and writing to file
//    }
//   }
//  }
    }

    @FXML
    private void handleMenuItemEncrypt(ActionEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Encrypt.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/EncryptStyle.css");

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Encrypt File");
        stage.setScene(scene);
        stage.showAndWait();
// FileChooser fileChooser = new FileChooser();
//
//        Stage stage = new Stage();
//        fileChooser.setTitle("Open File");
//         fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ File.separator + "Desktop"));
//        fileChooser.getExtensionFilters().addAll(
//               // new FileChooser.ExtensionFilter("CSV", "*.csv"),
//                new FileChooser.ExtensionFilter("All", "*.*")
//        );
//        file = fileChooser.showOpenDialog(stage);
//      File file1=new File("filesencrypt.txt");
//      
// KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//  keyGen.init(256);  //using AES-256
//  SecretKey key = keyGen.generateKey();  //generating key
//  Cipher aesCipher = Cipher.getInstance("AES");  //getting cipher for AES
//  aesCipher.init(Cipher.ENCRYPT_MODE, key);  //initializing cipher for encryption with key
//   
//  //creating file output stream to write to file
//  try(FileOutputStream fos = new FileOutputStream(file1)){
//   //creating object output stream to write objects to file
//   ObjectOutputStream oos = new ObjectOutputStream(fos);
//   oos.writeObject(key);  //saving key to file for use during decryption
// 
//   //creating file input stream to read contents for encryption
//   try(FileInputStream fis = new FileInputStream(file)){
//    //creating cipher output stream to write encrypted contents
//    try(CipherOutputStream cos = new CipherOutputStream(fos, aesCipher)){
//     int read;
//     byte buf[] = new byte[4096];
//     while((read = fis.read(buf)) != -1)  //reading from file
//      cos.write(buf, 0, read);  //encrypting and writing to file
//    }
//   }
//  }
    }

    @FXML
    private void handleMenuItemAbout(ActionEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/About.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/AboutStyles.css");

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("About");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void handleMenuItemHelp(ActionEvent event) {
       
//         WebView webview = new WebView();
//    webview.getEngine().load(
//      "https://www.youtube.com/watch?v=SCUwEy2CkHU"
//    );
//    webview.setPrefSize(1920, 1080);
// Stage stage = new Stage();
//    stage.setScene(new Scene(webview));
//    stage.show();

 Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Help.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/HelpStyles.css");

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Help");
        stage.setScene(scene);
        stage.showAndWait();
    }

    



}

