package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
@NamedQuery(name = "find student by id" , query = "select s from Student s where s.id = id")
public class Student {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
@Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

@Column(name = "last_name", nullable = false, length = 250)
    private String lastName;

    public Student(Long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
