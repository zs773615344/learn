package cn.zs.learn.bigdate.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;

public class JoinGroup extends ConnectionWatcher {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JoinGroup joinGroup = new JoinGroup();
		joinGroup.connect("10.11.100.130:2181");
		joinGroup.join("zk_group", "zk_member2");
		Thread.sleep(Long.MAX_VALUE);

	}
	
	public void join(String groupName,String memberName) throws Exception {
		String path = "/" + groupName + "/" + memberName;
		String CreatePath = zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println("Created " + CreatePath);
	}

}
