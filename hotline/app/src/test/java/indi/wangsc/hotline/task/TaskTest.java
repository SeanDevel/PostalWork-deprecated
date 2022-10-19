package indi.wangsc.hotline.task;


import indi.wangsc.hotline.Task;
import indi.wangsc.hotline.config.WordTableToExcelLineConfig;
import org.junit.Test;

import java.io.IOException;

public class TaskTest {
    Task task=new Task();

    @Test
    public void test(){
        String configFilepath="D:\\_test\\test.yml";
        WordTableToExcelLineConfig config=new WordTableToExcelLineConfig(configFilepath);
        task.wordTableToExcelLine(config);
    }

    @Test
    public void tes他() throws IOException {
        Task task=new Task();
        task.delete();
    }
}