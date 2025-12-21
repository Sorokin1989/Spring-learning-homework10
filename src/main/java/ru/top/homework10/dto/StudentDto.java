package ru.top.homework10.dto;

import ru.top.homework10.model.Student;

public class StudentDto {
    private String name;
    private String surname;
    private int age;
    private int phone;
    private String email;

    public StudentDto(String name, String surname, int age, int phone, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.email = email;
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
    public Student convert(){
return new Student(this.name, this.surname, this.age, this.phone, this.email);
    }
}
