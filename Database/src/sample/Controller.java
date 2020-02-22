package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller {

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private AnchorPane anchor_users;

    @FXML
    private AnchorPane anchor_profit;

    @FXML
    private AnchorPane anchor_settings;

    @FXML
    private AnchorPane anchor_main;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_users;

    @FXML
    private Button btn_profit;

    @FXML
    private Button btn_settings;

    @FXML
    private ImageView btn_close;

    @FXML
    private ImageView btn_underscore;

    @FXML
    private Label label_main_name;

    @FXML
    private TableView<Person> table_database;

    @FXML
    private TextField field_second_name;

    @FXML
    private TextField field_first_name;

    @FXML
    private TextField field_middle_name;

    @FXML
    private TextField field_salary;

    @FXML
    private ChoiceBox choise_position;

    @FXML
    private Button btn_add_person;

    @FXML
    private void handleCloseButtonAction(MouseEvent event) {
        if(event.getTarget()==btn_close) {
            Stage stage = (Stage) btn_home.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void handleHideButtonAction(MouseEvent event) {
        if(event.getTarget()==btn_underscore) {
            // Доработать
        }
    }

    @FXML
    /**Обработка отображения кнопок из меню**/
    private void handleMainButtonAction(MouseEvent event) {
        if (event.getTarget() == btn_home) {
            label_main_name.setText("Главная");
            anchor_users.setVisible(false);
            anchor_profit.setVisible(false);
            anchor_settings.setVisible(false);
            anchor_main.setVisible(true);
        }
        else
            if(event.getTarget()==btn_users) {
                label_main_name.setText("Пользователи");
                anchor_users.setVisible(true);
                anchor_profit.setVisible(false);
                anchor_settings.setVisible(false);
                anchor_main.setVisible(false);
            }
            else
                if(event.getTarget()==btn_profit) {
                    label_main_name.setText("Прибыль");
                    anchor_users.setVisible(false);
                    anchor_profit.setVisible(true);
                    anchor_settings.setVisible(false);
                    anchor_main.setVisible(false);
                }
                else
                    if (event.getTarget()==btn_settings) {
                        label_main_name.setText("Настройки");
                        anchor_users.setVisible(false);
                        anchor_profit.setVisible(false);
                        anchor_settings.setVisible(true);
                        anchor_main.setVisible(false);
                    }
    }


    @FXML
    void initialize() {
        /**Установка вариантов для Choise Box**/
        choise_position.setItems(FXCollections.observableArrayList(Position.Junior,Position.Middle,Position.Senior,Position.Директор));
    }
}
