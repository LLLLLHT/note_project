package com.lht.arithmetic.sort.impl;

import com.lht.arithmetic.sort.Sort;
import com.lht.arithmetic.sort.SortAction;

import java.util.Arrays;

public class SelectSort implements Sort<Integer> {
    @Override
    public void sort(Integer[] data) {
        int temp;
        int index;
        boolean flag = false;
        for (int i = 0; i < data.length -1; i++) {
            index = i;
            temp = data[i];
            for (int j = i + 1; j < data.length ; j++) {
                if (data[j] < temp){
                    temp = data[j];
                    index = j;
                    flag = true;
                }
            }
            if (flag){
                data[i] ^= data[index];
                data[index] ^= data[i];
                data[i] ^= data[index];
                flag = false;
            }
        }
    }

    /**
     * 前 => 后 循环选择最小数与索引0交换位置 => 最后(倒数2、1比较)
     * 68, 72, 90, 52, 57
     * 1、52, 72, 90, 68, 57
     * 2、52, 57, 90, 68, 72
     * 3、52, 57, 68, 90, 72
     * 4、52, 57, 68, 72, 90
     */
//    @Override
//    public void sort(Integer[] data) {
//        int index = 0;
//        int temp = data[index];
//        for (int i = 1; i < data.length; i++) {
//            if (data[i] < temp){
//                temp = data[i];
//                index = i;
//            }
//        }
//        data[0] ^= data[index];
//        data[index] ^= data[0];
//        data[0] ^= data[index];
//        System.out.println("第一次：" + Arrays.toString(data));
//
//        index = 1;
//        temp = data[index];;
//        for (int i = 2; i < data.length; i++) {
//            if (data[i] < temp){
//                temp = data[i];
//                index = i;
//            }
//        }
//        data[1] ^= data[index];
//        data[index] ^= data[1];
//        data[1] ^= data[index];
//        System.out.println("第二次：" + Arrays.toString(data));
//
//        index = 2;
//        temp = data[index];;
//        for (int i = 3; i < data.length; i++) {
//            if (data[i] < temp){
//                temp = data[i];
//                index = i;
//            }
//        }
//        data[2] ^= data[index];
//        data[index] ^= data[2];
//        data[2] ^= data[index];
//        System.out.println("第三次：" + Arrays.toString(data));
//
//        index = 3;
//        temp = data[index];;
//        for (int i = 4; i < data.length; i++) {
//            if (data[i] < temp){
//                temp = data[i];
//                index = i;
//            }
//        }
//        data[3] ^= data[index];
//        data[index] ^= data[3];
//        data[3] ^= data[index];
//        System.out.println("第四次：" + Arrays.toString(data));
//    }


    public static void main(String[] args) {
//        Integer[] data = {68, 72, 90, 52, 57};
        Integer[] data = SortAction.createData(10);
        new SelectSort().sort(data);
        System.out.println("结果：" + Arrays.toString(data));
    }
}
