package ro.ulbs.proiectaresoftware.students;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentiDinFiserText implements ImportStrategy {
    @Override
    public List<Student> importa(String caleFisier) {
        List<Student> studenti = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(caleFisier))) {
            while (scanner.hasNextLine()) {
                String[] campuri = scanner.nextLine().split(",");
                if (campuri.length == 5) {
                    int nrMatricol = Integer.parseInt(campuri[0].trim());
                    String prenume = campuri[1].trim();
                    String nume = campuri[2].trim();
                    String formatie = campuri[3].trim();
                    float nota = Float.parseFloat(campuri[4].trim());

                    studenti.add(new Student(nrMatricol, prenume, nume, formatie, nota));
                }
            }
            System.out.println("Importat text: " + caleFisier);
        } catch (FileNotFoundException e) {
            System.out.println("Eroare la citirea: " + e.getMessage());
        }
        return studenti;
    }
}