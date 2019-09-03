package cn.zs.learn.java;


public enum LearnEnum {
    	RED,YELLOW,GREEN;
    	
    public String toString() {
	String id = name();
	return id.charAt(0)+id.substring(1).toLowerCase();
    }
    
    public static void main(String[] args) {
	System.out.println(LearnTwoEnum.Red.getValueString());
	LearnTwoEnum[] values = LearnTwoEnum.values();
	for(Object value:values) {
	    System.out.println(value);
	}
	
    }
}

 enum LearnTwoEnum{
     Red(1,"red"),YELLOW(2,2),GREEN(3,false);
     	
     private int id;
     private String valueString;
     private int valueInt;
     private boolean valueBoolean;
     
     private LearnTwoEnum(int id,String value) {
	 this.id = id;
	 this.valueString = value;
     }
    private LearnTwoEnum(int id,int value) {
	 this.id = id;
	 this.valueInt = value;
     }
    private LearnTwoEnum(int id,boolean value) {
	 this.id = id;
	 this.valueBoolean = value;
     }
    public int getId() {
        return id;
    }
    public String getValueString() {
        return valueString;
    }
    public int getValueInt() {
        return valueInt;
    }
    public boolean isValueBoolean() {
        return valueBoolean;
    }
      
      
      
}