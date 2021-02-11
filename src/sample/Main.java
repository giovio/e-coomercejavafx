package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.print.PrinterAbortException;
import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {




        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("catalogo.fxml"));
        primaryStage.setTitle("eshop");
        fxmlLoader.setController(new Catalogo());
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
