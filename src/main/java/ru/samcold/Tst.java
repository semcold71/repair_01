package ru.samcold;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class Tst {

    public static void main(String[] args) throws IOException {

        XWPFDocument document = new XWPFDocument(Main.class.getResourceAsStream("/docx/blank1.docx"));
        XWPFParagraph paragraph = document.getParagraphs().get(0);
        for (XWPFRun run : paragraph.getRuns()) {
            if (run.getText(0) != null & run.getText(0).equals("tu")) {
                String text = run.getText(0);
                text = text.replace(text, "SAM");
                run.setText(text,0);
                break;
            }
        }
        System.out.println(paragraph.getText());

    }
}
