package ru.dabutskikh.questionnaires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.dabutskikh.questionnaires.model.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, UserAnswer.UserAnswerId> {

    @Transactional
    @Modifying
    @Query("delete from UserAnswer userAnswer where userAnswer.userAnswerId.user.id=:userId and userAnswer.userAnswerId.answer.id=:answerId")
    void customDelete(@Param("userId") Long userId, @Param("answerId") Long answerId);
}
