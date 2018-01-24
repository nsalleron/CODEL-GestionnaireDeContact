package entities;

import java.util.Set;


public class Contact implements IContact {
	private long idContact;
	private String firstName;
	private String lastName;
	private String email;
	private Address add;
	private Set<ContactGroup> books;
	private Set<PhoneNumber> phones;
	private int version;
	
	public Contact() {
		super();
	}
	
	public Contact(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see entities.IContact#getIdContact()
	 */
	@Override
	public long getIdContact() {
		return idContact;
	}
	/* (non-Javadoc)
	 * @see entities.IContact#setIdContact(long)
	 */
	@Override
	public void setIdContact(long idContact) {
		this.idContact = idContact;
	}
	/* (non-Javadoc)
	 * @see entities.IContact#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}
	/* (non-Javadoc)
	 * @see entities.IContact#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String name) {
		this.firstName = name;
	}
	/* (non-Javadoc)
	 * @see entities.IContact#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}
	/* (non-Javadoc)
	 * @see entities.IContact#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/* (non-Javadoc)
	 * @see entities.IContact#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}
	/* (non-Javadoc)
	 * @see entities.IContact#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	/* (non-Javadoc)
	 * @see entities.IContact#getAdd()
	 */
	@Override
	public Address getAdd() {
		return add;
	}

	/* (non-Javadoc)
	 * @see entities.IContact#setAdd(entities.Address)
	 */
	@Override
	public void setAdd(Address add) {
		this.add = add;
	}

	/* (non-Javadoc)
	 * @see entities.IContact#getBooks()
	 */
	@Override
	public Set<ContactGroup> getBooks() {
		return books;
	}

	/* (non-Javadoc)
	 * @see entities.IContact#setBooks(java.util.Set)
	 */
	@Override
	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}

	/* (non-Javadoc)
	 * @see entities.IContact#getPhones()
	 */
	@Override
	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	/* (non-Javadoc)
	 * @see entities.IContact#setPhones(java.util.Set)
	 */
	@Override
	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}

	/* (non-Javadoc)
	 * @see entities.IContact#getVersion()
	 */
	@Override
	public int getVersion() {
		return version;
	}

	/* (non-Javadoc)
	 * @see entities.IContact#setVersion(int)
	 */
	@Override
	public void setVersion(int version) {
		this.version = version;
	}
	
	/* (non-Javadoc)
	 * @see entities.IContact#log()
	 */
	@Override
	public String log() {
		System.out.println("HELLO");
		return "-----------hello";
	}

}
