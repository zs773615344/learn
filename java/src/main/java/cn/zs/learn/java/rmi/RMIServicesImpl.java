package cn.zs.learn.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServicesImpl extends UnicastRemoteObject implements RMIServices {

    protected RMIServicesImpl() throws RemoteException {
    }

    @Override
    public String getHomeENV() throws RemoteException {
        return System.getenv("HOME");
    }

    @Override
    public RMITransferObject getObejectInfo(String name, int age) throws RemoteException {
        if (name.startsWith("s"))
            throw new IllegalArgumentException("name is illegal");
        if (age < 0 | age > 200)
            throw new IllegalArgumentException("name is illegal");
        return new RMITransferObject(name, age);

    }
}
