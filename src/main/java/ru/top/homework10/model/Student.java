package ru.top.homework10.model;

import ru.top.homework10.dto.StudentDto;

public class Student {
    //    Id
//    Name
//    Surname
//    Age
//    Phone ne obyazatelnoe pole
//    Email  unikalnoe pole
    private int id;
    private String name;
    private String surname;
    private int age;
    private int phone;
    private String email;
    private static int count = 1;

    public Student( String name, String surname, int age, int phone, String email) {
        this.id = count++;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public StudentDto convert(){
        return new StudentDto(this.name, this.surname, this.age, this.phone, this.email);
    }
}
