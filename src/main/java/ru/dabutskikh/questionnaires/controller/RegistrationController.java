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
import ru.dabutskikh.questionnaires.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getRegistationPage(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("userForm") User userForm) {
        if (userService.findByLogin(userForm.getLogin()) != null
                || !userForm.getPassword().equals(userForm.getConfirmPassword())
        ) {
            return "registration";
        } else {
            userService.save(userForm);
            return "redirect:/auth/login";
        }
    }
}
