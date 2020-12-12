package ru.dabutskikh.questionnaires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dabutskikh.questionnaires.model.Questionnaire;
import ru.dabutskikh.questionnaires.repository.QuestionnaireRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Override
    public List<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    public void save(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
    }
}
