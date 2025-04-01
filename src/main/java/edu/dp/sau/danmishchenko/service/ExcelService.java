package edu.dp.sau.danmishchenko.service;

import edu.dp.sau.danmishchenko.model.Smartphone;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public void exportToExcel(List<Smartphone> smartphones, HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Smartphones");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Назва");
        headerRow.createCell(1).setCellValue("Ціна");
        headerRow.createCell(2).setCellValue("Посилання");

        int rowNum = 1;
        for (Smartphone smartphone : smartphones) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(smartphone.getName());
            row.createCell(1).setCellValue(smartphone.getPrice());
            row.createCell(2).setCellValue(smartphone.getLink());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=smartphones.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}