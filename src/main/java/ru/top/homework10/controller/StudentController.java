package ru.top.homework10.controller;

//ЗАДАНИЕ: РЕАЛИЗАЦИЯ CRUD ОПЕРАЦИЙ ДЛЯ СУЩНОСТИ STUDENT
//Техническое задание
//
//Цель задачи
//Создать полнофункциональное  приложение с CRUD операциями .
//Вернуть HTTP Statuses правильно!!
//
//Требования
//Структура Students:
//
// Students (
//    Id
//    Name
//    Surname
//    Age
//    Phone ne obyazatelnoe pole
//    Email  unikalnoe pole
//);
//
//
//Требуемый функционал
//1. Операция CREATE - Добавление записей
//Добавление одного студента
//
//Проверка уникальности email
//
//Валидация входных данных (возраст, email формат)
//
//2. Операция READ - Чтение записей
//Получение всех студентов
//
//Получение студента по ID
//
//Поиск по имени и фамилии
//
//Поиск по email
//
//Фильтрация по возрасту
//
//3. Операция UPDATE - Обновление записей
//Обновление данных существующего студента по ID
//
//Частичное обновление полей
//
//Проверка уникальности email при обновлении
//
//4. Операция DELETE - Удаление записей
//Удаление студента по ID
//
//Удаление всех записей
//
//
//1. Показать всех студентов
//2. Найти студента по ID
//3. Добавить нового студента
//4. Обновить данные студента
//5. Удалить студента
//6. Поиск по имени/фамилии
//7. Поиск по email
//8. Фильтр по возрасту
//
//


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.top.homework10.dto.StudentDto;
import ru.top.homework10.model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    List<Student> students;
    private static String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public StudentController() {
        students = new ArrayList<Student>();
    }
    @PostMapping("/")
public ResponseEntity <Student> add(@RequestBody StudentDto studentDto) {
       if (studentDto.getEmail() == null || studentDto.getEmail().isEmpty()||!studentDto.getEmail().matches(emailRegex)) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

       if(students.stream().anyMatch(student ->student.getEmail()!=null&& student.getEmail().equalsIgnoreCase(studentDto.getEmail()))) {
           return new ResponseEntity<>(HttpStatus.CONFLICT);
       }

       if (studentDto.getName()==null || studentDto.getName().isEmpty()|| studentDto.getName().length()<2 || studentDto.getName().length()>50) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       if(studentDto.getSurname()==null || studentDto.getSurname().isEmpty() || studentDto.getSurname().length()<2 || studentDto.getSurname().length()>50) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       if (studentDto.getAge()==null || studentDto.getAge()<0 || studentDto.getAge()>100 ){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
//        students.add(studentDto.convert());

      Student student=studentDto.convert();
students.add(student);


        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }
}
