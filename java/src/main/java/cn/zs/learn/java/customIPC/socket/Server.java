package cn.zs.learn.java.customIPC.socket;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        ServerSocket serverSocket = new ServerSocket(8088);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            MethodAndParam methodAndParam = (MethodAndParam) objectInputStream.readObject();

            socket.shutdownInput();

            String methodName = methodAndParam.getMethod();
            Object[] params = methodAndParam.getParams();
            String result;
            ServicesImpl services = new ServicesImpl();
            Method method = null;
            if (params == null || params.length == 0) {
                method = services.getClass().getMethod(methodName);
//                result = (String) method.invoke(services);
            } else {
                Class[] paramType = new Class[params.length];
                for (int i = 0; i < params.length; i++)
                    paramType[i] = params[i].getClass();
                method = services.getClass().getMethod(methodName, paramType);
            }
            result = (String) method.invoke(services, params);

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(result);


            inputStream.close();
            objectInputStream.close();
            outputStream.close();
            objectOutputStream.close();
            socket.close();
        }
    }
}
