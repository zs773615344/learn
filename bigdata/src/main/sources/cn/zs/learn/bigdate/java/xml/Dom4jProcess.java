package cn.zs.learn.bigdate.java.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dom4jProcess {
    public static void main(String[] args) throws DocumentException,Exception {
        URL url = Dom4jProcess.class.getClassLoader().getResource("data.xml");
        System.out.println(url);
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(url.getFile()));

        // 获取root元素
        Element root = document.getRootElement();

        /**
         * 获取DATAHEAD元素
         * 此处注意考虑xml文件太大时该采用什么方式读取？
        */
        List<Node> nodes = document.selectNodes("//DATAFILE//DATAHEAD");


        if (nodes.size() != 1 ) {  // 当DATAHEAD元素的个数不等于1时，该xml不满足规范。
            /**
             * do someting
             *
            */
            throw new XmlFormatException("xml format wrong");
        }

        Node head = nodes.get(0);

        // 将DATAHEAD转化为object
        JAXBContext jaxbContext = JAXBContext.newInstance(TableMeta.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        TableMeta tableMeta = (TableMeta) unmarshaller.unmarshal(new StringReader(head.asXML()));
        System.out.println(tableMeta);

        System.out.println("\n\n\n####################分隔符####################\n\n\n");

        // 将object转化为xml
        TableMeta meta = new TableMeta();
        meta.setDATA_NAME("AICSDB_TB_JUDCIAL_INQ_INFO");
        meta.setPRIMARY_KEY(Arrays.asList("MERCH_ID","TRAN_DT","REQ_ID"));
        meta.setUPDATE_FLAG("N");
        meta.setDELETE_FLAG("Y");
        meta.setDATA_TYPE("A");
        meta.setROW_NUM(2);
        meta.setPAGE_NUM(1);
        meta.setFIELD_NUM(20);
        List<Field> list = new ArrayList<Field>();
        Field field1 = new Field();
        field1.setID(1);
        field1.setTYPE("VARCHAR2(12)");
        field1.setValue("MERCH_ID");
        list.add(field1);
        Field field2 = new Field();
        field2.setID(2);
        field2.setTYPE("VARCHAR2(6)");
        field2.setValue("OPE_CD");
        list.add(field2);
        meta.setFEILDS(list);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
        marshaller.marshal(meta,System.out);



    }
}
