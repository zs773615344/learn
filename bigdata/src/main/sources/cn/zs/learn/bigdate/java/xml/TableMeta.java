package cn.zs.learn.bigdate.java.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "DATAHEAD")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"DATA_NAME","PRIMARY_KEY","UPDATE_FLAG","DELETE_FLAG","DATA_TYPE","ROW_NUM","PAGE_NUM","FIELD_NUM","FEILDS"})
public class TableMeta {

    @XmlElement(required = true, nillable = true)
    private String DATA_NAME;

//    @XmlElement(required = true, nillable = true)
//    private String PRIMARY_KEY;

    @XmlElement(required = true, nillable = true)
    @XmlList
    private List<String> PRIMARY_KEY;

    @XmlElement(required = true, nillable = true)
    private String UPDATE_FLAG;

    @XmlElement(required = true, nillable = true)
    private String DELETE_FLAG;

    @XmlElement(required = true, nillable = true)
    private String DATA_TYPE;

    @XmlElement(required = true, nillable = true)
    private int ROW_NUM;

    @XmlElement(required = true, nillable = true)
    private int PAGE_NUM;

    @XmlElement(required = true, nillable = true)
    private int FIELD_NUM;


    @XmlElementWrapper(name = "FIELDS")
    @XmlElement(name = "FIELD", required = true, nillable = true)
    private List<Field> FEILDS;

    public String getDATA_NAME() {
        return DATA_NAME;
    }

    public void setDATA_NAME(String DATA_NAME) {
        this.DATA_NAME = DATA_NAME;
    }

//    public String getPRIMARY_KEY() {
//        return PRIMARY_KEY;
//    }
//
//    public void setPRIMARY_KEY(String PRIMARY_KEY) {
//        this.PRIMARY_KEY = PRIMARY_KEY;
//    }


    public List<String> getPRIMARY_KEY() {
        return PRIMARY_KEY;
    }

    public void setPRIMARY_KEY(List<String> PRIMARY_KEY) {
        this.PRIMARY_KEY = PRIMARY_KEY;
    }

    public String getUPDATE_FLAG() {
        return UPDATE_FLAG;
    }

    public void setUPDATE_FLAG(String UPDATE_FLAG) {
        this.UPDATE_FLAG = UPDATE_FLAG;
    }

    public String getDELETE_FLAG() {
        return DELETE_FLAG;
    }

    public void setDELETE_FLAG(String DELETE_FLAG) {
        this.DELETE_FLAG = DELETE_FLAG;
    }

    public String getDATA_TYPE() {
        return DATA_TYPE;
    }

    public void setDATA_TYPE(String DATA_TYPE) {
        this.DATA_TYPE = DATA_TYPE;
    }

    public int getROW_NUM() {
        return ROW_NUM;
    }

    public void setROW_NUM(int ROW_NUM) {
        this.ROW_NUM = ROW_NUM;
    }

    public int getPAGE_NUM() {
        return PAGE_NUM;
    }

    public void setPAGE_NUM(int PAGE_NUM) {
        this.PAGE_NUM = PAGE_NUM;
    }

    public int getFIELD_NUM() {
        return FIELD_NUM;
    }

    public void setFIELD_NUM(int FIELD_NUM) {
        this.FIELD_NUM = FIELD_NUM;
    }

    public List<Field> getFEILDS() {
        return FEILDS;
    }

    public void setFEILDS(List<Field> FEILDS) {
        this.FEILDS = FEILDS;
    }

    @Override
    public String toString() {
        return "TableMeta{" +
                "DATA_NAME='" + DATA_NAME + '\'' + "\n" +
                ", PRIMAET_KEY=" + PRIMARY_KEY + "\n" +
                ", UPDATE_FLAG=" + UPDATE_FLAG + "\n" +
                ", DELETE_FLAG=" + DELETE_FLAG + "\n" +
                ", ROW_NUM=" + ROW_NUM + "\n" +
                ", PAGE_NUM=" + PAGE_NUM + "\n" +
                ", FIELD_NUM=" + FIELD_NUM + "\n" +
                ", FEILDS=" + FEILDS + "\n" +
                '}';
    }

}
