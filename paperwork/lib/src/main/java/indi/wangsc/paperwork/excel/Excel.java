package indi.wangsc.paperwork.excel;

import java.util.List;

public interface Excel {
    void setSheetAt(int sheetAt);
    void writeGrid(Grid grid);
    void writeLine(Line line);
    void writeLines(int rowAt, List<Line> linesList);
    void save();
}
