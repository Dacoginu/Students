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

    public static void exportaStudentiXls(List<Student> studenti, String fisierCale) {
        try (Workbook workbook = new HSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(fisierCale)) {

            Sheet sheet = workbook.createSheet("Studenti");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Numar Matricol");
            headerRow.createCell(1).setCellValue("Prenume");
            headerRow.createCell(2).setCellValue("Nume");
            headerRow.createCell(3).setCellValue("Formatie de Studiu");
            headerRow.createCell(4).setCellValue("Nota");

            int rowNum = 1;
            for (Student s : studenti) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getNumarMatricol());
                row.createCell(1).setCellValue(s.getPrenume());
                row.createCell(2).setCellValue(s.getNume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
                row.createCell(4).setCellValue(s.getNota());
            }

            workbook.write(fileOut);
            System.out.println("Fisierul Excel " + fisierCale + " a fost generat.");

        } catch (IOException e) {
            System.out.println("Eroare Excel: " + e.getMessage());
        }
    }

    public static List<Student> citesteStudentiXls(String fisierCale) {
        List<Student> studentiCititi = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(fisierCale);
             Workbook workbook = new HSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
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
            System.out.println("Eroare citire Excel: " + e.getMessage());
        }
        return studentiCititi;
    }

    public static void main(String[] args) {
        List<Student> studentiCititi = citesteStudentiDinFisier("studenti_in.txt");

        List<Student> studentiSortatiDupaNume = new ArrayList<>(studentiCititi);
        studentiSortatiDupaNume.sort(Comparator.comparing(Student::getNume));
        scrieStudentiInFisier("studenti_out.txt", studentiSortatiDupaNume);

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
                    float notaNoua = Float.parseFloat(campuri[1].trim());

                    Student sVechi = mapStudenti.get(nrMatricol);
                    if (sVechi != null) {
                        Student sNou = new Student(sVechi.getNumarMatricol(), sVechi.getPrenume(),
                                sVechi.getNume(), sVechi.getFormatieDeStudiu(), notaNoua);
                        mapStudenti.put(nrMatricol, sNou);
                    }
                }
            }
            scannerNote.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul de note nu a fost gasit    ");
        }

        System.out.println("--- Studenti procesati ---");
        mapStudenti.values().forEach(System.out::println);

        String excelFilePath = "src/main/java/ro/ulbs/proiectaresoftware/students/laborator8_students.xls";
        List<Student> listaExport = new ArrayList<>(mapStudenti.values());

        exportaStudentiXls(listaExport, excelFilePath);

        List<Student> studentiDinExcel = citesteStudentiXls(excelFilePath);
        System.out.println("Excel");
        studentiDinExcel.forEach(System.out::println);
    }
}