package com.sersoft.www.myprojectsfxgenerator.models;

import javax.swing.*;

/**
 *
 * @author ANONYM
 */
public class SelectMusicModel extends DefaultComboBoxModel {

    public SelectMusicModel() {

        super.addElement("SELECT");
        super.addElement(SelectMusic.RELAX);
        super.addElement(SelectMusic.HIPHOP);
        super.addElement(SelectMusic.RAP);
        super.addElement(SelectMusic.HOUSE);
        super.addElement(SelectMusic.POP);

    }

}
