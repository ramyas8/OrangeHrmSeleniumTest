package orangehrmabstractcomponents;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelOperations {

    private String filePath;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private String currentSheetName;

    public static final String DATA_PATH = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "main" + File.separator + "resources" +
            File.separator + "data" + File.separator;

    public ExcelOperations(String fileName, String sheetName) throws IOException {
        this.filePath = DATA_PATH + fileName;
        this.currentSheetName = sheetName;
        loadWorkbook();
    }

    private void loadWorkbook() throws IOException {
        FileInputStream fis = new FileInputStream(this.filePath);
        this.workbook = new XSSFWorkbook(fis);
        this.sheet = workbook.getSheet(currentSheetName);
        fis.close();
    }


    public int getRowCount() {
        int rowCount = sheet.getPhysicalNumberOfRows();
        return rowCount;
    }

    public int getColCount() {
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        return colCount;
    }

    public String getCellData(int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) return "";
        Cell cell = row.getCell(colIndex);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public void setCellData(int rowIndex, int colIndex, String value) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("Excel file missing at: " + filePath);
        }
        // 1. Re-open the file to ensure the workbook is fresh and active
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook writeWorkbook = new XSSFWorkbook(fis);
        XSSFSheet writeSheet = writeWorkbook.getSheet(currentSheetName);
        fis.close();

        // 2. Perform the update
        Row row = writeSheet.getRow(rowIndex);
        if (row == null) row = writeSheet.createRow(rowIndex);
        Cell cell = row.createCell(colIndex);
        cell.setCellValue(value);

        // 3. Write and close
        try (FileOutputStream fos = new FileOutputStream(file)) {
            writeWorkbook.write(fos);
            fos.flush(); // Force write to disk
        } finally {
            writeWorkbook.close();
        }
        // 4. Update the local sheet/workbook object so subsequent reads see the change
        loadWorkbook();
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
        int firstNameCol = getColumnIndex("firstName");
        int lastNameCol = getColumnIndex("lastName");

        for (int i = 1; i < rowCount; i++) {  // Start from row 1, assuming row 0 is the header
            String cellFirstName = getCellData(i, firstNameCol); // Adjust "firstName" column name if necessary
            String cellLastName = getCellData(i, lastNameCol); // Adjust "lastName" column name if necessary
            if (cellFirstName.equalsIgnoreCase(firstName) && cellLastName.equalsIgnoreCase(lastName)) {
                return i;  // Return the row index where the data matches
            }
        }
        return -1;  // Return -1 if no match found
    }


    public Object[][] getData() throws IOException {
        int rowCount = getRowCount();
        int colCount = getColCount();
        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }

        return data;
    }


}

