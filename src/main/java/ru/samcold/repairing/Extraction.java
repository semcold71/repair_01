package ru.samcold.repairing;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import ru.samcold.domain.Act;
import ru.samcold.domain.Customer;
import ru.samcold.domain.MyDocument;

import java.lang.reflect.Field;
import java.util.List;

public class Extraction {
    // region sinleton
    private static Extraction instance;

    private Extraction() {
    }

    public static Extraction getInstance() {
        if (instance == null) {
            instance = new Extraction();
        }
        return instance;
    }
    // endregion

    private final MyDocument myDocument = MyDocument.getInstance();

    public Act extractAct() {
        Act act = new Act();

        // РТК
        act.rtkProperty().set(foundRTK());

        // Договор
        act.contractNumberProperty().set(
                paragraphToLine(myDocument.getTemplate().getTables().get(2).getRow(1).getCell(1).getParagraphs()));
        act.contractDateProperty().set(
                paragraphToLine(myDocument.getTemplate().getTables().get(2).getRow(1).getCell(1).getParagraphs()));

        // Приказ
        act.orderNumberProperty().set(
                paragraphToLine(myDocument.getTemplate().getTables().get(3).getRow(1).getCell(1).getParagraphs()));
        act.orderDateProperty().set(
                paragraphToLine(myDocument.getTemplate().getTables().get(3).getRow(1).getCell(1).getParagraphs()));

        // Дата и место проведения технического диагностирования
        act.locationProperty().set(
                paragraphToLine(myDocument.getTemplate().getTables().get(4).getRow(0).getCell(0).getParagraphs()));
        act.periodProperty().set(
                paragraphToLine(myDocument.getTemplate().getTables().get(4).getRow(0).getCell(1).getParagraphs()));

        return act;
    }

    public Customer extractCustomer(Customer customer) throws IllegalAccessException {
        Class<?> cls = customer.getClass();
        Field[] fields = cls.getDeclaredFields();

        List<XWPFTableRow> rows = myDocument.getTemplate().getTables().get(1).getRows();
        for (int i = 0; i < rows.size(); i++) {
            int cellsCount = rows.get(i).getTableCells().size() - 1;
            List<XWPFParagraph> paras = rows.get(i).getCell(cellsCount).getParagraphs();
            String s = paragraphToLine(paras);
            if (fields[i].getType().isAssignableFrom(StringProperty.class)) {
                fields[i].setAccessible(true);
                fields[i].set(customer, new SimpleStringProperty(s));
            }
        }

        return customer;
    }

    private String paragraphToLine(List<XWPFParagraph> paragraphs) {
        String str = "";

        for (XWPFParagraph p : paragraphs) {
            str += p.getParagraphText() + " ";
        }

        str = str.replaceAll("\\s+", " ").trim();

        return str;
    }

    public String foundRTK() {
        int i = 0;
        String foundText;

        while (true) {
            foundText = myDocument.getTemplate().getParagraphs().get(i).getText();
            i++;
            if (foundText.contains("РТК")) {
                foundText = foundText.replaceAll("\\D+", "");
                break;
            }
            if (i >= myDocument.getTemplate().getParagraphs().size()) {
                foundText = "Не найдено";
                break;
            }
        }
        return foundText;
    }

}
