package ru.samcold;

import ru.samcold.domain.Customer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tst {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("cat", "dog", "elephant", "RTK -0hg", "fox", "rabbit", "duck"));
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            if (item.contains("RTK")) {
                System.out.println(i);
                System.out.println(item);
                break;
            }

        }
    }
}
