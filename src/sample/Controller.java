package sample;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public Button navi;
    public Tab suchenTab;
    public Tab startTab;
    public Tab speichernTab;
    public Tab naviTab;
    public WebView googleLink;

    public String text;


    public void changeColor(DragEvent dragEvent) {
        if (name != null) {
        }
        //name.setEffect(new InnerShadow());
        //name.setBorder(Color.GREEN);


    }

    public void speichern(MouseEvent mouseEvent) throws IOException {
        Person person = new Person(name.getText(), adresse.getText(), telefonnummer.getText());
        String dateiName = "Person" + name.getText();
        FileOutputStream fileOutputStream = new FileOutputStream(dateiName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.close();

        System.out.println("Datei gespeichert");
    }

    public void suchen(MouseEvent mouseEvent) throws IOException {
        byte zeichen;
        String text = "";
        String text2 = "";
        String StrA = "a";
        String StrE = "e";
        String Esz = "ß";

        //try, catch. Ausgabe ,wenn der Name nicht gespeichert ist
        //evtl. namenvorschläge einarbeiten.

        String dateiName1 = "Person" + name.getText() + 1 + ".txt";
        FileInputStream suchen1 = new FileInputStream(dateiName1);
        do {
            zeichen = (byte) suchen1.read();
            text += (char) zeichen;
            for(int i=0; i< text.length(); i++) {
                if (text.charAt(i)== StrA.charAt(0) && text.charAt(i + 2) == StrE.charAt(0) ) {
                   //text =  text.substring(0, i) + "ß" + text.substring(i, text.length()-1);
                   //text.replace(text.charAt(i+1), Esz.charAt(0));
                   String[] parts = text.split("☐", 2);
                   String part1 = parts[0];
                   String part2 = parts[1];

                   text = part1 + "ß" + part2;
                }
            }
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
                    if (newValue.getText().equals("Suchen")) {

                        startNeu.setVisible(false);
                        startSuche.setVisible(false);
                        speichern.setVisible(false);
                        adresse.setVisible(false);
                        telefonnummer.setVisible(false);
                        googleLink.setVisible(false);

                        suchen.setVisible(true);
                        labAdresse.setVisible(true);
                        labTelefon.setVisible(true);
                        name.setVisible(true);
                        navi.setVisible(true);
                    }
                    if (newValue.getText().equals("Speichern")) {
                        labAdresse.setVisible(false);
                        labTelefon.setVisible(false);
                        startNeu.setVisible(false);
                        startSuche.setVisible(false);
                        suchen.setVisible(false);
                        navi.setVisible(false);
                        googleLink.setVisible(false);

                        name.setVisible(true);
                        speichern.setVisible(true);
                        adresse.setVisible(true);
                        telefonnummer.setVisible(true);

                    }
                    if (newValue.getText().equals("Start")) {
                        labAdresse.setVisible(false);
                        labTelefon.setVisible(false);
                        suchen.setVisible(false);
                        speichern.setVisible(false);
                        adresse.setVisible(false);
                        telefonnummer.setVisible(false);
                        name.setVisible(false);
                        navi.setVisible(false);
                        googleLink.setVisible(false);

                        startNeu.setVisible(true);
                        startSuche.setVisible(true);
                    }

                    if (newValue.getText().equals("Navigation")) {
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

                        googleLink.setVisible(true);
                        System.out.print("https://www.google.de/maps/place/" + mapsFind(labAdresse));
                        googleLink.getEngine().load("https://www.google.de/maps/place/" + mapsFind(labAdresse));


                    }
                }
        );
    }

    public String mapsFind(Label labAdresse) {

        // einzelne Wörter vorher durchlaufen
        // ober beim Speichern anpassen

        text = "";
        String leer = " ";
        String ß = "ß";
        String ae = "ä";
        String ue = "ü";
        String oe = "ö";
        for (int i = 0; i < labAdresse.getText().length()-1; i++) {
            if (labAdresse.getText().charAt(i) == leer.charAt(0)) {
                text = text + "+";
                i++;

            }
            if (labAdresse.getText().charAt(i) == ß.charAt(0)) {
                text = text + "%C3%9F";
                i++;

            }
            if (labAdresse.getText().charAt(i) == ae.charAt(0)) {
                text = text + "%C3%BC";
                i++;

            }
            if (labAdresse.getText().charAt(i) == ue.charAt(0)) {
                text = text + "%C3%9C";
                i++;

            }
            if (labAdresse.getText().charAt(i) == oe.charAt(0)) {
                text = text + "%C3%B6";
                i++;

            }
            text = text + labAdresse.getText().charAt(i);


        }return text;
    }

    public void tabNeuKontakt(MouseEvent mouseDragEvent) {

        tabs.getSelectionModel().select(speichernTab);


    }

    public void tabKontaktSuche(MouseEvent mouseDragEvent) {

        tabs.getSelectionModel().select(suchenTab);

    }

    public void tabMaps(MouseEvent mouseDragEvent) {
        tabs.getSelectionModel().select(naviTab);
    }

}
