package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestionsByQuestionnaireId(Long questionnaireId);
}
