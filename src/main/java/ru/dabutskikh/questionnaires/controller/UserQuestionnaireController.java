package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dabutskikh.questionnaires.model.Answer;
import ru.dabutskikh.questionnaires.model.Question;
import ru.dabutskikh.questionnaires.model.Questionnaire;
import ru.dabutskikh.questionnaires.model.User;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionnaireService;
import ru.dabutskikh.questionnaires.service.interfaces.UserService;

import java.util.Set;

@Controller
public class UserQuestionnaireController {

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    UserService userService;

    @GetMapping("/questionnaire/{id}")
    public String getQuestion(@AuthenticationPrincipal UserDetails currentUser,
                              @PathVariable("id") Long questionnaireId,
                              @RequestParam("question") int idxQuestion,
                              Model model) {

        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        Question question = questionnaire.getQuestions().get(idxQuestion - 1);

        User user = userService.findByLogin(currentUser.getUsername());
        User userForm = new User();
        userForm.getAnswers().addAll(userService.getQuestionAnswers(user, question));
        System.out.println(userForm);

        Set<Answer> userAnswers = userService.getQuestionAnswers(user, question);

        model.addAttribute("userAnswers", userAnswers);
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("question", question);
        model.addAttribute("userForm", userForm);
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

        userService.replaceQuestionAnswers(user, question, userForm.getAnswers());
        return "redirect:/questionnaire/" + questionnaireId + "?question=" + idxQuestion;
    }

//    @GetMapping("/questionnaire/{id}")
//    public String getQuestionnaire(Model model,
//                                   @PathVariable Long id) {
//
//        model.addAttribute("user", new User());
//        model.addAttribute("questionnaire", questionnaireService.findById(id));
//        return "questionnaire";
//    }
//
//    @PostMapping("/questionnaire")
//    public String postQuestionnaire(@ModelAttribute("user") User user,
//                                    @AuthenticationPrincipal UserDetails currentUserDetails) {
//        User currentUser = userService.findByLogin(currentUserDetails.getUsername());
//        userService.addAnswers(currentUser, user.getAnswers());
//        return "redirect:/";
//    }
}
