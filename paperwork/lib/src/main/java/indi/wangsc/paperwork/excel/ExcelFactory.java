package indi.wangsc.paperwork.excel;

import indi.wangsc.paperwork.util.FileType;

public class ExcelFactory {
    public static Excel create(String filePath) {
        Excel excel = null;
        String fileType = FileType.judge(filePath);
        if (fileType.equals("Office 2007")) {
            excel = new XssfExcel(filePath);
        }
        return excel;
    }
}
