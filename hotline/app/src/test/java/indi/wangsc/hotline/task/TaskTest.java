package indi.wangsc.hotline.task;


import indi.wangsc.hotline.Task;
import indi.wangsc.hotline.config.WordTableToExcelLineConfig;
import org.junit.Test;

public class TaskTest {
    Task task=new Task();

    @Test
    public void test(){
        String configFilepath="D:\\test.yml";
        WordTableToExcelLineConfig config=new WordTableToExcelLineConfig(configFilepath);
        task.wordTableToExcelLine(config);
    }
}
