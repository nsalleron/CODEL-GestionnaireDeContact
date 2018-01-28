package services;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;

import daos.PhoneNumberDAO;
import entities.IContact;
import entities.PhoneNumber;

public class PhoneNumberService implements IPhoneNumberService {
	private PhoneNumberDAO pnDAO;
	
	public PhoneNumberService(PhoneNumberDAO pnDAO) {
		this.pnDAO = pnDAO;
	}
	
	/* (non-Javadoc)
	 * @see services.IPhoneNumberService#createPhoneNumber(java.lang.String, java.lang.String, entities.IContact)
	 */
	@Override
	public PhoneNumber createPhoneNumber(String phonekind, String phone,IContact c){
		return pnDAO.createPhoneNumber(phonekind, phone, c);
	}
	
	public void logpjp(ProceedingJoinPoint pjp, String phonekind, String phone) {
		System.out.println("logpjp()  is running with Spring Framework");
		System.out.println("Un contact change de téléphone, voici son numéro.");
		System.out.println("Son nouveau numéro est : "+phone);
		
		try {
			if(phonekind.length() < phone.length()) {
				System.out.println("Around: La requête semble correcte : proceed OK");
				System.out.println("******");
				pjp.proceed();

			}else {
				System.out.println("Around: Arf, on est dans le else, bon on est sympa ...");
				System.out.println("******");
				pjp.proceed();
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/* (non-Javadoc)
	 * @see services.IPhoneNumberService#deletePhoneNumberById(long)
	 */
	@Override
	public void deletePhoneNumberById(long id) {
		pnDAO.deletePhoneNumberById(id);
	}
	
	/* (non-Javadoc)
	 * @see services.IPhoneNumberService#updatePhoneNumberById(long, java.lang.String, java.lang.String, entities.IContact)
	 */
	@Override
	public boolean updatePhoneNumberById(long id, String phoneKind, String phoneNumber, IContact contact)  {
		return pnDAO.updatePhoneNumberById(id,phoneKind, phoneNumber, contact);
	}
	
	/* (non-Javadoc)
	 * @see services.IPhoneNumberService#getPhoneNumberById(java.lang.Long)
	 */
	@Override
	public PhoneNumber getPhoneNumberById(Long id) {
		return pnDAO.getPhoneNumberById(id);
	}
	/* (non-Javadoc)
	 * @see services.IPhoneNumberService#listPhoneNumberGroups()
	 */
	@Override
	public List<String> listPhoneNumberGroups() {
		return pnDAO.listPhoneNumberGroups();
	}

	/* (non-Javadoc)
	 * @see services.IPhoneNumberService#updatePhoneNumberByPN(entities.PhoneNumber, java.lang.String, java.lang.String, entities.IContact)
	 */
	@Override
	public boolean updatePhoneNumberByPN(PhoneNumber phone, String value, String value2, IContact c) {
		return pnDAO.updatePhoneNumberByPN(phone, value, value2, c);
	}
	
}
