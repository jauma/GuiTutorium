package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    public Label label;
    public TextField name;
    public TextField adresse;
    public TextField telefonnummer;
    public TabPane tabs;
    public Button suchen;
    public Button speichern;
    public Label labAdresse;
    public Label labTelefon;
    public Button startNeu;
    public Button startSuche;
    public Boolean tabNeu = false;
    public Button navi;


    public void changeColor(DragEvent dragEvent) {
        if (name != null) {
        }
        //name.setEffect(new InnerShadow());
        //name.setBorder(Color.GREEN);


    }

    public void speichern(MouseEvent mouseEvent) throws IOException {

        String strName = name.getText();
        String strAdresse = adresse.getText();
        String strTelefonnummer = telefonnummer.getText();


        String dateiName = "Person" + strName + ".txt";

        FileOutputStream speichern = new FileOutputStream(dateiName);
        for (int i = 0; i < strName.length(); i++) {
            speichern.write(strName.charAt(i));
        }
        speichern.close();

        String dateiAdresse = "Person" + strName + 1 + ".txt";
        FileOutputStream speichern1 = new FileOutputStream(dateiAdresse);
        for (int i = 0; i < strAdresse.length(); i++) {
            speichern1.write((byte) strAdresse.charAt(i));
        }
        speichern1.close();


        String dateiTelefonnummer = "Person" + strName + 2 + ".txt";
        FileOutputStream speichern2 = new FileOutputStream(dateiTelefonnummer);
        for (int i = 0; i < strTelefonnummer.length(); i++) {
            speichern2.write((byte) strTelefonnummer.charAt(i));
        }
        speichern2.close();

        System.out.println("Datei ist gespeichert");

    }

    public void suchen(MouseEvent mouseEvent) throws IOException {
        byte zeichen;
        char buchstabe;
        String text = "";
        String text2 = "";

        String dateiName1 = "Person" + name.getText() + 1 + ".txt";
        FileInputStream suchen1 = new FileInputStream(dateiName1);
        do {
            zeichen = (byte) suchen1.read();
            text += (char) zeichen;
        } while (zeichen != -1); // bis zum Ende

        labAdresse.setText(text);

        suchen1.close();

        String dateiName2 = "Person" + name.getText() + 2 + ".txt";
        FileInputStream suchen2 = new FileInputStream(dateiName2);
        do {
            zeichen = (byte) suchen2.read();
            text2 += (char) zeichen;
        } while (zeichen != -1); // bis zum Ende

        labTelefon.setText(text2);

        suchen2.close();

        suchen.setVisible(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabs.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
                    System.out.println("OLD: " + oldValue.getText());
                    System.out.println("NEW: " + newValue.getText());
                    //setvisible
                    if(newValue.getText().equals("Suchen")) {
                        labAdresse.setVisible(true);
                        labTelefon.setVisible(true);
                        startNeu.setVisible(false);
                        startSuche.setVisible(false);
                        name.setVisible(true);

                        suchen.setVisible(true);
                        speichern.setVisible(false);
                        adresse.setVisible(false);
                        telefonnummer.setVisible(false);
                        navi.setVisible(true);
                    }
                    if(newValue.getText().equals("Speichern")) {
                        labAdresse.setVisible(false);
                        labTelefon.setVisible(false);
                        startNeu.setVisible(false);
                        startSuche.setVisible(false);
                        name.setVisible(true);

                        suchen.setVisible(false);
                        speichern.setVisible(true);
                        adresse.setVisible(true);
                        telefonnummer.setVisible(true);
                        navi.setVisible(false);
                    }
                    if(newValue.getText().equals("Start")) {
                        labAdresse.setVisible(false);
                        labTelefon.setVisible(false);
                        suchen.setVisible(false);
                        speichern.setVisible(false);
                        adresse.setVisible(false);
                        telefonnummer.setVisible(false);
                        name.setVisible(false);
                        navi.setVisible(false);

                        startNeu.setVisible(true);
                        startSuche.setVisible(true);
                    }

                    if(tabNeu){
                        newValue.setText("Navigation");
                        labAdresse.setVisible(false);
                        labTelefon.setVisible(false);
                        suchen.setVisible(false);
                        speichern.setVisible(false);
                        adresse.setVisible(false);
                        telefonnummer.setVisible(false);
                        name.setVisible(false);

                        startNeu.setVisible(false);
                        startSuche.setVisible(false);
                        navi.setVisible(false);


                    }
    }
        );
    }

    public void tabNeuKontakt(MouseDragEvent mouseDragEvent) {
    }

    public void tabKontaktSuche(MouseDragEvent mouseDragEvent) {
    }

    public void tabMaps(MouseDragEvent mouseDragEvent) {
       /* tabNeu = true;
        initialize(URL location, ResourceBundle resource); */
    }


  /*  public boolean tabKontaktSuche(MouseEvent) throws IOException {
       if();)
           return true;
    } return false;

    public void tabNeuKontakt(MouseDragEvent mouseDragEvent) {

    } */
}
