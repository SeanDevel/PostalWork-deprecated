package indi.wangsc.hotline;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Controller {
    private final String EMPTY_FILEPATH = "";
    private final String START_UP_CONFIG_FILE_NAME = "startup.config";
    private String chosenFilePath;
    @FXML
    private Label startupConfigTitle;
    @FXML
    private TextField startupConfigTxt;
    @FXML
    private Button chooseFileBtn;
    @FXML
    private Button executeBtn;

    public void initialize() {
        startupConfigTitle.setText("config file");
        chooseFileBtn.setOnAction((ActionEvent e) -> {
            chosenFilePath = pathOfChosenFile();
            if (chosenFilePath != EMPTY_FILEPATH) {
                startupConfigTxt.setText(chosenFilePath);
            }
        });
    }

    private String pathOfChosenFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        return (file == null) ? EMPTY_FILEPATH : file.getAbsolutePath();
    }

    private void startupConfig(){
        Path startupConfigFile = Paths.get(START_UP_CONFIG_FILE_NAME);
        if (!Files.exists(startupConfigFile)) {
            try {
                Files.createFile(startupConfigFile);
                FileOutputStream out=new FileOutputStream(startupConfigFile.toFile());
                FileChannel fileChannel=out.getChannel();
                ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                while(true){
                    int readNumber=fileChannel.read(byteBuffer);
                    if(readNumber==-1){
                        break;
                    }
                    byteBuffer.flip();
                    fileChannel.write(byteBuffer);
                    byteBuffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
