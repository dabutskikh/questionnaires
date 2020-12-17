package ru.dabutskikh.questionnaires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dabutskikh.questionnaires.model.*;
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
    public void toPublish(Questionnaire questionnaire) {
        if (questionnaire.getQuestions().size() == 0) {
            return;
        }
        for (Question question : questionnaire.getQuestions()) {
            if (question.getAnswers().size() < 2) {
                return;
            }
        }
        questionnaire.setStatus(QuestionnaireStatus.PUBLISHED);
        save(questionnaire);
    }

    @Override
    public void toHide(Questionnaire questionnaire) {
        questionnaire.setStatus(QuestionnaireStatus.HIDDEN);
        save(questionnaire);
    }

    @Override
    public void toShow(Questionnaire questionnaire) {
        questionnaire.setStatus(QuestionnaireStatus.PUBLISHED);
        save(questionnaire);
    }

    @Override
    public void save(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
    }

    @Override
    public Set<Questionnaire> getCompletedQuestionnaires(User user) {
        Set<Questionnaire> set = new TreeSet<>();
        user.getUserAnswers().stream()
                .filter(userAnswer
                        -> userAnswer.getStatus()
                        .equals(UserAnswerStatus.FINAL)
                )
                .forEach(userAnswer
                        -> set.add(userAnswer
                        .getUserAnswerId()
                        .getAnswer()
                        .getQuestion()
                        .getQuestionnaire()
                )
        );
        return set;
    }

    @Override
    public Set<Questionnaire> getCurrentQuestionnaires(User user) {
        Set<Questionnaire> set = new TreeSet<>();
        user.getUserAnswers().stream()
                .filter(userAnswer
                        -> userAnswer.getStatus()
                        .equals(UserAnswerStatus.TEMPORARY)
                )
                .forEach(userAnswer
                                -> set.add(userAnswer
                                .getUserAnswerId()
                                .getAnswer()
                                .getQuestion()
                                .getQuestionnaire()
                        )
                );
        return set;
    }

    @Override
    public Set<Questionnaire> getAvailableQuestionnaires(User user) {
        Set<Questionnaire> set = new TreeSet<>();
        List<Questionnaire> allQuestionnaires = findAll();
        allQuestionnaires.stream()
                .filter(questionnaire ->
                        questionnaire.getStatus().equals(QuestionnaireStatus.PUBLISHED)
                                && !getCompletedQuestionnaires(user).contains(questionnaire)
                                && !getCurrentQuestionnaires(user).contains(questionnaire)
                )
                .forEach(set::add);
        return set;
    }
}
