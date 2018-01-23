package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ContactService contactService = (ContactService) context.getBean("beanContactService");
		AddressService addressService = (AddressService) context.getBean("beanAddressService");
		EntrepriseService entrepriseService = (EntrepriseService) context.getBean("beanEntrepriseService");
		PhoneNumberService phoneNumberService = (PhoneNumberService) context.getBean("beanPhoneNumberService");
		ContactGroupService contactGroupService = (ContactGroupService) context.getBean("beanContactGroupService");
		
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

		
		String tmp = "";
		/* Récupération des versions et des identifiants */
		while(tmp != null) {
			tmp = request.getParameter("updatePhones"+i);
			if(tmp != null) {
				alPhones.add(tmp);
				i++;
			}else {
				i=0;
				tmp = "";
				break;
			}
		}
		
		while(tmp != null) {
			tmp = request.getParameter("updateCG"+i);
			if(tmp != null) {
				alCg.add(tmp);
				i++;
			}else {
				i=1;
				tmp = "";
				break;
			}
		}
		
		/* Récupération nouvelles valeurs */
		while(tmp != null) {
			tmp = request.getParameter("telephone"+i);
			if(tmp != null) {
				alNewPhone.add(new StringAndBoolean(tmp,false));
				i++;
			}else {
				i=1;
				tmp = "";
				break;
			}
		}
		
		while(tmp != null) {
			tmp = request.getParameter("phonekind"+i);
			if(tmp != null) {
				alNewPhoneKind.add(new StringAndBoolean(tmp,false));
				i++;
			}else {
				i=1;
				tmp = "";
				break;
			}
		}
		
		i = 1;
		while(tmp != null) {
			tmp = request.getParameter("groupe"+i);
			if(tmp != null) {
				alNewContactGroups.add(new StringAndBoolean(tmp,false));
				i++;
			}else {
				i=1;
				tmp = "";
				break;
			}
		}
		
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
		
		Address a = addressService.getAddress(idAddressFromUser);
		ArrayList<PhoneNumber> phonesDB = new ArrayList<PhoneNumber>();
		ArrayList<ContactGroup> cgDB = new ArrayList<ContactGroup>();
		boolean bAllOk = true, bRemoveFirst = false;
		String rep = "";
		
		for(i = 0;i<tabPhones.length ; i++) {
			phonesDB.add(phoneNumberService.getPhoneNumberById(tabPhones[i]));
		}
		
		for(i = 0;i<tabCG.length ; i++) {
			cgDB.add(contactGroupService.getContactGroupById(tabCG[i]));
		}
		
		if(a.getVersion() != verAddressFromUser) {
			bAllOk = false;
			
		}
						
		for(i = 0;i<verPhones.length ; i++) {
			if(verPhones[i] != phonesDB.get(i).getVersion()) {
				bAllOk = false;
			}
		}
		for(i = 0;i<verCG.length ; i++) {
			if(verCG[i] != cgDB.get(i).getVersion()) {
				bAllOk = false;
			}
		}
		
		for(StringAndBoolean phone : alNewPhone)
			if(phone.value!=null && phone.value.length()>0) {}
			else {
				bAllOk = false;
				break;
			}

		for(StringAndBoolean phonekind : alNewPhoneKind)
			if(phonekind.value!=null && phonekind.value.length()>0) {}
			else {
				bAllOk = false;
				break;
			}

		if(siret != null && siret.length() > 0) {	//Cas entreprise
			Entreprise e = entrepriseService.getEntrepriseById(idContactFromUser);
			if(e.getVersion() != verContactFromUser) {
				bAllOk = false;
			}
			
			if(bAllOk) {
				e.setFirstName(firstName);
				e.setLastName(lastName);
				e.setEmail(email);
				entrepriseService.updateEntreprise(e.getIdContact(), firstName, lastName, email, siret);
				
				if(alNewPhone.size() != 0 && tabPhones.length != 0)
					for(i = 0;i < alNewPhone.size();i++) {
						if(i < (tabPhones.length - 1)) {
							phoneNumberService.updatePhoneNumberById(tabPhones[i],
									alNewPhoneKind.get(i).value, alNewPhone.get(i).value, e);
						}else {
							phoneNumberService.createPhoneNumber(alNewPhoneKind.get(i).value, alNewPhone.get(i).value, e);
							bRemoveFirst = true;
						}
					}
				
				if(bRemoveFirst) {
					Set<PhoneNumber> spn = e.getPhones();
					PhoneNumber pn = spn.iterator().next();
					phoneNumberService.deletePhoneNumberById(pn.getIdPhoneNumber());
					bRemoveFirst = false;
				}
					
				if(alNewContactGroups.size() != 0 && tabCG.length != 0)
					for(i = 0;i < alNewContactGroups.size();i++) {
						if(i < (tabCG.length - 1)) {
							contactGroupService.updateContactGroupById(tabCG[i], alNewContactGroups.get(i).value);
						}else {
							contactGroupService.createContactGroup(alNewContactGroups.get(i).value);
							//bRemoveFirst = true;
						}
					}
				/*
				if(bRemoveFirst) {
					Set<ContactGroup> scg = e.getBooks();
					ContactGroup cg = scg.iterator().next();
					ContactGroupService.deleteContactGroupById(cg.getIdContactGroup());
					bRemoveFirst = false;
				}*/
			}else {
				rep = "La mise à jour à échouer. "
						+ "Il y a eu une update en concurrence avec la votre sur l'entreprise : "+e.getLastName() + " " + e.getFirstName();
			}
		}else {										//Cas Contact
			Contact c = contactService.getContactById(idContactFromUser);
			if(c.getVersion() != verContactFromUser) 
				bAllOk = false;
			
			if(bAllOk) {
				c.setFirstName(firstName);
				c.setLastName(lastName);
				c.setEmail(email);
				contactService.updateContact(c.getIdContact(), firstName, lastName, email);
				
				if(alNewPhone.size() != 0 && tabPhones.length != 0)
					for(i = 0;i < alNewPhone.size();i++) {
						if(i < (tabPhones.length - 1)) {
							phoneNumberService.updatePhoneNumberById(tabPhones[i],
									alNewPhoneKind.get(i).value, alNewPhone.get(i).value, c);
						}else {
							phoneNumberService.createPhoneNumber(alNewPhoneKind.get(i).value, alNewPhone.get(i).value, c);
							bRemoveFirst = true;
						}
						
					}

				if(bRemoveFirst) {
					Set<PhoneNumber> spn = c.getPhones();
					PhoneNumber pn = spn.iterator().next();
					phoneNumberService.deletePhoneNumberById(pn.getIdPhoneNumber());
					bRemoveFirst = false;
				}
				
				if(alNewContactGroups.size() != 0 && tabCG.length != 0)
					for(i = 0;i < alNewContactGroups.size();i++) {
						if(i < (tabCG.length - 1)) {
							contactGroupService.updateContactGroupById(tabCG[i], alNewContactGroups.get(i).value);
						}else {
							contactGroupService.createContactGroup(alNewContactGroups.get(i).value);
						}
						
					}
				
			}else {
				rep = "La mise à jour à échouer. "
						+ "Il y a eu une update en concurrence avec la votre sur le contact : "+c.getLastName() + " " + c.getFirstName();
			}
		}
			
		
		if(!bAllOk) {
			request.setAttribute("updatefailed",rep); 
			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		}
		
		if(bAllOk) {
			
			addressService.updateAddress(a.getIdAddress(), street, city, zip, country);

			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		}		
	}
}
