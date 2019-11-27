package com.lht.distributed.lock.zk.impl;

import com.lht.distributed.lock.zk.ZkConfig;
import org.I0Itec.zkclient.IZkDataListener;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class ZkLockImpl extends ZkAbstractLock {
    CountDownLatch countDownLatch;
    @Override
    public boolean tryZkLock() {
        try {
            zkClient.createEphemeral(ZkConfig.ZK_LOCK_PATH);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void waitZkLock() throws InterruptedException {
        IZkDataListener zkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
            }
            @Override
            public void handleDataDeleted(String s) throws Exception {
                Optional.ofNullable(countDownLatch).ifPresent(x -> x.countDown());
            }
        };
        zkClient.subscribeDataChanges(ZkConfig.ZK_LOCK_PATH,zkDataListener);
        if (zkClient.exists(ZkConfig.ZK_LOCK_PATH)){
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
        }
        zkClient.unsubscribeDataChanges(ZkConfig.ZK_LOCK_PATH,zkDataListener);
    }
}
