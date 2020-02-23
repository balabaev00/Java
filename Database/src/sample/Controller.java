package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    /**
     * Создание Table View
     **/
    private ObservableList<Person> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<Person> table_database;

    @FXML
    private TableColumn<Person, String> db_second_name;

    @FXML
    private TableColumn<Person, String> db_first_name;

    @FXML
    private TableColumn<Person, String> db_middle_name;

    @FXML
    private TableColumn<Person, Position> db_position;

    @FXML
    private TableColumn<Person, Short> db_pay;

    @FXML
    private TableColumn<Person, Short> db_id;

    @FXML
    private TextField field_del_person;

    @FXML
    private Button btn_del_person;


    @FXML
    private void handleCloseButtonAction(MouseEvent event) {
        if (event.getTarget() == btn_close) {
            Stage stage = (Stage) btn_home.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void handleHideButtonAction(MouseEvent event) {
        if (event.getTarget() == btn_underscore) {
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
        } else if (event.getTarget() == btn_users) {
            label_main_name.setText("Пользователи");
            anchor_users.setVisible(true);
            anchor_profit.setVisible(false);
            anchor_settings.setVisible(false);
            anchor_main.setVisible(false);
        } else if (event.getTarget() == btn_profit) {
            label_main_name.setText("Прибыль");
            anchor_users.setVisible(false);
            anchor_profit.setVisible(true);
            anchor_settings.setVisible(false);
            anchor_main.setVisible(false);
        } else if (event.getTarget() == btn_settings) {
            label_main_name.setText("Настройки");
            anchor_users.setVisible(false);
            anchor_profit.setVisible(false);
            anchor_settings.setVisible(true);
            anchor_main.setVisible(false);
        }
    }

    @FXML
    private void handleAddUserAction(MouseEvent event) {
        if (event.getTarget() == btn_add_person) {
            Person person = new Person(field_second_name.getText(),field_first_name.getText(),field_middle_name.getText(),Position.Junior, (short) 1,Short.parseShort(field_salary.getText()));
            usersData.add(person);
        }
    }

    private void initTableView() {
        /**Устанавливаем тип и значение которое должно хранится в колонке**/
        db_second_name.setCellValueFactory(new PropertyValueFactory<Person,String>("secondName"));
        db_first_name.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
        db_middle_name.setCellValueFactory(new PropertyValueFactory<Person,String>("middleName"));
        db_position.setCellValueFactory(new PropertyValueFactory<Person,Position>("position"));
        db_pay.setCellValueFactory(new PropertyValueFactory<Person,Short>("pay"));
        db_id.setCellValueFactory(new PropertyValueFactory<Person,Short>("id"));
        /**Заполняем таблицу данными из коллекции UsersData**/
        table_database.setItems(usersData);
    }

    @FXML
    void initialize() {
        /**Установка вариантов для Choise Box**/
        choise_position.setItems(FXCollections.observableArrayList(Position.Junior, Position.Middle, Position.Senior, Position.Директор));
        initTableView();
    }
}
