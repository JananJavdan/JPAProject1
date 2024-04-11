
package org.example.repository;

import org.example.model.School;
import org.example.model.Tutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TutorRepository {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;


    public TutorRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("student_bu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public TutorRepository(String database) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("student_bu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Tutor add(Tutor tutor) {
        entityManager.getTransaction().begin();
        entityManager.persist(tutor);
        entityManager.getTransaction().commit();
        return tutor;
    }

    public Tutor find(Long id) {
        return entityManager.find(Tutor.class, id);
    }

    public Tutor update(Tutor tutor) {
        Tutor tutorToUpdate = find(tutor.getId());
        entityManager.getTransaction().begin();
        tutorToUpdate.setLastName(tutor.getLastName());
        tutorToUpdate.setFirstName(tutor.getFirstName());
        entityManager.merge(tutor);
        entityManager.getTransaction().commit();
        return tutorToUpdate;
    }

    public Tutor delete(Tutor tutor) {
        entityManager.getTransaction().begin();
        entityManager.remove(tutor);
        entityManager.getTransaction().commit();
        return tutor;
    }


    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }


}



