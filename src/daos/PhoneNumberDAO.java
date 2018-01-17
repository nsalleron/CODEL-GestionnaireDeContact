package daos;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entities.Contact;
import entities.PhoneNumber;
import utils.HibernateUtil;

public  class PhoneNumberDAO {

	static ArrayList<Contact> alContact = new ArrayList<Contact>();
	
	public static PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("DEBUG OBJT SESSION : "+session.toString());
		
		PhoneNumber phone = new PhoneNumber();
		phone.setPhoneKind(phoneKind);
		phone.setPhoneNumber(phoneNumber);
		phone.setContact(contact);
		
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive())
			transaction = session.beginTransaction();
		
		session.save(phone);
		
		transaction.commit();
		
		return phone;
	}
	
	public static void deletePhoneNumberById(long id) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("DEBUG OBJT SESSION : "+session.toString());
		Transaction transaction = session.getTransaction();
		
		if(!transaction.isActive())
			transaction = session.beginTransaction();

		PhoneNumber phone = (PhoneNumber) session.load(PhoneNumber.class, id);
		transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		session.delete(phone);
		
		transaction.commit();
	}
	
	public static boolean updatePhoneNumberById(long id, String phoneKind, String phoneNumber, Contact contact) {
		boolean bPhoneKind = false, bPhoneNumber = false;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("DEBUG OBJT SESSION : "+session.toString());
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		PhoneNumber phone = (PhoneNumber) session.load(PhoneNumber.class, id);
		
		/* Ctrl errors */
		if(phoneKind!=null && !phoneKind.isEmpty()) 
			bPhoneKind = true;
		if(phoneNumber!=null && !phoneNumber.isEmpty())
			bPhoneNumber = true;
		
		/* Try Catch? */
		if(bPhoneKind && bPhoneNumber) {
			phone.setPhoneKind(phoneKind);
			phone.setPhoneNumber(phoneNumber);
		}else {
			transaction.commit();
			return false;
		}
		
		session.saveOrUpdate(phone);
		transaction.commit();
		
		return true;
	}
	
	public static PhoneNumber getPhoneNumberById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("DEBUG OBJT SESSION : "+session.toString());
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		PhoneNumber phone = (PhoneNumber) session.load(PhoneNumber.class, id);
		transaction.commit();
		return phone;
	}
	
}
