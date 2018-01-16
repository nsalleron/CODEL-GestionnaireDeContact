package daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Address;
import entities.Contact;
import entities.Entreprise;
import entities.PhoneNumber;
import utils.HibernateUtil;

public  class AddressDAO {

	static ArrayList<Contact> alContact = new ArrayList<Contact>();
	
	public static Address createAddress(String street, String city, String zip, String country){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.save(address);
		tx.commit();
		
		return address;
	}
	
	public static void deleteAddress(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Address address = (Address) session.load(Address.class, id);
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(address);
		tx.commit();		
	}
	
	public static boolean updateAddress(long id, String street, String city, String zip, String country) {
		try{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
			Transaction tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
			
			Address address = (Address) session.load(Address.class, id);
			if(street!=null && !street.isEmpty()) address.setStreet(street);
			if(city!=null && !city.isEmpty())address.setCity(city);
			if(zip!=null && !zip.isEmpty())address.setZip(zip);
			if(country!=null && !country.isEmpty())address.setCountry(country);
			
			tx.commit();
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static Address getAddressById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Address address = (Address) session.load(Address.class, id);
		tx.commit();
		return address;
	}
	
}