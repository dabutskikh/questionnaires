package ru.dabutskikh.questionnaires.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questionnaire")
public class Questionnaire implements Comparable<Questionnaire> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private QuestionnaireStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "questionnaire",
            orphanRemoval = true
    )
    @OrderBy
    private List<Question> questions;

    public Questionnaire() {
    }

    public Questionnaire(Long id, String name) {
        this.id = id;
        this.name = name;
        this.status = QuestionnaireStatus.CREATED;
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

    public QuestionnaireStatus getStatus() {
        return status;
    }

    public void setStatus(QuestionnaireStatus status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean isChangeable() {
        return status.equals(QuestionnaireStatus.CREATED);
    }

    public boolean isValid() {
        if (questions.size() == 0) {
            return false;
        }
        for (Question question : questions) {
            if (question.getAnswers().size() < 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Questionnaire o) {
        return (int) (this.getId() - o.getId());
    }
}
