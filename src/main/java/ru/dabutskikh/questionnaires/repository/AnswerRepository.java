package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dabutskikh.questionnaires.model.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestionId(Long questionnaireId);

//    @Modifying
//    @Query("delete from Answer a where a.id=:id")
//    void deleteById(@Param("id") Long id);
}
