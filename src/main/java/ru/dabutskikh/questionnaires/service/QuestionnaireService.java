package ru.dabutskikh.questionnaires.service;

import ru.dabutskikh.questionnaires.model.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    List<Questionnaire> findAll();

    void save(Questionnaire questionnaire);
}
