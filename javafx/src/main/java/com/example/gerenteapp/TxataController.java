package com.example.gerenteapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class TxataController extends BaseController{

        public HBox navBarContainer;
        @FXML
        private VBox chatBox;
        @FXML
        private TextField messageField;
        @FXML
        private Button sendButton;
        @FXML
        private ScrollPane scrollPane;

        private Langilea langilea;

        private ChatClient chatClient;

        private String Izena;

        @FXML
        public void initialize() {
                chatClient = new ChatClient(this);
                chatClient.connect();

                sendButton.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white;");
        }

        public void setIzena(String Izena) {
                this.Izena = Izena;
        }

        @FXML
        private void sendMessage() {
                String message = messageField.getText();
                if (!message.isEmpty()) {
                        message = this.Izena +"> " + message;
                        chatClient.sendMessage(message);
                        displayMessage(message, true);
                        messageField.clear();
                }
        }

        public void setLangilea(Langilea langilea) {
                this.langilea = langilea;
                setIzena(langilea.getIzena());
        }

        public void displayMessage(String message, boolean isUserMessage) {
                Platform.runLater(() -> {
                        Text text = new Text(message);
                        text.setFill(Color.BLACK);
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle(isUserMessage ? "-fx-background-color: #ADD8E6;" : "-fx-background-color: #D3D3D3;");
                        textFlow.setMaxWidth(300);
                        textFlow.setMinHeight(50);
                        chatBox.getChildren().add(textFlow);
                        scrollPane.setVvalue(1.0); // Scroll to the bottom
                });
        }
}