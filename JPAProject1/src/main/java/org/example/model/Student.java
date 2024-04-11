package org.example.model;

import javax.persistence.*;
import javax.persistence.NamedQuery;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "find student by id" , query = "select s from Student s where s.id = :id")
public class Student {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
@Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

@Column(name = "last_name", nullable = false, length = 250)
    private String lastName;



    @OneToOne
    private Tutor tutor;

    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();

    public Student(Long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {
        boolean added = teachers.add(teacher);
        if (added) {
            teacher.getStudents().add(this);
        }
    }
    public void removeTeacher(Teacher teacher) {
        boolean removed = teachers.remove(teacher);
        if (removed) {
            teacher.getStudents().add(this);
        }
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tutor=" + tutor +
                '}';
    }
}
