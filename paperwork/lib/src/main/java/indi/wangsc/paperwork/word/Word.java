package indi.wangsc.paperwork.word;

import java.util.List;
import java.util.Map;

public interface Word {
    List<String> getTableContent(int tableAt, int[] locations);

    void writeParagraph(String text,String fontFamily,double fontSize);
    void replace(Map<String,String> map);
    void save();
}
