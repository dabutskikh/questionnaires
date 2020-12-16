package ru.dabutskikh.questionnaires.service.interfaces;

import ru.dabutskikh.questionnaires.model.*;

import java.util.List;
import java.util.Set;

public interface UserAnswerService {

    Set<UserAnswer> getUserAnswersToQuestionnaire(User user, Questionnaire questionnaire);

    Set<UserAnswer> getUserAnswersToQuestion(User user, Question question);

    void replaceQuestionUserAnswers(User user, Question question, Set<UserAnswer> newAnswers);

    Set<UserAnswer> toUserAnswers(User user, List<Answer> answers);

    void customDelete(Long userId, Long answerId);

    void setFinalStatus(Set<UserAnswer> userAnswers);
}
