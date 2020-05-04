package com.upgrad.quora.service.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;


/**
 * QuestionEntity class contains all the attributes to be mapped to all the fields in QUESTION table in the database.
 * All the annotations which are used to specify all the constraints to the columns in the database must be correctly implemented.
 */
@Entity
@Table(name = "question")
@NamedQueries(
        {
                @NamedQuery(name = "getAllQuestions", query = "select q from QuestionEntity q"),
                @NamedQuery(name = "questionByUuid", query = "select q from QuestionEntity q where q.uuid =:uuid"),
                @NamedQuery(name = "questionByid", query = "select q from QuestionEntity q where q.id =:id"),
                @NamedQuery(name = "getAllQuestionsByUser", query = "select q from QuestionEntity q where q.user= :user")
        }
)
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UUID")
    @Size(max = 64)
    @NotNull
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column
    @NotNull
    private String content;

    @Column
    @NotNull
    private ZonedDateTime date;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private UserEntity user;

    public QuestionEntity() {
    }

    public QuestionEntity(String content, ZonedDateTime date, UserEntity user) {
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ZonedDateTime getDate() {
        return this.date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
