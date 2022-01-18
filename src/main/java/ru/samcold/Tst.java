package ru.samcold;

import ru.samcold.domain.Customer;

import java.lang.reflect.Field;

public class Tst {
    public static void main(String[] args) {
        String s = " hggh    gjjg jj  gggkk ";

        s = s.replaceAll("\\s+", " ").trim();

//        while (s.contains("  ")) {
//            s = s.replace("  ", " ");
//        }

        //s = s.trim();

        System.out.println(s);
    }
}
