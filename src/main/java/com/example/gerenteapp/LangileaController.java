package com.example.gerenteapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LangileaController extends BaseController{

    @FXML
    private TableView<Langilea> langileakTable;

    @FXML
    private TableColumn<Langilea, Integer> idColumn;

    @FXML
    private TableColumn<Langilea, String> izenaColumn;

    @FXML
    private TableColumn<Langilea, String> abizenaColumn;

    @FXML
    private TableColumn<Langilea, String> emailColumn;

    @FXML
    private TableColumn<Langilea, String> pasahitzaColumn;

    @FXML
    private TableColumn<Langilea, Integer> nivelPermisosColumn;

    private ObservableList<Langilea> langileakData = FXCollections.observableArrayList();

    public void setStage(Stage stage) {
        this.usingStage = stage;
    }

    @FXML
    private AnchorPane navBar;

    private NavBarController navBarController;  // Esta es la referencia al controlador de NavBar.fxml


    @FXML
    public void initialize() throws IOException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        abizenaColumn.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        pasahitzaColumn.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
        nivelPermisosColumn.setCellValueFactory(new PropertyValueFactory<>("nivelPermisos"));

        navBarController = (NavBarController) navBar.getProperties().get("controller");

        if (navBarController != null) {
            System.out.println("Controlador de NavBar cargado correctamente.");
            navBarController.setStage(usingStage);

            // Ejemplo: navBarController.someMethod();
        }

        loadLangileakData();
    }

    private void loadLangileakData() {
        try (Connection connection = DBKonexioa.getKonexioa()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM 5_erronka1.langilea");

            while (resultSet.next()) {
                langileakData.add(new Langilea(
                        resultSet.getInt("id"),
                        resultSet.getString("izena"),
                        resultSet.getString("abizena"),
                        resultSet.getString("pasahitza"),
                        resultSet.getString("email"),
                        resultSet.getInt("nivel_permisos")
                ));
            }

            langileakTable.setItems(langileakData);
        } catch (SQLException e) {
            System.err.println("Error loading langileak data: " + e.getMessage());
        }
    }
}
