package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.Questionnaire;
import ru.dabutskikh.questionnaires.model.User;

import java.util.List;
import java.util.Set;

public interface QuestionnaireService {

    List<Questionnaire> findAll();

    Questionnaire findById(Long id);

    void save(Questionnaire questionnaire);

    void update(Long id, Questionnaire questionnaire);

    void toPublish(Questionnaire questionnaire);

    void toHide(Questionnaire questionnaire);

    void toShow(Questionnaire questionnaire);

    Set<Questionnaire> getCompletedQuestionnaires(User user);

    Set<Questionnaire> getAvailableQuestionnaires(User user);

    Set<Questionnaire> getCurrentQuestionnaires(User user);
}
