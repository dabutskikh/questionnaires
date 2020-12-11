package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dabutskikh.questionnaires.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
