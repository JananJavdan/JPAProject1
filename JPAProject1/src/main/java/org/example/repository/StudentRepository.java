package org.example.repository;

import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class StudentRepository {

private EntityManager entityManager;
private EntityManagerFactory entityManagerFactory;


    public StudentRepository(String data_co) {
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

    public List<Student> getAllStudents() {
        entityManager.getTransaction().begin();
        List<Student> students = entityManager.createQuery("from Student").getResultList();
        entityManager.getTransaction().commit();
        return students;
    }
    public Student getStudentById(int id) {
        entityManager.getTransaction();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        entityManager.getTransaction().commit();
        return student;
    }
    public Student findStudentById(int id) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        entityManager.getTransaction().commit();
        return student;
    }

}
