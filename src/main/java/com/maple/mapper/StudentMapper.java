package com.maple.mapper;

import com.maple.entities.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    List<Student> selectAllStudents();

    Student selectStudentByStudentID(@Param("stu_id") String studentID);

    boolean addStudent(Student student);

    boolean deleteStudentByStudentID(@Param("stu_id") String studentID);

    boolean modifyStudentData(Student student);

    Integer getTotalItem();

    List<Student> getStudentItems(@Param("start") int start, @Param("size") int size);

    List<Student> fuzzySearchStudent(Student student);
}
