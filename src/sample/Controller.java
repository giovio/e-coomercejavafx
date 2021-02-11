package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initpane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              Stage primaryStage = (Stage) initpane.getScene().getWindow();
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });
        initpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage primaryStage = (Stage) initpane.getScene().getWindow();
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });
warning.setOpacity(0);
        String current= System.getProperty("user.dir");
        File user = new File(current+"\\user.txt");
        if(!user.exists()){
            try {
                user.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String username="";
        String password="";
        String nome="";
        String indirizzo="";
        String Provincia="";
        String cap="";
        try{
            BufferedReader br = new BufferedReader(new FileReader(user));
            String line=null;
            while((line=br.readLine())!=null){
                String[] tmp = line.split("\t");
                username=tmp[0];
                password=tmp[1];
                nome=tmp[2];
                indirizzo=tmp[3];
                Provincia=tmp[4];
                cap=tmp[5];
             users.add(new Utente(username,password,nome,indirizzo,Provincia,cap));
            }


        }
        catch (Exception e){e.printStackTrace();}
    }
    Vector users= new Vector(1);
    private static double xOffset = 0;
    private static double yOffset = 0;
    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXButton btnaccedi;

    @FXML
    private JFXButton btnregistrati;

    @FXML
    private JFXCheckBox passvis;
    @FXML
    AnchorPane pane;
    JFXTextField textField;
    @FXML
    AnchorPane initpane;
@FXML
    Text warning;
    @FXML
    void accedi(ActionEvent event) throws IOException {
if(passvis.isSelected()){
    pass.setText(textField.getText());
}
        for (int i=0;i<users.size();i++){
            Utente utente = (Utente) users.elementAt(i);
            if(utente.username.equals(user.getText())){
                if(utente.password.equals(pass.getText())){
                    Catalogo catalogo = new Catalogo(utente);
                    FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("catalogo.fxml"));
                    fxmlLoader.setController(catalogo);
                    Parent root = fxmlLoader.load();
                    Stage primaryStage = (Stage) user.getScene().getWindow();
                    primaryStage.setScene(new Scene(root));
                }

            }

}
        warning.setOpacity(1);
    }

    @FXML
    void passvi(ActionEvent event) {
        if(passvis.isSelected()){
           textField  = new JFXTextField();
            pass.setVisible(false);
            textField.setLayoutX(pass.getLayoutX());
            textField.setLayoutY(pass.getLayoutY());
            textField.setPrefWidth(pass.getPrefWidth());
            textField.setPrefHeight(pass.getPrefHeight());
            textField.setText(pass.getText());
            textField.setFont(pass.getFont());
            pane.getChildren().remove(pass);
            pane.getChildren().add(textField);

        }
        else{


pass.setText(textField.getText());
pane.getChildren().remove(textField);
pane.getChildren().add(pass);
pass.setVisible(true);
        }
    }

    @FXML
    void registrati(ActionEvent event) throws IOException {

      Stage stage= (Stage) pane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        fxmlLoader.setController(new Register(users));
        Parent root =fxmlLoader.load();
        stage.setScene(new Scene(root));
    }
    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }
@FXML
    void indietro(MouseEvent event) throws IOException {
    FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("catalogo.fxml"));
  Stage primaryStage =(Stage) pane.getScene().getWindow();
    fxmlLoader.setController(new Catalogo());
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root));
}
}
