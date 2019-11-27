package com.lht.redis.struct;

public class HeadStruct {
    public enum RedisType {
        STRING, LIST, HASH, SET, ZSET;
    }
    //类型 4位
    RedisType type;
    //编码 4位
    int enCoding;
    //引用计数 4字节 = 32位
    int refCount;
    //过期算法 24位
    int lru;
    //对象内存地址指针 8字节 = 64位
    Object ptr;
    public static final HeadStruct newHeadStruct(RedisType redisType) {
        return new HeadStruct(redisType);
    }
    private HeadStruct(RedisType redisType) {
        this.type = redisType;
    }
}