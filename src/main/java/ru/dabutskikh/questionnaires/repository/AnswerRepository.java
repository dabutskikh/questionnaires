package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dabutskikh.questionnaires.model.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestionId(Long questionnaireId);
}
