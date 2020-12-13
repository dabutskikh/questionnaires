package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.Answer;

import java.util.List;

public interface AnswerService {

    Answer findById(Long id);

    void update(Long id, Answer answer);

    void delete(Long id);

    void save(Answer answer);

    List<Answer> getAllAnswersByQuestionId(Long questionId);
}
