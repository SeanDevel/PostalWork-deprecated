package indi.wangsc.paperwork.excel;

public class Grid {
    private int rowAt;
    private int colAt;
    private Object data;

    public Grid(int rowAt, int colAt, Object data) {
        this.rowAt = rowAt;
        this.colAt = colAt;
        this.data = data;
    }

    public int getRowAt() {
        return rowAt;
    }

    public void setRowAt(int rowAt) {
        this.rowAt = rowAt;
    }

    public int getColAt() {
        return colAt;
    }

    public void setColAt(int colAt) {
        this.colAt = colAt;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
