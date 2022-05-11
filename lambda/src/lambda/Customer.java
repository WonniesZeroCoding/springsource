package lambda;

public class Customer {
	private String n;
	private int age;
	private int pri;
	
	public Customer(String n, int age, int pri) {
		super();
		this.n = n;
		this.age = age;
		this.pri = pri;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPri() {
		return pri;
	}
	public void setPri(int pri) {
		this.pri = pri;
	}
	
	
}
