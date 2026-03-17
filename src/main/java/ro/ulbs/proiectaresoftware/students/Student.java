package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class Student {
    int numarMatricol;
    String prenume;
    String nume;
    String formatieDeStudiu;

    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
    }

    public int getNumarMatricol() {
        return numarMatricol;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getFormatieDeStudiu() {
        return formatieDeStudiu;
    }

    @Override
    public String toString() {
        return String.format("%-6d %-12s %-12s %-8s",numarMatricol,prenume,nume,formatieDeStudiu);


    }
    @Override
    public boolean equals(Object o) {
if (this == o) return true;
if(!(o instanceof Student)) return false;
Student student = (Student) o;
return numarMatricol == student.numarMatricol && prenume.equals(student.prenume) && nume.equals(student.nume)&& formatieDeStudiu.equals(student.formatieDeStudiu);
    }
    @Override
    public int hashCode() {
        return Objects.hash(numarMatricol, prenume, nume, formatieDeStudiu);
    }

}
