package cn.zs.learn.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServices extends Remote {
    String getHomeENV() throws RemoteException;

    RMITransferObject getObejectInfo(String name, int age) throws Exception;
}
