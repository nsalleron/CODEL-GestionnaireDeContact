package services;

import java.util.List;

import entities.IContact;
import entities.PhoneNumber;

public interface IPhoneNumberService {

	PhoneNumber createPhoneNumber(String phonekind, String phone, IContact c);

	void deletePhoneNumberById(long id);

	boolean updatePhoneNumberById(long id, String phoneKind, String phoneNumber, IContact contact);

	PhoneNumber getPhoneNumberById(Long id);

	List<String> listPhoneNumberGroups();

	boolean updatePhoneNumberByPN(PhoneNumber phone, String value, String value2, IContact c);

}