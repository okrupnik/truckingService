package by.htp.trucking.entity;

import java.time.LocalDate;

public class OrderInfo {
	
	private String title;
	private LocalDate dateOfDelivery;
	private double priceShipment;
	private int weight;	
	private String comment;
	private String statusDelete;
	private AddressFrom addressFrom;
	private AddressTo addressTo;
	
	public OrderInfo() {
	}
	
	public OrderInfo(String title, LocalDate dateOfDelivery, double priceShipment, int weight, String comment,
			 AddressFrom addressFrom, AddressTo addressTo) {
		this.title = title;
		this.dateOfDelivery = dateOfDelivery;
		this.priceShipment = priceShipment;
		this.weight = weight;
		this.comment = comment;
		this.addressFrom = addressFrom;
		this.addressTo = addressTo;
	}

	public OrderInfo(String title, LocalDate dateOfDelivery, double priceShipment, int weight, String comment,
			String statusDelete, AddressFrom addressFrom, AddressTo addressTo) {
		this.title = title;
		this.dateOfDelivery = dateOfDelivery;
		this.priceShipment = priceShipment;
		this.weight = weight;
		this.comment = comment;
		this.statusDelete = statusDelete;
		this.addressFrom = addressFrom;
		this.addressTo = addressTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(LocalDate dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public double getPriceShipment() {
		return priceShipment;
	}

	public void setPriceShipment(double priceShipment) {
		this.priceShipment = priceShipment;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatusDelete() {
		return statusDelete;
	}

	public void setStatusDelete(String statusDelete) {
		this.statusDelete = statusDelete;
	}

	public AddressFrom getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(AddressFrom addressFrom) {
		this.addressFrom = addressFrom;
	}

	public AddressTo getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(AddressTo addressTo) {
		this.addressTo = addressTo;
	}
}
