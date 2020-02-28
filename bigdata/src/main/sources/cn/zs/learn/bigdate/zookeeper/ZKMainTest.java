package cn.zs.learn.bigdate.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

import java.nio.charset.StandardCharsets;

public class ZKMainTest {



    public static void main(String[] args) {
//        Thread thread1 = new Thread(new CreateRunable(), "thread1");
        Thread thread2 = new Thread(new CreateRunable(), "thread2");
//        thread1.start();
        thread2.start();

    }

    static class CreateRunable implements Runnable {
        ZooKeeper zk;
        @Override
        public void run() {
            ConnectionWatcher connectionWatcher = new ConnectionWatcher();
            try {
                connectionWatcher.connect("zhangshuai:2181");
            } catch (Exception e) {
                System.out.println(e);
            }
            zk = connectionWatcher.zk;

            try {
                zk.create("/haha", Thread.currentThread().getName().getBytes(StandardCharsets.UTF_8),
                        Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                while (true) {
                    Thread.sleep(2000);
                    zk.exists("/haha",new ExistWatch());
//                    if (stat != null) {
//                        byte[] data = zk.getData("/haha",false, stat);
//                        if (("thread1".equals(new String(data, StandardCharsets.UTF_8)) &&
//                                "thread1".equals(Thread.currentThread().getName())) ||
//                                ("thread2".equals(new String(data, StandardCharsets.UTF_8)) &&
//                                        "thread2".equals(Thread.currentThread().getName()))) {
//                            System.out.println(Thread.currentThread().getName() + " 获得锁");
//                            zk.close();
//                            System.out.println(Thread.currentThread().getName() + "killed");
//                            Thread.currentThread().stop();
//                        }
//                    }
                }

            } catch (KeeperException.NodeExistsException nodeExists) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 没有获得锁");
                    while (true) {
                        zk.exists("/haha",new ExistWatch());
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        class ExistWatch implements Watcher {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(Thread.currentThread().getName() + "触发观察");
                if (event.getType() == Event.EventType.NodeCreated) {
                    System.out.println(Thread.currentThread().getName() + "观察到node被创建");
                    try {
                        zk.exists("/haha", this);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (event.getType() == Event.EventType.NodeDeleted) {
                    System.out.println(Thread.currentThread().getName() + "观察到node被删除");
                    try {
                        zk.create("/haha", Thread.currentThread().getName().getBytes(StandardCharsets.UTF_8),
                                Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                        System.out.println(Thread.currentThread().getName());
                    } catch (KeeperException.NodeExistsException nodeExists) {
                        try {
                            while (true) {
                                zk.exists("/haha",new ExistWatch());
                                Thread.sleep(2000);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getType() == Event.EventType.None) {
                    System.out.println(Thread.currentThread().getName() + "观察到node什么都没有");
                }
            }
        }
    }
}