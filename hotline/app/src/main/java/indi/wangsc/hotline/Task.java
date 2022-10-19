package indi.wangsc.hotline;

import indi.wangsc.hotline.config.WordTableToExcelLineConfig;
import indi.wangsc.paperwork.excel.Excel;
import indi.wangsc.paperwork.excel.ExcelFactory;
import indi.wangsc.paperwork.excel.Line;
import indi.wangsc.paperwork.word.Word;
import indi.wangsc.paperwork.word.WordFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task {

    public void wordTableToExcelLine(WordTableToExcelLineConfig config) {
        // config info
        Excel excel = ExcelFactory.get(config.getExcelFilepath());
        excel.setSheetAt(config.getExcelInsertSheetIndex());
        int insertLineNumber = config.getExcelInsertLineNumber();
        int[] tableCellLocation = config.getWordTableCellLocation();
        String wordDirectory = config.getWordDirectory();
        Path wordDirectoryPath = Paths.get(wordDirectory);
        String wordFilesMoveTo = config.getWordFilesMoveTo();
        Path wordFilesMoveToPath = Paths.get(wordFilesMoveTo);
        try {
            if (!Files.exists(wordFilesMoveToPath)) {
                Files.createDirectories(wordFilesMoveToPath);
            }
            Files.list(wordDirectoryPath).forEach((file) -> {
                String filename = file.getFileName().toString();
                Word word = WordFactory.get(file.toAbsolutePath().toString());
                for (int tableIndex : config.getWordTableIndices()) {
                    // read a Word file, then write to the Excel file
                    List<String> data = word.getTableContent(tableIndex, tableCellLocation);
                    data.add(0, filename.substring(0, filename.lastIndexOf('.')));
                    excel.writeLine(new Line(insertLineNumber, data));
                    // copy file
                    Path movingWordFilePath = Path.of(wordFilesMoveTo, file.getFileName().toString());
                    try {
                        Files.move(file, movingWordFilePath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            excel.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
