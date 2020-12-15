package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dabutskikh.questionnaires.model.User;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionnaireService;
import ru.dabutskikh.questionnaires.service.interfaces.UserService;

@Controller
public class UserQuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    UserService userService;

    @GetMapping("/questionnaire/{id}")
    public String getQuestionnaire(Model model,
                                   @PathVariable Long id) {

        model.addAttribute("user", new User());
        model.addAttribute("questionnaire", questionnaireService.findById(id));
        return "questionnaire";
    }

    @PostMapping("/questionnaire")
    public String postQuestionnaire(@ModelAttribute("user") User user,
                                    @AuthenticationPrincipal UserDetails currentUserDetails) {
        User currentUser = userService.findByLogin(currentUserDetails.getUsername());
        userService.addAnswers(currentUser, user.getAnswers());
        return "redirect:/";
    }
}
