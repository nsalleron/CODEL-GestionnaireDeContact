package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import entities.IContact;
import entities.PhoneNumber;
import services.AddressService;
import services.ContactGroupService;
import services.EntrepriseService;
import services.IContactService;
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
		IContactService contactService = (IContactService) context.getBean("beanContactService");
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
		IContact contactObj = (IContact) request.getSession().getAttribute("CONTACTOBJ");

		System.out.println("CONTACT : "+contactObj.toString());

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
		
		boolean bAllOk = true;
		String rep = "";

		for(StringAndBoolean phone : alNewPhone)
			if(phone.value!=null && phone.value.length()>0) {}
			else {
				System.out.println("-> Problème phone");
				bAllOk = false;
				break;
			}

		for(StringAndBoolean phonekind : alNewPhoneKind)
			if(phonekind.value!=null && phonekind.value.length()>0) {}
			else {
				System.out.println("-> Problème phoneKind");
				bAllOk = false;
				break;
			}

		if(siret != null && siret.length() > 0) {	//Cas entreprise
			IContact e = contactObj;
			
			if(e.getVersion() != verContactFromUser) {
				bAllOk = false;
			}

			if(bAllOk) {
				e.setFirstName(firstName);
				e.setLastName(lastName);
				e.setEmail(email);
				if(!(bAllOk = entrepriseService.updateEntreprise(e, firstName, lastName, email, siret))) {
					rep = setError(e);
				}
				
				if(alNewPhone.size() != 0 && tabPhones.length != 0)
					if(!bAllOk) {
						rep = setError(e);
					}else 
						for(i = 0;i < alNewPhone.size();i++) {
							if(i <= (tabPhones.length - 1)) {
								bAllOk = phoneNumberService.updatePhoneNumberByPN((PhoneNumber)(((Set<PhoneNumber>) e.getPhones()).toArray())[i],
										alNewPhoneKind.get(i).value,
										alNewPhone.get(i).value, e);
								if(!bAllOk) {
									rep = setError(e);
									break;
								}
							}else {
								phoneNumberService.createPhoneNumber(alNewPhoneKind.get(i).value, alNewPhone.get(i).value, e);

							}
						}

				if(alNewContactGroups.size() != 0 && tabCG.length != 0)
					if(!bAllOk) {
						rep = setError(e);
					}else 
						for(i = 0;i < alNewContactGroups.size();i++) {
							if(i <= (tabCG.length - 1)) {
								bAllOk = contactGroupService.updateContactGroupByCG((ContactGroup)(((Set<ContactGroup>) e.getBooks()).toArray())[i],
										alNewContactGroups.get(i).value);
								if(!bAllOk) {
									rep = setError(e);
									break;
								}
							}else {
								contactGroupService.createContactGroup(alNewContactGroups.get(i).value);
								contactService.addContactInGroup(e.getIdContact(),
										contactGroupService.getContactGroupByName(alNewContactGroups.get(i).value).getIdContactGroup());
							}
						}

				
				if(!bAllOk) {
					rep = setError(e);
				}else {
					bAllOk = addressService.updateAddress(e.getAdd(), street, city, zip, country);
				}

			}else {
				rep = "La mise à jour à échouer. "
						+ "Il y a eu une update en concurrence avec la votre sur l'entreprise : "+e.getLastName() + " " + e.getFirstName();
			}
		}else {										//Cas Contact
			IContact c = contactObj;//contactService.getContactById(idContactFromUser);
			//if(c.getVersion() != verContactFromUser) 
			//	bAllOk = false;

			if(bAllOk) {
				c.setFirstName(firstName);
				c.setLastName(lastName);
				c.setEmail(email);
				
				bAllOk = contactService.updateContact(c, firstName, lastName, email);
				System.out.println("value of bAllOk" + bAllOk);
				if(!(bAllOk)) {
					rep = setError(c);
				}

				if(alNewPhone.size() != 0 && tabPhones.length != 0)
					if(!bAllOk) {
						rep = setError(c);
					}else 
						for(i = 0;i < alNewPhone.size();i++) {
							System.out.println("->for loop alNewPhone : "+i);
							if(i <= (tabPhones.length - 1)) {
								System.out.println("-->UpdatePhoneNumberById");
								bAllOk = phoneNumberService.updatePhoneNumberByPN((PhoneNumber)(((Set<PhoneNumber>) c.getPhones()).toArray())[i],
										alNewPhoneKind.get(i).value,
										alNewPhone.get(i).value, c);
								if(!bAllOk) {
									rep = setError(c);
									break;
								}
							}else {
								System.out.println("-->createPhoneNumber");
								phoneNumberService.createPhoneNumber(alNewPhoneKind.get(i).value, alNewPhone.get(i).value, c);

							}

						}

				if(alNewContactGroups.size() != 0 && tabCG.length != 0)
					if(!bAllOk) {
						rep = setError(c);
					}else 
						for(i = 0;i < alNewContactGroups.size();i++) {
							if(i <= (tabCG.length - 1)) {
								//contactGroupService.updateContactGroupById(tabCG[i], alNewContactGroups.get(i).value);
								bAllOk = contactGroupService.updateContactGroupByCG((ContactGroup)(((Set<ContactGroup>) c.getBooks()).toArray())[i],
										alNewContactGroups.get(i).value);
								if(!bAllOk) {
									rep = setError(c);
									break;
								}
							}else {
								contactGroupService.createContactGroup(alNewContactGroups.get(i).value);
								contactService.addContactInGroup(c.getIdContact(),
										contactGroupService.getContactGroupByName(alNewContactGroups.get(i).value).getIdContactGroup());
							}

						}

				
				if(!bAllOk) {
					rep = setError(c);
				}else {
					bAllOk = addressService.updateAddress(c.getAdd(), street, city, zip, country);
				}
			}else {
				rep = setError(c);
			}
		}

		System.out.println("before");
		if(!bAllOk) {
			System.out.println("failed");
			request.setAttribute("updatefailed",rep); 
			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		}

		if(bAllOk) {		
			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		}		
	}

	private String setError(IContact c) {
		String rep;
		rep = "La mise à jour à échouer. "
				+ "Il y a eu une update en concurrence avec la votre sur le contact : "+c.getLastName() + " " + c.getFirstName();
		return rep;
	}
}
