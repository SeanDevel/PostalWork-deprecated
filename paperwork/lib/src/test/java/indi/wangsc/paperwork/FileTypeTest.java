package indi.wangsc.paperwork;

import indi.wangsc.paperwork.util.FileType;
import org.junit.jupiter.api.Test;

public class FileTypeTest {
    @Test void judge(){
        String result= FileType.judge("D:\\_test\\test.doc");
        System.out.println(result);
    }
}
