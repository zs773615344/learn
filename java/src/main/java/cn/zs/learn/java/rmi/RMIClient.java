package cn.zs.learn.java.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {

    public static void main(String[] args) throws Exception {
        RMIServices services = (RMIServices) Naming.lookup(RMIServer.RMI_URL);
        String home = services.getHomeENV();
        System.out.println(home);
        RMITransferObject info = services.getObejectInfo("zhang", 201);
        System.out.println(info);
    }
}
