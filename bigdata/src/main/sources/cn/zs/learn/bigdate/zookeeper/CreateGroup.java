package cn.zs.learn.bigdate.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class CreateGroup implements Watcher{
	
	private static final int SESSION_TIMEOUT = 5000;
	
	private ZooKeeper zk;
	private CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CreateGroup createGroup = new CreateGroup();
		createGroup.connect("0.0.0.0:2181");
		createGroup.create("zk_group");
		Thread.sleep(10000);
		createGroup.close();
	}
	
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
	
	public void create(String groupName) throws Exception {
		String path = "/" + groupName;
		byte[] data = null;
		data = "string".getBytes();
		String createPath = zk.create(path, data/*data*/, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("Created " + createPath);
	} 
	
	public void close() throws Exception {
		zk.close();
	}

}
