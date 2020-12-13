package ru.dabutskikh.questionnaires.service;

import ru.dabutskikh.questionnaires.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestionsByQuestionnaireId(Long questionnaireId);
}
