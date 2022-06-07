package com.maple.service.impl;

import com.maple.entities.Student;
import com.maple.mapper.StudentMapper;
import com.maple.service.StudentService;
import com.maple.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> selectAll() {
        List<Student> students;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            students = mapper.selectAllStudents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public boolean addStudent(Student student) {
        boolean flag;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            flag = mapper.addStudent(student);
            sqlSession.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    @Override
    public Student findStudent(String studentID) {
        Student student;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            student = mapper.selectStudentByStudentID(studentID);
            sqlSession.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public boolean deleteStudent(String studentID) {
        boolean flag;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            flag = mapper.deleteStudentByStudentID(studentID);
            sqlSession.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    @Override
    public boolean modifyStudentData(Student student) {
        boolean flag;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            flag = mapper.modifyStudentData(student);
            sqlSession.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    @Override
    public Integer getTotalItem() {
        Integer totalItem;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            totalItem = mapper.getTotalItem();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return totalItem;
    }

    @Override
    public List<Student> getStudentItems(int start, int size) {
        List<Student> students;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            students = mapper.getStudentItems(start, size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public List<Student> fuzzySearchStudent(Student student) {
        List<Student> students;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            students = mapper.fuzzySearchStudent(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
