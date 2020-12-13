package ru.dabutskikh.questionnaires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dabutskikh.questionnaires.model.Answer;
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
                answerService.getAllAnswersByQuestionId(questionId)
        );
        return "all_answers_of_question";
    }

    @PostMapping
    public String createAnswer(@ModelAttribute Answer answerForm,
                               @RequestParam("question_id") Long questionId) {
        answerForm.setQuestion(questionService.findById(questionId));
        answerService.save(answerForm);
        return "redirect:/answers?question_id=" + questionId;
    }

    @GetMapping("/new")
    public String getCreatingAnswerPage(Model model,
                                        @RequestParam("question_id") Long questionId) {
        model.addAttribute("parentQuestion", questionService.findById(questionId));
        model.addAttribute("answerForm", new Answer());
        return "new_answer";
    }

    @GetMapping("/{id}/edit")
    public String getUpdatingAnswerPage(@PathVariable Long id,
                                        Model model) {
        model.addAttribute("answer", answerService.findById(id));
        return "edit_answer";
    }

    @PatchMapping("/{id}")
    public String updateAnswer(@PathVariable Long id,
                               @ModelAttribute Answer answer) {
        answerService.update(id, answer);

        // Object "answer" has null field "question"
        // because should get id of parent question using service
        return "redirect:/answers?question_id="
                + answerService.findById(id).getQuestion().getId();
    }

    @DeleteMapping("/{id}")
    public String deleteAnswer(@PathVariable Long id) {
        Long parentQuestionId = answerService.findById(id).getQuestion().getId();
        answerService.delete(id);
        return "redirect:/answers?question_id=" + parentQuestionId;
    }
}
