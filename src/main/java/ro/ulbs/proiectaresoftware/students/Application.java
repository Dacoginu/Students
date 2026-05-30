package ro.ulbs.proiectaresoftware.students;

import java.util.*;
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

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
            Scanner scanner = new Scanner(new File(numeFisier));
            while (scanner.hasNextLine()) {
                String[] campuri = scanner.nextLine().split(",");
                if (campuri.length == 4) {
                    int nrMatricol = Integer.parseInt(campuri[0].trim());
                    String prenume = campuri[1].trim();
                    String nume = campuri[2].trim();
                    String formatie = campuri[3].trim();
                    studenti.add(new Student(nrMatricol, prenume, nume, formatie));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit: " + numeFisier);
        }
        return studenti;
    }

    public static void scrieStudentiInFisier(String numeFisier, List<? extends Student> studenti) {
        try {
            PrintWriter writer = new PrintWriter(new File(numeFisier));
            for (Student s : studenti) {
                writer.println(s.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Eroare la scriere: " + numeFisier);
        }
    }

    public static float gasesteNota(String prenume, String nume, Map<Integer, Student> tineri) {
        for (Student s : tineri.values()) {
            if (s.getPrenume().equals(prenume) && s.getNume().equals(nume)) {
                return s.getNota();
            }
        }
        return 0.0f;
    }

    public static void exportaStudentiXls(List<Student> studenti, String fisierCale) {
        try (Workbook workbook = new HSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(fisierCale)) {

            Sheet sheet = workbook.createSheet("Studenti");
            int rowNum = 0;
            for (Student s : studenti) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getNumarMatricol());
                row.createCell(1).setCellValue(s.getPrenume());
                row.createCell(2).setCellValue(s.getNume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
                row.createCell(4).setCellValue(s.getNota());
            }
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println("Eroare excel: " + e.getMessage());
        }
    }

    public static List<Student> citesteStudentiXls(String fisierCale) {
        List<Student> studentiCititi = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(fisierCale);
             Workbook workbook = new HSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int nrMatricol = (int) row.getCell(0).getNumericCellValue();
                    String prenume = row.getCell(1).getStringCellValue();
                    String nume = row.getCell(2).getStringCellValue();
                    String formatie = row.getCell(3).getStringCellValue();
                    float nota = (float) row.getCell(4).getNumericCellValue();
                    studentiCititi.add(new Student(nrMatricol, prenume, nume, formatie, nota));
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare citire excel: " + e.getMessage());
        }
        return studentiCititi;
    }

    public static void main(String[] args) {
        Student s1 = new Student(112, "Ioan", "Popa", "TI21/1");
        Student s2 = new Student(112, "Maria", "Oprea", "TI21/1");
        Student s3 = new Student(120, "Alis", "Popa", "TI21/2");
        Student s4 = new Student(122, "Mihai", "Vecerdea", "TI22/1");
        Student s5 = new Student(122, "Eugen", "Uritescu", "TI22/2");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);

        Set<Student> setStudenti = new HashSet<>(Arrays.asList(s1, s2, s3, s4, s5));
        System.out.println("Exista Alis Popa? " + existaStudentO1(setStudenti, new Student(120, "Alis", "Popa", "TI21/2")));
        System.out.println("Exista Maria Popa? " + existaStudentO1(setStudenti, new Student(112, "Maria", "Popa", "TI21/1")));

        List<Student> studentiCititi = citesteStudentiDinFisier("studenti_in.txt");
        List<Student> studentiSortatiDublu = new ArrayList<>(studentiCititi);
        studentiSortatiDublu.sort(Comparator.comparing(Student::getFormatieDeStudiu).thenComparing(Student::getNume));
        scrieStudentiInFisier("studenti_out_sorted.txt", studentiSortatiDublu);

        List<StudentBursier> bursieri = new ArrayList<>();
        bursieri.add(new StudentBursier(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        bursieri.add(new StudentBursier(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        bursieri.add(new StudentBursier(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        bursieri.add(new StudentBursier(1029, "Bianca", "Popescu", "TI131/1", 9.10, 780.80));
        scrieStudentiInFisier("bursieri_out.txt", bursieri);

        Map<Integer, Student> mapStudenti = new HashMap<>();
        for (Student s : studentiCititi) {
            mapStudenti.put(s.getNumarMatricol(), s);
        }

        try {
            Scanner scannerNote = new Scanner(new File("note_anon.txt"));
            while (scannerNote.hasNextLine()) {
                String[] campuri = scannerNote.nextLine().split(",");
                if (campuri.length == 2) {
                    int nrMatricol = Integer.parseInt(campuri[0].trim());
                    float notaNoua = Float.parseFloat(campuri[1].trim());

                    Student sVechi = mapStudenti.get(nrMatricol);
                    if (sVechi != null) {
                        mapStudenti.put(nrMatricol, new Student(sVechi.getNumarMatricol(), sVechi.getPrenume(), sVechi.getNume(), sVechi.getFormatieDeStudiu(), notaNoua));
                    }
                }
            }
            scannerNote.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul de note nu a fost gasit");
        }

        String excelFilePath = "laborator8_students.xls";
        exportaStudentiXls(new ArrayList<>(mapStudenti.values()), excelFilePath);
        List<Student> studentiDinExcel = citesteStudentiXls(excelFilePath);
    }
}