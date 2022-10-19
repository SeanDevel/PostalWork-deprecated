package indi.wangsc.paperwork;

import indi.wangsc.paperwork.word.Word;
import indi.wangsc.paperwork.word.WordFactory;
import org.junit.jupiter.api.Test;

public class WordTest {
    private final String filePath = "D:\\_test\\test.docx";

    @Test
    public void getTableContent() {
        Word word = WordFactory.get(filePath);
        for (var cellText : word.getTableContent(0, new int[]{0, 0, 1, 1})) {
            System.out.println(cellText);
        }
    }


    @Test
    public void writeParagraph(){
        Word word = WordFactory.get(filePath);
        word.writeParagraph("测试写入 Test writing.","仿宋_GB2312",15.75);
        word.save();
    }
/*
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
