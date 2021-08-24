package com.sersoft.www.myprojectsfxgenerator.entites;


public class DateSite {

private String site;
private String login;
  private int passwordSize;
private String passwordGenerate;

    public DateSite() {
    }

    public DateSite(String site, String login, int passwordSize, String passwordGenerate) {
        this.site = site;
        this.login = login;
        this.passwordSize = passwordSize;
        this.passwordGenerate = passwordGenerate;
    }

    public String getPasswordGenerate() {
        return passwordGenerate;
    }

    public void setPasswordGenerate(String passwordGenerate) {
        this.passwordGenerate = passwordGenerate;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPasswordSize() {
        return passwordSize;
    }

    public void setPasswordSize(int passwordSize) {
        this.passwordSize = passwordSize;
    }

    @Override
    public String toString() {
        return "DateSite{" + "site=" + site + ", login=" + login + ", passwordSize=" + passwordSize + ", passwordGenerate=" + passwordGenerate + '}';
    }

   

}
