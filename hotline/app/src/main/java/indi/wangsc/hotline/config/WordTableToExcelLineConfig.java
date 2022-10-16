package indi.wangsc.hotline.config;

import indi.wangsc.hotline.util.IntegerUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class WordTableToExcelLineConfig {
    private String configFilepath;
    private Config config;
    private String wordDirectory;
    private int[] tableIndices;
    private int[] tableCellLocation;
    private String excelFilepath;
    private int excelInsertSheetIndex;
    private int excelInsertLineNumber;

    public WordTableToExcelLineConfig(String configFilepath) {
        this.configFilepath = configFilepath;
        Path configFilePaths = Paths.get(configFilepath);
        if (Files.exists(configFilePaths)) {
            String configFilename = String.valueOf(configFilePaths.getFileName());
            if (configFilename.endsWith(".yml") || configFilename.endsWith(".yaml")) {
                this.config = new ConfigTypeYaml();
            }
            mapConfig();
        } else {
            System.out.println("No Such File......");
        }
    }

    private void mapConfig() {
        Map<String, String> map = this.config.parse(configFilepath);
        this.wordDirectory = map.get("wordDirectory");
        this.tableIndices = IntegerUtil.delimiteredStringToIntArray(map.get("tableIndices"));
        this.tableCellLocation = IntegerUtil.delimiteredStringToIntArray(map.get("tableCellLocation"));
        this.excelFilepath = map.get("excelFilepath");
        this.excelInsertLineNumber = Integer.valueOf(map.get("excelInsertLineNumber"));
        this.excelInsertSheetIndex = Integer.valueOf(map.get("excelInsertSheetIndex"));
    }

    public String getWordDirectory() {
        return wordDirectory;
    }

    public int[] getTableIndices() {
        return tableIndices;
    }

    public int[] getTableCellLocation() {
        return tableCellLocation;
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

    @Override
    public String toString() {
        return "WordTableToExcelLineConfig{" +
                "config=" + config +
                ", wordDirectory='" + wordDirectory + '\'' +
                ", tableIndices=" + Arrays.toString(tableIndices) +
                ", tableCellLocation=" + Arrays.toString(tableCellLocation) +
                ", excelFilepath='" + excelFilepath + '\'' +
                ", excelInsertSheetIndex=" + excelInsertSheetIndex +
                ", excelInsertLineNumber=" + excelInsertLineNumber +
                '}';
    }
}
