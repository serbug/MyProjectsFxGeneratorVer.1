package com.sersoft.www.myprojectsfxgenerator.services;

import com.sersoft.www.myprojectsfxgenerator.entites.DateSite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class DateSiteService {
    
    private static DateSiteService dateSiteService;

    private DateSiteService() {
    }

    public static DateSiteService getInstance() {
        if (dateSiteService == null) {
            dateSiteService = new DateSiteService();
        }
        return dateSiteService;
    }
    
    /**
     *
     */
  //  ObservableList dateSite = new ArrayList<>();
    
 public static ObservableList<DateSite> dateSite = FXCollections.observableArrayList();
    
    public ObservableList<DateSite> findAll() {
        return dateSite;
    }
    
    public static void add(DateSite dates){
        dateSite.add(dates);
    }
    
    public static void delete(DateSite dates){
        dateSite.remove(dates);
    }
}