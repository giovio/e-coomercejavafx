package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Articoli implements Initializable {
   String nome;
   int quantita;
   String descrizione;
   double prezzo;
   Image imaggine;
   String tag;
   int cod;
   Utente utente;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeart.setText(nome);
        prezzoart.setText(prezzo+"€");
        imageview.setImage(imaggine);
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(new Duration(1000));
        fadeTransition.setToValue(1);
        fadeTransition.setFromValue(0);
        fadeTransition.setNode(pane);
        fadeTransition.play();
    }
    Articoli(String n,int q, String d, double p, String image,String t,int id){
        quantita=q;
        nome=n;
        descrizione=d;
        prezzo=p;
        imaggine=new Image(image);
        tag=t;
        cod=id;
        utente=null;
    }
    Articoli(String n,int q, String d, double p, String image,String t,int id,Utente u){
        quantita=q;
        nome=n;
        descrizione=d;
        prezzo=p;
        imaggine=new Image(image);
        tag=t;
        cod=id;
        utente=u;
    }

    @FXML
    private ImageView imageview;

    @FXML
    private Text nomeart;


    @FXML
    private Text prezzoart;

    @FXML
    void addtocart(ActionEvent event) {


    }
    @FXML
    void dettagli(ActionEvent event) {
System.out.println(cod);

    }
    @FXML
    AnchorPane pane;


}
