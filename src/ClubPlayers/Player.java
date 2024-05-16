package ClubPlayers;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Player implements Serializable {
    private String Name;
    private String Country;
    private int Age;
    private double Height;
    private String Club;
    private String Position;
    private int Number;
    private double price;
    private double WeeklySalary;
    private transient Image image;
    private int freeAgent;

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }
    public void setCountry(String country) {
        Country = country;
    }

    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        this.Age = age;
    }

    public double getHeight() {
        return Height;
    }
    public void setHeight(double height) {
        this.Height = height;
    }

    public String getClub() {
        return Club;
    }
    public void setClub(String club) {
        Club = club;
    }

    public String getPosition() {
        return Position;
    }
    public void setPosition(String position) {
        Position = position;
    }

    public int getNumber() {
        return Number;
    }
    public void setNumber(int number) {
        Number = number;
    }

    public double getWeeklySalary() {
        return WeeklySalary;
    }
    public void setWeeklySalary(double weeklySalary) {
        WeeklySalary = weeklySalary;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public Image getImage() {
        return image;
    }

    public int getFreeAgent() {
        return freeAgent;
    }
    public void setFreeAgent(int freeAgent) {
        this.freeAgent = freeAgent;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Player{" +
                "\nName=" + Name +
                ",\nCountry=" + Country +
                ",\nAge=" + Age +
                ",\nHeight=" + Height +
                ",\nClub=" + Club +
                ",\nPosition=" + Position +
                ",\nNumber=" + Number +
                ",\nWeeklySalary=" + WeeklySalary +
                '}';
    }


}

