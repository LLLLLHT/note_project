package com.lht.arithmetic;

import java.time.Instant;

/**
 * 1B:符号位：0 (1表示负数、0表示正数)
 * 41B：时间戳位：2的41次方，正整数：0 - 2的41次方 -1即 (2^(41) -1 )/(1000 * 60 * 60 * 24 *365) = 69年
 * 10B：工作进程位：2的10次方=1024：工作机器id: 即可部署在1024个节点：机器ID + 数据中心ID(即 1号机器 1号数据仓库 中)  = 10B、
 * 12B: 序列号位：2的12次方 * 1000 = 409.6万
 */
public class SnowflakeDemo {
    //工作机器id
    private long workerId;
    //数据中心ID
    private long datacenterId;
    //序列在id
    private long sequence;
    //最后时间 - UTC时间
    private volatile long lastTimestamp;
    private static final long NOW_TIME = 754934400000L;
    public SnowflakeDemo(long workerId, long datacenterId) {
        this(workerId, datacenterId, false);
    }
    public SnowflakeDemo(long workerId, long datacenterId, boolean isUseSystemClock) {
        this.sequence = 0L;
        this.lastTimestamp = -1L;
        if (workerId <= 31L && workerId >= 0L) {
            if (datacenterId <= 31L && datacenterId >= 0L) {
                this.workerId = workerId;
                this.datacenterId = datacenterId;
            } else {
                throw new IllegalArgumentException("datacenter Id can't be greater than {} or less than 0");
            }
        } else {
            throw new IllegalArgumentException("worker Id can't be greater than {} or less than 0");
        }
    }

    public synchronized long nextId() {
        long timestamp = Instant.now().toEpochMilli();
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1L & 4095L;
            if (this.sequence == 0L) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }
        this.lastTimestamp = timestamp;
        return timestamp - NOW_TIME << 22 | this.datacenterId << 17 | this.workerId << 12 | this.sequence;
    }


    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for (timestamp = Instant.now().toEpochMilli(); timestamp <= lastTimestamp; timestamp = Instant.now().toEpochMilli()) {
        }
        return timestamp;
    }

}
