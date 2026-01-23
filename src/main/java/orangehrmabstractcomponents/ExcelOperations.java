package orangehrmabstractcomponents;

import  org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelOperations {

        private String filePath;
        private XSSFWorkbook workbook;
        private XSSFSheet sheet;

        public ExcelOperations(String filePath, String sheetName) throws IOException
        {
            this.filePath = filePath;
            try (FileInputStream fis = new FileInputStream(filePath)) {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet(sheetName);
            }

        }

        public int getRowCount()
        {
            int rowCount = sheet.getPhysicalNumberOfRows();
            return rowCount;
        }

        public int getColCount()
        {
            XSSFRow row = sheet.getRow(0);
            int colCount = row.getLastCellNum();
            return colCount;
        }

    public String getCellData(int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(colIndex);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

        public void setCellData(int rowIndex, int colIndex, String value) throws IOException {
            Row row = sheet.getRow(rowIndex);
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(value);
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
        }

    public int getLastRowNum() {
        return sheet.getLastRowNum();
    }

    public int getColumnIndex(String headerName) {
        Row headerRow = sheet.getRow(0); // Assuming the first row contains headers
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(headerName)) {
                return i; // Return the index of the matching column
            }
        }
        return -1; // Return -1 if header not found
    }

    public int getRowNumber(String firstName, String lastName) {
        int rowCount = getRowCount();
        for (int i = 1; i < rowCount; i++) {  // Start from row 1, assuming row 0 is the header
            String cellFirstName = getCellData(i, getColumnIndex("firstName")); // Adjust "firstName" column name if necessary
            String cellLastName = getCellData(i, getColumnIndex("lastName")); // Adjust "lastName" column name if necessary
            if (cellFirstName.equalsIgnoreCase(firstName) && cellLastName.equalsIgnoreCase(lastName)) {
                return i;  // Return the row index where the data matches
            }
        }
        return -1;  // Return -1 if no match found
    }



    public Object[][] getData(String filePath, String sheetName) throws IOException {

        Object[][] data = null;

        int rowCount = getRowCount();
        int colCount = getColCount();

        data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {  // Start from 1 to skip header row
            //Row currentRow = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
              //  Cell cell = currentRow.getCell(j);
                data[i - 1][j] = getCellData(i, j);  // Fill the data array
            }
        }
        //workbook.close();
       // fis.close();
        return data;
    }


    }

