package by.htp.trucking.entity;

public class AddressTo {
	
	private String street;
	private String house;
	private String flat;
	private City city;
	
	public AddressTo() {
	}

	public AddressTo(String street, String house, String flat, City city) {
		this.street = street;
		this.house = house;
		this.flat = flat;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
