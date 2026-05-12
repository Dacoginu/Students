package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public interface ExportStrategy {
    void exporta(List<Student> studenti, String caleFisier);
}