package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFiserXlsx implements ImportStrategy {
    @Override
    public List<Student> importa(String caleFisier) {
        List<Student> studenti = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(caleFisier);
             Workbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int nrMatricol = (int) row.getCell(0).getNumericCellValue();
                    String prenume = row.getCell(1).getStringCellValue();
                    String nume = row.getCell(2).getStringCellValue();
                    String formatie = row.getCell(3).getStringCellValue();
                    float nota = (float) row.getCell(4).getNumericCellValue();

                    studenti.add(new Student(nrMatricol, prenume, nume, formatie, nota));
                }
            }
            System.out.println("Importat cu succes din fisierul XLSX: " + caleFisier);

        } catch (IOException e) {
            System.out.println("Eroare Excel XLSX (Import): " + e.getMessage());
        }
        return studenti;
    }
}