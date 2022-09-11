package com.example.tirgul_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view_model.ViewModel;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root =(Parent) fxmlLoader.load();
        HelloController hc = fxmlLoader.getController();
        Model m = new Model("properties.txt");
        ViewModel vm = new ViewModel(m);
        hc.init(vm);
        hc.paint();

        Scene scene = new Scene(root, 520, 440);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
