package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entities.Contact;
import entities.PhoneNumber;
import utils.HibernateUtil;

public class PhoneNumberDAO extends HibernateDaoSupport{
	
	public PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		
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
	
	public void deletePhoneNumberById(long id) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();

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
	
	public void deletePhoneNumberById(PhoneNumber phone) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
	
		Transaction transaction = session.getTransaction();
		
		if(!transaction.isActive())
			transaction = session.beginTransaction();
		
		session.delete(phone);
		
		//transaction.commit();
	}
	
	public boolean updatePhoneNumberById(long id, String phoneKind, String phoneNumber, Contact contact) {
		boolean bPhoneKind = false, bPhoneNumber = false;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		System.out.println("----> Identifiant de PhoneNumber : "+id);
		
		PhoneNumber phone = (PhoneNumber) session.load(PhoneNumber.class, id);
		
		/* Ctrl errors */
		if(phoneKind!=null && !phoneKind.isEmpty()) 
			bPhoneKind = true;
		if(phoneNumber!=null && !phoneNumber.isEmpty())
			bPhoneNumber = true;
		System.out.println("----> Valeur de bPhoneKind :" +bPhoneKind);
		System.out.println("----> Valeur de bPhoneNumber :"+bPhoneNumber);
		
		/* Try Catch? */
		if(bPhoneKind && bPhoneNumber) {
			System.out.println("----> PhoneNumber getID : "+phone.getIdPhoneNumber());
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
	
	public boolean updatePhoneNumberById(List<Long> idList, List<String> kindList, List<String> number, Contact contact) {
		
		for(int i=0; i<idList.size(); i++) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			Transaction transaction = session.getTransaction();
			if(!transaction.isActive()) 
				transaction = session.beginTransaction();
			
			PhoneNumber phone = (PhoneNumber) session.load(PhoneNumber.class, idList.get(i));
			phone.setPhoneKind(kindList.get(i));
			phone.setPhoneNumber(number.get(i));
			phone.setContact(contact);
			transaction.commit();
		}
		
		return true;
	}
	
	public PhoneNumber getPhoneNumberById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		
		System.out.println("VALEUR DE LA LIGNE ID : "+id);
		PhoneNumber phone = (PhoneNumber) session.get(PhoneNumber.class, id);
		transaction.commit();
		return phone;
	}
	
	public List<String> listPhoneNumberGroups(){
		List<String> rm = new ArrayList<String>();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		if(!transaction.isActive()) 
			transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PhoneNumber> lc = session.createCriteria(PhoneNumber.class).list();
		session.getTransaction().commit();
		
		for(PhoneNumber pn : lc) {
			rm.add(pn.getPhoneKind());
		}
	
		return rm;
	}	
}
