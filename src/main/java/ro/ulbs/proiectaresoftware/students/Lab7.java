package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.List;

public class Lab7 {

    public static List<Student> reasigneazaFormatii(List<Student> listaInitiala, String formatie1, String formatie2) {
        List<Student> listaNoua = new ArrayList<>();

        int mijloc = (int) Math.ceil((double) listaInitiala.size() / 2);

        for (int i = 0; i < listaInitiala.size(); i++) {
            Student studentCurent = listaInitiala.get(i);

            if (i < mijloc) {
                listaNoua.add(studentCurent.schimbaFormatiaDeStudiu(formatie1));
            } else {
                listaNoua.add(studentCurent.schimbaFormatiaDeStudiu(formatie2));
            }
        }

        return listaNoua;
    }

    public static void main(String[] args) {
        List<Student> studenti = List.of(
                new Student(101, "Ana", "Pop", "TI1"),
                new Student(102, "Bogdan", "Ion", "TI1"),
                new Student(103, "Cristi", "Dan", "TI2"),
                new Student(104, "Diana", "Stan", "TI2"),
                new Student(105, "Elena", "Radu", "TI2")
        );

        System.out.println("--- Lista initiala ---");
        studenti.forEach(System.out::println);

        List<Student> studentiNoi = reasigneazaFormatii(studenti, "Grupa_A", "Grupa_B");

        System.out.println("\n--- Lista dupa impartirea in formatii ---");
        studentiNoi.forEach(System.out::println);
    }
}