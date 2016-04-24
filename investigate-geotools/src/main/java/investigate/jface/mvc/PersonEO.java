package investigate.jface.mvc;

public class PersonEO {
	private int ID;
	private String name;
	private String gender;
	private String color;
	public PersonEO (){}
	public PersonEO ( int id , String name , String gender, String color){
		this.ID = id;
		this.name = name;
		this.gender = gender;
		this.color = color;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
