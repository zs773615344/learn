package cn.zs.learn.bigdate.learnhadoopsource.ipc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import java.io.IOException;
import java.net.InetSocketAddress;

public class IPCClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress address = new InetSocketAddress("localhost", IPCServer.IPC_PORT);
        IPCServices proxy = RPC.getProxy(IPCServices.class, IPCServer.IPC_VER,
                address, new Configuration());
        IPCFileStatus status = proxy.getFileStatus("/tmp/testIPC");
        System.out.println(status);
        proxy.getFileStatus("/haha");
        Thread.sleep(5000);
        proxy.getFileStatus("/haha");
        RPC.stopProxy(proxy);
        System.out.println(RPC.getRpcTimeout(new Configuration()));
        System.out.println(new Configuration().get("ipc.client.ping"));
    }
}
