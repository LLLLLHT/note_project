package com.lht.distributed.lock.zk.impl;

import com.lht.distributed.lock.zk.ZkConfig;
import com.lht.distributed.lock.zk.ZkLock;
import org.I0Itec.zkclient.ZkClient;
import java.util.Optional;

public abstract class ZkAbstractLock implements ZkLock {
    protected final ZkClient zkClient = new ZkClient(ZkConfig.ZK_SERVER,ZkConfig.TIME_OUT);
    public abstract boolean tryZkLock();
    public abstract void waitZkLock() throws InterruptedException;
    @Override
    public void zkLock() throws InterruptedException {
        if (!tryZkLock()){
            waitZkLock();
            zkLock();
        }
    }
    @Override
    public void zkUnlock() {
        Optional.ofNullable(zkClient).ifPresent(x -> x.close());
    }
}
