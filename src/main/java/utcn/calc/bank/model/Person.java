package utcn.calc.bank.model;

import java.io.Serializable;

public class Person implements Serializable{

	private String cnp;
	private String name;
	private int age;
	private String address;
	public Person(String cnp, String name, int age,String address) {
		this.cnp=cnp;
		this.name=name;
		this.age=age;
		this.address=address;
	}
	public String toString() {
		return "*Person* CNP: "+cnp+" Name: "+name+" Age: "+age+" Address: "+address+"\n";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnp == null) ? 0 : cnp.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (cnp == null) {
			if (other.getCnp() != null)
				return false;
		} else if (!cnp.equals(other.getCnp()))
			return false;
		return true;
	}

	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
