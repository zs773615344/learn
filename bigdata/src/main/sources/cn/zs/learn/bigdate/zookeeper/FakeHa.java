package cn.zs.learn.bigdate.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class FakeHa implements  Watcher, AsyncCallback.StringCallback, AsyncCallback.StatCallback {
    private static final int MAX_RETRYNUM = 3;
    private int retryTime = 0;
    private int sessionTimeout;
    private CountDownLatch hasReceivedEvent = new CountDownLatch(1);
    private CountDownLatch hasSetZookeeper = new CountDownLatch(1);
    ZooKeeper zooKeeper;

    public static void main(String[] args) throws InterruptedException{

        try {
            FakeHa fakeHa = new FakeHa();
            fakeHa.connect("zhangshuai:2181", 5000);
//            fakeHa.createLockNodeAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true);

    }

    public void connect(String host, int sessionTimeout) throws Exception{
        this.sessionTimeout = sessionTimeout;
        zooKeeper = new ZooKeeper(host, sessionTimeout, this);
        hasSetZookeeper.countDown();
        waitForConnect(sessionTimeout);
        hasReceivedEvent.await();

    }

    @Override
    public synchronized void process(WatchedEvent event) {
        hasReceivedEvent.countDown();
        try {
            if (!hasSetZookeeper.await(sessionTimeout, TimeUnit.MILLISECONDS)) {
                System.out.println("just a stale zk");
            }
        } catch (InterruptedException e) {
            System.out.println("dead");
        }
        Event.EventType type = event.getType();
        if (type == Event.EventType.None) {
            Event.KeeperState state = event.getState();
            if (state == Event.KeeperState.SyncConnected) {
                System.out.println(zooKeeper.getSessionId());
//                hasReceivedEvent.countDown();
                monitorLockNodeAsync();
            }
        } else {
            switch (type) {
                case NodeDeleted:
                    System.out.println("watch node deleted");
                    createLockNodeAsync();
                    break;
                case NodeCreated:
                    System.out.println("watch node created");
                    /**
                     * 是否需要加上？目前来说是没有必要的。
                    * */
//                    monitorLockNodeAsync();
                    break;
                case NodeDataChanged:
                    System.out.println("watch node dataChanged");
                    monitorLockNodeAsync();
                    break;
                 default:
                     System.out.println("watch children changed");
                     monitorLockNodeAsync();
            }
        }
    }

    public void waitForConnect(int sessionTime) throws InterruptedException, KeeperException {
        try {
            if (!hasReceivedEvent.await(sessionTime, TimeUnit.MILLISECONDS)) {
                System.out.println("connection lost");
                zooKeeper.close();
                throw KeeperException.create(Code.CONNECTIONLOSS);
            }
        } catch (InterruptedException interruptException) {
            throw interruptException;
        }

    }

    @Override
    public synchronized void processResult(int rc, String path, Object ctx, Stat stat) {
        Code code = Code.get(rc);
        switch (code) {
            case OK:
                if (stat.getEphemeralOwner() == zooKeeper.getSessionId())
                    System.out.println("I am active, waiting for the happenning of the watcher");
                else
                    System.out.println("I am standby, waiting for the happenning of the watcher");
                break;
            case NONODE:
                System.out.println("no node, want to by active");
                createLockNodeAsync();
                break;
            default:
                System.out.println(code.toString());
                isSessionProblem(code, true);

        }
    }

    @Override
    public synchronized void processResult(int rc, String path, Object ctx, String name) {
        Code code = Code.get(rc);
        switch (code) {
            case OK:
                System.out.println("node created by me, I am active");
                monitorLockNodeAsync();
                break;
            case NODEEXISTS:
                System.out.println("node created but not by me,I am standby");
                monitorLockNodeAsync();
                break;
            default:
                System.out.println(code.toString());
                isSessionProblem(code, false);
        }

    }

    public void isSessionProblem(Code code, boolean existOrCreate) {
        if (code == Code.CONNECTIONLOSS || code == Code.OPERATIONTIMEOUT) {
            if (retryTime < MAX_RETRYNUM) {
                ++ retryTime;
                if (existOrCreate)
                    monitorLockNodeAsync();
                else
                    createLockNodeAsync();
                return;
            } else if (code == Code.SESSIONEXPIRED) {
                System.out.println("session expird");
                return;
            }
            System.out.println("dead");
            zooKeeper = null;
        }
    }

    public void createLockNodeAsync() {
        zooKeeper.create("/fakeHa", ("fakeHa" + zooKeeper.getSessionId()).getBytes(StandardCharsets.UTF_8), Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL, this, this);
    }

    public void monitorLockNodeAsync() {
        this.retryTime = 0;
        zooKeeper.exists("/fakeHa", this, this, this);
    }
}
