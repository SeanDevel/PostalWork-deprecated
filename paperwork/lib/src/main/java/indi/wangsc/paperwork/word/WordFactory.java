package indi.wangsc.paperwork.word;

import indi.wangsc.paperwork.util.FileType;

public class WordFactory {

    public static Word create(String filePath) {
        Word word = null;
        String fileType = FileType.judge(filePath);
        if (fileType.equals("Office 2003")) {
            word = new HwpfWord(filePath);
        } else if (fileType.equals("Office 2007")) {
            word = new XwpfWord(filePath);
        }
        return word;
    }
}
