package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dabutskikh.questionnaires.service.QuestionService;
import ru.dabutskikh.questionnaires.service.QuestionnaireService;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionnaireService questionnaireService;

    //@Autowired
    //QuestionService questionService;

    @GetMapping
    public String getAllQuestionsByQustionnaireId(Model model,
                                                  @RequestParam("questionnaire_id") Long questionnaireId) {
        model.addAttribute("parentQuestionnaire", questionnaireService.findById(questionnaireId));
        //model.addAttribute("questions", questionService.findAllByQuestionnaireId(questionnaireId));
        return "all_questions_of_questionnaire";
    }
}
