package Utilities;

import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.DataFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import static org.apache.poi.ss.usermodel.WorkbookFactory.create;
import org.openqa.selenium. OutputType;
import org.openqa.selenium. TakesScreenshot;
import io.qameta.allure.Allure;

import javax.naming.MalformedLinkException;
import java.io.ByteArrayInputStream;

public class OperationalUtility {
    public static Properties prop = new Properties();

    public static String strWorkspaceLocation = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java";

    public static String strTestDataLocation = strWorkspaceLocation + File.separator + "TestData" + File.separator + "D2C_E2E_Testdata.xlsx";

    public static String strPropertiesLocation = strWorkspaceLocation + File.separator + "TestData" + File.separator + "Config.properties";
    public static List<Map<String, String>>lstTestData;
    public static int intRowNum;

    public static List<Map<String, String>> getValue(String strDataSheet) throws DataFormatException, IOException {
        Sheet sheet = getSheetByName(strDataSheet);
        return readSheet(sheet);

    }

    private static Sheet getSheetByName(String strDataSheet){
        try{
            return getWorkBook().getSheet(strDataSheet);
        }catch (Exception e) {
            System.out.println("---------------------" + e);

        }
        return null;
    }

    private static Workbook getWorkBook() {
        try {
            return create(new File(strTestDataLocation));

        } catch (Exception e) {
            System.out.println("---------------------" + e);

        }
        return null;
    }

    private static List<Map<String, String>> readSheet (Sheet sheet) {
        Row row;
        int totalRow = sheet.getPhysicalNumberOfRows();
        List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
        int headerRowNumber = getHeaderRowNumber(sheet);
        if (headerRowNumber != -1) {
            int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
            int setCurrentRow = 1;
            for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
                row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
                LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                    columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
                    excelRows.add(columnMapdata);
                }

            }

        }
        return excelRows;
    }


    private static int getHeaderRowNumber(Sheet sheet) {
        Row row = null;
        int totalRow = sheet.getLastRowNum();

        for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {

            row = getRow(sheet, currentRow);

            if (row != null) {

                int totalColumn = row.getLastCellNum();

                for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {

                    Cell cell;

                    cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    if (cell.getCellType() == CellType.STRING) {

                        return row.getRowNum();

                    } else if (cell.getCellType() == CellType.NUMERIC) {

                        return row.getRowNum();

                    } else if (cell.getCellType() == CellType.BOOLEAN) {

                        return row.getRowNum();

                    } else if (cell.getCellType() == CellType.ERROR) {
                        return row.getRowNum();
                    }

                }

            }

        }
        return (-1);

    }


    private static Row getRow(Sheet sheet, int rowNumber){
        return sheet.getRow(rowNumber);
    }



    private static LinkedHashMap<String, String> getCellValue (Sheet sheet, Row row, int currentColumn) {
        LinkedHashMap<String, String> columnMapdata =new LinkedHashMap<String, String>();
        Cell cell;
        if (row == null) {
            if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
                        .getStringCellValue();
                columnMapdata.put(columnHeaderName, "");
            }

        } else {

            cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell.getCellType() == CellType.STRING) {

                if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
                    columnMapdata.put(columnHeaderName, cell.getStringCellValue());
                }
            } else if (cell.getCellType() == CellType.NUMERIC) {
                if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(),
                        Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
                    columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
                } else if (cell.getCellType() == CellType.BLANK) {
                    if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(),
                            Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
                        String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
                        columnMapdata.put(columnHeaderName, "");
                    }
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(),
                            Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
                        String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
                        columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
                    }

                } else if (cell.getCellType() == CellType.ERROR) {
                    if (sheet.getRow(sheet.getFirstRowNum())
                            .getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
                        String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).
                                getStringCellValue();
                        columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
                    }
                }


            }


        }


        return columnMapdata;
    }


    //Property file loader method
    public static void LoadTestData(){
        try{
            prop.load(new FileReader(strPropertiesLocation));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String strPropertyName){
        //Return property value
        return(prop.getProperty(strPropertyName));
    }

    public static int getRowNum(String strTestCase, String strDataSheet) throws DataFormatException, IOException {
        try{
            lstTestData=getValue(strDataSheet);
        }catch(DataFormatException| IOException e){
            e.printStackTrace(); }
        for(int i =0; i<lstTestData.size(); i++){
            if(lstTestData.get(i).get("TestCase").compareToIgnoreCase(strTestCase)==0){
                intRowNum =i;
            }
        }
        return intRowNum;
    }

    public static String getData(String strColumnName){
        return(lstTestData.get(intRowNum).get(strColumnName));
    }


    public static void takeScreenshot(String strInfo, String strMessage, String strScreenshotInfo){
        Allure.addAttachment(strInfo, strMessage);
        Allure.addAttachment(strScreenshotInfo, new ByteArrayInputStream(((TakesScreenshot)ApplicationUtility.driver).getScreenshotAs(OutputType.BYTES)));
    }


}
