package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Autosave extends Thread {
    private boolean flag = true;
    private int second;
    private File file;
    private Label label;
    private ObservableList<Person> list;
    private short count = 0;


    public Autosave(File file, Label label, ObservableList<Person> list) {
        this.file = file;
        this.label = label;
        this.list = list;
    }

    public ObservableList<Person> getList() {
        return list;
    }

    public void setList(ObservableList<Person> list) {
        this.list = list;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        this.count=0;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        if (second > 20) {
            this.second = second * 1000;
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private ArrayList<Person> toArrayList(ObservableList<Person> usersData) {
        ArrayList<Person> usersDataArray = new ArrayList<Person>();
        for (int i = 0; i < usersData.size(); i++) {
            usersDataArray.add(usersData.get(i));
        }
        return usersDataArray;
    }

    /**Сохранение list в file**/
    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.getName()))) {
            oos.writeObject(toArrayList(list));
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Во время автосохранения файла произошла ошибка!");
            alert.showAndWait();
        }
    }

    public void run() {
        while (flag == true) {
            /**Если это первое автосохранение после дефолтного сохранения, то ....**/
            if (count == 0) {
                try {
                    Thread.sleep(getSecond());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                save();
                label.setVisible(true);
                count++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                label.setVisible(false);
                try {
                    Thread.sleep(getSecond());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            } else {
                save();
                label.setVisible(true);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                label.setVisible(false);
                try {
                    Thread.sleep(getSecond());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}