package services;

import daos.AddressDAO;
import entities.Address;

public class AddressService {

	
	public static Address createAddress(String street, String city, String zip, String country) {
		return AddressDAO.createAddress(street,city,zip,country);
	}
	
	public static void deleteAddress(long id) {
		AddressDAO.deleteAddress(id);
	}
	
	public static void deleteAddress(Address address) {
		AddressDAO.deleteAddress(address);
	}
	
	public static void updateAddress(long id, String street, String city, String zip, String country) {
		AddressDAO.updateAddress(id,street,city,zip,country);
	}
	
	public static Address getAddress(Long id) {
		return AddressDAO.getAddressById(id);
	}
}
