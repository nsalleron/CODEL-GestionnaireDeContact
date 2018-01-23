package entities;


public class PhoneNumber {
	private long idPhoneNumber;
	private String phoneKind;
	private String phoneNumber;
	private Contact contact;
	private int version;
	
	public PhoneNumber() {	
		this("DEFAULT","DEFAULT",new Contact("DEFAULT","DEFAULT","DEFAULT@DEFAULT.COM"));
	}

	public PhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		super();
		this.contact = contact;
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public long getIdPhoneNumber() {
		return idPhoneNumber;
	}
	public void setIdPhoneNumber(long idPhoneNumber) {
		this.idPhoneNumber = idPhoneNumber;
	}
	public String getPhoneKind() {
		return phoneKind;
	}
	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
	
	
	

}
