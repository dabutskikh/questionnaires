package ru.dabutskikh.questionnaires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dabutskikh.questionnaires.model.Question;
import ru.dabutskikh.questionnaires.model.Questionnaire;
import ru.dabutskikh.questionnaires.repository.QuestionnaireRepository;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionnaireService;

import java.util.*;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Override
    public List<Questionnaire> findAll() {
        List<Questionnaire> questionnaires = questionnaireRepository.findAll();
        questionnaires.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return questionnaires;
    }

    @Override
    public Questionnaire findById(Long id) {
        return questionnaireRepository
                .findById(id)
                .orElseThrow(() -> new NullPointerException());
    }

    @Override
    public void update(Long id, Questionnaire questionnaire) {
        Questionnaire newQuestionnaire = findById(id);
        newQuestionnaire.setName(questionnaire.getName());
        save(newQuestionnaire);
    }

    @Override
    public boolean toPublish(Long id) {
        Questionnaire questionnaire = findById(id);
        if (questionnaire.getQuestions().size() == 0) {
            return false;
        }
        for (Question question : questionnaire.getQuestions()) {
            if (question.getAnswers().size() < 2) {
                return false;
            }
        }
        questionnaire.setPublished(true);
        save(questionnaire);
        return true;
    }

    @Override
    public void toUnpublish(Long id) {
        Questionnaire questionnaire = findById(id);
        questionnaire.setPublished(false);
        save(questionnaire);
    }

    @Override
    public void save(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
    }
}
