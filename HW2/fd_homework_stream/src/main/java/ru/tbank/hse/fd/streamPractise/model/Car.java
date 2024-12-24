package ru.tbank.hse.fd.streamPractise.model;

import ru.tbank.hse.fd.streamPractise.utils.Condition;

import java.util.List;
import java.util.Objects;

public class Car {

    private String name;
    private int age;
    private Condition condition;
    private List<Owner> owners;

    public Car(String name, int age, Condition condition, List<Owner> owners) {
        this.name = name;
        this.age = age;
        this.condition = condition;
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

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return age == car.age && Objects.equals(name, car.name) && condition == car.condition && Objects.equals(owners, car.owners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, condition, owners);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", condition=" + condition +
                ", owners=" + owners +
                '}';
    }


}
