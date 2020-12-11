package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dabutskikh.questionnaires.model.User;
import ru.dabutskikh.questionnaires.repository.UserRepository;

@Controller
@RequestMapping("/registration")
public class RegistationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String getRegistationPage(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("userForm") User userForm) {
        if (userRepository.findByLogin(userForm.getLogin()).isPresent()) {
            userRepository.save(userForm);
            return "registation";
        } else {
            return "redirect:/";
        }
    }
}
