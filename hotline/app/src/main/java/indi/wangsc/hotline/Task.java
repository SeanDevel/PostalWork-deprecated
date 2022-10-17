package indi.wangsc.hotline;

import indi.wangsc.hotline.config.WordTableToExcelLineConfig;
import indi.wangsc.paperwork.excel.Excel;
import indi.wangsc.paperwork.excel.ExcelFactory;
import indi.wangsc.paperwork.excel.Line;
import indi.wangsc.paperwork.word.Word;
import indi.wangsc.paperwork.word.WordFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Task {

    public void wordTableToExcelLine(WordTableToExcelLineConfig config) {
        System.out.println(config.toString());
        Excel excel = ExcelFactory.get(config.getExcelFilepath());
        excel.setSheetAt(config.getExcelInsertSheetIndex() - 1);
        int insertLineNumber = config.getExcelInsertLineNumber() - 1;
        int[] tableCellLocation = config.getTableCellLocation();
        try {
            Files.list(Paths.get(config.getWordDirectory())).forEach((file) -> {
                Word word = WordFactory.get(file.toAbsolutePath().toString());
                for (int tableIndex : config.getTableIndices()) {
                    List<String> content = word.getTableContent(tableIndex, tableCellLocation);
                    content.add(0, file.getFileName().toString());
                    excel.writeLine(new Line(insertLineNumber, content));
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
