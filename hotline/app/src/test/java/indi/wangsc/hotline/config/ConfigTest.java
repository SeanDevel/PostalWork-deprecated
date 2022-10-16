package indi.wangsc.hotline.config;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ConfigTest {

    @Test
    public void test(){
        WordTableToExcelLineConfig config=new WordTableToExcelLineConfig("D:\\test.yml");
        System.out.println(config);
    }

}
