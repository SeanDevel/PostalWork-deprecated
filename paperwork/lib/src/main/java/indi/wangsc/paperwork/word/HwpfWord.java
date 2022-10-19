package indi.wangsc.paperwork.word;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HwpfWord implements Word {
    private String filePath;
    private File wordFile;
    private HWPFDocument hwpfDocument;
    private List<Table> tableList;

    public HwpfWord() {
    }

    public HwpfWord(String filePath) {
        this.filePath = filePath;
        this.wordFile = new File(filePath);
        if (wordFile.exists()) {
            try {
                InputStream inputStream=new FileInputStream(wordFile);
                this.hwpfDocument = new HWPFDocument(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getWordFile() {
        return wordFile;
    }

    public void setWordFile(File wordFile) {
        this.wordFile = wordFile;
    }

    public HWPFDocument getHwpfDocument() {
        return hwpfDocument;
    }

    public void setHwpfDocument(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
    }

    public List<Table> getTableList() {
        List<Table> tableList = new ArrayList<>();
        Range range = hwpfDocument.getRange();
        TableIterator tableIterator = new TableIterator(range);
        while (tableIterator.hasNext()) {
            Table t = tableIterator.next();
            tableList.add(t);
        }
        this.tableList=tableList;
        return tableList;
        //return this.hwpfDocument.getListTables();
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    @Override
    public List<String> getTableContent(int tableAt, int[] locations) {
        Table table = getTableList().get(tableAt);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < locations.length - 1; i += 2) {
            data.add(table.getRow(locations[i]).getCell(locations[i + 1]).text().trim());
        }
        return data;
    }

    @Override
    public void writeParagraph(String text, String fontFamily, double fontSize) {

    }

    @Override
    public void replace(Map<String, String> map) {

    }

    public Boolean isFileExists(){
        return this.wordFile.exists();
    }

    @Override
    public void save() {
        try {
//            wordFile.createNewFile();
//            POIFSFileSystem poifsFileSystem=new POIFSFileSystem(wordFile);
//            this.hwpfDocument=new HWPFDocument(new FileInputStream(wordFile));
//            hwpfDocument.write(new FileOutputStream(wordFile));
            FileOutputStream out = new FileOutputStream(filePath);
            hwpfDocument.write(out);
            hwpfDocument.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
