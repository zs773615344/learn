package cn.zs.learn.bigdate.learnhadoopsource.ipc;

import org.apache.hadoop.ipc.ProtocolInfo;
import org.apache.hadoop.ipc.ProtocolSignature;
import org.apache.hadoop.ipc.VersionedProtocol;

import java.io.IOException;

@ProtocolInfo(protocolName = "cn.zs.learn.bigdate.learnhadoopsource.ipc.IPCServices", protocolVersion = IPCServer.IPC_VER)
public interface IPCServices extends VersionedProtocol {

//    public static final long versionID = IPCServer.IPC_VER;
    IPCFileStatus getFileStatus(String filename) throws InterruptedException;
}
