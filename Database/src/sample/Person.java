package sample;

public class Person {
    private String secondName; // Фамилия
    private String firstName; // Имя
    private String middleName; // Отчество
    private Position position; // Должность
    private short id; // ID

    public Person(String secondName, String firstName, String middleName, Position position, short id) {
       setSecondName(secondName);
       setFirstName(firstName);
       setMiddleName(middleName);
       setPosition(position);
       setId(id);
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }
}
