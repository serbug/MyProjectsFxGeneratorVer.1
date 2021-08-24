package com.sersoft.www.myprojectsfxgenerator.services;

import com.sersoft.www.myprojectsfxgenerator.controllers.AddDateSiteController;
import com.sersoft.www.myprojectsfxgenerator.controllers.LoginController;
import com.sersoft.www.myprojectsfxgenerator.controllers.MainController;

/**
 * Created by denees on 15/07/16.
 */
public class ControllerManager {

    private static ControllerManager controllerManager;

    private static MainController mainController;
    private static LoginController loginController;
    private static AddDateSiteController optionsController;

    
       private ControllerManager() {
           
    }
       
     public static ControllerManager getInstance() {
        if (controllerManager == null) {
            controllerManager = new ControllerManager();
        }
        return controllerManager;
    }
    
    public static ControllerManager getControllerManager() {
        return controllerManager;
    }

    public static void setControllerManager(ControllerManager aControllerManager) {
        controllerManager = aControllerManager;
    }

    public static MainController getMainController() {
        return mainController;
    }

    public static void setMainController(MainController aMainController) {
        mainController = aMainController;
    }

    public static LoginController getLoginController() {
        return loginController;
    }

    public static void setLoginController(LoginController aLoginController) {
        loginController = aLoginController;
    }

    public static AddDateSiteController getOptionsController() {
        return optionsController;
    }

    public static void setOptionsController(AddDateSiteController aOptionsController) {
        optionsController = aOptionsController;
    }

 

   

  
}
