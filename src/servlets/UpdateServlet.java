package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Address;
import entities.Contact;
import entities.ContactGroup;
import entities.Entreprise;
import entities.PhoneNumber;
import services.AddressService;
import services.ContactGroupService;
import services.ContactService;
import services.EntrepriseService;
import services.PhoneNumberService;

/**
 * Servlet implementation class CreateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}
	
	/**
	 * InnerClass Verification
	 */
	protected class StringAndBoolean{
		public String value;
		public boolean checked;
		
		public StringAndBoolean(String value, boolean checked) {
			super();
			this.value = value;
			this.checked = checked;
		}
		
		@Override
		public String toString() {
			return "[" + value +" , " + checked + "]";
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName, lastName, email, siret, street, city, zip, country, 
		versionContact = null,versionAddress = null, idAddr = null, idContact = null;
		
		ArrayList<String> alPhones = new ArrayList<String>();
		ArrayList<String> alCg = new ArrayList<String>();
		ArrayList<StringAndBoolean>alNewPhone = new ArrayList<StringAndBoolean>();
		ArrayList<StringAndBoolean>alNewPhoneKind = new ArrayList<StringAndBoolean>();
		ArrayList<StringAndBoolean>alNewContactGroups = new ArrayList<StringAndBoolean>();
		
		int i = 0;

		/* Récupération des élements */
		firstName = request.getParameter("firstname");
		lastName = request.getParameter("lastname");
		email = request.getParameter("email");
		street = request.getParameter("street");
		city = request.getParameter("city");
		zip = request.getParameter("zip");
		country = request.getParameter("country");
		siret = request.getParameter("siret");
		idContact = request.getParameter("updateContact").split(";")[0];
		idAddr = request.getParameter("updateAddress").split(";")[0];
		versionContact = request.getParameter("updateContact").split(";")[1];
		versionAddress = request.getParameter("updateAddress").split(";")[1];
		
		/* DEBUG */
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(street);
		System.out.println(city);
		System.out.println(zip);
		System.out.println(country);
		System.out.println(siret);
		System.out.println(idContact);
		System.out.println(versionContact);
		System.out.println(idAddr);
		System.out.println(versionAddress);
		
		String tmp = "";
		/* Récupération des versions et des identifiants */
		while(tmp != null) {
			tmp = request.getParameter("updatePhones"+i);
			if(tmp != null) {
				System.out.println("updatePhones"+i);
				alPhones.add(tmp);
				i++;
			}else {
				i=0;
				tmp = "";
				System.out.println("----> BREAK UPDATEPHONES");
				break;
			}
		}
		
		System.out.println(alPhones);
		while(tmp != null) {
			tmp = request.getParameter("updateCG"+i);
			if(tmp != null) {
				System.out.println("updateCG"+i);
				alCg.add(tmp);
				i++;
			}else {
				i=1;
				tmp = "";
				System.out.println("----> BREAK UPDATECG");
				break;
			}
		}
		
		/* Récupération nouvelles valeurs */
		while(tmp != null) {
			tmp = request.getParameter("telephone"+i);
			if(tmp != null) {
				System.out.println("alPhone +1");
				alNewPhone.add(new StringAndBoolean(tmp,false));
				i++;
			}else {
				System.out.println("alPhoneLoop -> break;");
				i=1;
				tmp = "";
				break;
			}
		}
		
		System.out.println(alNewPhone);
		while(tmp != null) {
			tmp = request.getParameter("phonekind"+i);
			if(tmp != null) {
				System.out.println("alPhoneKind +1");
				alNewPhoneKind.add(new StringAndBoolean(tmp,false));
				i++;
			}else {
				System.out.println("alPhoneKind -> break;");
				i=1;
				tmp = "";
				break;
			}
		}
		
		System.out.println(alNewPhoneKind);
		
		i = 1;
		while(tmp != null) {
			tmp = request.getParameter("groupe"+i);
			if(tmp != null) {
				System.out.println("alContactGroups +1");
				alNewContactGroups.add(new StringAndBoolean(tmp,false));
				i++;
			}else {
				System.out.println("alContactGroups -> break;");
				i=1;
				tmp = "";
				break;
			}
		}
		
		System.out.println(alNewContactGroups);
		
		long idContactFromUser = Long.parseLong(idContact);
		long verContactFromUser = Long.parseLong(versionContact);
		long idAddressFromUser = Long.parseLong(idAddr);
		long verAddressFromUser = Long.parseLong(versionAddress);
		
		long[] tabPhones = new long[alPhones.size()];
		long[] verPhones = new long[alPhones.size()];
		long[] tabCG = new long[alCg.size()];
		long[] verCG = new long[alCg.size()];
		
		
		i = 0;
		for(i = 0;i<tabPhones.length ; i++) {
			String[] idAndVerPhone = alPhones.get(i).split(";");
			tabPhones[i] = Long.parseLong(idAndVerPhone[0]);
			verPhones[i] = Long.parseLong(idAndVerPhone[1]);
		}
		for(i = 0;i<tabCG.length ; i++) {
			String[] idAndVerCG = alCg.get(i).split(";");
			tabCG[i] = Long.parseLong(idAndVerCG[0]);
			verCG[i] = Long.parseLong(idAndVerCG[1]);
		}
		
		Address a = AddressService.getAddress(idAddressFromUser);
		ArrayList<PhoneNumber> phonesDB = new ArrayList<PhoneNumber>();
		ArrayList<ContactGroup> cgDB = new ArrayList<ContactGroup>();
		boolean bAllOk = true;
		String rep = "";
		
		for(i = 0;i<tabPhones.length ; i++) {
			phonesDB.add(PhoneNumberService.getPhoneNumberById(tabPhones[i]));
		}
		System.out.println(phonesDB);
		for(i = 0;i<tabCG.length ; i++) {
			cgDB.add(ContactGroupService.getContactGroupById(tabCG[i]));
		}
		
		if(a.getVersion() != verAddressFromUser) {
			bAllOk = false;
			System.out.println("a.getVersion "+a.getVersion() + " versionContact : "+verAddressFromUser);
		}
						
		for(i = 0;i<verPhones.length ; i++) {
			if(verPhones[i] != phonesDB.get(i).getVersion()) {
				bAllOk = false;
				System.out.println("verPhones : "+verPhones[i]+" celui db : "+phonesDB.get(i).getIdPhoneNumber());
			}
		}
		for(i = 0;i<verCG.length ; i++) {
			if(verCG[i] != cgDB.get(i).getVersion()) {
				bAllOk = false;
				System.out.println("verPhones : "+verCG[i]+" celui db : "+cgDB.get(i).getIdContactGroup());
			}
		}
		
		for(StringAndBoolean phone : alNewPhone)
			if(phone.value!=null && phone.value.length()>0) {}
			else {
				bAllOk = false;
				break;
			}
		System.out.println("Après alNewPhone: " + bAllOk);
		for(StringAndBoolean phonekind : alNewPhoneKind)
			if(phonekind.value!=null && phonekind.value.length()>0) {}
			else {
				bAllOk = false;
				break;
			}
		System.out.println("Après alNewPhoneKind: " + bAllOk);

		if(siret != null && siret.length() > 0) {	//Cas entreprise
			Entreprise e = EntrepriseService.getEntrepriseById(idContactFromUser);
			if(e.getVersion() != verContactFromUser) {
				bAllOk = false;
			}
			
			if(bAllOk) {
				e.setFirstName(firstName);
				e.setLastName(lastName);
				e.setEmail(email);
				EntrepriseService.updateEntreprise(e.getIdContact(), firstName, lastName, email, siret);
				
				if(alNewPhone.size() != 0 && tabPhones.length != 0)
					for(i = 0;i < alNewPhone.size();i++) {
						PhoneNumberService.updatePhoneNumberById(tabPhones[i],
								alNewPhoneKind.get(i).value, alNewPhone.get(i).value, e);
					}
				if(alNewContactGroups.size() != 0 && tabCG.length != 0)
					for(i = 0;i < alNewContactGroups.size();i++) {
						ContactGroupService.updateContactGroupById(tabCG[i], alNewContactGroups.get(i).value);
					}
				
			}else {
				rep = "La mise à jour à échouer. "
						+ "Il y a eu une update en concurrence avec la votre sur l'entreprise : "+e.getLastName() + " " + e.getFirstName();
			}
		}else {										//Cas Contact
			Contact c = ContactService.getContactById(idContactFromUser);
			if(c.getVersion() != verContactFromUser) 
				bAllOk = false;
			
			if(bAllOk) {
				c.setFirstName(firstName);
				c.setLastName(lastName);
				c.setEmail(email);
				ContactService.updateContact(c.getIdContact(), firstName, lastName, email);
				
				if(alNewPhone.size() != 0 && tabPhones.length != 0)
					for(i = 0;i < alNewPhone.size();i++) {
						PhoneNumberService.updatePhoneNumberById(tabPhones[i],
								alNewPhoneKind.get(i).value, alNewPhone.get(i).value, c);
					}
				if(alNewContactGroups.size() != 0 && tabCG.length != 0)
					for(i = 0;i < alNewContactGroups.size();i++) {
						ContactGroupService.updateContactGroupById(tabCG[i], alNewContactGroups.get(i).value);
					}
				
			}else {
				rep = "La mise à jour à échouer. "
						+ "Il y a eu une update en concurrence avec la votre sur le contact : "+c.getLastName() + " " + c.getFirstName();
			}
		}
			
		
		if(!bAllOk) {
			request.setAttribute("updatefailed",rep); 
			System.out.println("FAILED : "+rep);
			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		}
		
		if(bAllOk) {
			
			AddressService.updateAddress(a.getIdAddress(), street, city, zip, country);
			
			System.out.println("alNewPhone size : "+alNewPhone.size());
			System.out.println("alNewPhone size : "+alNewPhoneKind.size());
			System.out.println("verPhones size : "+verPhones.length);

			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		}		
	}
}
