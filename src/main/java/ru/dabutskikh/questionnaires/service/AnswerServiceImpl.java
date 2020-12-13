package ru.dabutskikh.questionnaires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dabutskikh.questionnaires.model.Answer;
import ru.dabutskikh.questionnaires.repository.AnswerRepository;
import ru.dabutskikh.questionnaires.service.interfaces.AnswerService;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer findById(Long id) {
        return answerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void update(Long id, Answer answer) {
        Answer newAnswer = findById(id);
        newAnswer.setName(answer.getName());
        save(newAnswer);
    }

    @Override
    public void delete(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public List<Answer> getAllAnswersByQuestionId(Long questionId) {
        List<Answer> answers = answerRepository.findByQuestionId(questionId);
        answers.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return answers;
    }
}
