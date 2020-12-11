package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dabutskikh.questionnaires.model.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
