package ru.dabutskikh.questionnaires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import ru.dabutskikh.questionnaires.model.*;
import ru.dabutskikh.questionnaires.repository.UserAnswerRepository;
import ru.dabutskikh.questionnaires.service.interfaces.UserAnswerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Override
    public Set<UserAnswer> getUserAnswersToQuestionnaire(User user, Questionnaire questionnaire) {
        Set<UserAnswer> result = new TreeSet<>();
        Set<Answer> questionnaireAnswers = new HashSet<>();

        questionnaire.getQuestions().stream()
                .map(Question::getAnswers)
                .forEach(questionnaireAnswers::addAll);

        user.getUserAnswers().stream()
                .filter(userAnswer
                        -> questionnaireAnswers.contains(userAnswer.getUserAnswerId().getAnswer()))
                .forEach(result::add);

        return result;
    }

    @Override
    public Set<UserAnswer> getUserAnswersToQuestion(User user, Question question) {
        Set<UserAnswer> result = new HashSet<>();
        Set<Answer> questionAnswers = new HashSet<>(question.getAnswers());

        user.getUserAnswers().stream()
                .filter(userAnswer
                        -> questionAnswers.contains(userAnswer.getUserAnswerId().getAnswer()))
                .forEach(result::add);

        return result;
    }

    public void customDelete(Long userId, Long answerId) {
        userAnswerRepository.customDelete(userId, answerId);
    }

    @Override
    @Modifying
    public void replaceQuestionUserAnswers(User user, Question question, Set<UserAnswer> newAnswers) {

        userAnswerRepository.findAll().stream()
                .filter(userAnswer
                        -> getUserAnswersToQuestion(user, question).contains(userAnswer)
                        && !newAnswers.contains(userAnswer)
                )
                .forEach(userAnswer
                        -> customDelete(user.getId(), userAnswer.getUserAnswerId().getAnswer().getId())
        );
        userAnswerRepository.saveAll(newAnswers);
    }

    @Override
    public Set<UserAnswer> toUserAnswers(User user, List<Answer> answers) {
        Set<UserAnswer> result = new TreeSet<>();
        answers.forEach(answer
                -> result.add(
                        new UserAnswer(
                                new UserAnswer.UserAnswerId(user, answer),
                                UserAnswerStatus.TEMPORARY
                        )
                )
        );
        return result;
    }
}
