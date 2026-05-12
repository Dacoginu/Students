package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class StudentBursier extends Student {
    private final double cuantumBursa;

    public StudentBursier(int numarMatricol, String prenume, String nume, String formatieDeStudiu, double nota, double bursa) {
        super(numarMatricol, prenume, nume, formatieDeStudiu, (float) nota);
        this.cuantumBursa = bursa;
    }

    public double getCuantumBursa() {
        return cuantumBursa;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Bursa: %.2f", cuantumBursa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentBursier that = (StudentBursier) o;
        return Double.compare(that.cuantumBursa, cuantumBursa) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cuantumBursa);
    }
}