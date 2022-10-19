package indi.wangsc.paperwork;

import indi.wangsc.paperwork.excel.Grid;
import indi.wangsc.paperwork.excel.Line;
import indi.wangsc.paperwork.excel.XssfExcel;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XssfExcelTest {
    private final String fileName = "D:\\_test\\test.xlsx";

    @Test
    public void writeCellValue() {
        XssfExcel excel = new XssfExcel(fileName);
        Grid grid = new Grid(0, 0, "A Test...");
        excel.writeGrid(grid);
    }

    @Test
    public void lastRowNumber() {
        XssfExcel excel = new XssfExcel(fileName);
        System.out.println("Last row number: " + excel.getSheet().getLastRowNum());
        // getLastRowNum() equals -1 if the Sheet is empty.
    }

    @Test
    public void getRow() {
        XssfExcel excel = new XssfExcel(fileName);
        Row row = excel.getSheet().getRow(0);
        System.out.println(row);
    }

    @Test
    public void shiftRows() {
        XssfExcel excel = new XssfExcel(fileName);
        if(excel.shiftRows(0, 3)){
            System.out.println("\nsuccess...\n");
        }else{
            System.out.println("\nfailed...\n");
        }
    }

    @Test
    public void writeLine() {
        XssfExcel excel = new XssfExcel(fileName);
        Map<Integer, String> data = new HashMap<>();
        data.put(0, "test_1");
        data.put(4, "test_5");
        data.put(5, "11.11");
        data.put(7, LocalDateTime.now().toString());
        excel.writeLine(new Line(6, data));
    }

    @Test
    public void writeLines(){
        XssfExcel excel = new XssfExcel(fileName);
        excel.setSheetAt(0);
        Map<Integer, String> data_1 = new HashMap<>();
        data_1.put(0, "A");
        data_1.put(1, "B");
        data_1.put(2, "C");
        Map<Integer, String> data_2 = new HashMap<>();
        data_2.put(0, "D");
        data_2.put(1, "E");
        data_2.put(2, "F");
        List<String> data_3= List.of("G","H","I","J","K","L","M","N");
        List<Line> lines=List.of(new Line(data_1),new Line(data_2),new Line(data_3));
        excel.writeLines(11,lines);
    }
}
