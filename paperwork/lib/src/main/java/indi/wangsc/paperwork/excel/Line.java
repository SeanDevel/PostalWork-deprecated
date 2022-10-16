package indi.wangsc.paperwork.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
    private int rowAt;
    private Map<Integer, String> data;

    public Line(Map<Integer, String> data) {
        this.data = data;
    }

    public Line(int rowNumber, Map<Integer, String> data) {
        this.rowAt = rowNumber;
        this.data = data;
    }

    public Line(List<String> data) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            map.put(i, data.get(i));
        }
        this.data = map;
    }

    public Line(int rowAt, List<String> data) {
        this(data);
        this.rowAt = rowAt;
    }

    public int getRowAt() {
        return rowAt;
    }

    public void setRowAt(int rowAt) {
        this.rowAt = rowAt;
    }

    public Map<Integer, String> getData() {
        return data;
    }

    public void setData(Map<Integer, String> data) {
        this.data = data;
    }
}
