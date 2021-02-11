package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class Register implements Initializable {
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
    }
    Register(Vector us){
        utenti=us;
        esiste=false;
    }
    Vector utenti;
    boolean esiste;
    private static double xOffset = 0;
    private static double yOffset = 0;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXButton btnregistrati;

    @FXML
    private JFXTextField via;

    @FXML
    private JFXTextField cap;

    @FXML
    private JFXTextField prov;

    @FXML
    private JFXTextField nome;
    @FXML
    AnchorPane initpane;
    @FXML
    Text warning;
    @FXML
    void exit(MouseEvent event) {
System.exit(0);
    }

    @FXML
    void indietro(MouseEvent event) throws IOException {

        Stage stage = (Stage)prov.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    void registrati(ActionEvent event) throws IOException {
        String current= System.getProperty("user.dir");
        File users = new File(current+"\\user.txt");
        if(!users.exists()){users.createNewFile();}



        FileWriter fileWriter = new FileWriter(users,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
if(user.getText().equals("")||pass.getText().equals("")||nome.getText().equals("")||via.getText().equals("")||prov.getText().equals("")
||cap.getText().equals("")){
  warning.setOpacity(1);
}

else {
for(int i=0;i<utenti.size();i++){
    Utente utente =(Utente) utenti.elementAt(i);
    if(user.getText().equals(utente.username)){
        warning.setText("Utente già esistente");
        warning.setOpacity(1);
        esiste=true;
    }
}
if(!esiste){
    printWriter.println(user.getText() + "\t" + pass.getText() + "\t" + nome.getText() + "\t" + via.getText()
            + "\t" + prov.getText() + "\t" + cap.getText());
    printWriter.close();
}

}

        Stage stage = (Stage)prov.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(root));


    }
}
