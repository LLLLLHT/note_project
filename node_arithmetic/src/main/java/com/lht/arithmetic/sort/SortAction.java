package com.lht.arithmetic.sort;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class SortAction {
    Sort<Integer> sort;
    private static final int COUNT = (int)10e4;
    private static final Random RANDOM = new Random();
    public SortAction(Sort<Integer> sort) {
        this.sort = sort;
    }

    public void setSort(Sort<Integer> sort) {
        this.sort = sort;
    }
    public static Integer[] createData(Integer count){
        Integer[] data = new Integer[count];
        for (int i = 0; i < count; i++) {
            data[i] = (int)(Math.random() * (count * 10e2));
        }
        return data;
    }
    public void testSort() {
        Instant begin = Instant.now();

        sort.sort(SortAction.createData(COUNT));
        Instant end = Instant.now();
        System.out.println("时间：" + Duration.between(begin,end));
    }
}
