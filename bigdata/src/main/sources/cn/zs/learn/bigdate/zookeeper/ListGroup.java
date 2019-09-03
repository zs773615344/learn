package cn.zs.learn.bigdate.zookeeper;

import java.util.List;

import org.apache.zookeeper.KeeperException;


public class ListGroup extends ConnectionWatcher {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ListGroup listGroup = new ListGroup();
		listGroup.connect("10.11.100.130:2181");
		listGroup.list("zk_group");
		listGroup.close();
	}
	
	public void list(String groupName) throws InterruptedException, KeeperException  {
		try {
			String path = "/" + groupName;
			List<String> children = zk.getChildren(path,false);
			if(children.isEmpty()) {
				System.out.println("no members in group "+ groupName);
				System.exit(1);
			}
			for(String child:children) {
				System.out.println(child);
			}			
		} catch (KeeperException.NoNodeException e) {
			// TODO: handle exception
			System.out.println("group "+groupName+" does not exist");
			System.exit(1);
		}
	}

}
