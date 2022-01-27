package ru.samcold;

import org.apache.poi.xwpf.usermodel.*;
import padeg.lib.Padeg;
import ru.samcold.domain.MyDocument;
import ru.samcold.utils.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Tst {

    public static void main(String[] args) {
        StringUtils stringUtils = StringUtils.getInstance();

        String inputStr = "Кран-погрузчик портальный КБ-586П-11, зав.№ 008";

        System.out.println(stringUtils.firstToLower(stringUtils.strToRod(inputStr)));


    }




//    String result = "";
//    result += work;
//        if (!crane.isEmpty()) {
//        String[] arr = crane.split(" ");
//        StringBuilder craneOut = new StringBuilder();
//        for (String c : arr) {
//            c = Padeg.getAppointmentPadeg(c, 2);
//            craneOut.append(c).append(" ");
//        }
//        result += " " + firstToLower(craneOut.toString()).trim();
//    }
//        return result;
}
