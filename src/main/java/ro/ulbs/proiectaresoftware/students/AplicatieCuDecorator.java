package ro.ulbs.proiectaresoftware.students;

import java.util.Arrays;
import java.util.List;

public class AplicatieCuDecorator {
    public static void main(String[] args) {
        List<Student> studentiCuNote = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70f),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10.0f),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90f)
        );

        List<ExportStrategy> strategies = Arrays.asList(
                new StudentiInConsola(),
                new StudentiInFiserText(),
                new StudentiInFisierXlsx()
        );

        for (ExportStrategy strategy : strategies) {
            String fileName = "studenti_decorator_" + strategy.getClass().getSimpleName() + ".txt";
            if (strategy instanceof StudentiInFisierXlsx) {
                fileName = "studenti_decorator.xlsx";
            } else if (strategy instanceof StudentiInConsola) {
                fileName = null;
            }

            ExportStrategy decorator = new ExportTimeDecorator(strategy);

            decorator.exporta(studentiCuNote, fileName);
        }
    }
}