package cn.zs.learn.java.rmi;

import java.io.Serializable;
import java.util.Objects;

public class RMITransferObject implements Serializable {

    private String name;
    private int age;

    public RMITransferObject() {
    }

    public RMITransferObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "RMITransferObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RMITransferObject that = (RMITransferObject) o;
        return age == that.age &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
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
}
