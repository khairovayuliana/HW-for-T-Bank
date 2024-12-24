package ru.tbank.hse.fd.streamPractise.model;

public class CarInfo {
    // Название машины
    private String name;
    // Возраст машины
    private int age;
    // Количество владельцев
    private int owners;

    public CarInfo(String name, int age, int owners) {
        this.name = name;
        this.age = age;
        this.owners = owners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOwners() {
        return owners;
    }

    public void setOwners(int owners) {
        this.owners = owners;
    }
}
