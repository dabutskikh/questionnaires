package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.Question;

import java.util.List;

public interface QuestionService {

    Question findById(Long id);

    void update(Long id, Question question);

    void save(Question question);

    List<Question> getAllQuestionsByQuestionnaireId(Long questionnaireId);
}
