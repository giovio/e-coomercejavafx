package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class Aggiungart implements Initializable {
@FXML
    public AnchorPane pannelloscroll;
boolean elimina;
int y=0;
int linea=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idart.setEditable(false);
        try{
        Articoli art = (Articoli)articoli.elementAt(articoli.size()-1);
int id = art.cod+1;
            idart.setText(id+"");
        }
        catch (Exception e){idart.setText(0+"");}





for(int i=0;i<articoli.size();i++){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListaArticoli.fxml"));
    fxmlLoader.setController(new ListaArticoli((Articoli) articoli.elementAt(i),this));
    try {
        AnchorPane anchorPane = fxmlLoader.load();
        anchorPane.setLayoutY(y);
        pannelloscroll.getChildren().add(anchorPane);
        y=y+152;
    } catch (IOException e) {
        e.printStackTrace();

    }

}

    }
    String urlimage;

    @FXML
    private ImageView imageview;

    @FXML
    private JFXTextField tagart;

    @FXML
    private JFXTextField nomeart;

    @FXML
    private JFXTextField prezzoart;

    @FXML
    private JFXTextField quantiart;

    @FXML
    private JFXTextArea descriart;


boolean modifica=false;
@FXML
private JFXTextField idart;
Vector articoli;
Catalogo catalogo;
Aggiungart(Vector a,Catalogo c){
    articoli=a;
    catalogo=c;

     String current= System.getProperty("user.dir");
     fileart = new File(current+"\\articoli.txt");

}
    File fileart;

    @FXML
    void aggiungiimage(MouseEvent event) throws MalformedURLException {

        FileChooser fileChooser= new FileChooser();
        File file = fileChooser.showOpenDialog(((Node)event.getTarget()).getScene().getWindow());
        System.out.println(file.toURL().toString());
        Image image = new Image(file.toURL().toString());
        imageview.setImage(image);

    }

    @FXML
    void aggiungi(ActionEvent event) throws Exception {


        FileWriter fileWriter = new FileWriter(fileart,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);


if(modifica){
    BufferedReader br = new BufferedReader(new FileReader(fileart));
    String line =null;

    while((line=br.readLine())!=null){
        new PrintWriter(fileart.getPath()).close();
        String[] tmp =line.split("\t");
        if(!idart.getText().equals(tmp[6])){
printWriter.println(line);

        }


    }
    modifica=false;
}


    if (!fileart.exists()) {
        fileart.createNewFile();
    }

    urlimage = imageview.getImage().impl_getUrl();


    printWriter.println(nomeart.getText() + "\t" + quantiart.getText() + "\t" + descriart.getText() + "\t" + prezzoart.getText()
            + "\t" + urlimage + "\t" + tagart.getText() + "\t" + idart.getText());
    printWriter.close();

    indietro();


    }
    public void modifica(Articoli art){
nomeart.setText(art.nome);
quantiart.setText(art.quantita+"");
descriart.setText(art.descrizione);
prezzoart.setText(art.prezzo+"");
imageview.setImage(art.imaggine);
tagart.setText(art.tag);
idart.setText(art.cod+"");
modifica=true;

    }
    public void elimina(Articoli art) throws IOException {
        File tempFile = new File(getClass().getResource("temp.txt").getPath());
        FileWriter tempwriter = new FileWriter(tempFile,true);
        PrintWriter tempprint = new PrintWriter(tempwriter);


        if(tempFile.exists()){tempFile.createNewFile();}

            BufferedReader br = new BufferedReader(new FileReader(fileart));
            String line = null;
            String id = art.cod + "";
            FileWriter fileWriter = new FileWriter(fileart,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            while ((line = br.readLine()) != null) {

                String[] tmp = line.split("\t");
                if (id.equals(tmp[6])) {


                }
                else{

                    tempprint.println(line);
                }

            }
            tempprint.close();
            BufferedReader tempbr = new BufferedReader(new FileReader(tempFile));
            String linea = null;
            new PrintWriter(fileart.getPath()).close();
            while ((linea=tempbr.readLine())!=null){
                printWriter.println(linea);
            }
            printWriter.close();
            new PrintWriter(tempFile.getPath()).close();
            indietro();
        }

    public void indietro() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Catalogo.fxml"));
        fxmlLoader.setController(catalogo);
        Parent root = fxmlLoader.load();
        Stage stage=(Stage)tagart.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
public void indietro(MouseEvent event) throws IOException {

        indietro();
}

}
