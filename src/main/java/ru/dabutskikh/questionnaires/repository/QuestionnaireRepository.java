package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dabutskikh.questionnaires.model.Questionnaire;

public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {
}
