package ru.dabutskikh.questionnaires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dabutskikh.questionnaires.model.Question;
import ru.dabutskikh.questionnaires.repository.QuestionRepository;
import ru.dabutskikh.questionnaires.service.interfaces.QuestionService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question findById(Long id) {
        return questionRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void update(Long id, Question question) {
        Question newQuestion = findById(id);
        newQuestion.setName(question.getName());
        newQuestion.setMultiplyAnswer(question.getMultiplyAnswer());
        save(newQuestion);
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestionsByQuestionnaireId(Long questionnaireId) {
        List<Question> questions = questionRepository.findByQuestionnaireId(questionnaireId);
        questions.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return questions;
    }
}
