package com.maple.service;

import com.maple.entities.Student;

import java.util.List;

public interface StudentService {

    List<Student> selectAll();

    boolean addStudent(Student student);

    Student findStudent(String studentID);

    boolean deleteStudent(String studentID);

    boolean modifyStudentData(Student student);

    Integer getTotalItem();

    List<Student> getStudentItems(int start, int size);

    List<Student> fuzzySearchStudent(Student student);
}
