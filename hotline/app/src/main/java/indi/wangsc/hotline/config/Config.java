package indi.wangsc.hotline.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    protected String configFilepath;
    protected ConfigParser config;

    public Config(String configFilepath) throws IOException {
        this.configFilepath = configFilepath;
        Path configFilePaths = Paths.get(configFilepath);
        if (Files.exists(configFilePaths)) {
            String configFilename = String.valueOf(configFilePaths.getFileName());
            if (configFilename.endsWith(".yml") || configFilename.endsWith(".yaml")) {
                this.config = new ConfigTypeYaml();
            }
        } else {
            throw new IOException();
        }
    }

    public String getConfigFilepath() {
        return configFilepath;
    }

    public void setConfigFilepath(String configFilepath) {
        this.configFilepath = configFilepath;
    }

    public ConfigParser getConfig() {
        return config;
    }

    public void setConfig(ConfigParser config) {
        this.config = config;
    }
}
