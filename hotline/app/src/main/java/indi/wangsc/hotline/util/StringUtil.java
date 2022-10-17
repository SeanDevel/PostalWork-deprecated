package indi.wangsc.hotline.util;

import java.util.StringTokenizer;

public class StringUtil {
    public static String[] splitByCommonDelimiter(String str) {
        StringBuilder formattedStr = new StringBuilder();
        String delimiter = " ,，/、。|()（）【】][";
        StringTokenizer st = new StringTokenizer(str, delimiter);
        while (st.hasMoreTokens()) {
            formattedStr.append(st.nextToken()).append(" ");
        }
        return formattedStr.toString().split(" ");
    }
}