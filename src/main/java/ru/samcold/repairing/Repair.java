package ru.samcold.repairing;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import ru.samcold.domain.Customer;
import ru.samcold.domain.MyDocument;

import java.lang.reflect.Field;
import java.util.List;

public class Repair {
    // region sinleton
    private static Repair instance;

    private Repair() {
    }

    public static Repair getInstance() {
        if (instance == null) {
            instance = new Repair();
        }
        return instance;
    }
    // endregion

    private final MyDocument myDocument = MyDocument.getInstance();

    public Customer extractCustomer() throws IllegalAccessException {
        Customer customer = new Customer();

        Class<?> cls = customer.getClass();
        Field[] fields = cls.getDeclaredFields();

        List<XWPFTableRow> rows = myDocument.getTemplate().getTables().get(1).getRows();
        for (int i = 0; i < rows.size(); i++) {
            int cellsCount = rows.get(i).getTableCells().size() - 1;
            List<XWPFParagraph> paras = rows.get(i).getCell(cellsCount).getParagraphs();
            String s = paragraphToLine(paras);
            fields[i].setAccessible(true);
            fields[i].set(customer, s);
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

}
