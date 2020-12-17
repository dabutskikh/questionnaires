package ru.dabutskikh.questionnaires.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dabutskikh.questionnaires.model.Question;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionService;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionnaireService;

@Controller
@PreAuthorize("hasAuthority('questionnaires:write')")
@RequestMapping("/admin/questions")
public class AdminQuestionController {

    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    QuestionService questionService;

    @GetMapping
    public String getAllQuestionsByQuestionnaireId(Model model,
                                                  @RequestParam("questionnaire_id") Long questionnaireId) {
        model.addAttribute("parentQuestionnaire",
                questionnaireService.findById(questionnaireId)
        );
        model.addAttribute("questions",
                questionService.getAllQuestionsByQuestionnaireId(questionnaireId)
        );
        return "admin/all_questions_of_questionnaire";
    }

    @PostMapping
    public String createQuestion(@ModelAttribute Question questionForm,
                                 @RequestParam("questionnaire_id") Long questionnaireId) {
        questionForm.setQuestionnaire(questionnaireService.findById(questionnaireId));
        questionService.save(questionForm);
        return "redirect:/admin/questions?questionnaire_id=" + questionnaireId;
    }

    @GetMapping("/new")
    public String getCreatingQuestionPage(Model model,
                                          @RequestParam("questionnaire_id") Long questionnaireId) {
        model.addAttribute("parentQuestionnaire", questionnaireService.findById(questionnaireId));
        model.addAttribute("questionForm", new Question());
        return "admin/new_question";
    }

    @GetMapping("/{id}/edit")
    public String getUpdatingQuestionPage(@PathVariable Long id,
                                          Model model) {
        Question question = questionService.findById(id);
        if (!question.isChangeable()) {
            return "redirect:/admin/questions?questionnaire_id="
                    + question.getQuestionnaire().getId();
        }

        model.addAttribute("question", question);
        return "admin/edit_question";
    }

    @PatchMapping("/{id}")
    public String updateQuestion(@PathVariable Long id,
                                 @ModelAttribute Question newQuestion) {
        Question question = questionService.findById(id);
        if (question.isChangeable()) {
            questionService.update(id, newQuestion);
        }

        return "redirect:/admin/questions?questionnaire_id="
                + question.getQuestionnaire().getId();
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        Question question = questionService.findById(id);
        Long parentQuestionnaireId = question.getQuestionnaire().getId();
        if (question.isChangeable()) {
            questionService.delete(id);
        }
        return "redirect:/admin/questions?questionnaire_id=" + parentQuestionnaireId;
    }
}
