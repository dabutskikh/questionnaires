package ru.dabutskikh.questionnaires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dabutskikh.questionnaires.model.*;
import ru.dabutskikh.questionnaires.repository.UserRepository;
import ru.dabutskikh.questionnaires.service.interfaces.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public void addAnswers(User user, Set<Answer> answers) {
        user.getAnswers().addAll(answers);
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void deleteAnswers(User user) {
        user.setAnswers(null);
        userRepository.save(user);
    }

    @Override
    public Set<Answer> getQuestionnaireAnswers(User user, Questionnaire questionnaire) {
        Set<Answer> result = new HashSet<>();
        Set<Answer> questionnaireAnswers = new HashSet<>();

        questionnaire.getQuestions().stream()
                .map(Question::getAnswers)
                .forEach(questionnaireAnswers::addAll);

        user.getAnswers().stream()
                .filter(questionnaireAnswers::contains)
                .forEach(result::add);

        return result;
    }
}
