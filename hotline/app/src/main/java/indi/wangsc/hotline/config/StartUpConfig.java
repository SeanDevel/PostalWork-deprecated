package indi.wangsc.hotline.config;

import java.io.IOException;
import java.util.Map;

public class StartUpConfig extends Config {
    private String wordTableToExcelLineConfigFilePath;

    public StartUpConfig(String configFilePath) throws IOException {
        super(configFilePath);
        mapConfig();
    }

    private void mapConfig() {
        Map<String, String> map = this.config.parse(configFilepath);
        this.wordTableToExcelLineConfigFilePath = map.get("wordTableToExcelLineConfigFilePath");
    }

    public String getWordTableToExcelLineConfigFilePath() {
        return wordTableToExcelLineConfigFilePath;
    }

    public void setWordTableToExcelLineConfigFilePath(String wordTableToExcelLineConfigFilePath) {
        this.wordTableToExcelLineConfigFilePath = wordTableToExcelLineConfigFilePath;
    }

    @Override
    public String toString() {
        return "StartUpConfig{" +
                "wordTableToExcelLineConfigFilePath='" + wordTableToExcelLineConfigFilePath + '\'' +
                '}';
    }
}
