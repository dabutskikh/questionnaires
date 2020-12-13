package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dabutskikh.questionnaires.service.interfaces.AnswerService;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionService;

@Controller
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @GetMapping
    public String getAllAnswersByQuestionId(Model model,
                                                  @RequestParam("question_id") Long questionId) {
        model.addAttribute("parentQuestion",
                questionService.findById(questionId)
        );
        model.addAttribute("answers",
                answerService.getAllQuestionsByQuestionnaireId(questionId)
        );
        return "all_answers_of_question";
    }
}
