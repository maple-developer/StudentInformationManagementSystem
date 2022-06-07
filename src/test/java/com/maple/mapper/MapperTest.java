package com.maple.mapper;

import com.maple.entities.Student;
import com.maple.entities.User;
import com.maple.utils.SqlSessionFactoryUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class MapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public void testSelectStudentById() throws IOException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//            Student student = mapper.selectStudentById(1);
//            log.info("select successfully: {}", student);
        }
    }

    @Test
    public void testSelectAllStudents() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> students = mapper.selectAllStudents();
            for (Student student : students) {
                log.info("{}", student);
            }
        }
    }

    @Test
    public void testRegister() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername("testName");
            user.setPasswd("testPasswd");
            user.setEmail("testEmail");
            boolean snow = mapper.register(user);
            System.out.println(snow);
            sqlSession.commit();
        }
    }

    @Test
    public void testFuzzySearch() {
        Student student = new Student();
        student.setStudentID("");
        student.setName("三丰");
        student.setAge(22);
        student.setAcademy("");
        student.setGrade("");
        student.setContact("");
        student.setStatus("");
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> students = mapper.fuzzySearchStudent(student);
            System.out.println(students.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
