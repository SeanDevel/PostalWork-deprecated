package indi.wangsc.hotline.config;

import indi.wangsc.hotline.util.IntegerUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class WordTableToExcelLineConfig extends Config {
    private String wordDirectory;
    private int[] wordTableIndices;
    private int[] wordTableCellLocation;
    private String excelFilepath;
    private int excelInsertSheetIndex;
    private int excelInsertLineNumber;
    private String wordFilesMoveTo;

    public WordTableToExcelLineConfig(String configFilepath) throws IOException {
        super(configFilepath);
        mapConfig();
    }

    private void mapConfig() {
        Map<String, String> map = this.config.parse(configFilepath);
        this.wordDirectory = map.get("wordDirectory");
        this.wordTableIndices = IntegerUtil.delimiterStringToIntArray(map.get("wordTableIndices"));
        this.wordTableCellLocation = IntegerUtil.delimiterStringToIntArray(map.get("wordTableCellLocation"));
        this.excelFilepath = map.get("excelFilepath");
        this.excelInsertLineNumber = Integer.valueOf(map.get("excelInsertLineNumber")) - 1;
        this.excelInsertSheetIndex = Integer.valueOf(map.get("excelInsertSheetIndex")) - 1;
        this.wordFilesMoveTo = map.get("wordFilesMoveTo");
    }

    public String getWordDirectory() {
        return wordDirectory;
    }

    public int[] getWordTableIndices() {
        return wordTableIndices;
    }

    public int[] getWordTableCellLocation() {
        return wordTableCellLocation;
    }

    public String getExcelFilepath() {
        return excelFilepath;
    }

    public int getExcelInsertSheetIndex() {
        return excelInsertSheetIndex;
    }

    public int getExcelInsertLineNumber() {
        return excelInsertLineNumber;
    }

    public String getWordFilesMoveTo() {
        return wordFilesMoveTo;
    }

    public void setWordFilesMoveTo(String wordFilesMoveTo) {
        this.wordFilesMoveTo = wordFilesMoveTo;
    }

    @Override
    public String toString() {
        return "WordTableToExcelLineConfig{" +
                "configFilepath='" + configFilepath + '\'' +
                ", wordDirectory='" + wordDirectory + '\'' +
                ", wordTableIndices=" + Arrays.toString(wordTableIndices) +
                ", wordTableCellLocation=" + Arrays.toString(wordTableCellLocation) +
                ", excelFilepath='" + excelFilepath + '\'' +
                ", excelInsertSheetIndex=" + excelInsertSheetIndex +
                ", excelInsertLineNumber=" + excelInsertLineNumber +
                ", wordFilesMoveTo='" + wordFilesMoveTo + '\'' +
                '}';
    }
}
