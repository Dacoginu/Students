package ro.ulbs.proiectaresoftware.students;

import java.util.Arrays;
import java.util.List;

class ProcesatorStudenti {
    private ExportStrategy exportStrategy;
    private ImportStrategy importStrategy;

    public void setExportStrategy(ExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    public void setImportStrategy(ImportStrategy importStrategy) {
        this.importStrategy = importStrategy;
    }

    public void executaExport(List<Student> studenti, String destinatie) {
        if (exportStrategy != null) {
            exportStrategy.exporta(studenti, destinatie);
        }
    }

    public List<Student> executaImport(String sursa) {
        if (importStrategy != null) {
            return importStrategy.importa(sursa);
        }
        return null;
    }
}

public class AplicatieCuStrategy {
    public static void main(String[] args) {

        List<Student> studenti = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70f),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10.0f),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90f),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10.0f),
                new Student(1029, "Maria", "Pana", "TI131/1", 4.10f),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33f),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20f),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12f),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22f)
        );

        ProcesatorStudenti procesator = new ProcesatorStudenti();

        procesator.setExportStrategy(new StudentiInConsola());
        procesator.executaExport(studenti, null);

        procesator.setExportStrategy(new StudentiInFiserText());
        procesator.executaExport(studenti, "studenti_strategy.txt");

        procesator.setExportStrategy(new StudentiInFisierXlsx());
        procesator.executaExport(studenti, "studenti_strategy.xlsx");

        procesator.setImportStrategy(new StudentiDinFiserText());
        List<Student> studentiDinTxt = procesator.executaImport("studenti_strategy.txt");

        procesator.setExportStrategy(new StudentiInConsola());
        System.out.println("--- Date citite din TXT ---");
        procesator.executaExport(studentiDinTxt, null);

        procesator.setImportStrategy(new StudentiDinFiserXlsx());
        List<Student> studentiDinXlsx = procesator.executaImport("studenti_strategy.xlsx");

        System.out.println("--- Date citite din XLSX ---");
        procesator.executaExport(studentiDinXlsx, null);
    }
}