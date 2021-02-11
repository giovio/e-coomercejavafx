package sample;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class Carello implements Initializable {

    Vector arti;

    public void aggiungi(Articoli art){
        arti.add(art);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
