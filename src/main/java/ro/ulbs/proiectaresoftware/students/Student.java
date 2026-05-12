package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class Student {
    private final int numarMatricol;
    private final String prenume;
    private final String nume;
    private final String formatieDeStudiu;
    private final float nota;

    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu, float nota) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.nota = nota;
    }

    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this(numarMatricol, prenume, nume, formatieDeStudiu, 0.0f);
    }

    // Returnam un obiect nou - regula imutabilitatii
    public Student schimbaFormatiaDeStudiu(String nouaFormatie) {
        return new Student(this.numarMatricol, this.prenume, this.nume, nouaFormatie, this.nota);
    }

    // Doar Getters, FARA Setters
    public int getNumarMatricol() { return numarMatricol; }
    public String getPrenume() { return prenume; }
    public String getNume() { return nume; }
    public String getFormatieDeStudiu() { return formatieDeStudiu; }
    public float getNota() { return nota; }

    @Override
    public String toString() {
        return String.format("%14d %15s %-12s %-12s %5.2f", numarMatricol, prenume, nume, formatieDeStudiu, nota);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return numarMatricol == student.numarMatricol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numarMatricol);
    }
}