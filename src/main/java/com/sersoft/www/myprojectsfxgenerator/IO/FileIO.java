
package com.sersoft.www.myprojectsfxgenerator.IO;


import com.sersoft.www.myprojectsfxgenerator.entites.DateSite;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.*;





public  class FileIO {
     
 DateCSVUtil u = new DateCSVUtil();
   public static void scrie(ObservableList<DateSite> dateSites, File file) throws IOException {
   
       try (BufferedWriter fw = new BufferedWriter(new FileWriter(file))) { // +".csv" true daca dorim ca fisierul sa nu se stearga continul si sai adaugam altul nou 
 
 
            for (DateSite dateSite : dateSites) {
                
                fw.write(DateCSVUtil.toText(dateSite));
              
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    
 
    }

    public static void citeste(ObservableList<DateSite> dateSites,File file,TableView<DateSite> tableView) throws IOException {
    
    
   
        try (FileReader fr = new FileReader(file); 
                BufferedReader br = new BufferedReader(fr);) {

            String linie;
            while ((linie = br.readLine()) != null) {

                DateSite dateSite = DateCSVUtil.fromText(linie);
                dateSites.add(dateSite);
            }

        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
     tableView.setItems(dateSites);

     
    }
   
}

class DateCSVUtil {

    @SuppressWarnings("empty-statement")
    public static String toText(DateSite ang) {

        // reflection p/n ceva automatizat
        //ang.getClass().getFields() ....
        //
      
        StringBuilder sb = new StringBuilder();

        sb.append(ang.getSite()).append(",");
        sb.append(ang.getLogin()).append(",");
        sb.append(ang.getPasswordSize()).append(",");
        sb.append(ang.getPasswordGenerate()).append("\n");

       

        //sb.append(ang.getDataNasterii());
        sb.trimToSize();
      
        return sb.toString();
    }

    public static DateSite fromText(String linie) {

        DateSite dateSite = new DateSite();

        String[] cuvinte = linie.split(",");

        dateSite.setSite(cuvinte[0]);
        dateSite.setLogin(cuvinte[1]);
        dateSite.setPasswordSize(Integer.parseInt(cuvinte[2]));
        dateSite.setPasswordGenerate(cuvinte[3]);
        //angajat.setDataNasterii(Integer.parseInt( cuvinte[0] ) );

        return dateSite;
    }

}

