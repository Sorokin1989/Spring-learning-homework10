package ru.top.homework10.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.top.homework10.dto.StudentDto;

@JsonPropertyOrder({"id", "name", "surname", "age", "phone", "email"})
public class Student {
    //    Id
//    Name
//    Surname
//    Age
//    Phone ne obyazatelnoe pole
//    Email  unikalnoe pole

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Integer phone;
    private String email;
    private static int count = 1;

    public Student( String name, String surname, Integer age, Integer phone, String email) {
        this.id = count++;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
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
