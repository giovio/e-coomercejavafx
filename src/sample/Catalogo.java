package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import javax.xml.bind.ValidationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class Catalogo implements Initializable {
  @FXML
    ScrollPane scroll;

    Vector item = new Vector();
    @FXML
    AnchorPane paneart;
    @FXML
    AnchorPane trascpane;
 int x;
 int y;
 int c;
private static double xOffset = 0;
private static double yOffset = 0;
Utente utente;
@FXML
Label labelben;
@FXML
JFXButton buttaccedi;
@FXML
Button buttonart;

Catalogo(Utente u){
    utente=u;
}
Catalogo(){
    utente=null;
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
x=20;
y=20;
c=0;
   item.removeAllElements();
    buttonart.setVisible(false);
if(utente!=null){
    if(utente.username.equals("admin")){
        buttonart.setVisible(true);
    }

    labelben.setText("Benvenuto "+utente.username);
    buttaccedi.setText("Esci");
    buttaccedi.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("login.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage = (Stage) scroll.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    });
}

        trascpane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage primaryStage = (Stage) trascpane.getScene().getWindow();
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });
        trascpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage primaryStage = (Stage) trascpane.getScene().getWindow();
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });








        String current= System.getProperty("user.dir");
        File articoli = new File(current+"\\articoli.txt");

        String nome="";
        String quantita="";
        String descrizione="";
        String prezzo="";
        String imaggine="";
        String tag="";
        String id ="";

        try{
            BufferedReader br = new BufferedReader(new FileReader(articoli));
            String line=null;
            while ((line=br.readLine())!=null){
               String[] tmp = line.split("\t");
               nome=tmp[0];
              quantita=tmp[1];
               descrizione=tmp[2];
               prezzo=tmp[3];
                imaggine=tmp[4];
                tag=tmp[5];
                 id=tmp[6];
                 if(utente==null){
              item.addElement(new Articoli(nome,Integer.parseInt(quantita),descrizione,Double.parseDouble(prezzo),imaggine,tag,Integer.parseInt(id)));}
                 else{ item.addElement(new Articoli(nome,Integer.parseInt(quantita),descrizione,Double.parseDouble(prezzo),imaggine,tag,Integer.parseInt(id),utente));}


            }

        }
        catch (Exception e){e.printStackTrace();}

        for(int i=0;i<item.size();i++){
          try{
           FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Articoli.fxml"));
           Articoli articolo = (Articoli) item.elementAt(i);
           fxmlLoader.setController(articolo);
           AnchorPane pane =(AnchorPane) fxmlLoader.load();
              pane.setLayoutX(x);
              pane.setLayoutY(y);
              paneart.getChildren().add(pane);
              x=x+420;
              c=c+1;
              if(c==3){
                  c=0;
                  y=y+520;
                  x=20;
              }
            } catch (IOException e) {
                e.printStackTrace();
            }



        }







    }

    @FXML
    public void accedi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) scroll.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    public void aggiungiart(ActionEvent event)throws IOException{

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("aggiungart.fxml"));
    fxmlLoader.setController(new Aggiungart(item,this));
    Parent root = fxmlLoader.load();
Stage stage =(Stage) scroll.getScene().getWindow();
stage.setScene(new Scene(root));

    }
}
