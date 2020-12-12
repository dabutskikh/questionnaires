package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dabutskikh.questionnaires.model.Questionnaire;
import ru.dabutskikh.questionnaires.service.QuestionnaireService;
import ru.dabutskikh.questionnaires.service.UserService;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnaireController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionnaireService questionnaireService;

    @GetMapping
    public String getAllQuestionnaires() {
        return null;
    }

    @PostMapping
    public String createQuestionnaire() {
        return null;
    }

    @GetMapping("/new")
    public String getCreatingQuestionnairePage(Model model,
                                               @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("author", userService.findByLogin(currentUser.getUsername()));
        model.addAttribute("questionnaireForm", new Questionnaire());
        return "new_questionnaire";
    }

    @GetMapping("/{id}")
    public String getQuestionnaire() {
        return null;
    }

    @GetMapping("/{id}/edit")
    public String getUpdatingQuestionnairePage() {
        return null;
    }

    @PatchMapping("/{id}")
    public String updateQuestionnaire() {
        return null;
    }
}
