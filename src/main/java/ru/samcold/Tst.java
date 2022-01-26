package ru.samcold;

import org.apache.poi.xwpf.usermodel.*;
import ru.samcold.domain.MyDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Tst {

    public static void main(String[] args) {
        tst();
    }

    private static void tst() {

        XWPFDocument doc = null;

        try {
            doc = new XWPFDocument(Main.class.getResourceAsStream("/docx/blank.docx"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream("output.docx")) {
            doc.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
