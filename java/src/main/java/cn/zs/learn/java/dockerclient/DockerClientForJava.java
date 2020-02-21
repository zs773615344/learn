package cn.zs.learn.java.dockerclient;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.api.DockerClient;

public class DockerClientForJava {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		

		
		DockerClientConfig conf=DefaultDockerClientConfig.createDefaultConfigBuilder()
//				.withDockerHost("unix:///var/run/docker.sock")
				.withDockerHost("tcp://192.168.1.231:1234")
				.withApiVersion("1.30")
				.build();

		DockerClient cli=DockerClientBuilder.getInstance(conf)
//				.withDockerCmdExecFactory(new NettyDockerCmdExecFactory())
				.build();
//		Gson gson=new Gson();
		
//		String containerId="b7597a1ab376";
		
		// 获取docker信息
//		Info info = cli.infoCmd().exec();
//		System.out.println(info.toString());
		
		// 获取docker版本
//		Version version = cli.versionCmd().exec();
//		System.out.println(version.toString());
		
		// 删除镜像
//		cli.removeImageCmd("b0a12402cc98").exec();
		
		// 列出本地镜像
//		List<Image> images = cli.listImagesCmd().exec();
//		for(Image image:images) {
//			System.out.println(image.toString());
//		}
		
		/*
		 * 创建镜像的三种方式
		 * 1.基于已有镜像的容器创建
		 * 2.基于本地模版导入
		 * 		1).load -i/< tarFile
		 * 		2).import -m "" file - repo:tag
		 * 	import 导入容器快照----------->export 导出一个镜像快照
		 * 	load   导入完整记录----------->save 将一个或多个镜像保存为tar
		 * 
		 * 3.基于Dockerfile文件创建
		*/
		
		// 1.基于已有镜像的容器创建  commit -m -a -p containerId repo:tag
//		Map<String,String> label = new HashMap<String, String>();
//		label.put("docker-java-label", "docker-java");
//		String commit = cli.commitCmd(containerId)
//				.withMessage("docker")  //无效
//				.withLabels(label)  
//				.withRepository("docker-java-haha")
//				.withTag("docker-java")
//				.exec();
//		System.out.println(commit);
		
		// 2.基于本地模版导入 
		// load镜像 
//		cli.loadImageCmd(in).exec();
		
		// save镜像
//		InputStream saveImage = cli.saveImageCmd("docker-java").withTag("docker-java").exec();
//		BufferedInputStream bis = new BufferedInputStream(saveImage);
//		BufferedOutputStream bos = new BufferedOutputStream(
//				new FileOutputStream(
//						new File("/home/zhang/Desktop/docker/docker-java.tar")));
//		int ins;
//		while((ins=bis.read()) != -1) {
//			bos.write(ins);
//		}
//		bis.close();
//		saveImage.close();
//		bos.close();
				
		// import镜像 import tarfile - repo:tag
//		File file = new File("/home/zhang/Desktop/docker/repo-tag-java.tar.gz");
//		InputStream in = new FileInputStream(file);
//		cli.createImageCmd("repo-java", in).withTag("tag-java").exec();

		// tag 修改镜像repo：tag
//		cli.tagImageCmd("cc4feed84a56", "centos", "test").exec();
		
		// inspect 
//		InspectImageResponse inspectImageResponse = cli.inspectImageCmd("cc4feed84a56").exec();
//		System.out.println(gson.toJson(inspectImageResponse));

		// 创建容器
		CreateContainerResponse createContainerResponse = cli.createContainerCmd("novnc")
//				.withName("docker-java")
				.withCmd("/bin/bash")
				.withTty(true)
				.withStdinOpen(true)
				.exec();
		System.out.println(createContainerResponse.toString());
		
		// 启动容器
//		cli.startContainerCmd(containerId).exec();
//		System.out.println("success");
		
		// 停止容器
//		cli.stopContainerCmd(containerId).exec();
//		System.out.println("shutdown");
		
		// 重启
//		cli.restartContainerCmd(containerId).exec();
		
		//删除镜像
//		cli.removeContainerCmd(containerId).exec();
		
		// 列出已启动容器的信息
//		List<Container> listContainers = cli.listContainersCmd()
//				.withShowAll(true)		// 列出所有
////				.withLimit(2)
////				.withShowSize(true)
//				.exec();
//		System.out.println(gson.toJson(listContainers));
		
		// copy	
//		InputStream is = cli.copyArchiveFromContainerCmd(containerId, "/home/haha").exec();
//		BufferedInputStream bis = new BufferedInputStream(is);
//		BufferedOutputStream bos = new BufferedOutputStream(
//				new FileOutputStream(
//						new File("/home/zhang/Desktop/docker/haha")));
//		int ins;
//		while((ins=bis.read()) != -1) {
//			bos.write(ins);
//		}
//		bis.close();
//		is.close();
//		bos.close();
		// 未测试
//		cli.copyArchiveToContainerCmd(containerId).exec();
		

		// rename
//		cli.renameContainerCmd(containerId).withName("java-rename").exec();
		
		// inspect 容器
//		InspectContainerResponse inspectContainerResponse = cli.inspectContainerCmd(containerId).exec();
//		System.out.println(inspectContainerResponse.toString());
		
		// top 显示容器内进程
//		TopContainerResponse topContainerResponse = cli.topContainerCmd(containerId).exec();
//		System.out.println(gson.toJson(topContainerResponse));
		
		// diff
//		List<ChangeLog> list = cli.containerDiffCmd(containerId).exec();
//		for(ChangeLog cl:list) {
//			System.out.println(cl);
//		}
		
		// kill
//		cli.killContainerCmd(containerId).exec();
		
		// pause
//		cli.pauseContainerCmd(containerId).exec();
		
		// umpause
//		cli.unpauseContainerCmd(containerId).exec();
		
		// update
//		cli.updateContainerCmd(containerId).withCpuPeriod(1000).exec();
		
		// exec 进入已启动容器执行命令  先create，再start
//		ExecCreateCmdResponse execCreateCmdResponse = cli.execCreateCmd("b7597a1ab376")
//				.withAttachStdin(true)
//				.withAttachStdout(true)
//				.withTty(true)
//				.withUser("root")
//				.withCmd("touch","/home/java")
//				.exec();
//		
//		System.out.println(execCreateCmdResponse.getId());
//		
//		ExecStartResultCallback execStartResultCallback = cli.execStartCmd(execCreateCmdResponse.getId())
//				.exec(new ExecStartResultCallback(System.out, System.err))
//				.awaitCompletion();
//		
//		System.out.println(execStartResultCallback.toString());
		
		
		
		// pull镜像	未测试
//		cli.pullImageCmd("repository").withTag("tag").exec(new PullImageResultCallback()).awaitCompletion(30, TimeUnit.SECONDS);
		
		// push镜像	未测试
//		cli.pushImageCmd("name").withTag("tag").exec(new PushImageResultCallback()).awaitCompletion(30, TimeUnit.SECONDS);		
		
		//logs 未测试
//		cli.logContainerCmd(containerId).withTimestamps(true).exec(resultCallback)
		
		//events 未测试
//		cli.eventsCmd().exec(new EventsResultCallback());
		
		//wait 等待容器停止，并返回状态码
//		int awaitStatusCode = cli.waitContainerCmd(containerId)
//				.exec(new WaitContainerResultCallback())
//				.awaitStatusCode();
//		System.out.println(awaitStatusCode);
		
		
		// 当使用netty时，需要使用close关闭流，否则无法停止程序。
//		cli.close();
		
//		InputStream stdin = new ByteArrayInputStream("".getBytes());
//		cli.attachContainerCmd(containerId)
//			.withStdErr(true)
//			.withStdOut(true)
//			.withFollowStream(true)
//			.withLogs(true)
//			.withStdIn(stdin)
//			.exec(new AttachContainerResultCallback() {
//
//				private StringBuffer log = new StringBuffer();
//
//		        @Override
//		        public void onNext(Frame item) {
//		            log.append(new String(item.getPayload()));
//		            super.onNext(item);
//		        }
//
//		        @Override
//		        public String toString() {
//		            return log.toString();
//		        }
//				
//			});
		
		// 未测试
//		cli.statsCmd(containerId).exec(new Stats)
		
		// 由本地Dockerfile生成docker镜像
//		Set<String> tags = new TreeSet<String>();
//		tags.add("java:java-build");
//		String imageId = cli.buildImageCmd(new File("/home/zhang/docker")).withTags(tags).exec(new BuildImageResultCallback()).awaitImageId();
//		System.out.println(imageId);
		
		//由dockerfile打成的tar文件生成docker镜像
//		Set<String> tags = new TreeSet<String>();
//		tags.add("java-tar:java-build");
//		String imageId = cli.buildImageCmd(new FileInputStream(new File("/home/zhang/file.tar"))).withTags(tags).exec(new BuildImageResultCallback()).awaitImageId();
//		System.out.println(imageId);
		
		// 列出网络
//		List<Network> networks = cli.listNetworksCmd().exec();
//		System.out.println(gson.toJson(networks));
		
		// 列出服务 
		// 注意无service
//		List<Service> services = cli.listServicesCmd().exec();
		
		// 列出逻辑卷
//		ListVolumesResponse volumesResponse = cli.listVolumesCmd().exec();
//		System.out.println(volumesResponse.toString());
		

		// 容器的信息
//		for(Container container:listContainers) {
//		String command = container.getCommand();
//		System.out.println(command);
//		
//		String id = container.getId();
//		System.out.println(id);
//		
//		String image = container.getImage();
//		System.out.println(image);
//		
//		String imageId = container.getImageId();
//		System.out.println(imageId);
//		
//		String status = container.getStatus();
//		System.out.println(status);
//		
//		Long created = container.getCreated();
//		System.out.println(created);
//		
//		Map<String, String> labels = container.getLabels();
//		System.out.println(labels);
//		
//		String[] names = container.getNames();
//		for(String name:names) {
//			System.out.println(name);
//		}
//		
//		Long sizeRootFs = container.getSizeRootFs();
//		System.out.println(sizeRootFs);
//		
//		Long sizeRW = container.getSizeRw();
//		System.out.println(sizeRW);
//		
//		ContainerHostConfig hostConfig = container.getHostConfig();
//		String networkMode = hostConfig.getNetworkMode();
//		System.out.println(networkMode);
//		
//		ContainerNetworkSettings networkSettings = container.getNetworkSettings();
//		Map<String, ContainerNetwork> networks = networkSettings.getNetworks();
//		for(String key:networks.keySet()) {
//			System.out.println(key);
//			ContainerNetwork containerNetwork = networks.get(key);
//			System.out.println(containerNetwork.getEndpointId());
//			System.out.println(containerNetwork.getGateway());
//			System.out.println(containerNetwork.getGlobalIPv6Address());
//			System.out.println(containerNetwork.getIpAddress());
//			System.out.println(containerNetwork.getIpV6Gateway());
//			System.out.println(containerNetwork.getMacAddress());
//			System.out.println(containerNetwork.getNetworkID());
//			System.out.println(containerNetwork.getGlobalIPv6PrefixLen().intValue());
//			System.out.println(containerNetwork.getIpPrefixLen().intValue());
//			
//			List<String> aliases = containerNetwork.getAliases();
//			if (aliases != null && aliases.size()>0) {
//				for(String aliase:aliases) {
//					System.out.println(aliase);
//				}					
//			}
//			
//			Link[] links = containerNetwork.getLinks();
//			if(links != null && links.length>0) {					
//				for(Link link:links) {
//					System.out.println(link.getName());
//					System.out.println(link.getAlias());
//				}
//			}
//			
//			
//			Ipam ipamConfig = containerNetwork.getIpamConfig();
//			if(ipamConfig != null) {
//				System.out.println(ipamConfig.getIpv4Address());
//				System.out.println(ipamConfig.getIpv6Address());					
//			}
//		}
//		System.out.println(container.toString());
//	}
		
		
	}

}
