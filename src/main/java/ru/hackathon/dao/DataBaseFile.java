package ru.hackathon.dao;

public class DataBaseFile implements DataBase{

    private String urlDB;
    private String user;
    private String password;

    public DataBaseFile(String urlDB, String user, String password) {
        this.urlDB = urlDB;
        this.user = user;
        this.password = password;
    }




    @Override
    public String getUrlDB() {
        return urlDB;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }


}
