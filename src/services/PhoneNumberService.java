package services;

import java.util.List;


import daos.PhoneNumberDAO;
import entities.Contact;
import entities.PhoneNumber;

public class PhoneNumberService {
	private PhoneNumberDAO pnDAO;
	
	public PhoneNumberService(PhoneNumberDAO pnDAO) {
		this.pnDAO = pnDAO;
	}
	
	public PhoneNumber createPhoneNumber(String phonekind, String phone,Contact c){
		return pnDAO.createPhoneNumber(phonekind, phone, c);
	}
	
	public void deletePhoneNumberById(long id) {
		pnDAO.deletePhoneNumberById(id);
	}
	
	public boolean updatePhoneNumberById(long id, String phoneKind, String phoneNumber, Contact contact)  {
		return pnDAO.updatePhoneNumberById(id,phoneKind, phoneNumber, contact);
	}
	
	public boolean updatePhoneNumberById(List<Long> idList, List<String> kindList, List<String> number,  Contact contact)  {
		return pnDAO.updatePhoneNumberById(idList, kindList, number,  contact);
}
	public PhoneNumber getPhoneNumberById(Long id) {
		return pnDAO.getPhoneNumberById(id);
	}
	public List<String> listPhoneNumberGroups() {
		return pnDAO.listPhoneNumberGroups();
	}
	
}
