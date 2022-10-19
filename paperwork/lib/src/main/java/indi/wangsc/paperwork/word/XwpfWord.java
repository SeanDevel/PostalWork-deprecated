package indi.wangsc.paperwork.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XwpfWord implements Word {
    private String filePath;
    private File wordFile;
    private XWPFDocument xwpfDocument;
    private List<XWPFTable> tableList;

    public XwpfWord() {
        xwpfDocument = new XWPFDocument();
    }

    public XwpfWord(String filePath) {
        this.filePath = filePath;
        this.wordFile = new File(filePath);
        if (wordFile.exists()) {
            try {
                InputStream inputStream=new FileInputStream(wordFile);
                this.xwpfDocument = new XWPFDocument(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
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

    public XWPFDocument getXwpfDocument() {
        return xwpfDocument;
    }

    public void setXwpfDocument(XWPFDocument xwpfDocument) {
        this.xwpfDocument = xwpfDocument;
    }

    public List<XWPFTable> getTableList() {
        Iterator<IBodyElement> docElementsIterator = this.xwpfDocument.getBodyElementsIterator();
        while (docElementsIterator.hasNext()) {
            IBodyElement docElement = docElementsIterator.next();
            if ("TABLE".equalsIgnoreCase(docElement.getElementType().name())) {
                this.tableList = docElement.getBody().getTables();
            }
        }
        return tableList;
    }

    public void setTableList(List<XWPFTable> tableList) {
        this.tableList = tableList;
    }

    public void writeParagraph(String text, String fontFamily, double fontSize) {
        XWPFParagraph paragraph = this.xwpfDocument.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
    }

    public void replace(Map<String, String> token) {
        for (var paragraph : this.xwpfDocument.getParagraphs()) {
            for (var run : paragraph.getRuns()) {
                String text = run.getText(run.getTextPosition());
                for (var entry : token.entrySet()) {
                    text = text.replace(entry.getKey(), entry.getValue());
                }
                run.setText(text, 0);
            }
        }
    }

    @Override
    public List<String> getTableContent(int tableAt, int[] locations) {
        XWPFTable table = getTableList().get(tableAt);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < locations.length - 1; i += 2) {
            data.add(table.getRow(locations[i]).getCell(locations[i + 1]).getText().trim());
        }
        return data;
    }

    @Override
    public void save() {
        try {
            if(!wordFile.exists()){
                wordFile.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(wordFile);
            xwpfDocument.write(out);
            xwpfDocument.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
