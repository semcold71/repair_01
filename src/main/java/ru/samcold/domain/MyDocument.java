package ru.samcold.domain;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class MyDocument {
    // region sinleton
    private static MyDocument instance;

    private MyDocument(){}

    public static MyDocument getInstance(){
        if(instance == null){
            instance = new MyDocument();
        }
        return instance;
    }
    // endregion

    private XWPFDocument template;
    private XWPFDocument output;

    public XWPFDocument getTemplate() {
        return template;
    }

    public void setTemplate(String path) throws IOException {
        this.template = new XWPFDocument(new FileInputStream(path));
    }

    public XWPFDocument getOutput() {
        return output;
    }

    private void setOutput() throws IOException {
        //this.output = new XWPFDocument(MyDocument.class.getResourceAsStream("output.docx"));
    }

    public void save() throws IOException {
        setOutput();
        FileOutputStream fos = new FileOutputStream("output.docx");
        output.write(fos);
    }

    public void save2() throws IOException {
        FileOutputStream fos = new FileOutputStream("template2.docx");
        template.write(fos);
    }

}
