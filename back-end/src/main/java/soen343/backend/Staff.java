package soen343.backend;

import java.util.List;

public class Staff {
	
	private String name;
	private int age;
	private String[] position;
	private List<String> skills;
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
	public String[] getPosition() {
		return position;
	}
	public void setPosition(String[] position) {
		this.position = position;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public Staff(String name, int age, String[] position, List<String> skills) {
		super();
		this.name = name;
		this.age = age;
		this.position = position;
		this.skills = skills;
	}
	public Staff() {
		super();
	}
	

}
