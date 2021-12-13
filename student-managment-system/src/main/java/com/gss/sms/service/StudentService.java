package com.gss.sms.service;

import com.gss.sms.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudentId(Long id);

    Student updateStudent(Student student);

    void deleteStudent(Long id);
}
