package com.example.studentdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/")
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String studentForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "studentform";
        }

        studentRepository.save(student);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showPet(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", studentRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updatePet(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", studentRepository.findOne(id));
        return "studentform";
    }

    @RequestMapping("/delete/{id}")
    public String delPet(@PathVariable("id") long id, Model model) {
        studentRepository.delete(id);
        return "redirect:/";
    }


}
