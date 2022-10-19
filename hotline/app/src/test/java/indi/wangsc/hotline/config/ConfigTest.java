package indi.wangsc.hotline.config;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ConfigTest {

    @Test
    public void test() throws IOException {
        WordTableToExcelLineConfig config=new WordTableToExcelLineConfig("D:\\_test\\test.yml");
        System.out.println(config);
    }

    @Test
    public void startup() throws IOException {
        final String START_UP_CONFIG_FILE_NAME = "startup.yml";
        StartUpConfig startUpConfig = new StartUpConfig(START_UP_CONFIG_FILE_NAME);
        System.out.println(startUpConfig);
    }
}
