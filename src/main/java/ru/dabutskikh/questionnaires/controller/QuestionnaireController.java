package ru.dabutskikh.questionnaires.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnaireController {

    @GetMapping
    public String getAllQuestionnaires() {
        return null;
    }

    @PostMapping
    public String createQuestionnaire() {
        return null;
    }

    @GetMapping("/new")
    public String getCreatingQuestionnairePage() {
        return null;
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
