package ru.hackathon.dao;

import ru.hackathon.model.Camera;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FromMySql implements CameraRepository {
    @Override
    public void setCamera(Camera camera) {

    }

    @Override
    public List<Camera> getCameras() {
        SQLHandler sqlHandler = new SQLHandler();
        String sql = "select * from camera_times";
        List<Camera> cameras = new ArrayList();
        try {
            sqlHandler.connect();
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
