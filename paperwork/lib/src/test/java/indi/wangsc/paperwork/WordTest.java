package indi.wangsc.paperwork;

import indi.wangsc.paperwork.word.Word;
import indi.wangsc.paperwork.word.WordFactory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class WordTest {
    private final String filePath = "D:\\test.doc";

    Word word= WordFactory.create(filePath);


    @Test
    public void getTableContent() {
        for(var cellText:word.getTableContent(0,new int[]{0,0,1,1})){
            System.out.println(cellText);
        }
    }

    /*
    @Test
    public void writeParagraph(){
        word.writeParagraph("Testing write paragraph.","仿宋_GB2312",15.75);
        word.save();
    }

    @Test
    public void replace(){
        Map<String,String> map=new HashMap<>();
        map.put("${company}","YT");
        map.put("${problem}","!bad-bad-issue!");
        word.replace(map);
        word.save();
    }
     */
}
