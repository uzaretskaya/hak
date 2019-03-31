package ru.hackathon.dao;

public class DataBaseFile {
    private static String urlDB = "jdbc:mysql://mysql.bakhuss.myjino.ru:3306/bakhuss_hakaton?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";

    public static String getUrlDB() {return urlDB;}
}
