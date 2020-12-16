package ru.dabutskikh.questionnaires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dabutskikh.questionnaires.model.*;
import ru.dabutskikh.questionnaires.repository.UserAnswerRepository;
import ru.dabutskikh.questionnaires.service.interfaces.UserAnswerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Override
    public Set<UserAnswer> getUserAnswersToQuestionnaire(User user, Questionnaire questionnaire) {
        Set<UserAnswer> result = new HashSet<>();
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
//        System.out.println(question);
//        System.out.println(question.getAnswers());
        Set<UserAnswer> result = new HashSet<>();
        Set<Answer> questionAnswers = new HashSet<>(question.getAnswers());

        user.getUserAnswers().stream()
                .filter(userAnswer
                        -> questionAnswers.contains(userAnswer.getUserAnswerId().getAnswer()))
                .forEach(result::add);

        return result;
    }

    @Override
    public void replaceQuestionUserAnswers(User user, Question question, Set<UserAnswer> newAnswers) {
        userAnswerRepository.deleteAll(getUserAnswersToQuestion(user, question));
        userAnswerRepository.saveAll(newAnswers);
    }

    @Override
    public Set<UserAnswer> toUserAnswers(User user, List<Answer> answers) {
        Set<UserAnswer> result = new HashSet<>();
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

    @Override
    public UserAnswer findByUserIdAndAnswerId(Long userId, Long answerId) {
        return userAnswerRepository.findByUserIdAndAnswerId(userId, answerId).get(0);
    }
}
