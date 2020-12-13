package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dabutskikh.questionnaires.model.Questionnaire;

import java.util.List;
import java.util.Optional;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    @Override
    List<Questionnaire> findAll();

    @Override
    Optional<Questionnaire> findById(Long aLong);
}
