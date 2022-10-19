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
//            Stream<Path> stream = Files.walk(wordDirectoryPath, 1);
//            List<String> fileList=stream.map(Path::toString).collect(Collectors.toList());
//            for(String s:fileList){
//                System.out.println(">>> "+s);
//            }

//            for (File file : new File(wordDirectory).listFiles()) {
//                String filename = file.getName();
//                Word word = WordFactory.get(file.getAbsolutePath());
//                for (int tableIndex : config.getWordTableIndices()) {
//                    // read a Word file, then write to the Excel file
//                    List<String> data = word.getTableContent(tableIndex, tableCellLocation);
//                    data.add(0, filename.substring(0, filename.lastIndexOf('.')));
//                    excel.writeLine(new Line(insertLineNumber, data));
//                    // copy the Word file to a certain directory, then delete the original file
//                    Path movingWordFilePath = Path.of(wordFilesMoveTo, filename);
//                    file.renameTo(new File(wordFilesMoveTo, filename));
//                }
//            }

            /*
            Files.list(wordDirectoryPath).forEach((file) -> {
                String filename = file.getFileName().toString();
                Word word = WordFactory.get(file.toAbsolutePath().toString());
                for (int tableIndex : config.getWordTableIndices()) {
                    // read a Word file, then write to the Excel file
                    List<String> data = word.getTableContent(tableIndex, tableCellLocation);
                    data.add(0, filename.substring(0, filename.lastIndexOf('.')));
                    excel.writeLine(new Line(insertLineNumber, data));
                    // copy the Word file to a certain directory, then delete the original file
                    try {
                        Path movingWordFilePath = Path.of(wordFilesMoveTo, filename);
                        Files.move(file, movingWordFilePath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */
            DirectoryStream<Path> visitWordFilesStream = Files.newDirectoryStream(wordDirectoryPath);
            visitWordFilesStream.forEach((file) -> {
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
                        Files.copy(file, movingWordFilePath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            visitWordFilesStream.close();
            Stream<Path> stream = Files.walk(wordDirectoryPath, 1);
            List<String> filePathList = stream.map(Path::toString).collect(Collectors.toList());
            stream.close();
            filePathList.remove(0);
            for (String filePath : filePathList) {
                Files.delete(Paths.get(filePath));
            }
            // move
//            DirectoryStream<Path> moveWordFilesStream = Files.newDirectoryStream(wordDirectoryPath);
//            moveWordFilesStream.forEach((file) -> {
//                // copy the Word file to a certain directory, then delete the original file
//                Path movingWordFilePath = Path.of(wordFilesMoveTo, file.getFileName().toString());
//                try {
//                    Files.move(file, movingWordFilePath, StandardCopyOption.REPLACE_EXISTING);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            moveWordFilesStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() throws IOException {
//        Stream<Path> stream = Files.walk(Paths.get("D:\\_test\\word_files_ready_to_read"), 1);
//        List<String> filePathList = stream.map(Path::toString).collect(Collectors.toList());
//        stream.close();
//        filePathList.remove(0);
//        for (String filePath : filePathList) {
//            Files.delete(Path.of(filePath));
//        }
        Files.list(Path.of("D:\\_test\\word_files_ready_to_read")).forEach((file)->{
            try {
                Files.delete(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
