package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class StudentiInFisierXlsx implements ExportStrategy {
    @Override
    public void exporta(List<Student> studenti, String caleFisier) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(caleFisier)) {

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
            System.out.println("Exportat cu succes in fisierul XLSX: " + caleFisier);

        } catch (IOException e) {
            System.out.println("Eroare Excel XLSX (Export): " + e.getMessage());
        }
    }
}