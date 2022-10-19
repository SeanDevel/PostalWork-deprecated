package indi.wangsc.hotline.util;

import org.junit.Test;

public class UtilTest {
    @Test
    public void delimiterStringToIntArray(){
        String[] result=StringUtil.splitByCommonDelimiter("[1,2][3,4][5,6]");
        for (String s : result) {
            System.out.printf("%s, ",s);
        }
    }

    @Test
    public void test(){
        int[] result=IntegerUtil.delimiterStringToIntArray("[1,2][3,4][5,6]");
        for (int i : result) {
            System.out.printf("%d, ",i);
        }
    }
}
