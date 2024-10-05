package orangehrmabstractcomponents;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelOperations {

    public ArrayList<String> getData(String testCaseName) throws IOException {

        ArrayList<String> a = new ArrayList<String>();
        FileInputStream fis = new FileInputStream("//Users//ramyasri//Downloads//OrangeHRMTestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("TestData")) {

                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> ce = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (ce.hasNext()) ;
                {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        column = k;
                        //desired column
                    }
                    k++;
                }

                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();

                            a.add(c.getStringCellValue());
                        }
                    }
                }

            }
        }
    return a;

    }
}
