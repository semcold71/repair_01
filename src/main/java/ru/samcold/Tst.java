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

        XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();

        XWPFHeader header = headerFooterPolicy.getFirstPageHeader();
        XWPFFooter footer = headerFooterPolicy.getDefaultFooter();

        XWPFParagraph hPara = header.getParagraphs().get(0);
        XWPFParagraph fPara = footer.getParagraphs().get(0);

        XWPFTable table = fPara.getBody().getTableArray(0);

        XWPFRun craneRun = table.getRow(0).getCell(0).getParagraphs().get(0).getRuns().get(0);
        String craneStr = craneRun.getText(0);
        craneStr = craneStr.replace("crane", "new crane");
        craneRun.setText(craneStr,0);

        XWPFParagraph tuPara = table.getRow(0).getCell(1).getParagraphs().get(0);
        for (XWPFRun tuRun : tuPara.getRuns()) {
            if (tuRun.getText(0) != null && !tuRun.getText(0).isEmpty()) {
                String tuStr = tuRun.getText(0);
                if (tuStr.equals("TU")) {
                    tuStr = tuStr.replace(tuStr, "new TU");
                    tuRun.setText(tuStr, 0);
                }
            }
        }

        FileOutputStream fos = new FileOutputStream("output.docx");
        document.write(fos);
        fos.close();


        // create header start
//        XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
//
//        XWPFParagraph paragraph = header.createParagraph();
//        paragraph.setAlignment(ParagraphAlignment.CENTER);
//
//        XWPFRun run = paragraph.createRun();
//        run.setText("Header");
//
//        // create footer start
//        XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
//
//        paragraph = footer.createParagraph();
//        paragraph.setAlignment(ParagraphAlignment.CENTER);
//
//        run = paragraph.createRun();
//        run.setText("Footer");

//        CTSectPr sectPr = document.getDocument().getBody().getSectPr();
//        if (sectPr == null) sectPr = document.getDocument().getBody().addNewSectPr();
//        CTPageMar pageMar = sectPr.getPgMar();
//        if (pageMar == null) pageMar = sectPr.addNewPgMar();
//        pageMar.setLeft(BigInteger.valueOf(720)); //720 TWentieths of an Inch Point (Twips) = 720/20 = 36 pt = 36/72 = 0.5"
//        pageMar.setRight(BigInteger.valueOf(720));
//        pageMar.setTop(BigInteger.valueOf(1440)); //1440 Twips = 1440/20 = 72 pt = 72/72 = 1"
//        pageMar.setBottom(BigInteger.valueOf(1440));
//
//        pageMar.setHeader(BigInteger.valueOf(908)); //45.4 pt * 20 = 908 = 45.4 pt header from top
//        pageMar.setFooter(BigInteger.valueOf(568)); //28.4 pt * 20 = 568 = 28.4 pt footer from bottom

        FileOutputStream out = new FileOutputStream("CreateWordHeaderFooterTopBottom.docx");
        document.write(out);
        out.close();
        document.close();
    }
}
