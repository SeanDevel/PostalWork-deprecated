package indi.wangsc.paperwork.excel;

public interface Excel {
    void setSheetAt(int sheetAt);
    void writeGrid(Grid grid);
    void writeLine(Line line);
    void save();
}
