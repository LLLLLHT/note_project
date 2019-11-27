package com.lht.arithmetic.sort.impl;

import com.lht.arithmetic.sort.Sort;
import com.lht.arithmetic.sort.SortAction;

import java.util.Arrays;

public class BubblingSort implements Sort<Integer> {
    /**
     * 前 => 后 相邻两数 依次 比较，较大数下沉，较小上冒
     *     68 57 59 52
     *  1: 57 68 59 52
     *  2：57 59 68 52
     *  3：52 57 59 68
     */
//    @Override
//    public void com.lht.arithmetic.sort(Integer[] data) {
//        for (int i = 0; i < data.length -1; i++) {
//            if (data[i] < data[i + 1]){
//                data[i] ^= data[i + 1];
//                data[i + 1] ^= data[i];
//                data[i] ^= data[i + 1];
//            }
//        }
//        System.out.println("第一次：" + Arrays.toString(data));
//
//        for (int i = 0; i < data.length -1-1; i++) {
//            if (data[i] < data[i + 1]){
//                data[i] ^= data[i + 1];
//                data[i + 1] ^= data[i];
//                data[i] ^= data[i + 1];
//            }
//        }
//        System.out.println("第二次：" + Arrays.toString(data));
//
//        for (int i = 0; i < data.length -1-1-1; i++) {
//            if (data[i] < data[i + 1]){
//                data[i] ^= data[i + 1];
//                data[i + 1] ^= data[i];
//                data[i] ^= data[i + 1];
//            }
//        }
//        System.out.println("第三次：" + Arrays.toString(data));
//
//        for (int i = 0; i < data.length -1-1-1-1; i++) {
//            if (data[i] < data[i + 1]){
//                data[i] ^= data[i + 1];
//                data[i + 1] ^= data[i];
//                data[i] ^= data[i + 1];
//            }
//        }
//        System.out.println("第四次：" + Arrays.toString(data));
//    }

    @Override
    public void sort(Integer[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] < data[j + 1]) {
                    data[j] ^= data[j + 1];
                    data[j + 1] ^= data[j];
                    data[j] ^= data[j + 1];
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] data = SortAction.createData(10);
        new BubblingSort().sort(data);
        System.out.println("结果：" + Arrays.toString(data));
    }


}
