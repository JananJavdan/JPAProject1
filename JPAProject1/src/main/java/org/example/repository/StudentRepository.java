package org.example.repository;

import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class StudentRepository {

private EntityManager entityManager;
private EntityManagerFactory entityManagerFactory;


    public StudentRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("database-configuration");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public StudentRepository(String database) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("database-configuration");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Student add(Student student) {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            return student;
    }
    public Student find(Long id) {
        return entityManager.find(Student.class, id);
    }
    public Student findById(Long id) {
        Query query = entityManager.createNamedQuery("find student by id");
        query.getParameter("id");
        return (Student) query.getSingleResult();
    }
    public Student update(Student student) {
        Student studentToUpdate = find(student.getId());
        entityManager.getTransaction().begin();
        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        entityManager.merge(student);
        entityManager.getTransaction().commit();
        return studentToUpdate;
    }

    public Student delete(Student student) {
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        return student;
    }

    public List<Student> findFirstName() {
        Query query = entityManager.createQuery("SELECT s.firstName FROM Student s");
        return query.getResultList();
    }
    public List<Student> findLastName() {
        Query query = entityManager.createQuery("SELECT s.lastName FROM Student s");
        return query.getResultList();
    }
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }





}
