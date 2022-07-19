package com.synacy.gradprogram.spock;

public class Person {
    private String name;
    private int age;
    private Address address;
    private Sex sex;

    public Person(String name, int age, Address address, Sex sex) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String toString() {
        return "Name: " + name +
                ", Age: " + age +
                ", Address: (" + address.toString() + ")" +
                ", Sex: " + sex;
    }
}
