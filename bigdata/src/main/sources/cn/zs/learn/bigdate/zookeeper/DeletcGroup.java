package cn.zs.learn.bigdate.zookeeper;

import java.util.List;

import org.apache.zookeeper.KeeperException;

public class DeletcGroup extends ConnectionWatcher {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DeletcGroup deletcGroup = new DeletcGroup();
		deletcGroup.connect("10.11.100.130:2181");
		deletcGroup.delete("zk_group");
		deletcGroup.close();
	}
	public void delete(String groupName) throws KeeperException, InterruptedException {
		try {
			String path = "/" + groupName;
			List<String> children = zk.getChildren(path, false);
			for(String child:children) {
				zk.delete(path+"/"+child, -1);
			}
			zk.delete(path, -1);			
		}catch (KeeperException.NoNodeException e) {
			// TODO: handle exception
			System.out.println("group "+groupName+" does not exist");
			System.exit(1);
		}
	}

}
