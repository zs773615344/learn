package cn.zs.learn.web.frame.spring.DI;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
       AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
       TextEditor textEditor = (TextEditor) context.getBean("textEditor");
       textEditor.spellCheck();
//       CollectionDI collectionDI = (CollectionDI) context.getBean("collectionDI");
//       List addressList = collectionDI.getAddressList();
//       System.out.println(addressList);
//       Set addressSet = collectionDI.getAddressSet();
//       System.out.println(addressSet);
//       Map addressMap = collectionDI.getAddressMap();
//       System.out.println(addressMap);
//       Properties addressProp = collectionDI.getAddressProp();
//       System.out.println(addressProp);
       context.close();
    }
    
}
