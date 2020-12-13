package ru.dabutskikh.questionnaires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

//    @Override
//    public void editName(Long questionnaireId, String newName) {
//        Questionnaire questionnaire = questionnaireRepository
//                .findById(questionnaireId)
//                .orElseThrow(() -> new NullPointerException());
//        questionnaire.setName(newName);
//        questionnaireRepository.save(questionnaire);
//    }

    @Override
    public void update(Long id, Questionnaire questionnaire) {
        Questionnaire newQuestionnaire = findById(id);
//        Questionnaire newQuestionnaire = questionnaireRepository
//                .findById(id)
//                .orElseThrow(() -> new NullPointerException());
        newQuestionnaire.setName(questionnaire.getName());
        save(newQuestionnaire);
    }

    @Override
    public void save(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
    }
}
