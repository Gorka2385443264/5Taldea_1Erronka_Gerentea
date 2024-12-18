package com.example.gerenteapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController extends BaseController {


    @FXML
    protected void sartuBotoia() throws IOException {
        if (this.getUsingStage() == null) {
            throw new IllegalStateException("Stage no inicializado. Asegúrate de llamar a setStage antes de usar sartuBotoia.");
        }

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(loginLoader.load());


        LoginController loginController = loginLoader.getController();
        loginController.setStage(this.getUsingStage());
        this.getUsingStage().setScene(loginScene);
        this.getUsingStage().setTitle("Login pantalla");
        this.getUsingStage().show();
    }


}
