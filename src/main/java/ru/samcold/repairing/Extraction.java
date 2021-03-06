package ru.samcold.repairing;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import ru.samcold.domain.Rtk;
import ru.samcold.domain.Crane;
import ru.samcold.domain.Customer;
import ru.samcold.domain.MyDocument;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Extraction {
    // region singleton
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

    // extraction
    public Rtk extractRtk() {
        Rtk rtk = new Rtk();

        // РТК
        rtk.numberProperty().set(foundRTK());

        // Договор
        rtk.contractNumberProperty().set(
                paragraphToLine(myDocument.getExternalDocument().getTables().get(2).getRow(0).getCell(1).getParagraphs()));
        rtk.contractDateProperty().set(
                paragraphToLine(myDocument.getExternalDocument().getTables().get(2).getRow(1).getCell(1).getParagraphs()));

        // Приказ
        rtk.orderNumberProperty().set(
                paragraphToLine(myDocument.getExternalDocument().getTables().get(3).getRow(0).getCell(1).getParagraphs()));
        rtk.orderDateProperty().set(
                paragraphToLine(myDocument.getExternalDocument().getTables().get(3).getRow(1).getCell(1).getParagraphs()));

        // Дата и место проведения технического диагностирования
        rtk.periodProperty().set(
                paragraphToLine(myDocument.getExternalDocument().getTables().get(4).getRow(0).getCell(0).getParagraphs()));
        rtk.locationProperty().set(
                paragraphToLine(myDocument.getExternalDocument().getTables().get(4).getRow(0).getCell(1).getParagraphs()));

        // Дата следующего ТД
        Map<Integer, String> rusMonths = new HashMap<>();
        rusMonths.put(1, "Январь");
        rusMonths.put(2, "Февраль");
        rusMonths.put(3, "Март");
        rusMonths.put(4, "Апрель");
        rusMonths.put(5, "Май");
        rusMonths.put(6, "Июнь");
        rusMonths.put(7, "Июль");
        rusMonths.put(8, "Август");
        rusMonths.put(9, "Сентябрь");
        rusMonths.put(10, "Октябрь");
        rusMonths.put(11, "Ноябрь");
        rusMonths.put(12, "Декабрь");

        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear() + 2;

        String nextDateStr = rusMonths.get(month) + " " + year;
        rtk.nextProperty().set(nextDateStr);

        return rtk;
    }

    public Customer extractCustomer(Customer customer) throws IllegalAccessException {
        Class<?> cls = customer.getClass();
        Field[] fields = cls.getDeclaredFields();

        List<XWPFTableRow> rows = myDocument.getExternalDocument().getTables().get(1).getRows();
        for (int i = 0; i < fields.length; i++) {
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

    public Crane extractCrane(Crane crane) throws IllegalAccessException {

        Class<?> cls = crane.getClass();
        Field[] fields = cls.getDeclaredFields();

        List<XWPFTableRow> rows = myDocument.getExternalDocument().getTables().get(7).getRows();
        for (int i = 0; i < fields.length; i++) {
            int cellsCount = rows.get(i).getTableCells().size() - 1;
            List<XWPFParagraph> paras = rows.get(i).getCell(cellsCount).getParagraphs();
            String s = paragraphToLine(paras);
            if (fields[i].getType().isAssignableFrom(StringProperty.class)) {
                fields[i].setAccessible(true);
                fields[i].set(crane, new SimpleStringProperty(s));
            }
        }

        return crane;
    }

    // utils
    public String foundRTK() {
        int i = 0;
        String foundText;

        while (true) {
            foundText = myDocument.getExternalDocument().getParagraphs().get(i).getText();
            i++;
            if (foundText.startsWith("РТК")) {
                foundText = foundText.replaceAll("\\D+", "");
                break;
            }
            if (i >= myDocument.getExternalDocument().getParagraphs().size()) {
                foundText = "Не найдено";
                break;
            }
        }
        return foundText;
    }

    private String paragraphToLine(List<XWPFParagraph> paragraphs) {
        String str = "";

        for (XWPFParagraph p : paragraphs) {
            str += p.getParagraphText() + " ";
        }

        str = str.replaceAll("\\s+", " ").trim();

        return str;
    }


}
