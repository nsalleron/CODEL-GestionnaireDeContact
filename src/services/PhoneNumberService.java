package services;

import java.util.List;


import daos.PhoneNumberDAO;
import entities.IContact;
import entities.PhoneNumber;

public class PhoneNumberService {
	private PhoneNumberDAO pnDAO;
	
	public PhoneNumberService(PhoneNumberDAO pnDAO) {
		this.pnDAO = pnDAO;
	}
	
	public PhoneNumber createPhoneNumber(String phonekind, String phone,IContact c){
		return pnDAO.createPhoneNumber(phonekind, phone, c);
	}
	
	public void deletePhoneNumberById(long id) {
		pnDAO.deletePhoneNumberById(id);
	}
	
	public boolean updatePhoneNumberById(long id, String phoneKind, String phoneNumber, IContact contact)  {
		return pnDAO.updatePhoneNumberById(id,phoneKind, phoneNumber, contact);
	}
	
//	public boolean updatePhoneNumberById(List<Long> idList, List<String> kindList, List<String> number,  IContact contact)  {
//		return pnDAO.updatePhoneNumberById(idList, kindList, number,  contact);
//	}
	public PhoneNumber getPhoneNumberById(Long id) {
		return pnDAO.getPhoneNumberById(id);
	}
	public List<String> listPhoneNumberGroups() {
		return pnDAO.listPhoneNumberGroups();
	}

	public boolean updatePhoneNumberByPN(PhoneNumber phone, String value, String value2, IContact c) {
		return pnDAO.updatePhoneNumberByPN(phone, value, value2, c);
	}
	
}
