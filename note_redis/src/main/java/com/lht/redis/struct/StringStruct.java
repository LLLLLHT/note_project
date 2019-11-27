package com.lht.redis.struct;

import java.util.Arrays;

public class StringStruct {
    private static final int MALLOC_BYTES = 64;
    private static final int MB = 1024 * 1024 / 4;
    //对象头
    HeadStruct headStruct;
    //初始容量：1字节 = 8 位
    int capacity;
    //长度：1字节 = 8 位
    int length;
    //标记(标记结束索引)：1字节 = 8 位
    int flag;
    //内容
    char[] content = new char[length];
    // *特殊：末尾追加：1字节 = 8 位
    private static final char GLIBC = '\0';
    public StringStruct(HeadStruct headStruct, int capacity, String data) {
        char[] chars = data.toCharArray();
        this.headStruct = headStruct;
        this.capacity = capacity;
        this.length = chars.length;
        this.flag = 0;
        this.content = new char[capacity];
        for (int i = 0; i < chars.length; i++) {
            content[flag++] = chars[i];
        }
        length = flag;
    }
    enum Store {
        EMB, STS;
    }
    public void append(char[] data) {
        sdsMakeRoomFor(data.length);
        for (int i = 0; i < data.length; i++) {
            content[flag++] = data[i];
        }
        length = flag;

    }

    //扩容
    private int sdsMakeRoomFor(int dataLength) {
        if (capacity < flag + dataLength) {
            if (capacity < MB) {
                capacity = capacity * 2;
            } else {
                capacity += MB;
            }
            char[] chars = new char[capacity];
            System.arraycopy(content, 0, chars, 0, content.length);
            content = chars;
        }
        return capacity;
    }

    //获取存储格式
    public Store storeMode() {
        int headLength = 4 + 4 + 32 + 24 + 64;
        int stringLength = 8 + 8 + 8 + 8;
        //即 <= 44 EMB存储
        if (length <= MALLOC_BYTES - (headLength + stringLength) / 8) {
            return Store.EMB;
        }
        //反之STS存储
        return Store.STS;
    }

    public static void main(String[] args) {
        StringStruct stringStruct = new StringStruct(HeadStruct.newHeadStruct(HeadStruct.RedisType.STRING), 10, "abc");
        System.out.println("当前数组：" + Arrays.toString(stringStruct.content));
        stringStruct.append("def".toCharArray());
        System.out.println("当前数组：" + Arrays.toString(stringStruct.content));
        System.out.println("当前数组存储格式：" + stringStruct.storeMode().name());
        for (int i = 0; i < 4; i++) {
            stringStruct.append("ghijklmnopq".toCharArray());
        }
        System.out.println("当前数组：" + Arrays.toString(stringStruct.content));
        System.out.println("当前数组存储格式：" + stringStruct.storeMode().name());
    }
}
