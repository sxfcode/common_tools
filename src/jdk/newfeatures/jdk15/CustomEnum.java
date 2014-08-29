package jdk.newfeatures.jdk15;

public enum CustomEnum {
    RED("red",1),
    GREEN("green",2),
    BLACK("black",3);
    private String name;
    private int value;
    private CustomEnum(String name,int value){
    	this.name = name;
    	this.value = value;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
