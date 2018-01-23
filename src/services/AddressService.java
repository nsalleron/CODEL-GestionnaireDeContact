package services;

import daos.AddressDAO;
import entities.Address;

public class AddressService {
	private AddressDAO aDAO;
	
	public AddressService(AddressDAO aDAO) {
		this.aDAO = aDAO;
	}
	
	public Address createAddress(String street, String city, String zip, String country) {
		return aDAO.createAddress(street,city,zip,country);
	}
	
	public void deleteAddress(long id) {
		aDAO.deleteAddress(id);
	}
	
	public void deleteAddress(Address address) {
		aDAO.deleteAddress(address);
	}
	
	public void updateAddress(long id, String street, String city, String zip, String country) {
		aDAO.updateAddress(id,street,city,zip,country);
	}
	
	public Address getAddress(Long id) {
		return aDAO.getAddressById(id);
	}
}
