package org.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 250)
    private String lastName;


    @ManyToOne
    private School school;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "teacher_student",
    joinColumns = {@JoinColumn(name = "teacher_id")},
    inverseJoinColumns = {@JoinColumn(name = "student_id")},
    uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = {"teacher_id", "student_id"}
            )
    })
    private Set<Student> students = new HashSet<>();

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Teacher() {
    }

    public Teacher(Long id) {
        this.id = id;
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

    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }


    public void addStudent(Student student) {
        boolean added = students.add(student);
        if (added) {
            student.getTeachers().add(this);
        }
    }
    public void removeStudent(Student student) {
        boolean removed = students.remove(student);
        if (removed) {
            student.getTeachers().add(this);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", school=" + school +
                '}';
    }
}
