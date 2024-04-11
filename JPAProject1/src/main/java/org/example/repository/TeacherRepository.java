package org.example.repository;

import org.example.model.Teacher;
import org.example.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TeacherRepository {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;


    public TeacherRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("student_bu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public TeacherRepository(String database) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("student_bu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Teacher add(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        return teacher;
    }

    public Teacher find(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public Teacher update(Teacher teacher) {
        Teacher teacherToUpdate = find(teacher.getId());
        entityManager.getTransaction().begin();
        teacherToUpdate.setLastName(teacher.getLastName());
        teacherToUpdate.setFirstName(teacher.getFirstName());
        entityManager.merge(teacher);
        entityManager.getTransaction().commit();
        return teacherToUpdate;
    }

    public Teacher delete(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();
        return teacher;
    }


    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
