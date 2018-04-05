package by.htp.trucking.entity;

public class UserInfo extends User {
	
	private int id;
	private String name;
	private String surname;
	private String phoneNumber;
	private String address;
	private int idUser;
	private int idCity;
	
	public UserInfo() {
		super();
	}

	public UserInfo(String name, String surname, String phoneNumber, String address, int idUser, int idCity) {
		super();
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.idUser = idUser;
		this.idCity = idCity;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdCity() {
		return idCity;
	}

	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}
	
	
	
}
