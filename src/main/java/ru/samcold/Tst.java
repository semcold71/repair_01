package ru.samcold;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.samcold.domain.Customer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tst {
    private String str;

    public Tst(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public static void main(String[] args) {
        IntStream stream = IntStream.rangeClosed(1,10);
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3));
        ObservableList<Integer> list = FXCollections.observableArrayList(IntStream.rangeClosed(1,10).boxed().collect(Collectors.toList()));
        list.forEach(System.out::println);
    }
}
