package cn.zs.learn.bigdate.learnhadoopsource.ipc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import java.io.IOException;

public class IPCServer {
    public static final int IPC_PORT = 32121;
    public static final long IPC_VER = 5473L;
    public static void main(String[] args) throws IOException {
        IPCServices services = new IPCServicesImpl();
        RPC.Server server = new RPC.Builder(new Configuration())
                .setBindAddress("0.0.0.0")
                .setPort(IPC_PORT)
                .setProtocol(IPCServices.class)
                .setInstance(services)
                .build();
        server.start();
    }
}
