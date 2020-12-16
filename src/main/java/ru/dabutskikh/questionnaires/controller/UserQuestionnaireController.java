package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dabutskikh.questionnaires.model.*;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionnaireService;
import ru.dabutskikh.questionnaires.service.interfaces.UserAnswerService;
import ru.dabutskikh.questionnaires.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserQuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    UserService userService;

    @Autowired
    UserAnswerService userAnswerService;

    @GetMapping("/questionnaire/{id}")
    public String getQuestion(@AuthenticationPrincipal UserDetails currentUser,
                              @PathVariable("id") Long questionnaireId,
                              @RequestParam("question") int idxQuestion,
                              Model model) {

        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        Question question = questionnaire.getQuestions().get(idxQuestion - 1);

        User user = userService.findByLogin(currentUser.getUsername());
        User userForm = new User();

        userForm.getUserAnswers().addAll(
                userAnswerService.getUserAnswersToQuestion(user, question)
        );

        model.addAttribute("userId", user.getId());

        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("question", question);

        model.addAttribute("userForm", userForm);
        model.addAttribute("userAnswerOptions", userAnswerService.toUserAnswers(user, question.getAnswers()));

        model.addAttribute("idxQuestion", idxQuestion);

        return "test_question";
    }


    @PostMapping("/questionnaire/{id}")
    public String saveAnswers(@AuthenticationPrincipal UserDetails currentUser,
                              @PathVariable("id") Long questionnaireId,
                              @RequestParam("question") int idxQuestion,
                              @ModelAttribute User userForm) {

        User user = userService.findByLogin(currentUser.getUsername());

        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        Question question = questionnaire.getQuestions().get(idxQuestion - 1);

        System.out.println(userForm.getUserAnswers());

        userAnswerService.replaceQuestionUserAnswers(user, question, userForm.getUserAnswers());
        return "redirect:/questionnaire/" + questionnaireId + "?question=" + idxQuestion;
    }

    @PostMapping("/questionnaire/{id}/save")
    public String completeQuestionnaire(@AuthenticationPrincipal UserDetails currentUser,
                                        @PathVariable Long id) {
        User user = userService.findByLogin(currentUser.getUsername());
        Questionnaire questionnaire = questionnaireService.findById(id);
        for (int i = 0; i < questionnaire.getQuestions().size(); i++) {
            Question currentQuestion = questionnaire.getQuestions().get(i);
            if (userAnswerService.getUserAnswersToQuestion(user, currentQuestion).size() == 0) {
                return "redirect:/questionnaire/" + id + "?question=" + (i + 1);
            }
        }
        userAnswerService.setFinalStatus(userAnswerService.getUserAnswersToQuestionnaire(user, questionnaire));
        return "redirect:/history";
    }
}
