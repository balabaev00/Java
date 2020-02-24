package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.ShortStringConverter;

import java.io.*;
import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.List;

//TODO
// 1 - fix Position add
// 2 - add search
// 3 - add icon program
// 4 - info of program developer
// 5 - hot key
// 6 - auto save database
// 7 - small info of program
// 8 - сворачивание программы

public class Controller {

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private AnchorPane anchor_users;

    @FXML
    private AnchorPane anchor_edit;

    @FXML
    private AnchorPane anchor_settings;

    @FXML
    private AnchorPane anchor_main;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_users;

    @FXML
    private Button btn_edit;

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

    /**ObservableList convert to ArrayList**/
    private ArrayList<Person> toArrayList(ObservableList<Person> usersData) {
        ArrayList<Person> usersDataArray = new ArrayList<Person>();
        for (int i = 0; i < usersData.size(); i++) {
            usersDataArray.add(usersData.get(i));
        }
        return usersDataArray;
    }

    /**ArrayList convert to ObservableList **/
    private ObservableList<Person> toObservableList(ArrayList<Person> usersData) {
        ObservableList<Person> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < usersData.size(); i++) {
            observableList.add(usersData.get(i));
        }
        return observableList;
    }

    @FXML
    private TableView<Person> table_database;

    @FXML
    private TableColumn<Person, String> db_second_name;

    @FXML
    private TableColumn<Person, String> db_first_name;

    @FXML
    private TableColumn<Person, String> db_middle_name;

    @FXML
    private TableColumn<Person, String> db_position;

    @FXML
    private TableColumn<Person, Short> db_pay;

    @FXML
    private TableColumn<Person, Short> db_id;

    @FXML
    private TextField field_del_person;

    @FXML
    private Button btn_del_person;

    @FXML
    private Button btn_open_file;

    @FXML
    private Button btn_save_file;


    @FXML
    private void handleCloseButtonAction() {
        Stage stage = (Stage) btn_home.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleHideButtonAction(MouseEvent event) {
        if (event.getTarget() == btn_underscore) {
            // Доработать
        }
    }

    @FXML
    private void handleOpenFileAction() {
        FileChooser fileChooser = new FileChooser();
        /**Устанавливаем ограничения на TXT Format**/
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        /**Открываем openDialog**/
        File file = fileChooser.showOpenDialog(null);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getName())))
        {
            usersData=toObservableList((ArrayList<Person>)ois.readObject());
            table_database.setItems(usersData);
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Во время открытия файла произошла ошибка!");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSaveFileAction() {
        FileChooser fileChooser = new FileChooser();
        /**Устанавливаем ограничения на TXT Format**/
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        /**Открываем saveDialog**/
        File file = fileChooser.showSaveDialog(null);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.getName())))
        {
            oos.writeObject(toArrayList(usersData));
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Во время сохранения файла произошла ошибка!");
            alert.showAndWait();
        }
    }

    @FXML
    /**Обработка отображения кнопок из меню**/
    private void handleMainButtonAction(MouseEvent event) {
        if (event.getTarget() == btn_home) {
            label_main_name.setText("Главная");
            anchor_users.setVisible(false);
            anchor_edit.setVisible(false);
            anchor_settings.setVisible(false);
            anchor_main.setVisible(true);
        } else if (event.getTarget() == btn_users) {
            label_main_name.setText("Пользователи");
            anchor_users.setVisible(true);
            anchor_edit.setVisible(false);
            anchor_settings.setVisible(false);
            anchor_main.setVisible(false);
        } else if (event.getTarget() == btn_edit) {
            label_main_name.setText("Редактирование");
            anchor_users.setVisible(false);
            anchor_edit.setVisible(true);
            anchor_settings.setVisible(false);
            anchor_main.setVisible(false);
        } else if (event.getTarget() == btn_settings) {
            label_main_name.setText("Настройки");
            anchor_users.setVisible(false);
            anchor_edit.setVisible(false);
            anchor_settings.setVisible(true);
            anchor_main.setVisible(false);
        }
    }

    /**Проверка всех полей для добавления сотрудника**/
    private boolean checkAdd() {
        boolean flag = false;
        Position temp = (Position) choise_position.getValue();
        if (field_second_name.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Введите фамилию!");
            alert.showAndWait();
            flag = true;
        } else if (field_second_name.getText().length() < 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Длина фамилии слишком маленькая!");
            alert.showAndWait();
            flag = true;
        } else if (field_first_name.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Введите имя!");
            alert.showAndWait();
            flag = true;
        } else if (field_first_name.getText().length() < 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Длина имени слишком маленькая!");
            alert.showAndWait();
            flag = true;
        } else if (field_middle_name.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Введите отчество!");
            alert.showAndWait();
            flag = true;
        } else if (field_middle_name.getText().length() < 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Длина отчества слишком маленькая!");
            alert.showAndWait();
            flag = true;
        } else if (temp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Выберите должность сотрудника!");
            alert.showAndWait();
            flag = true;
        } else if (field_salary.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Введите зарплату сотрудника!");
            alert.showAndWait();
            flag = true;
        } else if (Short.parseShort(field_salary.getText()) < 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Зарплата сотрудника должна быть > 0");
            alert.showAndWait();
            flag = true;
        }
        return flag;
    }

    /**Генерация не повторяющихся ID**/
    private short generateId() {
        short random = (short) (80 + Math.random()*1000);
        for (int i = 0; i < usersData.size(); i++) {
            if(usersData.get(i).getId()==random) {
                i=0;
                random = (short) (80 + Math.random()*1000);
            }
        }
        return random;
    }

    @FXML
    /**Добавление сотрудника в список**/
    private void handleAddUserAction() {
        if (checkAdd() == false) {
            usersData.add(new Person(field_second_name.getText(), field_first_name.getText(), field_middle_name.getText(), Position.Junior, generateId(), Short.parseShort(field_salary.getText())));
        }
    }

    @FXML
    /**Удаление из списка по ID**/
    private void handleDelUserAction() {
        if (usersData.size() != 0) {
            short count = 0;
            String id = field_del_person.getText();
            if (!id.equals("")) {
                for (int i = 0; i < usersData.size(); i++) {
                    if (usersData.get(i).getId()==Short.parseShort(id)) {
                        usersData.remove(i);
                        count++;
                        i--;
                    }
                }
                if (count == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Информация");
                    alert.setHeaderText(null);
                    alert.setContentText("Сотрудник с данным ID не найден!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите ID сотрудника!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText(null);
            alert.setContentText("Список сотрудников пуст!");
            alert.showAndWait();
        }
    }

    private void initTableView() {
        /**Устанавливаем тип и значение которое должно хранится в колонке**/
        db_second_name.setCellValueFactory(new PropertyValueFactory<Person, String>("secondName"));
        db_first_name.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        db_middle_name.setCellValueFactory(new PropertyValueFactory<Person, String>("middleName"));
        db_position.setCellValueFactory(new PropertyValueFactory<Person, String>("position"));
        db_pay.setCellValueFactory(new PropertyValueFactory<Person, Short>("pay"));
        db_id.setCellValueFactory(new PropertyValueFactory<Person, Short>("id"));
        /**Заполняем таблицу данными из коллекции UsersData**/
        table_database.setItems(usersData);
        /**Разрешаем редактирование**/
        table_database.setEditable(true);
        db_second_name.setCellFactory(TextFieldTableCell.forTableColumn());
        db_first_name.setCellFactory(TextFieldTableCell.forTableColumn());
        db_middle_name.setCellFactory(TextFieldTableCell.forTableColumn());
        db_pay.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter<Short>) new ShortStringConverter()));
    }

    @FXML
    void initialize() {
        /**Установка вариантов для Choise Box**/
        choise_position.setItems(FXCollections.observableArrayList(Position.Junior, Position.Middle, Position.Senior, Position.Director));
        initTableView();
    }

    /**Редактирование ячеек Фамилии**/
    public void onEditSecondName(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = table_database.getSelectionModel().getSelectedItem();
        person.setSecondName(personStringCellEditEvent.getNewValue());
    }

    /**Редактирование ячеек Имени**/
    public void onEditFirstName(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = table_database.getSelectionModel().getSelectedItem();
        person.setFirstName(personStringCellEditEvent.getNewValue());
    }

    /**Редактирование ячеек Отчества**/
    public void onEditMiddleName(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = table_database.getSelectionModel().getSelectedItem();
        person.setMiddleName(personStringCellEditEvent.getNewValue());
    }

    /**Редактирование ячеек Оклада**/
    public void onEditPayName(TableColumn.CellEditEvent<Person, Short> personStringCellEditEvent) {
        Person person = table_database.getSelectionModel().getSelectedItem();
        person.setPay(personStringCellEditEvent.getNewValue());
    }
}
