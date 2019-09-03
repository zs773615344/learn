package cn.zs.learn.java.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    static {
        try {
            // 启动RMI的服务注册服务，1099是默认端口。效果与在命令行上使用rmiregistry启动rmi注册服务相同。
            LocateRegistry.createRegistry(12090);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static final String RMI_URL = "rmi://192.168.1.245:12090/services";

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        RMIServices services = new RMIServicesImpl();
        Naming.rebind(RMI_URL, services);
        System.out.println("server ready");

    }
}
