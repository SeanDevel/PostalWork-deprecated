package indi.wangsc.hotline;

import indi.wangsc.hotline.config.StartUpConfig;
import indi.wangsc.hotline.config.WordTableToExcelLineConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Controller {
    private final String START_UP_CONFIG_FILE_NAME = "startup.yml";
    @FXML
    private TextField startupConfigTxt;
    @FXML
    private Button chooseFileBtn;
    @FXML
    private Button executeBtn;

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
            WordTableToExcelLineConfig config = null;
            try {
                config = new WordTableToExcelLineConfig(startupConfigFilePath);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Task task = new Task();
            task.wordTableToExcelLine(config);
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
