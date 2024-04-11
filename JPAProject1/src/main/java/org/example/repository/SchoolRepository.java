package org.example.repository;

import org.example.model.School;
import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


    public class SchoolRepository {

        private EntityManager entityManager;
        private EntityManagerFactory entityManagerFactory;


        public SchoolRepository() {
            this.entityManagerFactory = Persistence.createEntityManagerFactory("student_bu");
            this.entityManager = entityManagerFactory.createEntityManager();
        }
        public SchoolRepository(String database) {
            this.entityManagerFactory = Persistence.createEntityManagerFactory("student_bu");
            this.entityManager = entityManagerFactory.createEntityManager();
        }

        public School add(School school) {
            entityManager.getTransaction().begin();
            entityManager.persist(school);
            entityManager.getTransaction().commit();
            return school;
        }

        public School find(Long id) {
            return entityManager.find(School.class, id);
        }

        public School update(School school) {
            School schoolToUpdate = find(school.getId());
            entityManager.getTransaction().begin();
            schoolToUpdate.setCity(school.getCity());
            schoolToUpdate.setName(school.getName());
            entityManager.merge(school);
            entityManager.getTransaction().commit();
            return schoolToUpdate;
        }

        public School delete(School school) {
            entityManager.getTransaction().begin();
            entityManager.remove(school);
            entityManager.getTransaction().commit();
            return school;
        }

        public void addStudent(Long id, Student student) {
            entityManager.getTransaction().begin();
            School school = find(id);
            if (school != null) {
                school.getStudents().add(student);
            }
            entityManager.persist(school);
            entityManager.getTransaction().commit();
        }

        public void removeStudent(Long id, Student student) {
            entityManager.getTransaction().begin();
            School school = find(id);
            if (school != null) {
                school.getStudents().add(student);
            }
            entityManager.persist(school);
            entityManager.getTransaction().commit();
        }


        public void close() {
            entityManager.close();
            entityManagerFactory.close();
        }


    }


