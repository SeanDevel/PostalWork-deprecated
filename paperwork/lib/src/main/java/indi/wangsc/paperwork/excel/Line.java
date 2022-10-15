package indi.wangsc.paperwork.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
    private int rowAt;
    private Map<Integer, Object> data;

    public Line(Map<Integer, Object> data) {
        this.data = data;
    }

    public Line(int rowNumber, Map<Integer, Object> data) {
        this.rowAt = rowNumber;
        this.data = data;
    }

    public Line(List<Object> data) {
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            map.put(i, data.get(i));
        }
        this.data = map;
    }

    public Line(int rowAt, List<Object> data) {
        this(data);
        this.rowAt = rowAt;
    }

    public int getRowAt() {
        return rowAt;
    }

    public void setRowAt(int rowAt) {
        this.rowAt = rowAt;
    }

    public Map<Integer, Object> getData() {
        return data;
    }

    public void setData(Map<Integer, Object> data) {
        this.data = data;
    }
}
