package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    List<Questionnaire> findAll();

    Questionnaire findById(Long id);

    void save(Questionnaire questionnaire);

    void update(Long id, Questionnaire questionnaire);
}
