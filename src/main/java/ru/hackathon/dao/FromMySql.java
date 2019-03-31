package ru.hackathon.dao;

import ru.hackathon.model.Camera;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FromMySql implements CameraRepository {

    private DataBase dataBase;

    public FromMySql() {
        String urlDB = "jdbc:mysql://mysql.bakhuss.myjino.ru:3306/bakhuss_hakaton?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";
        DataBase dataBase = new DataBaseFile(urlDB,"046835859_hakat", "hakat");
        this.dataBase = dataBase;
    }

    @Override
    public void setCamera(Camera camera) {

    }

    @Override
    public List<Camera> getCameras() {
        SQLHandler sqlHandler = new SQLHandler();
        String sql = "select * from camera_times";
        List<Camera> cameras = new ArrayList();
        try {
            sqlHandler.connect(dataBase);
            ResultSet rs = sqlHandler.getStmt().executeQuery(sql);
            while (rs.next()) {
                Camera camera = new Camera();
                camera.setCameraId(rs.getLong("camera_id"));
                camera.setPathVideo(rs.getString("path_video"));
                cameras.add(camera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlHandler.disconnect();
        }
        return cameras;
    }

    @Override
    public void updateCamera(Camera camera) {

    }

    @Override
    public void removeCamera(Camera camera) {

    }
}
