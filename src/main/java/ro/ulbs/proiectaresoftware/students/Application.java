package ro.ulbs.proiectaresoftware.students;

import java.util.*;
import java.io.*;

public class Application {

    public static boolean existaStudentInLista(List<Student> lista, Student cautat) {
        return lista.contains(cautat);
    }

    public static boolean existaStudentO1(Set<Student> set, Student cautat) {
        return set.contains(cautat);
    }

    public static List<Student> citesteStudentiDinFisier(String numeFisier) {
        List<Student> studenti = new ArrayList<>();
        try {
            File fisier = new File(numeFisier);
            Scanner scanner = new Scanner(fisier);

            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                String[] campuri = linie.split(",");

                if (campuri.length == 4) {
                    int nrMatricol = Integer.parseInt(campuri[0].trim());
                    String prenume = campuri[1].trim();
                    String nume = campuri[2].trim();
                    String formatie = campuri[3].trim();

                    Student s = new Student(nrMatricol, prenume, nume, formatie);
                    studenti.add(s);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Eroare: Fisierul " + numeFisier + " nu a fost gasit!");
        }
        return studenti;
    }

    public static void scrieStudentiInFisier(String numeFisier, List<Student> studenti) {
        try {
            PrintWriter writer = new PrintWriter(new File(numeFisier));
            writer.println("numar matricol         prenume         nume formatieDeStudiu  nota");

            for (Student s : studenti) {
                writer.println(s.toString());
            }

            writer.close();
            System.out.println("Fisierul " + numeFisier + " a fost salvat.");
        } catch (FileNotFoundException e) {
            System.out.println("Eroare la crearea fisierului: " + numeFisier);
        }
    }

    public static float gasesteNota(String prenume, String nume, Map<Integer, Student> tineri) {
        Map<String, Student> mapDupaNume = new HashMap<>();

        for (Student s : tineri.values()) {
            String cheie = s.getPrenume() + "-" + s.getNume();
            mapDupaNume.put(cheie, s);
        }

        String cheieCautata = prenume + "-" + nume;
        Student studentGasit = mapDupaNume.get(cheieCautata);

        if (studentGasit != null) {
            return studentGasit.getNota();
        } else {
            return 0.0f;
        }
    }

    public static void main(String[] args) {


        System.out.println();
        List<Student> studentiLab2 = new ArrayList<>();
        studentiLab2.add(new Student(112, "Ioan", "Popa", "TI21/1"));
        studentiLab2.add(new Student(112, "Maria", "Oprea", "TI21/1"));
        studentiLab2.add(new Student(120, "Alis", "Popa", "TI21/2"));

        Student studentCautat = new Student(120, "Alis", "Popa", "TI21/2");
        System.out.println("Exista in Lista (O(N)): " + existaStudentInLista(studentiLab2, studentCautat));

        Set<Student> setStudenti = new HashSet<>(studentiLab2);
        System.out.println("Exista in Set (O(1)): " + existaStudentO1(setStudenti, studentCautat));


        System.out.println();
        List<Student> studentiCititi = citesteStudentiDinFisier("studenti_in.txt");


        List<Student> studentiSortatiDupaNume = new ArrayList<>(studentiCititi);
        Collections.sort(studentiSortatiDupaNume, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getNume().compareTo(s2.getNume());
            }
        });
        scrieStudentiInFisier("studenti_out.txt", studentiSortatiDupaNume);


        List<Student> studentiTemaCasa = new ArrayList<>(studentiCititi);
        Collections.sort(studentiTemaCasa, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {

                int rezultat = s1.getFormatieDeStudiu().compareTo(s2.getFormatieDeStudiu());

                if (rezultat == 0) {
                    return s1.getNume().compareTo(s2.getNume());
                }
                return rezultat;
            }
        });
        scrieStudentiInFisier("studenti_out_sorted.txt", studentiTemaCasa);


        System.out.println();
        Map<Integer, Student> mapStudenti = new HashMap<>();
        for (Student s : studentiCititi) {
            mapStudenti.put(s.getNumarMatricol(), s);
        }


        try {
            File fisierNote = new File("note_anon.txt");
            Scanner scannerNote = new Scanner(fisierNote);

            while (scannerNote.hasNextLine()) {
                String linie = scannerNote.nextLine();
                String[] campuri = linie.split(",");

                if (campuri.length == 2) {
                    int nrMatricol = Integer.parseInt(campuri[0].trim());
                    float nota = Float.parseFloat(campuri[1].trim());

                    Student student = mapStudenti.get(nrMatricol);
                    if (student != null) {
                        student.setNota(nota);
                    }
                }
            }
            scannerNote.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul de note nu a fost gasit!");
        }

        System.out.println("--- Studenti dupa asocierea notelor ---");
        for (Student s : mapStudenti.values()) {
            System.out.println(s);
        }

        System.out.println("\n--- TEMA LAB 4 ---");
        System.out.println("Nota Bianca Popescu: " + gasesteNota("Bianca", "Popescu", mapStudenti));
        System.out.println("Nota Ioan Popa: " + gasesteNota("Ioan", "Popa", mapStudenti));
    }
}