package com.gss.sms.controller;

import com.gss.sms.entity.Student;
import com.gss.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getListStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createUserForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_students";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }


    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentId(id);
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {
        Student old = studentService.getStudentId(id);
        old.setEmail(student.getEmail());
        old.setFirstName(student.getFirstName());
        old.setLastName(student.getLastName());
        studentService.updateStudent(old);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String getStudent(@PathVariable Long id,
                             Model model) {
        model.addAttribute("student", studentService.getStudentId(id));
        return "student";
    }
}
