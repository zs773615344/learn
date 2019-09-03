package cn.zs.learn.bigdate.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ConnectionWatcher implements Watcher {

    private static final int SESSION_TIMEOUT = 5000;

    protected ZooKeeper zk;
    private CountDownLatch cdl = new CountDownLatch(1);

    public void connect(String hosts) throws Exception {
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        cdl.await();
    }

    @Override
    public void process(WatchedEvent event) {
        // TODO Auto-generated method stub
        if(event.getState() == KeeperState.SyncConnected) {
            cdl.countDown();
        }
    }

    public void close() throws Exception {
        zk.close();
    }

}
