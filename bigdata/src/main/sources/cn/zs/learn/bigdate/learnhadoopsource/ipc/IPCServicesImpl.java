package cn.zs.learn.bigdate.learnhadoopsource.ipc;


import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;


public class IPCServicesImpl implements IPCServices {

    @Override
    public IPCFileStatus getFileStatus(String filename) throws InterruptedException {
        Thread.sleep(5000);
        IPCFileStatus status = new IPCFileStatus(filename);
        return status;
    }

    @Override
    public long getProtocolVersion(String s, long l) throws IOException {
        System.out.println("protocol: " + s);
        System.out.println("clientVersion: " + l);
        return IPCServer.IPC_VER;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return new ProtocolSignature(l, new int[]{i});
    }
}
