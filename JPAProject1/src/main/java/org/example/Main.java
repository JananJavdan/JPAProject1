package org.example;

import org.example.model.School;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.model.Tutor;
import org.example.repository.SchoolRepository;
import org.example.repository.StudentRepository;
import org.example.repository.TeacherRepository;
import org.example.repository.TutorRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Student student = new Student();
        student.setFirstName("Janan");
        student.setLastName("Javdan");

        StudentRepository studentRepository = new StudentRepository();

        SchoolRepository schoolRepository = new SchoolRepository();

        TutorRepository tutorRepository = new TutorRepository();

        TeacherRepository teacherRepository = new TeacherRepository();

        studentRepository.add(student);

        System.out.println("Added student " + student.toString());

        Tutor tutor = new Tutor("FirstName_tutor_1", "LastName_tutor_2");

        tutorRepository.add(tutor);


        System.out.println("Added tutor " + tutor.toString());

        studentRepository.addTutor(student.getId(), tutor);


        System.out.println("Student with school " + student.toString());

        student = studentRepository.find(student.getId());

        System.out.println("Found student with id " + student.toString());

        School school = new School("School_1", "City_1");


        schoolRepository.add(school);

        System.out.println("Added school " + school.toString());

        schoolRepository.addStudent(school.getId(), student);

        school = schoolRepository.find(school.getId());

        school.getStudents().forEach(System.out::println);

        schoolRepository.removeStudent(school.getId(), student);

        school = schoolRepository.find(school.getId());

        school.getStudents().forEach(System.out::println);

        //Add Teacher

        Teacher teacher = new Teacher("FirstName_teacher_1", "LastName_teacher_2");

        teacher.addStudent(new Student("SFirstName_1", "SLastName_1"));

        teacher.addStudent(new Student("SFirstName_1", "SLastName_1"));

        teacherRepository.add(teacher);

        //Teacher teacher = new Teacher("Mary", "Bos");

        teacher.setSchool(school);

        teacherRepository.add(teacher);

        System.out.println("Added teacher " + teacher.toString());

        Teacher teacher2 = new Teacher("Tom", "Hamilton");

        teacher2.setSchool(school);

        teacherRepository.add(teacher2);

        System.out.println("Added teacher " + teacher2.toString());


        //studentRepository.findFirstName().forEach(System.out::println);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.forEach(student1 -> System.out.println(student1.getFirstName()));


        //studentRepository.findLastName().forEach(System.out::println);

        ArrayList<Student> studentList2 = new ArrayList<>();
        studentList2.forEach(student1 -> System.out.println(student1.getLastName()));

        student = studentRepository.find(student.getId());

        System.out.println("Found student " + student.toString());

        student = studentRepository.findById(student.getId());

        System.out.println("Found student (JPQL) " + student.toString());

        student.setLastName("Javdan");

        studentRepository.update(student);

        System.out.println("Update student "+student.toString());

        student = studentRepository.updateFirstNameById("Fred", student.getId());

        System.out.println("Update first name (JPQL)" + student.toString());

        student = studentRepository.updateLastNameById("Yellow", student.getId());

        System.out.println("Update last name (JPQL)" + student.toString());

        List<Student> students = studentRepository.findByFirstNameStartWith("Fr");

        students.forEach(System.out::println);

        students = studentRepository.findByLastNameStartWith("ow");

        students.forEach(System.out::println);

        System.out.println("Number of students in the list: " + studentRepository.count());

        students = studentRepository.findSortingByFirstName();

        students.forEach(System.out::println);

        students = studentRepository.findSortingById();

        students.forEach(System.out::println);

        //studentRepository.deleteById(student.getId());

        //studentRepository.delete(student);

        System.out.println("Deleted student "+student.toString());

        studentRepository.close();
    }
}