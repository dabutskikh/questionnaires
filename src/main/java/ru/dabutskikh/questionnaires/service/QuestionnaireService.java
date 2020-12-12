package ru.dabutskikh.questionnaires.service;

import ru.dabutskikh.questionnaires.model.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    List<Questionnaire> findAll();

    void editName(Long questionnaireId, String newName);

    void save(Questionnaire questionnaire);
}
