package indi.wangsc.hotline;

import indi.wangsc.hotline.config.StartUpConfig;
import indi.wangsc.hotline.config.WordTableToExcelLineConfig;
import indi.wangsc.paperwork.excel.Excel;
import indi.wangsc.paperwork.excel.ExcelFactory;
import indi.wangsc.paperwork.excel.Line;
import indi.wangsc.paperwork.word.Word;
import indi.wangsc.paperwork.word.WordFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class Controller {
    private final String START_UP_CONFIG_FILE_NAME = "startup.yml";
    @FXML
    private TextField startupConfigTxt;
    @FXML
    private Button chooseFileBtn;
    @FXML
    private Button executeBtn;
    @FXML
    private Label processCondition;

    public void initialize() throws IOException {
        createStartUpConfigFileIfNotExists();
        chooseFile();
        StartUpConfig startUpConfig = new StartUpConfig(START_UP_CONFIG_FILE_NAME);
        startupConfigTxt.setText(startUpConfig.getWordTableToExcelLineConfigFilePath());
        executeWordTableToExcelLine();
    }

    private void createStartUpConfigFileIfNotExists() throws IOException {
        final Path path = Paths.get("./", START_UP_CONFIG_FILE_NAME);
        if (Files.exists(path)) {
            Files.lines(path, Charset.forName("UTF-8"));
        } else {
            Files.createFile(path);
        }
    }

    private void executeWordTableToExcelLine() {
        executeBtn.setOnAction((ActionEvent e) -> {
            String startupConfigFilePath = startupConfigTxt.getText();
            //WordTableToExcelLineConfig config = null;
            try {
                WordTableToExcelLineConfig config = new WordTableToExcelLineConfig(startupConfigFilePath);
                // config info
                Excel excel = ExcelFactory.get(config.getExcelFilepath());
                excel.setSheetAt(config.getExcelInsertSheetIndex());
                int insertLineNumber = config.getExcelInsertLineNumber();
                int[] tableCellLocation = config.getWordTableCellLocation();
                String wordDirectory = config.getWordDirectory();
                Path wordDirectoryPath = Paths.get(wordDirectory);
                String wordFilesMoveTo = config.getWordFilesMoveTo();
                Path wordFilesMoveToPath = Paths.get(wordFilesMoveTo);

                // create directory for moving word files
                if (!Files.exists(wordFilesMoveToPath)) {
                    Files.createDirectories(wordFilesMoveToPath);
                }

                // processing word-table-to-excel-line task
                Files.list(wordDirectoryPath).forEach((file) -> {
                    String filename = file.getFileName().toString();
                    Word word = WordFactory.get(file.toAbsolutePath().toString());
                    System.out.println("word -->> "+word);
                    for (int tableIndex : config.getWordTableIndices()) {
                        System.out.println("??>>> "+tableIndex);
                        // read a Word file, then write to the Excel file
                        List<String> data = word.getTableContent(tableIndex, tableCellLocation);
                        data.add(0, filename.substring(0, filename.lastIndexOf('.')));
                        excel.writeLine(new Line(insertLineNumber, data));
                        // copy file
                        Path movingWordFilePath = Path.of(wordFilesMoveTo, file.getFileName().toString());
                        try {
                            Files.move(file, movingWordFilePath, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                excel.save();
                processCondition.setText("finished!");
            } catch (IOException ex) {
                processCondition.setText("error happened.");
                throw new RuntimeException(ex);
            }
        });
    }

    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("YAML files (*.yml)", "*.yml"),
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
        );
        chooseFileBtn.setOnAction((ActionEvent e) -> {
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                startupConfigTxt.setText(file.getAbsolutePath());
            }
        });
    }
}
