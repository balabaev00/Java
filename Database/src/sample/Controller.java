package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.ShortStringConverter;

import java.io.*;
import java.security.Key;
import java.util.ArrayList;

//TODO
// 1 - fix Position add (+)
// 2 - add search (+)
// 3 - add icon program (+)
// 4 - info of program developer (+)
// 5 - hot key (+-)
// 6 - auto save database (+)
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
    private TextField field_search_data;

    @FXML
    private ChoiceBox choise_position;

    @FXML
    private ChoiceBox choice_del_person;

    @FXML
    private Button btn_add_person;

    private String version = "Версия программы 1.0";

    /**Для хранения имени файла сохранения**/
    private String filename;

    /**
     * Создание Table View
     **/
    private ObservableList<Person> usersData = FXCollections.observableArrayList();

    /**
     * ObservableList convert to ArrayList
     **/
    private ArrayList<Person> toArrayList(ObservableList<Person> usersData) {
        ArrayList<Person> usersDataArray = new ArrayList<Person>();
        for (int i = 0; i < usersData.size(); i++) {
            usersDataArray.add(usersData.get(i));
        }
        return usersDataArray;
    }

    /**
     * ArrayList convert to ObservableList
     **/
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
    private ImageView img_open;

    @FXML
    private ImageView img_save;

    @FXML
    private ImageView img_faq;

    @FXML
    private Label label_autosave;

    @FXML
    private Button btn_search_person;

    private boolean faqFlag = false;

    private Autosave autosave;

    private void initAutoSave(int second) {
        autosave = new Autosave(null,label_autosave,usersData);
        autosave.setSecond(second);
    }

    @FXML
    private void handleCloseButtonAction() {
        Stage stage = (Stage) btn_home.getScene().getWindow();
        autosave.setFlag(false);
        if(autosave.isAlive())
            autosave.interrupt();
        stage.close();
    }

    @FXML
    private void handleHideButtonAction() {
        Stage stage = (Stage) btn_close.getScene().getWindow();
        // Доработать
        stage.iconifiedProperty().addListener( ( ov, t, t1 ) -> {
            Platform.runLater( stage::hide );
        });
    }

    @FXML
    private void handleOpenFileAction() {
        FileChooser fileChooser = new FileChooser();
        /**Устанавливаем ограничения на TXT Format**/
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        /**Открываем openDialog**/
        File file = fileChooser.showOpenDialog(null);
        autosave.setFile(file);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getName()))) {
            usersData = toObservableList((ArrayList<Person>) ois.readObject());
            table_database.setItems(usersData);
            autosave.setList(usersData);
            if(!autosave.isAlive())
                autosave.start();

        } catch (Exception ex) {
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
        autosave.setFile(file);
        filename = file.getName();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.getName()))) {
            oos.writeObject(toArrayList(usersData));
            if(!autosave.isAlive())
                autosave.start();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Во время сохранения файла произошла ошибка!");
            alert.showAndWait();
        }
    }

    /**
     * Костыль всех костылей
     **/
    private void faqBool() {
        /**My name is "Костыль"**/
        if (faqFlag == false) {
            setHotKey();
            faqFlag = true;
        }
    }

    @FXML
    /**Обработка кнопки Главная из кнопок меню**/
    private void handleMainButtonAction() {
        faqBool();
        label_main_name.setText("Главная");
        anchor_users.setVisible(false);
        anchor_edit.setVisible(false);
        anchor_main.setVisible(true);
    }

    @FXML
    /**Обработка кнопки Пользователи из кнопок меню**/
    private void handleUsersButtonAction() {
        faqBool();
        label_main_name.setText("Пользователи");
        anchor_users.setVisible(true);
        anchor_edit.setVisible(false);
        anchor_main.setVisible(false);
    }


    @FXML
    /**Обработка кнопки Редактирование из кнопок меню**/
    private void handleEditButtonAction() {
        faqBool();
        label_main_name.setText("Редактирование");
        anchor_users.setVisible(false);
        anchor_edit.setVisible(true);
        anchor_main.setVisible(false);
    }

    /**
     * Проверка всех полей для добавления сотрудника
     **/
    private boolean checkAdd() {
        boolean flag = false;
        Position temp = (Position) choise_position.getValue();
        if(Person.checkInfo(field_second_name.getText(),field_first_name.getText(),field_middle_name.getText(),
                Short.parseShort(field_salary.getText()),temp)) {
            flag=true;
        }
        return flag;
    }

    /**
     * Генерация не повторяющихся ID
     **/
    private short generateId() {
        short random = (short) (80 + Math.random() * 1000);
        for (int i = 0; i < usersData.size(); i++) {
            if (usersData.get(i).getId() == random) {
                i = 0;
                random = (short) (80 + Math.random() * 1000);
            }
        }
        return random;
    }

    @FXML
    /**Добавление сотрудника в список**/
    private void handleAddUserAction() {
        if (checkAdd() == false) {
            usersData.add(new Person(field_second_name.getText(), field_first_name.getText(), field_middle_name.getText(), (Position) choise_position.getValue(), generateId(), Short.parseShort(field_salary.getText())));
        }
    }

    @FXML
    /**Удаление из списка**/
    private void handleDelUserAction() {
        if (usersData.size() != 0) {
            short count = 0;
            String text = field_del_person.getText();
            String choiceBox = (String) choice_del_person.getValue();
            if (!text.equals("")) {
                if (choiceBox != null) {
                    if (choiceBox.equals("ID")) {
                        for (int i = 0; i < usersData.size(); i++) {
                            if (usersData.get(i).getId() == Short.parseShort(text)) {
                                usersData.remove(i);
                                count++;
                                i--;
                            }
                        }
                    } else if (choiceBox.equals("Фамилия")) {
                        for (int i = 0; i < usersData.size(); i++) {
                            if (usersData.get(i).getSecondName().equals(text)) {
                                usersData.remove(i);
                                count++;
                                i--;
                            }
                        }
                    } else if (choiceBox.equals("Имя")) {
                        for (int i = 0; i < usersData.size(); i++) {
                            if (usersData.get(i).getFirstName().equals(text)) {
                                usersData.remove(i);
                                count++;
                                i--;
                            }
                        }
                    } else if (choiceBox.equals("Отчество")) {
                        for (int i = 0; i < usersData.size(); i++) {
                            if (usersData.get(i).getMiddleName().equals(text)) {
                                usersData.remove(i);
                                count++;
                                i--;
                            }
                        }
                    }
                    if (count == 0) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Информация");
                        alert.setHeaderText(null);
                        alert.setContentText("Сотрудник по заданным параметрам не найден!");
                        alert.showAndWait();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Информация");
                        alert.setHeaderText(null);
                        alert.setContentText("Сотрудник удален!");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Информация");
                    alert.setHeaderText(null);
                    alert.setContentText("Выберите параметр для поиска!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("Введите параметр для поиска!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Информация");
            alert.setHeaderText(null);
            alert.setContentText("Список сотрудников пуст!");
            alert.showAndWait();
        }
    }

    @FXML
    /**Поиск сотрудника в списке**/
    private void handleFindPerson() {
        String text = field_search_data.getText();
        boolean flag = false;
        if(!text.equals("") && usersData.size()>0) {
            for (int i = 0; i < usersData.size(); i++) {
                if(flag==false && usersData.get(i).getSecondName().equals(text)) {
                    flag=true;
                    table_database.getSelectionModel().select(i);
                    break;
                } else
                    if(flag==false && usersData.get(i).getFirstName().equals(text)) {
                        flag=true;
                        table_database.getSelectionModel().select(i);
                        break;
                    } else
                    if(flag==false && usersData.get(i).getMiddleName().equals(text)) {
                        flag = true;
                        table_database.getSelectionModel().select(i);
                        break;
                    }
            }
            if (flag == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("Сотрудник не найден!");
                alert.showAndWait();
                table_database.getSelectionModel().clearSelection();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Информация");
            alert.setHeaderText(null);
            alert.setContentText("Введите параметр для поиска!");
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
    /**Редактирование ячеек Фамилии**/
    private void onEditSecondName(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = table_database.getSelectionModel().getSelectedItem();
        person.setSecondName(personStringCellEditEvent.getNewValue());
    }

    @FXML
    /**Редактирование ячеек Имени**/
    private void onEditFirstName(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = table_database.getSelectionModel().getSelectedItem();
        person.setFirstName(personStringCellEditEvent.getNewValue());
    }

    @FXML
    /**Редактирование ячеек Отчества**/
    private void onEditMiddleName(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent) {
        Person person = table_database.getSelectionModel().getSelectedItem();
        person.setMiddleName(personStringCellEditEvent.getNewValue());
    }

    @FXML
    /**Редактирование ячеек Оклада**/
    private void onEditPayName(TableColumn.CellEditEvent<Person, Short> personStringCellEditEvent) {
        Person person = table_database.getSelectionModel().getSelectedItem();
        person.setPay(personStringCellEditEvent.getNewValue());
    }

    /**
     * FAQ
     **/
    @FXML
    private void handleFAQAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("faq.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    /**Установка горячих клавиш**/
    private void setHotKey() {
        Stage stage = (Stage) img_open.getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setOnKeyPressed(event -> {
            if (event.getCode().getName().equals("F1")) {
                handleOpenFileAction();
            } else if (event.getCode().getName().equals("F2")) {
                handleSaveFileAction();
            } else if (event.getCode().getName().equals("F3")) {
                try {
                    handleFAQAction();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void initialize() {
        /**Настраиваем поток**/
        initAutoSave(30);
        /**Установка вариантов для Choise Box**/
        choise_position.setItems(FXCollections.observableArrayList(Position.Junior, Position.Middle, Position.Senior, Position.Director));
        choice_del_person.setItems(FXCollections.observableArrayList(new String("Фамилия"),new String("Имя"),new String("Отчество"),new String("ID")));
        initTableView();
    }
}
