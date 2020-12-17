package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dabutskikh.questionnaires.model.Answer;
import ru.dabutskikh.questionnaires.model.Questionnaire;
import ru.dabutskikh.questionnaires.model.User;
import ru.dabutskikh.questionnaires.model.UserAnswer;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionnaireService;
import ru.dabutskikh.questionnaires.service.interfaces.UserAnswerService;
import ru.dabutskikh.questionnaires.service.interfaces.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ServiceController {

    @Autowired
    UserService userService;

    @Autowired
    UserAnswerService userAnswerService;

    @Autowired
    QuestionnaireService questionnaireService;

    @GetMapping("/catalog")
    public String getAvailableQuestionnaires(@AuthenticationPrincipal UserDetails currentUser,
                                             Model model) {
        User user = userService.findByLogin(currentUser.getUsername());
        model.addAttribute("qustionnaires", questionnaireService.getAvailableQuestionnaires(user));
        return "available_questionnaires";
    }

    @GetMapping("/current")
    public String getCurrentQuestionnaires(@AuthenticationPrincipal UserDetails currentUser,
                                           Model model) {
        User user = userService.findByLogin(currentUser.getUsername());
        model.addAttribute("qustionnaires", questionnaireService.getCurrentQuestionnaires(user));
        return "current_questionnaires";
    }

    @GetMapping("/history")
    public String getCompletedQuestionnaires(@AuthenticationPrincipal UserDetails currentUser,
                                             Model model) {
        User user = userService.findByLogin(currentUser.getUsername());
        model.addAttribute("qustionnaires", questionnaireService.getCompletedQuestionnaires(user));
        return "completed_questionnaires";
    }

    @GetMapping("/watch")
    public String getCompletedQuestionnaire(@AuthenticationPrincipal UserDetails currentUser,
                                            @RequestParam("questionnaire_id") Long questionnaireId,
                                            Model model) {
        User user = userService.findByLogin(currentUser.getUsername());
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        Set<Questionnaire> completedQuestionnaires = questionnaireService.getCompletedQuestionnaires(user);

        if (!completedQuestionnaires.contains(questionnaire)) {
            return "redirect:/history";
        }

        Set<Answer> userAnswers = userAnswerService
                .getUserAnswersToQuestionnaire(user, questionnaire).stream()
                .map(userAnswer -> userAnswer.getUserAnswerId().getAnswer())
                .collect(Collectors.toSet());

        model.addAttribute("userAnswers", userAnswers);
        model.addAttribute("questionnaire", questionnaire);
        return "watch_completed_questionnaire";
    }
}
