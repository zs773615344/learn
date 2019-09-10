package cn.zs.learn.bigdate.java.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ID", "TYPE", "value"})
public class Field {

    @XmlAttribute(name = "ID")
    private int ID;

    @XmlAttribute(name = "TYPE")
    private String TYPE;

    @XmlValue
    private String value;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field{" +
                "ID=" + ID +
                ", TYPE='" + TYPE + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
