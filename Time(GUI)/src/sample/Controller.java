package sample;

import java.net.URL;
import java.util.ResourceBundle;

import com.company.Time;
import com.company.TimeException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label_time_first;

    @FXML
    private Label label_time_second;

    @FXML
    private ImageView btn_plus_minus;

    @FXML
    private ImageView btn_add;

    @FXML
    private ImageView btn_refresh;

    @FXML
    private ImageView btn_info;

    @FXML
    private Spinner<Integer> spinner_hour_first;

    @FXML
    private Spinner<Integer> spinner_minute_first;

    @FXML
    private Spinner<Integer> spinner_second_first;

    @FXML
    private Spinner<Integer> spinner_hour_second;

    @FXML
    private Spinner<Integer> spinner_minute_second;

    @FXML
    private Spinner<Integer> spinner_second_second;

    @FXML
    private Spinner<Integer> spinner_second_how_add;

    @FXML
    private Spinner<Integer> spinner_hour_add;

    @FXML
    private Spinner<Integer> spinner_minute_add;

    @FXML
    private Spinner<Integer> spinner_second_add;

    @FXML
    private Spinner<Integer> spinner_hour_refresh;

    @FXML
    private Spinner<Integer> spinner_minute_refresh;

    @FXML
    private Spinner<Integer> spinner_second_refresh;

    @FXML
    private AnchorPane anchor_add,anchor_plus_minus,anchor_refresh,anchor_info;

    @FXML
    private RadioButton radio_btn_minus;

    @FXML
    private RadioButton radio_btn_sum;

    @FXML
    private Button btn_result;

    @FXML
    private TextField textfield_result;

    @FXML
    private RadioButton rbn_minute;

    @FXML
    private RadioButton rbn_second;

    @FXML
    private RadioButton rbn_hour;

    @FXML
    private TextField text_field_add_result;

    @FXML
    private RadioButton rbn_minute_refresh;

    @FXML
    private RadioButton rbn_second_refresh;

    @FXML
    private RadioButton rbn_hour_refresh;

    @FXML
    private TextField text_field_refresh;

    private ToggleGroup toggleGroupButton = new ToggleGroup();
    private ToggleGroup toggleGroupRbn = new ToggleGroup();
    private ToggleGroup toggleGroupRbnRefresh = new ToggleGroup();

    @FXML
    private void handleButtonAction(MouseEvent event) {
        if(event.getTarget() == btn_plus_minus) {
            anchor_plus_minus.setVisible(true);
            anchor_add.setVisible(false);
            anchor_refresh.setVisible(false);
            anchor_info.setVisible(false);
        }
        else
            if(event.getTarget() == btn_add) {
                anchor_plus_minus.setVisible(false);
                anchor_add.setVisible(true);
                anchor_refresh.setVisible(false);
                anchor_info.setVisible(false);
            }
            else
                if(event.getTarget() == btn_refresh) {
                    anchor_plus_minus.setVisible(false);
                    anchor_add.setVisible(false);
                    anchor_refresh.setVisible(true);
                    anchor_info.setVisible(false);
                }
                else
                    if(event.getTarget() == btn_info) {
                        anchor_plus_minus.setVisible(false);
                        anchor_add.setVisible(false);
                        anchor_refresh.setVisible(false);
                        anchor_info.setVisible(true);
                    }
    }
    @FXML
    /**Обработка арифметических действий над временем**/
    private void handleResultAction(MouseEvent event) throws TimeException {
        if(radio_btn_sum.isSelected()) {
            Time first = new Time(spinner_hour_first.getValue(),spinner_minute_first.getValue(),spinner_second_first.getValue());
            Time second = new Time(spinner_hour_second.getValue(),spinner_minute_second.getValue(),spinner_second_second.getValue());
            Time result;
            result = first.Sum(second);
            textfield_result.setText(result.getHours() + " час(ов) " + result.getMinutes() + " минут(ы) " + result.getSeconds() + " секунд(ы)");
        } else
            if(radio_btn_minus.isSelected()) {
                    Time first = new Time(spinner_hour_first.getValue(),spinner_minute_first.getValue(),spinner_second_first.getValue());
                    Time second = new Time(spinner_hour_second.getValue(),spinner_minute_second.getValue(),spinner_second_second.getValue());
                    Time result;
                    result = first.Sub(second);
                    textfield_result.setText(result.getHours() + " час(ов) " + result.getMinutes() + " минут(ы) " + result.getSeconds() + " секунд(ы)");
                }
            else {
                textfield_result.setText("Выберите действие!");
            }
    }

    @FXML
    /**Обработка добавления часов, минут, секунд**/
    private void handleAddTimeAction(MouseEvent event) throws TimeException {
        if(rbn_hour.isSelected()) {
            Time time = new Time(spinner_hour_add.getValue(),spinner_minute_add.getValue(),spinner_second_add.getValue());
            time.addHours(spinner_second_how_add.getValue());
            text_field_add_result.setText(time.getHours() + " час(ов) " + time.getMinutes() + " минут(ы) " + time.getSeconds() + " секунд(ы)");
        } else
            if(rbn_minute.isSelected()) {
                Time time = new Time(spinner_hour_add.getValue(),spinner_minute_add.getValue(),spinner_second_add.getValue());
                time.addMinutes(spinner_second_how_add.getValue());
                text_field_add_result.setText(time.getHours() + " час(ов) " + time.getMinutes() + " минут(ы) " + time.getSeconds() + " секунд(ы)");
            } else
                if(rbn_second.isSelected()) {
                    Time time = new Time(spinner_hour_add.getValue(),spinner_minute_add.getValue(),spinner_second_add.getValue());
                    time.addSeconds(spinner_second_how_add.getValue());
                    text_field_add_result.setText(time.getHours() + " час(ов) " + time.getMinutes() + " минут(ы) " + time.getSeconds() + " секунд(ы)");
                }
                else {
                    text_field_add_result.setText("Выберите действие!");
                }
    }

    @FXML
    private void handleRefreshAction(MouseEvent event) throws TimeException {
        if(rbn_hour_refresh.isSelected()) {
            Time time = new Time(spinner_hour_refresh.getValue(),spinner_minute_refresh.getValue(),spinner_second_refresh.getValue());
            text_field_refresh.setText(time.toHours()+"");
        }
        else
            if(rbn_minute_refresh.isSelected()) {
                Time time = new Time(spinner_hour_refresh.getValue(),spinner_minute_refresh.getValue(),spinner_second_refresh.getValue());
                text_field_refresh.setText(time.toMinutes()+"");
            }
            else
                if(rbn_second_refresh.isSelected()) {
                    Time time = new Time(spinner_hour_refresh.getValue(),spinner_minute_refresh.getValue(),spinner_second_refresh.getValue());
                    text_field_refresh.setText(time.toSeconds()+"");
                }
                else {
                    text_field_refresh.setText("Выберите действие!");
                }

    }


    /**Создаем группу radioButton, для раздельной работы в разделе +/-**/
    private void setToggleGroupButton(ToggleGroup toggleGroupButton) {
        radio_btn_sum.setToggleGroup(toggleGroupButton);
        radio_btn_minus.setToggleGroup(toggleGroupButton);
    }

    /**Создаем группу radioButton, для раздельной работы в разделе +**/
    private void setToggleGroupRbn(ToggleGroup toggleGroupButton) {
        rbn_hour.setToggleGroup(toggleGroupButton);
        rbn_minute.setToggleGroup(toggleGroupButton);
        rbn_second.setToggleGroup(toggleGroupButton);
    }

    /**Создаем группу radioButton, для раздельной работы в разделе перевода времени**/
    private void setToggleGroupRbnRefresh(ToggleGroup toggleGroupButton) {
        rbn_hour_refresh.setToggleGroup(toggleGroupButton);
        rbn_minute_refresh.setToggleGroup(toggleGroupButton);
        rbn_second_refresh.setToggleGroup(toggleGroupButton);
    }

    /**Устанавливаем ограничения и начальные значения
     * для спиннеров времени**/
    private void setSpinnerValue() {
        spinner_hour_first.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0));
        spinner_hour_second.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0));
        spinner_minute_first.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        spinner_minute_second.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        spinner_second_first.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        spinner_second_second.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        spinner_hour_add.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0));
        spinner_minute_add.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        spinner_second_add.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        spinner_second_how_add.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000000,0));
        spinner_hour_refresh.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0));
        spinner_minute_refresh.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        spinner_second_refresh.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
    }

    @FXML
    void initialize() {
        setSpinnerValue();
        setToggleGroupButton(toggleGroupButton);
        setToggleGroupRbn(toggleGroupRbn);
        setToggleGroupRbnRefresh(toggleGroupRbnRefresh);
    }
}
