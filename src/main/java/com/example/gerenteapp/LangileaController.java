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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.*;

public class LangileaController extends BaseController {

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

    @FXML
    private AnchorPane navBar;

    private NavBarController navBarController;  // Esta es la referencia al controlador de NavBar.fxml

    @FXML
    private HBox navBarContainer;

    @FXML
    public void initialize() throws IOException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        abizenaColumn.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        pasahitzaColumn.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
        nivelPermisosColumn.setCellValueFactory(new PropertyValueFactory<>("nivelPermisos"));

        loadLangileakData();
        // Configurar columnas (asegúrate de que estos métodos coincidan con los atributos de tu clase Langilea)
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));

        // Listener para detectar la selección en la tabla
        langileakTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Llenar los TextField con los datos seleccionados
                IdDeleteField.setText(String.valueOf(newSelection.getId()));
                izenaDeleteField.setText(newSelection.getIzena());

                //Edit form
                izenaEditField.setText(newSelection.getIzena());
                abizenaEditField.setText(newSelection.getAbizena());
                emailaEditField.setText(newSelection.getEmail());
                pasahitzaEditField.setText(newSelection.getPasahitza());
                nivelPermisosEditField.setText(String.valueOf(newSelection.getNivelPermisos()));
            }
        });


    }

    private void loadLangileakData() {
        try (Connection connection = DBKonexioa.getKonexioa()) {
            // Limpiar la lista antes de cargar nuevos datos
            langileakData.clear();

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

    // Form gehitu langilea
    @FXML
    private TextField izenaField;

    @FXML
    private TextField abizenaField;

    @FXML
    private TextField emailaField;

    @FXML
    private TextField pasahitzaField;

    @FXML
    private TextField nivelPermisosField;


    public void createLangilea(ActionEvent actionEvent) {
        // Obtener los valores de los campos
        String izena = izenaField.getText().trim();
        String abizena = abizenaField.getText().trim();
        String emaila = emailaField.getText().trim();
        String pasahitza = pasahitzaField.getText().trim();
        int nivelPermisos;

        try {
            nivelPermisos = Integer.parseInt(nivelPermisosField.getText().trim());
        } catch (NumberFormatException e) {
            System.out.println("El campo Nivel permisos debe ser un número.");
            return;
        }

        // Validar que los campos no estén vacíos
        if (izena.isEmpty() || abizena.isEmpty() || emaila.isEmpty() || pasahitza.isEmpty()) {
            System.out.println("Por favor, llena todos los campos.");
            return;
        }

        // Crear el objeto Langilea
        Langilea langilea = new Langilea(0, izena, abizena, pasahitza, emaila, nivelPermisos);

        // Llamar al método para insertar el objeto en la base de datos
        LangileaKudeatzailea.insertLangilea(langilea);

        // Limpiar los campos
        clearInputFields();

        // Recargar los datos de la tabla
        loadLangileakData();
    }



    // Método para limpiar los campos de texto
    private void clearInputFields() {
        izenaField.clear();
        abizenaField.clear();
        emailaField.clear();
        pasahitzaField.clear();
        nivelPermisosField.clear();
    }






    //Form delete langilea
    @FXML
    private TextField izenaDeleteField;

    @FXML
    private TextField IdDeleteField;


    public void deleteLangilea(ActionEvent actionEvent) {
        String id = IdDeleteField.getText();

        if (id.isEmpty()) {
            System.out.println("Por favor, selecciona un langilea o introduce un ID válido.");
            return;
        }
        if(LangileaKudeatzailea.deleteLangilea(id)){
            System.out.println("Langilea eliminado correctamente.");
            // Actualizar los datos en la tabla
            loadLangileakData();
            // Limpiar los TextField
            IdDeleteField.clear();
            izenaDeleteField.clear();
        }


    }

    //Form Edit langilea
    @FXML
    private TextField izenaEditField;
    @FXML
    private TextField abizenaEditField;
    @FXML
    private TextField emailaEditField;
    @FXML
    private TextField pasahitzaEditField;
    @FXML
    private TextField nivelPermisosEditField;


    public void editLangilea(ActionEvent actionEvent) {
        // Validar que haya un registro seleccionado en la tabla
        Langilea selectedLangilea = langileakTable.getSelectionModel().getSelectedItem();
        if (selectedLangilea == null) {
            System.out.println("Por favor, selecciona un registro para editar.");
            return;
        }

        // Obtener los valores de los TextFields (si están vacíos, usar los valores actuales del objeto seleccionado)
        String izena = izenaEditField.getText().trim().isEmpty() ? selectedLangilea.getIzena() : izenaEditField.getText().trim();
        String abizena = abizenaEditField.getText().trim().isEmpty() ? selectedLangilea.getAbizena() : abizenaEditField.getText().trim();
        String emaila = emailaEditField.getText().trim().isEmpty() ? selectedLangilea.getEmail() : emailaEditField.getText().trim();
        String pasahitza = pasahitzaEditField.getText().trim().isEmpty() ? selectedLangilea.getPasahitza() : pasahitzaEditField.getText().trim();
        int nivelPermisos;

        try {
            nivelPermisos = nivelPermisosEditField.getText().trim().isEmpty() ? selectedLangilea.getNivelPermisos() : Integer.parseInt(nivelPermisosEditField.getText().trim());
        } catch (NumberFormatException e) {
            System.out.println("El campo Nivel permisos debe ser un número.");
            return;
        }

        // Actualizar los datos en el objeto
        selectedLangilea.setIzena(izena);
        selectedLangilea.setAbizena(abizena);
        selectedLangilea.setEmail(emaila);
        selectedLangilea.setPasahitza(pasahitza);
        selectedLangilea.setNivelPermisos(nivelPermisos);

        if (LangileaKudeatzailea.editLangilea(selectedLangilea)){
            // Limpiar los campos de entrada después de la edición
            izenaEditField.clear();
            abizenaEditField.clear();
            emailaEditField.clear();
            pasahitzaEditField.clear();
            nivelPermisosEditField.clear();

            // Recargar los datos de la tabla
            loadLangileakData();
        }else{
            System.out.println("Errorea langilea eguneratzerakoan:");
        }


    }




}
