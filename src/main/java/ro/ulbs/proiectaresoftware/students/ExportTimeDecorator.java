package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public class ExportTimeDecorator implements ExportStrategy {


    private ExportStrategy decoratedStrategy;

    public ExportTimeDecorator(ExportStrategy decoratedStrategy) {
        this.decoratedStrategy = decoratedStrategy;
    }

    @Override
    public void exporta(List<Student> studenti, String caleFisier) {
        long startTime = System.currentTimeMillis();

        decoratedStrategy.exporta(studenti, caleFisier);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution time for " + decoratedStrategy.getClass().getSimpleName() + ": " + executionTime + " ms\n");
    }
}