package ru.dabutskikh.questionnaires.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dabutskikh.questionnaires.model.Answer;
import ru.dabutskikh.questionnaires.model.Questionnaire;
import ru.dabutskikh.questionnaires.model.QuestionnaireStatus;
import ru.dabutskikh.questionnaires.model.User;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionnaireService;
import ru.dabutskikh.questionnaires.service.interfaces.UserAnswerService;
import ru.dabutskikh.questionnaires.service.interfaces.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('questionnaires:write')")
@RequestMapping("/questionnaires")
public class QuestionnaireController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    UserAnswerService userAnswerService;

    @GetMapping
    public String getAllQuestionnaires(Model model) {
        model.addAttribute("questionnaires", questionnaireService.findAll());
        return "all_questionnaires";
    }

    @PostMapping
    public String createQuestionnaire(@ModelAttribute Questionnaire questionnaireForm,
                                      @AuthenticationPrincipal UserDetails currentUser) {
        questionnaireForm.setAuthor(userService.findByLogin(currentUser.getUsername()));
        questionnaireForm.setStatus(QuestionnaireStatus.CREATED);
        questionnaireService.save(questionnaireForm);
        return "redirect:/questionnaires";
    }

    @GetMapping("/new")
    public String getCreatingQuestionnairePage(Model model) {

        model.addAttribute("questionnaireForm", new Questionnaire());
        return "new_questionnaire";
    }

    @GetMapping("/{id}/edit")
    public String getUpdatingQuestionnairePage(@PathVariable Long id,
                                               Model model) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        if (!questionnaire.isChangeable()) {
            return "redirect:/questionnaires";
        }

        model.addAttribute("questionnaire", questionnaire);
        return "edit_questionnaire";
    }

    @PatchMapping("/{id}")
    public String updateQuestionnaire(@PathVariable Long id,
                                      @ModelAttribute Questionnaire newQuestionnaire) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        if (questionnaire.isChangeable()) {
            questionnaireService.update(id, newQuestionnaire);
        }
        return "redirect:/questionnaires";
    }

    @DeleteMapping("/{id}")
    public String changeStatus(@PathVariable Long id) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        switch (questionnaire.getStatus()){
            case CREATED:
                questionnaireService.toPublish(questionnaire);
                break;
            case PUBLISHED:
                questionnaireService.toHide(questionnaire);
                break;
            case HIDDEN:
                questionnaireService.toShow(questionnaire);
                break;
        }
        return "redirect:/questionnaires";
    }

    @GetMapping("/{id}/users")
    public String showAllAnsweredUsers(@PathVariable Long id,
                                      Model model) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        model.addAttribute("users", userService.getAnsweredUser(questionnaire));
        model.addAttribute("questionnaire_id", id);
        return "show_answered_users";
    }

    @PostMapping("/answers")
    public String showUserAnswers(@RequestParam("questionnaire_id") Long id,
                                  @RequestParam("login") String login,
                                  Model model) {
        User user = userService.findByLogin(login);
        Questionnaire questionnaire = questionnaireService.findById(id);

        Set<Answer> userAnswers = userAnswerService
                .getUserAnswersToQuestionnaire(user, questionnaire).stream()
                .map(userAnswer -> userAnswer.getUserAnswerId().getAnswer())
                .collect(Collectors.toSet());

        model.addAttribute("user", user);
        model.addAttribute("userAnswers", userAnswers);
        model.addAttribute("questionnaire", questionnaire);

        return "show_user_answers";


    }
}
