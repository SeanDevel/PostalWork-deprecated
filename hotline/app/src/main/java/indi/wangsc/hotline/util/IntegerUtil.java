package indi.wangsc.hotline.util;

public class IntegerUtil {
    public static int[] stringArrayToNoOffsetIntArray(String[] strArr) {
        int[] result = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            try {
                result[i] = Integer.parseInt(strArr[i]) - 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int[] delimiterStringToIntArray(String str) {
        String[] strings = StringUtil.splitByCommonDelimiter(str);
        return stringArrayToNoOffsetIntArray(strings);
    }
}