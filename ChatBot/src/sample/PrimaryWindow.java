package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;

public class PrimaryWindow {

    private static int width = 343;
    private static int height = 484;

    private ChatBot bob;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TextField messageField;

    @FXML
    private TextArea textArea;

    @FXML
    private void send() {
        System.out.println(bob);
        String message = bob.getUserName()+" : " + messageField.getText() + "\n";
        textArea.setText(textArea.getText() + message); // Отправка сообщения
    }

    public void start(Stage primaryStage) throws Exception {
        /*Создаем окно*/
        Parent root = FXMLLoader.load(getClass().getResource("primaryWindow.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED); // Убираем кнопки (Закрыть,свернуть и т.п)
        primaryStage.setTitle("Бот по имени Боб");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }

    @FXML
    void initialize() {
        bob = new ChatBot();
        bob.setBotName("Боб");
        try {
            bob.setUserName(bob.loadName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void close() { // Закрытие программы
        Stage stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }
}

