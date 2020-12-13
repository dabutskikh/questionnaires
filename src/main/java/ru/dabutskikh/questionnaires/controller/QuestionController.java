package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dabutskikh.questionnaires.model.Question;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionService;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionnaireService;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    QuestionService questionService;

    @GetMapping
    public String getAllQuestionsByQustionnaireId(Model model,
                                                  @RequestParam("questionnaire_id") Long questionnaireId) {
        model.addAttribute("parentQuestionnaire",
                questionnaireService.findById(questionnaireId)
        );
        model.addAttribute("questions",
                questionService.getAllQuestionsByQuestionnaireId(questionnaireId)
        );
        return "all_questions_of_questionnaire";
    }

    @PostMapping
    public String createQuestion(@ModelAttribute Question questionForm,
                                 @RequestParam("questionnaire_id") Long questionnaireId) {
        questionForm.setQuestionnaire(questionnaireService.findById(questionnaireId));
        questionService.save(questionForm);
        return "redirect:/questions?questionnaire_id=" + questionnaireId;
    }

    @GetMapping("/new")
    public String getCreatingQuestionPage(Model model,
                                          @RequestParam("questionnaire_id") Long questionnaireId) {
        model.addAttribute("parentQuestionnaire", questionnaireService.findById(questionnaireId));
        model.addAttribute("questionForm", new Question());
        return "new_question";
    }

    @GetMapping("/{id}/edit")
    public String getUpdatingQuestionPage(@PathVariable Long id,
                                          Model model) {
        model.addAttribute("question", questionService.findById(id));
        return "edit_question";
    }

    @PatchMapping("/{id}")
    public String updateQuestion(@PathVariable Long id,
                                 @ModelAttribute Question question) {
        questionService.update(id, question);

        // Object "question" has null field "questionnaire"
        // because should get id of parent questionnaire using service
        return "redirect:/questions?questionnaire_id="
                + questionService.findById(id).getQuestionnaire().getId();
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        Long parentQuestionnaireId = questionService.findById(id).getQuestionnaire().getId();
        questionService.delete(id);
        return "redirect:/questions?questionnaire_id=" + parentQuestionnaireId;
    }
}
