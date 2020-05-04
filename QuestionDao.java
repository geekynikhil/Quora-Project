package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * QuestionDao class provides the database access for all the endpoints in question controller.
 */
@Repository
public class QuestionDao {
    @PersistenceContext
    private EntityManager entityManager;

    public QuestionEntity createQuestion(QuestionEntity questionEntity) {
        entityManager.persist(questionEntity);
        return questionEntity;
    }


    public TypedQuery<QuestionEntity> getQuestions() {
        try {
            return entityManager.createNamedQuery("getAllQuestions", QuestionEntity.class);
        } catch (NoResultException nre) {
            return null;
        }
    }

    public QuestionEntity getQuestionByUuid(String Uuid) {
        try {
            return entityManager.createNamedQuery("questionByUuid", QuestionEntity.class).setParameter("uuid", Uuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public QuestionEntity editQuestion(QuestionEntity questionEntity) {
        return entityManager.merge(questionEntity);
    }

    public QuestionEntity deleteQuestion(QuestionEntity questionEntity) {
        entityManager.remove(questionEntity);
        return questionEntity;
    }

    public TypedQuery<QuestionEntity> getQuestionsByUser(UserEntity userEntity) {
        try {
            return entityManager.createNamedQuery("getAllQuestionsByUser", QuestionEntity.class).setParameter("user", userEntity);
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UserEntity getUserByUuid(String Uuid) {
        try {
            return entityManager.createNamedQuery("userByUuid", UserEntity.class).setParameter("uuid", Uuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
