package com.lht.arithmetic.sort.impl;

import com.lht.arithmetic.sort.Sort;
import com.lht.arithmetic.sort.SortAction;

import java.util.Arrays;

public class InsertSort implements Sort<Integer> {
    /**
     * 前 => 后 指定数 后 => 前比较，寻找合适索引交换位置
     *     68 57 59 52
     *  1: 57 68 59 52
     *  2：57 59 68 52
     *  3：52 57 59 68
     */
    //    @Override
//    public void com.lht.arithmetic.sort(Integer[] data) {
//        int index;
//        int temp = data[1];
//        for (index = 0; index >= 0 && temp < data[index]; index--) {
//            data[index + 1] = data[index];
//        }
//        data[index + 1] = temp;
//        System.out.println("第一次：" + Arrays.toString(data));
//
//        temp = data[2];
//        for (index = 1; index >= 0 && temp < data[index]; index--) {
//            data[index + 1] = data[index];
//        }
//        data[index + 1] = temp;
//        System.out.println("第二次：" + Arrays.toString(data));
//
//        temp = data[3];
//        for (index = 2; index >= 0 && temp < data[index]; index--) {
//            data[index + 1] = data[index];
//        }
//        data[index + 1] = temp;
//        System.out.println("第三次：" + Arrays.toString(data));
//    }
    @Override
    public void sort(Integer[] data) {
        int temp;
        for (int i = 1; i < data.length; i++) {
            temp = data[i];
            int j = i - 1;
            for (; j >= 0 && temp < data[j] ; j--) {
                data[j + 1] = data[j];
            }
            data[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] data = SortAction.createData(10);
        new InsertSort().sort(data);
        System.out.println("结果：" + Arrays.toString(data));
    }


}
