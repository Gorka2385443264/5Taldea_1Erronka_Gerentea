package com.example.gerenteapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MahailaController extends BaseController {

        private Stage usingStage;
        @FXML
        private TableView<Mahaila> mahailakTable;

        @FXML
        private TableColumn<Mahaila, Integer> idColumn;

        @FXML
        private TableColumn<Mahaila, Integer> mahailaZenbakiaColumn;

        @FXML
        private TableColumn<Mahaila, Integer> eserlekuakColumn;

        private ObservableList<Mahaila> mahailakData = FXCollections.observableArrayList();

        @FXML
        public void initialize() {
                // Configurar las columnas para la tabla
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                mahailaZenbakiaColumn.setCellValueFactory(new PropertyValueFactory<>("mahaila_zenbakia"));
                eserlekuakColumn.setCellValueFactory(new PropertyValueFactory<>("eserlekuak"));

                // Cargar los datos desde la base de datos
                loadMahailakData();
        }

        private void loadMahailakData() {
                try (Connection connection = DBKonexioa.getKonexioa()) {
                        // Limpiar la lista antes de cargar nuevos datos
                        mahailakData.clear();

                        // Crear una consulta SQL
                        String query = "SELECT * FROM 5_erronka1.mahaila";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);

                        // Procesar el resultado de la consulta
                        while (resultSet.next()) {
                                Mahaila mahaila = new Mahaila(
                                        resultSet.getInt("id"),
                                        resultSet.getInt("mahaila_zenbakia"),
                                        resultSet.getInt("eserlekuak")
                                );
                                mahailakData.add(mahaila);
                        }

                        // Asignar los datos a la tabla
                        mahailakTable.setItems(mahailakData);
                } catch (SQLException e) {
                        System.err.println("Errorea mahailak datuak kargatzerakoan: " + e.getMessage());
                }
        }
}
