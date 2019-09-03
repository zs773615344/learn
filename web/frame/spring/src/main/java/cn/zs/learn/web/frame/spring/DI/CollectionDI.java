package cn.zs.learn.web.frame.spring.DI;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionDI {
    @SuppressWarnings("rawtypes")
    List addressList;
    @SuppressWarnings("rawtypes")
    Set addressSet;
    @SuppressWarnings("rawtypes")
    Map addressMap;
    Properties addressProp;
    @SuppressWarnings("rawtypes")
    public List getAddressList() {
        return addressList;
    }
    
    @SuppressWarnings("rawtypes")
    public void setAddressList(List addressList) {
        this.addressList = addressList;
    }
    @SuppressWarnings("rawtypes")
    public Set getAddressSet() {
        return addressSet;
    }
    @SuppressWarnings("rawtypes")
    public void setAddressSet(Set addressSet) {
        this.addressSet = addressSet;
    }
    @SuppressWarnings("rawtypes")
    public Map getAddressMap() {
        return addressMap;
    }
    @SuppressWarnings("rawtypes")
    public void setAddressMap(Map addressMap) {
        this.addressMap = addressMap;
    }
    public Properties getAddressProp() {
        return addressProp;
    }
    public void setAddressProp(Properties addressPro) {
        this.addressProp = addressPro;
    }
    
}
