package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListaArticoli implements Initializable {
    @FXML
    private Label nome;

    @FXML
    private ImageView image;

    @FXML
    private Text descrizione;
    @FXML
    private Label prezzo;

    @FXML
    void elimina(ActionEvent event) throws IOException {
aggiun.elimina(articoli);
    }

    @FXML
    void modifica(ActionEvent event) {
aggiun.modifica(articoli);
    }
    Articoli articoli;
    Aggiungart aggiun;
    ListaArticoli(Articoli art,Aggiungart aggiungart){articoli=art; aggiun=aggiungart;}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
nome.setText(articoli.nome);
descrizione.setText(articoli.descrizione);
image.setImage(articoli.imaggine);
prezzo.setText(articoli.prezzo+"€");
System.out.println(articoli.cod);
    }

}
