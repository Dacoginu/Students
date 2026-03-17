package ro.ulbs.proiectaresoftware.students;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(112, "Ioan", "Popa", "TI21/1"));
        students.add(new Student(112, "Maria", "Oprea", "TI21/1"));
        students.add(new Student(120, "Alis", "Popa", "TI21/2"));
        students.add(new Student(122, "Mihai", "Vecerdea", "TI22/1"));
        students.add(new Student(122, "Eugen", "Uritescu", "TI22/2"));

        System.out.printf("numarMatricol Prenume Nume formatieDeStudiu");
        students.forEach(student -> System.out.println(student));

    Student s =new Student(120, "Alis", "Popa", "TI21/2");

 if(boolean exista if (students.contains(s) != 0)





    }

}
