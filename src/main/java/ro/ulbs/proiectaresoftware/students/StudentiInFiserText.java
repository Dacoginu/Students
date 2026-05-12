package ro.ulbs.proiectaresoftware.students;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class StudentiInFiserText implements ExportStrategy {
    @Override
    public void exporta(List<Student> studenti, String caleFisier) {
        try (PrintWriter writer = new PrintWriter(caleFisier)) {
            for (Student s : studenti) {
                writer.println(s.getNumarMatricol() + "," + s.getPrenume() + "," + s.getNume() + "," + s.getFormatieDeStudiu() + "," + s.getNota());
            }
            System.out.println("Exportat in fisierul: " + caleFisier);
        } catch (FileNotFoundException e) {
            System.out.println("Eroare la crearea fisierului: " + e.getMessage());
        }
    }
}