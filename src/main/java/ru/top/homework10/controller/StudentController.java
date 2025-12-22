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
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    List<Student> students;
    private static String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public StudentController() {
        students = new ArrayList<Student>();
    }

    @PostMapping("/")
    public ResponseEntity<StudentDto> add(@RequestBody StudentDto studentDto) {
        if (studentDto.getEmail() == null || studentDto.getEmail().isEmpty() || !studentDto.getEmail().matches(emailRegex)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (students.stream().anyMatch(student -> student.getEmail() != null && student.getEmail().equalsIgnoreCase(studentDto.getEmail()))) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (studentDto.getName() == null || studentDto.getName().isEmpty() || studentDto.getName().length() < 2 || studentDto.getName().length() > 50) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (studentDto.getSurname() == null || studentDto.getSurname().isEmpty() || studentDto.getSurname().length() < 2 || studentDto.getSurname().length() > 50) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (studentDto.getAge() == null || studentDto.getAge() < 0 || studentDto.getAge() > 100) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        students.add(studentDto.convert());

        Student student = studentDto.convert();
        students.add(student);

        StudentDto newStudentDto = student.convert();


        return new ResponseEntity<>(newStudentDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAll() {
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<StudentDto> list = students.stream().map(student -> student.convert()).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable Integer id) {
        if (id<=0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (Student student : students) {
            if (student.getId() != null  && student.getId().equals(id)) {
                return new ResponseEntity<>(student.convert(), HttpStatus.OK);
            }

//        Optional<Student> student = students.stream().findFirst();
//            StudentDto studentDto = student.get().convert();
//        Optional<StudentDto> studentDto = students.stream().filter(student -> student.getId() != null && student.getId().equals(id)).map(student -> student.convert()).findFirst();
//        if (studentDto.isPresent()) {
//            return ResponseEntity.ok(studentDto.get());
//        }
//        return ResponseEntity.notFound().build();
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/")
    public ResponseEntity<List<StudentDto>> searchByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        if (name==null || name.isEmpty() || surname==null || surname.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     List<StudentDto> result =students.stream().filter(student -> student.getName()!=null&&student.getName().equalsIgnoreCase(name) &&
                student.getSurname()!=null&&student.getSurname().equalsIgnoreCase(surname)).map(student -> student.convert()).toList();
if (result.isEmpty()) {
//    return new ResponseEntity<>(result,HttpStatus.NO_CONTENT);
    return ResponseEntity.noContent().build();
}
return new ResponseEntity<>(result, HttpStatus.OK);

    }
@GetMapping("/email")
public ResponseEntity<StudentDto> searchByEmail(@RequestParam String email){
        if (email==null || email.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
   Optional<StudentDto> studentDto=students.stream().filter(student -> student.getEmail()!=null&&student.getEmail().equalsIgnoreCase(email)).map(student -> student.convert()).findFirst();
if (studentDto.isPresent()) {
    return new ResponseEntity<>(studentDto.get(), HttpStatus.OK);
}
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

}