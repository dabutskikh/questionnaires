package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.*;

import java.util.Set;

public interface UserService {

    void save(User user);

    void addAnswers(User user, Set<Answer> answers);

    User findByLogin(String login);

    User findById(Long id);

    void deleteAnswers(User user);

    Set<UserAnswer> getUserAnswersToQuestionnaire(User user, Questionnaire questionnaire);

    Set<UserAnswer> getUserAnswersToQuestion(User user, Question question);

    void replaceQuestionUserAnswers(User user, Question question, Set<UserAnswer> newAnswers);

    Set<Answer> getQuestionnaireAnswers(User user, Questionnaire questionnaire); //old

    Set<Answer> getQuestionAnswers(User user, Question question); //old

    void replaceQuestionAnswers(User user, Question question, Set<Answer> newAnswers); //old
}
