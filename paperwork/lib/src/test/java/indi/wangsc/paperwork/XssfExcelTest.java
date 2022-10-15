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
    private String fileName = "D:\\test.xlsx";

    XssfExcel excel = new XssfExcel(fileName);

    @Test
    public void writeCellValue() {
        Grid grid = new Grid(0, 0, "A Test...");
        excel.writeGrid(grid);
    }

    @Test
    public void lastRowNumber() {
        System.out.println("Last row number: " + excel.getSheet().getLastRowNum());
        // getLastRowNum() equals -1 if the Sheet is empty.
    }

    @Test
    public void getRow() {
        Row row = excel.getSheet().getRow(0);
        System.out.println(row);
    }

    @Test
    public void shiftRows() {
        excel.shiftRows(1, 3);
    }

    @Test
    public void writeLine() {
        Map<Integer, Object> data = new HashMap<>();
        data.put(0, "test_1");
        data.put(4, "test_5");
        data.put(5, 11.11);
        data.put(7, LocalDateTime.now());
        excel.writeLine(new Line(6, data));
    }

    @Test
    public void writeLines(){
        Map<Integer, Object> data_1 = new HashMap<>();
        data_1.put(0, "A");
        data_1.put(1, "B");
        data_1.put(2, "C");
        Map<Integer, Object> data_2 = new HashMap<>();
        data_2.put(0, "D");
        data_2.put(1, "E");
        data_2.put(2, "F");
        List<Object> data_3= List.of("G","H","I","J","K","L","M","N");
        List<Line> lines=List.of(new Line(data_1),new Line(data_2),new Line(data_3));
        excel.writeLines(11,lines);
    }
}
