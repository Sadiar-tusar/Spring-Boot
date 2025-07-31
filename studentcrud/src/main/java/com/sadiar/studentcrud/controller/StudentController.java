package com.sadiar.studentcrud.controller;

import com.sadiar.studentcrud.entity.Student;
import com.sadiar.studentcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String home(){

        return "home";
    }

    @GetMapping("/studentForm")
    public String studentForm(Model model){
        model.addAttribute("student", new Student());
        return "addstudent";
    }

    @PostMapping("/save")
    public  String save(@ModelAttribute Student student){
        studentService.save(student);
        return "redirect:/";

    }

    @GetMapping("")
    public String getAllStudent(Model model){
        List<Student> list=studentService.getAll();
        model.addAttribute("list",list);
        return "home";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Student student=studentService.findById(id);
        model.addAttribute("student", student);
        return "addstudent";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        studentService.deleteById(id);
        return "redirect:/";
    }
}
