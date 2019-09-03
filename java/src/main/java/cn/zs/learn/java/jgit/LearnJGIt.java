package cn.zs.learn.java.jgit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RefDatabase;
import org.eclipse.jgit.lib.RefUpdate;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class LearnJGIt {
    	public static void main(String[] args) throws IOException, IllegalStateException, GitAPIException, URISyntaxException  {
    	    /*
    	   * JGit 的 API 有两种基本的层次：底层命令和高层命令。 
    	     * 这个两个术语都来自 Git ，并且 JGit 也被按照相同的方式粗略地划分：
    	     * 高层 API 是一个面向普通用户级别功能的友好的前端（一系列普通用户使用 Git 命令行工具时可能用到的东西），
    	     * 底层 API 则直接作用于低级的仓库对象。
    	    */
    	    
    	    /*
    	     * 底层api：JGit 会话会以 Repository 类作为起点
    	    */
    	    // 创建一个新版本库
    	    File newRepoFile = new File("/home/zhang/git/newGitRepo/.git");
//    	    Repository newRepo = FileRepositoryBuilder.create(newRepoFile);
//    	    	newRepo.create();
    	    
    	    	// 打开一个已存在的版本库，需判断版本库是否存在
    	    	File existRepoFile = new File("/home/zhang/git/existGitRepo/.git");
    	    	Repository repo = new FileRepositoryBuilder().setGitDir(newRepoFile).build();
    	    	if(!repo.getDirectory().exists())
    	    	    repo.create();
    	    	
    	    	RefDatabase refDatabase = repo.getRefDatabase();
    	    	// 获取引用
    	    	Ref master = repo.getRef("master");
    	    	//获取该引用所指向的对象
//    	    	System.out.println(master.getName());
//    	    	Ref target = master.getTarget();
//    	    	System.out.println(target.getName());
    	    	ObjectId masterTip = master.getObjectId();
//    	    	System.out.println(masterTip.getName());
    	    
//    	    	ObjectId resolve = repo.resolve("HEAD^{tree}");
//    	    	System.out.println(resolve.getName());
    	    
//    	    	FileOutputStream outputStream = new FileOutputStream(new File("/home/zhang/git/newGitRepo/hehe"));
//    	    ObjectLoader loader = repo.open(masterTip);
//    	    loader.copyTo(System.out);
    	    	
    	    	// 创建分支
//    	    	RefUpdate createBranch1 = repo.updateRef("refs/heads/branch1");
//    	    	createBranch1.setNewObjectId(masterTip);
//    	    	createBranch1.update();
    	    	
    	    	// 删除分支
//    	    	RefUpdate deleteBranch1 = repo.updateRef("refs/heads/branch1");
//    	    	deleteBranch1.setForceUpdate(true);
//    	    	deleteBranch1.delete();

    	    	// 配置
//    	    	Config cfg = repo.getConfig();
//    	    	String name = cfg.getString("user", null, "name");
//    	    System.out.println(cfg);
//    	    System.out.println(name);
    	    	
    	    	
    	    	/*
    	    	 * 高层api
    	    	*/
    	    	// 初始化一个空版本库
//    	    Git git = Git.init().setBare(true).setDirectory(new File("/home/zhang/git/jgit")).call();
//    	    Repository repository = git.getRepository();
    	    	
    	    	Git git = new Git(repo);
    	    	
    	    	RemoteAddCommand remoteAdd = git.remoteAdd();
    	    	remoteAdd.setName("origin");
    	    	remoteAdd.setUri(new URIish("http://192.168.1.228/softDevelopmentGroup/sdp.git"));
    	    	remoteAdd.call();
    	    	
    	    	List<RemoteConfig> list = git.remoteList().call();
    	    	for (RemoteConfig remoteConfig : list) {
//             System.out.println(remoteConfig.getName()+remoteConfig.getURIs()+"\n"+remoteConfig.toString());
                }
    	    	
    	    	UsernamePasswordCredentialsProvider credentials = new UsernamePasswordCredentialsProvider("zhangshuai@casic.com", "12345678");
//    	    	Collection<Ref> remoteRefs = git.lsRemote().setCredentialsProvider(credentials)
//    	    	        .setRemote("origin")
//    	    	        .setTags(true)
//    	    	        .setHeads(false)
//    	    	        .call();
//    	    	for(Ref ref:remoteRefs) {
//    	    	    System.out.println(ref.getName()+"->"+ref.getObjectId().name());
//    	    	}
    	    	
    	    	// Git的静态方法：init，clone，lsRemoteRepository
//    	    	CloneCommand clone = Git.cloneRepository().setURI("http://192.168.1.228/softDevelopmentGroup/sdp.git")
//    	    	        .setDirectory(new File("/home/zhang/git/sdp"))
//    	    	        .setCredentialsProvider(credentials);
//    	    	Git cloneGit = clone.call();

    	    	Repository repository = new FileRepositoryBuilder().setGitDir(new File("/home/zhang/git/sdp/.git")).build();
    	    	Git git2 = new Git(repository);
    	    	List<Ref> call = git2.branchList().call();
    	    	for (Ref ref : call) {
                   System.out.println(ref.getName());
                }
//    	    	PullCommand pull = git2.pull();
//    	    	pull.setRemote("origin").setRemoteBranchName("master").setCredentialsProvider(credentials).call();
    	    	PushCommand push = git2.push();
    	    	push.setRemote("origin").setCredentialsProvider(credentials).setDryRun(false).call();
    	    	
    	  
	}
}
