package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
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
    public Integer Namennummer;
    public String dateiNamennummer;
    public TabPane tabs;
    public Button suchen;
    public Button speichern;


    public int setNamennummer(String name) throws IOException {
        String text = "";
        byte zeichen;
        int dateiNN = 0;

        String dateiName = name + ".txt";
        FileOutputStream speichern = new FileOutputStream(dateiName);
        speichern.close();

        String dateiNamennummer = Namennummer + ".txt";
        FileInputStream suche = new FileInputStream(dateiNamennummer);
        do {
            zeichen = (byte) suche.read();
            text += (char) zeichen;
        } while (zeichen != -1);

        text = dateiNamennummer;

        if (Integer.parseInt(dateiNamennummer) != 0) {
            dateiNN = Integer.parseInt(dateiNamennummer + 1);
            speichern.write((byte) dateiNN);
            speichern.close();

            return dateiNN;
        }
        dateiNN += 1;
        speichern.write((byte) dateiNN);
        speichern.close();

        return dateiNN;
    }

    ;

    public int getNamennummer(String name) throws IOException {
        String text = "";
        byte zeichen;

        String dateiName = name + ".txt";
        FileInputStream suchen = new FileInputStream(dateiName);
        do {
            zeichen = (byte) suchen.read();
            text += (char) zeichen;
        } while (zeichen != -1); // bis zum Ende

        text = Integer.toString(Namennummer);

        return Namennummer;
    }


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


        int sNN = 1; //setNamennummer(strName);

        String dateiName = sNN + "Person" + strName + ".txt";

        FileOutputStream speichern = new FileOutputStream(dateiName);
        for (int i = 0; i < strName.length(); i++) {
            speichern.write(strName.charAt(i));
        }
        speichern.close();

        String dateiAdresse = sNN + "Person" + strName + 1 + ".txt";
        FileOutputStream speichern1 = new FileOutputStream(dateiAdresse);
        for (int i = 0; i < strAdresse.length(); i++) {
            speichern1.write((byte) strAdresse.charAt(i));
        }
        speichern1.close();


        String dateiTelefonnummer = sNN + "Person" + strName + 2 + ".txt";
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
        Integer gNN = 1;//getNamennummer(name.getText()) ;
        
       /* String dateiName = gNN + "Person" + name + ".txt";
        FileInputStream suchen = new FileInputStream(dateiName);
        do{
            zeichen = (byte) suchen.read();
            text += (char)zeichen;
        } while (zeichen != -1); // bis zum Ende

        text = name;
          suchen.close();*/

        String dateiName1 = gNN + "Person" + name.getText() + 1 + ".txt";
        FileInputStream suchen1 = new FileInputStream(dateiName1);
        do {
            zeichen = (byte) suchen1.read();
            text += (char) zeichen;
        } while (zeichen != -1); // bis zum Ende

        adresse.setText(text);

        suchen1.close();

        String dateiName2 = gNN + "Person" + name.getText() + 2 + ".txt";
        FileInputStream suchen2 = new FileInputStream(dateiName2);
        do {
            zeichen = (byte) suchen2.read();
            text2 += (char) zeichen;
        } while (zeichen != -1); // bis zum Ende

        telefonnummer.setText(text2);

        suchen2.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabs.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("OLD: " + oldValue.getText());
                    System.out.println("NEW: " + newValue.getText());
                    //setvisible
                    if(newValue.getText().equals("2")) {
                    suchen.setVisible(true);
                    speichern.setVisible(false);

                    }
                }
        );
    }
}
