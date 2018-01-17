package services;

import daos.PhoneNumberDAO;
import entities.Contact;
import entities.PhoneNumber;

public class PhoneNumberService {

	
	public static PhoneNumber createPhoneNumber(String phonekind, String phone,Contact c){
		return PhoneNumberDAO.createPhoneNumber(phonekind, phone, c);
	}
	
	public static void deletePhoneNumberById(long id) {
		PhoneNumberDAO.deletePhoneNumberById(id);
	}
	
	public static boolean updatePhoneNumberById(long id, String phoneKind, String phoneNumber, Contact contact)  {
		return PhoneNumberDAO.updatePhoneNumberById(id,phoneKind, phoneNumber, contact);
	}
	
	public static PhoneNumber getPhoneNumberById(Long id) {
		return PhoneNumberDAO.getPhoneNumberById(id);
	}
}
