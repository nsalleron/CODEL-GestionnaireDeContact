package entities;

public class Entreprise extends Contact {
	
	private long numSiret;
	private int version;

	public Entreprise() {
		super();
	}
	
	public Entreprise(String firstName, String lastName, String email, long numSiret){
		super(firstName, lastName, email);
		this.numSiret = numSiret;
	}

	public long getNumSiret() {
		return numSiret;
	}
	public void setNumSiret(long numSiret) {
		this.numSiret = numSiret;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}	
	
	

}
