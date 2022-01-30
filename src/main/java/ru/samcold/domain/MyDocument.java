package ru.samcold.domain;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyDocument {
    // region sinleton
    private static MyDocument instance;

    private MyDocument() {
    }

    public static MyDocument getInstance() {
        if (instance == null) {
            instance = new MyDocument();
        }
        return instance;
    }
    // endregion

    private XWPFDocument externalDocument;
    private XWPFDocument outputDocument;
    private XWPFDocument conclusionDocument;

    public XWPFDocument getExternalDocument() {
        return externalDocument;
    }

    public void setExternalDocument(String path) throws IOException {
        this.externalDocument = new XWPFDocument(new FileInputStream(path));
    }

    public XWPFDocument getOutputDocument() {
        return outputDocument;
    }

    public void setOutputDocument() throws IOException {
        outputDocument = new XWPFDocument(MyDocument.class.getResourceAsStream("/docx/blank.docx"));

    }

    public XWPFDocument getConclusionDocument() {
        return conclusionDocument;
    }

    public void setConclusionDocument(XWPFDocument conclusionDocument) {
        this.conclusionDocument = conclusionDocument;
    }

    public void save() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("output.docx")) {
            outputDocument.write(fos);
            System.out.println("Save succeeded.");
        } catch (IOException e) {
            System.out.println("Save failed!");
            e.printStackTrace();
        }
    }

    public void saveConclusion() throws IOException {
        try(FileOutputStream fos = new FileOutputStream("conclusion.docx")) {
            conclusionDocument.write(fos);
            System.out.println("Save conclusion succeeded.");
        } catch (IOException e) {
            System.out.println("Save conclusion failed!");
            e.printStackTrace();
        }
    }
}
