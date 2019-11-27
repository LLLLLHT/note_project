package com.lht.distributed.lock.zk;

public interface ZkLock {
    void zkLock() throws InterruptedException;
    void zkUnlock();
}