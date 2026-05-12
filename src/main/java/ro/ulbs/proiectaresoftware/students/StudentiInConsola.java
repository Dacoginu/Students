package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public class StudentiInConsola implements ExportStrategy {
    @Override
    public void exporta(List<Student> studenti, String caleFisier) {
        System.out.println("\n--- Afisare studenti in consola ---");
        studenti.forEach(System.out::println);
    }
}