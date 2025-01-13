package com.example.gerenteapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlmazenaController {
    private Stage usingStage;

    @FXML
    private TableView<Almazena> almazenaTable;

    @FXML
    private TableColumn<Almazena, Integer> idColumn;

    @FXML
    private TableColumn<Almazena, String> izenaColumn;

    @FXML
    private TableColumn<Almazena, String> motaColumn;

    @FXML
    private TableColumn<Almazena, String> ezaugarriaColumn;

    @FXML
    private TableColumn<Almazena, Integer> stockColumn;

    @FXML
    private TableColumn<Almazena, String> unitateaColumn;

    @FXML
    private TableColumn<Almazena, Integer> minColumn;

    @FXML
    private TableColumn<Almazena, Integer> maxColumn;

    @FXML
    private TableColumn<Almazena, String> createdAtColumn;

    @FXML
    private TableColumn<Almazena, Integer> createdByColumn;

    private ObservableList<Almazena> almazenaData = FXCollections.observableArrayList();





    @FXML
    public void initialize() {
        // Configuración de las columnas de la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        motaColumn.setCellValueFactory(new PropertyValueFactory<>("mota"));
        ezaugarriaColumn.setCellValueFactory(new PropertyValueFactory<>("ezaugarria"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        unitateaColumn.setCellValueFactory(new PropertyValueFactory<>("unitatea"));
        minColumn.setCellValueFactory(new PropertyValueFactory<>("min"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<>("max"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        createdByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));

        // Carga los datos de la tabla
        loadAlmazenaData();
    }

    private void loadAlmazenaData() {
        try (Connection connection = DBKonexioa.getKonexioa()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM almazena");

            while (resultSet.next()) {
                almazenaData.add(new Almazena(
                        resultSet.getInt("id"),
                        resultSet.getString("izena"),
                        resultSet.getString("mota"),
                        resultSet.getString("ezaugarria"),
                        resultSet.getInt("stock"),
                        resultSet.getString("unitatea"),
                        resultSet.getInt("min"),
                        resultSet.getInt("max"),
                        resultSet.getString("created_at"),
                        resultSet.getInt("created_by")
                ));
            }

            almazenaTable.setItems(almazenaData);
        } catch (SQLException e) {
            System.err.println("Error loading almazena data: " + e.getMessage());
        }
    }


}
