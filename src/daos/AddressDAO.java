package daos;

import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entities.Address;
import utils.HibernateUtil;

public  class AddressDAO extends HibernateDaoSupport{
	
	public Address createAddress(String street, String city, String zip, String country){
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
	
	public void deleteAddress(long id) {
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
	
	public void deleteAddress(Address address) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction transaction = session.getTransaction();
		
		if(!transaction.isActive())
			transaction = session.beginTransaction();
		
		session.delete(address);
		
		//transaction.commit();
	} 
	
	public boolean updateAddress(long id, String street, String city, String zip, String country) {
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
	
	public Address getAddressById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		System.out.println("Identifiant de l'addresse : "+id);
		Address address = (Address) session.get(Address.class, id);
		if(address == null) {
			 address = (Address) session.load(Address.class, id);
			 address.getCity();
		}
		tx.commit();
		return address;
	}

	public boolean updateAddress(Address add, String street, String city, String zip, String country) {
		
		if(street!=null && !street.isEmpty()) add.setStreet(street);
		if(city!=null && !city.isEmpty()) add.setCity(city);
		if(zip!=null && !zip.isEmpty()) add.setZip(zip);
		if(country!=null && !country.isEmpty()) add.setCountry(country);
		
		try{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			Transaction transaction = session.getTransaction();
			if(!transaction.isActive()) transaction = session.beginTransaction();
			
			//session.saveOrUpdate(c);	//throw ?
			session.merge(add); //throw?
			
			transaction.commit();
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
}
