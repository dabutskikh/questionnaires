package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String getAllQuestionnaires(Model model) {
        model.addAttribute("questionnaires", questionnaireService.findAll());
        return "all_questionnaires";
    }

    @PostMapping
    public String createQuestionnaire(@ModelAttribute Questionnaire questionnaireForm,
                                      @AuthenticationPrincipal UserDetails currentUser) {
        questionnaireForm.setAuthor(userService.findByLogin(currentUser.getUsername()));
        questionnaireService.save(questionnaireForm);
        return "redirect:/questionnaires";
    }

    @GetMapping("/new")
    public String getCreatingQuestionnairePage(Model model) {

        model.addAttribute("questionnaireForm", new Questionnaire());
        return "new_questionnaire";
    }

//    @GetMapping("/{id}")
//    public String getQuestionnaire() {
//        return null;
//    }

    @GetMapping("/{id}/edit")
    public String getUpdatingQuestionnairePage(@PathVariable Long id,
                                               Model model) {
        model.addAttribute("questionnaire", questionnaireService.findById(id));
        return "edit_questionnaire";
    }

    @PatchMapping("/{id}")
    public String updateQuestionnaire(@PathVariable Long id,
                                      @ModelAttribute Questionnaire questionnaire) {
        questionnaireService.update(id, questionnaire);
        return "redirect:/questionnaires";
    }
}
