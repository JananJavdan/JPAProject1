package org.example.repository;

import org.example.model.School;
import org.example.model.Student;
import org.example.model.Tutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class StudentRepository {

private EntityManager entityManager;
private EntityManagerFactory entityManagerFactory;


    public StudentRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("student_bu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public StudentRepository(String database) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("student_bu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Student add(Student student) {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            return student;
    }
    public Tutor addTutor(Long id, Tutor tutor) {
        entityManager.getTransaction().begin();
        Student student = find(id);
        student.setTutor(tutor);
        entityManager.getTransaction().commit();
        return tutor;
    }
    public Student find(Long id) {
        return entityManager.find(Student.class, id);
    }
    public Student findById(Long id) {
        Query query = entityManager.createNamedQuery("find student by id");
        query.setParameter("id", id);
        return (Student) query.getSingleResult();
    }
    public Student updateFirstNameById(String firstName, long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Update Student set firstName = '" + firstName + "' where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
        return findById(id);
    }
    public Student updateLastNameById(String lastName, long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Update Student set lastName = '" + lastName + "' where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
        return findById(id);
    }
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Delete Student where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
    public List<Student> findByFirstNameStartWith(String keyword) {
        Query query = entityManager.createQuery("Select s from Student s where s.firstName like '" + keyword + "%'");
        return query.getResultList();
    }
    public List<Student> findByLastNameStartWith(String keyword) {
        Query query = entityManager.createQuery("Select s from Student s where s.lastName like '%" + keyword + "'");
        return query.getResultList();
    }
    public List<Student> findSortingByFirstName() {
        Query query = entityManager.createQuery("Select s from Student s order by s.firstName desc ");
        return query.getResultList();
    }
    public List<Student> findSortingById() {
        Query query = entityManager.createQuery("Select s from Student s order by s.id desc ");
        return query.getResultList();
    }
    public Long count() {
        Query query = entityManager.createQuery("select count(*) from Student");
        return (Long) query.getSingleResult();
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
