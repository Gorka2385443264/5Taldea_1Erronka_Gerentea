package com.example.gerenteapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class NavBarController extends BaseController {

    public NavBarController() {
        super();
    }
    /**
     * Carga una nueva escena y actualiza el Stage principal.
     *
     * @param fxmlFile Archivo FXML a cargar (debe incluir la extensión .fxml).
     * @param title    Título de la nueva ventana.
     */
    private void loadScene(String fxmlFile, String title) {
        try {
            // Verificar que el Stage esté inicializado

            //TODO NULL DATOR STAGE-ea
            Stage stage = this.getUsingStage();
            if (stage == null) {
                throw new IllegalStateException("Stage no inicializado. Asegúrate de configurar el Stage antes de usar esta función.");
            }

            // Verificar si el archivo FXML existe
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            if (loader.getLocation() == null) {
                throw new IOException("Archivo FXML no encontrado: " + fxmlFile);
            }

            // Cargar la escena
            Scene newScene = new Scene(loader.load(), 1800, 850);

            // Configurar el controlador del nuevo archivo FXML si es necesario
            Object controller = loader.getController();
            if (controller instanceof BaseController) {
                ((BaseController) controller).setStage(stage);
                ((BaseController) controller).navBarKargatu();
            }

            // Actualizar la escena y el título del Stage
            stage.setTitle(title);
            stage.setScene(newScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showError("Error al cargar la vista", "No se pudo cargar la vista: " + fxmlFile + ". Verifica la ruta y el archivo.");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            showError("Error de configuración", e.getMessage());
        }
    }

    // Botón para cambiar a langileakTable.fxml
    public void goToLangileak(ActionEvent actionEvent) {
        loadScene("langileakTable.fxml", "Langileak Table");
    }

    // Botón para cambiar a almazenaTable.fxml
    public void goToAlmazena(ActionEvent actionEvent) {
        loadScene("almazenaTable.fxml", "Almazena Table");
    }

    // Botón para cambiar a plateraTable.fxml
    public void goToMenua(ActionEvent actionEvent) {
        loadScene("plateraTable.fxml", "Platera Table");
    }

    /**
     * Muestra un mensaje de error en pantalla.
     *
     * @param title   Título del mensaje.
     * @param message Contenido del mensaje.
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
