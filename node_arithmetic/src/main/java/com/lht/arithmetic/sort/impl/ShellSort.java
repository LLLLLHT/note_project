package com.lht.arithmetic.sort.impl;

import com.lht.arithmetic.sort.Sort;
import com.lht.arithmetic.sort.SortAction;

import java.util.Arrays;

public class ShellSort implements Sort<Integer> {
    /**
     * 增量d：length/2 分组 到 d = 1 ，期间插入排序
     * 68, 72, 90, 52, 57
     * 1：d = 3 68:52，59，57:72 => 52,:68,90,57:72
     * 2：d = 52, 57, 90, 68, 72
     */
//    @Override
//    public void com.lht.arithmetic.sort(Integer[] data) {
//        int d = 3;
//        int temp;
//        for (int k = 0; k < d; k++) {
//            for (int i = k + d; i < data.length; i += d) {
//                temp = data[i];
//                int j = i - d;
//                for (; j >= 0 && temp < data[j]; j -= d) {
//                    data[j + d] = data[j];
//                }
//                data[j + d] = temp;
//            }
//        }
//        System.out.println("第一次：" + Arrays.toString(data));
//
//        d = 1;
//        for (int k = 0; k < d; k++) {
//            for (int i = k + d; i < data.length; i += d) {
//                temp = data[i];
//                int j = i - d;
//                for (; j >= 0 && temp < data[j]; j -= d) {
//                    data[j + d] = data[j];
//                }
//                data[j + d] = temp;
//            }
//        }
//        System.out.println("第二次：" + Arrays.toString(data));
//    }\
    @Override
    public void sort(Integer[] data) {
        double dd = data.length;
        while (true){
            dd = Math.ceil(dd/2);
            int d = (int)dd;
            for (int i = 0; i < d; i++) {
                int temp;
                for (int j = 1; j < data.length; j += d) {
                    temp = data[j];
                    int inedex = j - d;
                    for (; inedex >= 0 && temp < data[inedex]; inedex -= d) {
                        data[inedex + d] = data[inedex];
                    }
                    data[inedex + d] = temp;
                }
            }
            if (d == 1){
                break;
            }
        }

    }
    public static void main(String[] args) {
//        Integer[] data = {68, 72, 90, 52, 57};
        Integer[] data = SortAction.createData(10);
        new ShellSort().sort(data);
        System.out.println("结果：" + Arrays.toString(data));
    }


}
