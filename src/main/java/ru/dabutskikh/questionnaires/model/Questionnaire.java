package ru.dabutskikh.questionnaires.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_published")
    private Boolean published;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "questionnaire",
            orphanRemoval = true
    )
    private List<Question> questions;

    public Questionnaire() {
    }

    public Questionnaire(Long id, String name) {
        this.id = id;
        this.name = name;
        this.published = false;
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

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
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

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                '}';
    }
}
