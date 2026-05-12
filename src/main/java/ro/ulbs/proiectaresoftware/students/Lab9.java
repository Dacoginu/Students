package ro.ulbs.proiectaresoftware.students;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lab9 {

    public static void main(String[] args) {

        List<Student> studentiCuNote = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70f),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10.0f),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90f),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10.0f),
                new Student(1029, "Maria", "Pana", "TI131/2", 4.10f),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33f),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20f),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12f),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22f)
        );

        // a)
        System.out.println("--- a) Studenti cu nota 10 ---");
        studentiCuNote.stream()
                .filter(s -> s.getNota() == 10.0f)
                .forEach(System.out::println);

        // b)
        System.out.println("\n--- b) Studenti cu nota sub 5 ---");
        studentiCuNote.stream()
                .filter(s -> s.getNota() < 5.0f)
                .forEach(System.out::println);

        // c)
        System.out.println("\n--- c) Studenti dupa modificarea notelor < 4 ---");
        List<Student> studentiModificati = studentiCuNote.stream()
                .map(s -> s.getNota() < 4.0f ?
                        new Student(s.getNumarMatricol(), s.getPrenume(), s.getNume(), s.getFormatieDeStudiu(), 4.0f) : s)
                .collect(Collectors.toList());
        studentiModificati.forEach(System.out::println);

        // d)
        System.out.println("\n--- d) Suma notelor ---");
        float sumaNote = studentiCuNote.stream()
                .map(Student::getNota)
                .reduce(0.0f, Float::sum);
        System.out.println("Suma notelor este: " + sumaNote);

        // e)
        System.out.println("\n--- e) Media notelor ---");
        float media = sumaNote / studentiCuNote.size();
        System.out.println("Media notelor este: " + media);
    }
}