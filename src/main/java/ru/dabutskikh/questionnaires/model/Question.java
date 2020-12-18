package ru.dabutskikh.questionnaires.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_multiply_answer")
    private Boolean multiplyAnswer;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "question"
    )
    @OrderBy
    private List<Answer> answers;

    public Question() {
    }

    public Question(Long id, String name, Boolean multiplyAnswer) {
        this.id = id;
        this.name = name;
        this.multiplyAnswer = multiplyAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMultiplyAnswer() {
        return multiplyAnswer;
    }

    public void setMultiplyAnswer(Boolean multiplyAnswer) {
        this.multiplyAnswer = multiplyAnswer;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isChangeable() {
        return questionnaire.isChangeable();
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                '}';
    }
}
