package org.example;

import org.example.model.Student;
import org.example.repository.StudentRepository;

public class Main {
    public static void main(String[] args) {

        Student student = new Student();
        student.setFirstName("Janan");
        student.setLastName("Javdan");

        StudentRepository studentRepository = new StudentRepository(student.toString());


        studentRepository.add(student);
        System.out.println("Added student " + student.toString());

        student = studentRepository.find(student.getId());
        System.out.println("Found student " + student.toString());

        student.setLastName("Javdan");
        studentRepository.update(student);
        System.out.println("Update student "+student.toString());

        //studentRepository.delete(student);
        //System.out.println("Deleted student "+student.toString());
    }
}