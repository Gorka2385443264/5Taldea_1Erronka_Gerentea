package com.example.gerenteapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TextField;

public class LoginController extends BaseController {

    @FXML
    private TextField emai;
    @FXML
    private TextField pasa;

    @FXML
    protected void loginBotoia() throws IOException {
        //String email = emai.getText();
        //String pasahitza = pasa.getText();
        String email = "asier@gmail.com";
        String pasahitza = "123";


        if (erabiltzaileaKomprobatu(email, pasahitza)) {
            try {
                if (this.getUsingStage() == null) {
                    throw new IllegalStateException("Stage no inicializado. Asegúrate de llamar a setStage antes de usar sartuBotoia.");
                }

                FXMLLoader langileaLoader = new FXMLLoader(getClass().getResource("langileakTable.fxml"));
                Scene langileaScene = new Scene(langileaLoader.load(), 1800, 850);

                LangileaController langileaController = langileaLoader.getController();  // Asegúrate de que este controlador es el correcto
                langileaController.setStage(this.getUsingStage());
                this.getUsingStage().setTitle("Langilea Table");
                this.getUsingStage().setScene(langileaScene);
                this.getUsingStage().show();
            } catch (IOException e) {
                System.err.println("Error al cargar la siguiente vista: " + e.getMessage());
                mezuaPantailaratu("Error", "Hubo un problema al cargar la vista", Alert.AlertType.ERROR);
            }
        }
    }



    private boolean erabiltzaileaKomprobatu(String email, String pasa) {
        String query = "SELECT * FROM langilea WHERE email = ? AND pasahitza = ? and nivel_permisos=0";

        try (Connection conn = DBKonexioa.getKonexioa();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, pasa);

            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Errorea autentikazioan: " + e.getMessage());
        }
        mezuaPantailaratu("Arazoa", "Erabiltzailea edo Pasahitza gaizki sartu dituzu", Alert.AlertType.ERROR);
        return false;
    }


    private void mezuaPantailaratu(String izena, String mezuLuzea, Alert.AlertType mota) {
        Alert alerta = new Alert(mota);
        alerta.setTitle(izena);
        alerta.setHeaderText(null); // Header hutsa
        alerta.setContentText(mezuLuzea); // Mezu printzipala
        alerta.showAndWait(); // mezua pantailaratu eta itxi
    }

    public void setStage(Stage stage) {
        this.usingStage = stage;
    }
}