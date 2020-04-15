package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChatBot extends Bot {

    public ChatBot() {
        super();
    }

    @Override
    public String loadName() throws FileNotFoundException {
        File file = new File("information.csv");
        Scanner scanner = new Scanner(file);
        String name = null;
        while(scanner.hasNextLine()) {
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public void addHistory(String message) {

    }

    @Override
    public void loadHistory(String message) {

    }

}
