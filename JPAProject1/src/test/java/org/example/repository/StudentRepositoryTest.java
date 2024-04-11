package org.example.repository;

import org.example.model.Student;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class StudentRepositoryTest {

    private static StudentRepository repository;

    @BeforeClass
    public static void beforeClass() throws Exception {
        repository = new StudentRepository("student_bu");

    }

    @AfterClass
    public static void afterClass() throws Exception {
        repository.close();

    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void add() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");

        repository.add(student);

        assertNotNull(student.getId());
        assertTrue(student.getId().equals(1L));
    }

    @org.junit.Test
    public void find() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");

        repository.add(student);

        assertNotNull(student.getId());
        assertTrue(student.getId().equals(1L));
    }

    @org.junit.Test
    public void update() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");

        student = repository.add(student);
        student.setFirstName("Jaaan");
        student = repository.update(student);

        assertNotNull(student);
        assertEquals("Jaaan", student.getFirstName());
        assertEquals("Doe", student.getLastName());


    }

    @org.junit.Test
    public void delete() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");

        student = repository.add(student);

        repository.delete(student);

        student = repository.find(student.getId());

        assertNull(student);
    }
}