package entities;

public class Address {
	private long idAddress;
	private String street;
	private String city;
	private String zip;
	private String country;
	
	public Address() {
		super();
	}
	public Address(String street, String city, String zip, String country) {
		super();
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}
	public long getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(long idAddress) {
		this.idAddress = idAddress;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
