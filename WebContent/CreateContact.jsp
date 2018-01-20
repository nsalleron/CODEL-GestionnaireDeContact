<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<meta name="Content-Language" content="fr" />
<meta name="Description" content="" />
<meta name="Subject" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="style.css" />
</head>
<%@page import="services.ContactGroupService"%>
<%@page import="services.PhoneNumberService"%>
<%@page import="entities.ContactGroup"%>
<%@page import="entities.PhoneNumber"%>
<%@page import="entities.Entreprise"%>
<%@page import="entities.Contact"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
</head>
<script type="text/javascript">
	var nbNumero = 1;
	var nbGroupe = 1;
	
	function changeGroups(e) {
		document.getElementById("groupe"+e.target.name.substr(17,e.target.name.length)).value = e.target.value;
	}
	function changePhoneKind(e) {
		document.getElementById(e.target.name.substr(3,e.target.name.length)).value = e.target.value;
	}
	function add_fields_telephone(e) {
		  	var d = document.getElementById("input-group-telephone");	
		  	var phone = document.getElementById("tel1");
		  	var kind = document.getElementById("phonekind1");
		  	var option = document.getElementById("chgphonekind1");
		  	var arrayTel = [];
		  	var arrayKind = [];
		  	var arrayOption = [];
		  	var i;
		  
		  	/* Sauvegarde des élements avant modification du HTML */
		  	for (i = 1; i < (nbNumero+1) ; i++){
		  		phone = document.getElementById("tel"+i);
		  		kind =  document.getElementById("phonekind"+i);
		  		option =  document.getElementById("chgphonekind"+i);
		  		arrayTel.push(phone.value);
		  		arrayKind.push(kind.value);
		  		arrayOption.push(option.value);
		  	}
		  	
		  	/* Augmentation du numero */
			nbNumero = nbNumero+1;
		  	
		  	/* Modification du HTML */
		   	d.innerHTML += "<br><span> Téléphone "+nbNumero+" : "; 
		   	d.innerHTML += "<span> <input type=\"tel\" class=\"form-control\" name=\"telephone"+nbNumero+"\" id=\"tel"+nbNumero+"\" maxlength=\"10\" style=\"border-radius: 4px;\"> </span>";
		   	d.innerHTML += " <label for=\"phonekind\">PhoneKind :  </label>\n";
			d.innerHTML += "<input type=\"text\"" + "STYLE=\"color: #000000; background-color: #FFFFFF;\"" + 
							"class=\"form-control\" id=\"phonekind"+nbNumero+"\" name=\"phonekind" + nbNumero+"\" value =\"\" placeholder=\"PhoneKind\">\n";
			var valueSelect = document.getElementById("chgphonekind1")		
			var buildHtml = "<select id=\"chgphonekind"+nbNumero+"\" name=\"chgphonekind"+nbNumero+"\" onchange=\"changePhoneKind(event)\">";
			for (i = 0; i < valueSelect.length; i++) {
				buildHtml += "<option value=\""+valueSelect.options[i].value + "\">"+valueSelect.options[i].text+"</option>\n";
		    }
			buildHtml += "</select><br>";
			d.innerHTML += buildHtml;
			
			/* Restauration des valeurs */
			for (i = 1; i < (nbNumero) ; i++){
				phone = document.getElementById("tel"+i);
				kind = document.getElementById("phonekind"+i);
				option =  document.getElementById("chgphonekind"+i);
				phone.value = arrayTel[i-1];
				kind.value = arrayKind[i-1];
				option.value = arrayOption[i-1]
		  		console.log("--->"+"tel"+i+" added : "+phone.value);
		  	}
			
	}
	
	function add_fields_groupe(e) {
		  	var d = document.getElementById("input-group-groupes");
		  	var groupe = document.getElementById("groupe1");
		  	var option = document.getElementById("chgcontact_groups1");
		  	var arrayGroup = [];
		  	var arrayOption = [];
		  	var i;
		  
		  	/* Sauvegarde des valeurs */
		  	for (i = 1; i < (nbGroupe+1) ; i++){
		  		groupe = document.getElementById("groupe"+i);
		  		option =  document.getElementById("chgcontact_groups"+i);
		  		arrayGroup.push(groupe.value);
		  		arrayOption.push(option.value);
		  	}
		  	
		  	/* Augmentation du nombre de groupes */
		   	nbGroupe = nbGroupe+1
		   	d.innerHTML += "<br><label> Groupe "+nbGroupe+" : </label> "; 
		   	d.innerHTML += "<input type=\"text\" class=\"form-control\" id=\"groupe"+nbGroupe+"\" name=\"groupe"+nbGroupe+"\""+"value=\"\" placeholder=\"Nom du groupe\"> ";
		  	var buildHtml = "<select id=\"chgcontact_groups"+nbGroupe+"\" name=\"chgcontact_groups"+nbGroupe+"\" onchange=\"changeGroups(event)\">"
			var valueSelect = document.getElementById("chgcontact_groups1");	
			var i;
		   	for(i=0;i< valueSelect.length; i++) {
		   		buildHtml += "<option value=\""+valueSelect.options[i].value + "\">"+valueSelect.options[i].text+"</option>\n";
		   	}
		   	buildHtml += "</select><br>";
		   	d.innerHTML += buildHtml;
		   	
		   	/* Restauration des données */
		   	for (i = 1; i < (nbGroupe) ; i++){
		   		groupe = document.getElementById("groupe"+i);
		  		option =  document.getElementById("chgcontact_groups"+i);
				groupe.value = arrayGroup[i-1];
				option.value = arrayOption[i-1]
		  	}
	}

</script>
<body>
<body class="my_background">
	<%
		/* Déclaration des éléments */
		String showError = "STYLE=\"color:#FFFFFF; border: solid 3px #6E6E6E; background-color: #ff6633; \"";
		String noError = "STYLE=\"color: #000000; background-color: #FFFFFF;\"";
		String create = "\"/CODEL-GestionnaireDeContact/CreateServlet\"";
		String update = "\"/CODEL-GestionnaireDeContact/UpdateServlet\"";
		String information, firstname, lastname, email, siret = "", street, city, zip, country, groupe, version = "";
		ArrayList<String> alPhone = new ArrayList<String>();
		ArrayList<String> alPhoneKind = new ArrayList<String>();
		ArrayList<String> alGroupes = new ArrayList<String>();
		
		String[] whereFails;
		boolean bFirstName = true, bLastName = true, bEmail = true, bStreet = true, bZip = true, bCity = true,
				bCountry = true, bPhone = true, bPhoneKind = true;
		Boolean success;
		Contact contact;
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
	<%	/* Vérification du mode (Création ou Update) */
		contact = (Contact) request.getAttribute("contact");
		if(contact == null){
	%>
				<h1>
					Création de contact <br /> <small> Merci de renseigner vos
						informations </small>
				</h1>
	<%
		}else{%>


				<h1>
					Modification de contact <br /> <small> Merci de renseigner
						vos informations </small>
				</h1>
	<%
		}
	%>

			</div>
		</div>


	<%
	information = ((String) request.getAttribute("information"));
	success = ((Boolean) request.getAttribute("success"));
	if (information != null) {
		/* Affichage endroit fail */
		whereFails = information.split(";");
		if (!success) {
	%>
		<h1>
		Il y a eu une erreur. Vérifier les champs :
	<%
		String tmp = "";
		for (String str : whereFails) {
			tmp += str + ", ";
		}
		tmp = tmp.substring(0, tmp.length() - 2) + ".";

		/*Vérification de qui à échoué */
		if (information.contains("firstname"))
			bFirstName = false;
		if (information.contains("lastname"))
			bLastName = false;
		if (information.contains("email"))
			bEmail = false;
		if (information.contains("street"))
			bStreet = false;
		if (information.contains("zip"))
			bZip = false;
		if (information.contains("city"))
			bCity = false;
		if (information.contains("country"))
			bCountry = false;
		if (information.contains("phone"))
			bPhone = false; 
		if (information.contains("phonekind"))
			bPhoneKind = false;%>
		<%=tmp%>
		</h1>
	<%	}
	}
	if(contact == null){ // récupération des éléments déjà renseignés (cas d'erreur)
		firstname = request.getParameter("firstname") == null ? "" : request.getParameter("firstname");
		lastname = request.getParameter("lastname") == null ? "" : request.getParameter("lastname");
		email = request.getParameter("email") == null ? "" : request.getParameter("email");
		siret = request.getParameter("siret") == null ? "" : request.getParameter("siret");
		street = request.getParameter("street") == null ? "" : request.getParameter("street");
		city = request.getParameter("city") == null ? "" : request.getParameter("city");
		zip = request.getParameter("zip") == null ? "" : request.getParameter("zip");
		country = request.getParameter("country") == null ? "" : request.getParameter("country");
		
		int i = 1;
		String tmp = "";
		while(tmp != null) {
			tmp = request.getParameter("telephone"+i);
			if(tmp != null) {
				alPhone.add(tmp);
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
				alPhoneKind.add(tmp);
				i++;
			}else {
				i=1;
				tmp = "";
				break;
			}
		}	
	
		while(tmp != null) {
			tmp = request.getParameter("groupe"+i);
			if(tmp != null) {
				alGroupes.add(tmp);
				System.out.println("CREATE : "+tmp);
				i++;
			}else{
				i=1;
				tmp = "";
				System.out.println("break");
				break;
			}
		}
		
		
	}else {
		firstname = contact.getFirstName();
		lastname = contact.getLastName();
		email = contact.getEmail();
		if(contact instanceof Entreprise) {
			siret = ""+((Entreprise)contact).getNumSiret();
		}
		street = contact.getAdd().getStreet();
		city = contact.getAdd().getCity();
		zip = contact.getAdd().getZip();
		country = contact.getAdd().getCountry();
		groupe = contact.getBooks().iterator().next().getGroupName();
		
		for(PhoneNumber pn: contact.getPhones()){
			alPhone.add(pn.getPhoneNumber());
			alPhoneKind.add(pn.getPhoneKind());
		}
		
		for(ContactGroup cg: contact.getBooks()){
			alGroupes.add(cg.getGroupName());
		}
		
		version = ""+contact.getIdContact()+";"+contact.getVersion();
		
	}
	%>

		<form method="post" action= 
<%  if(contact == null){ %>
		<%=create %>
<%  } else {	
		String updateAddress = ""+contact.getAdd().getIdAddress()+";"+contact.getAdd().getVersion();%>
		<%=update %>>
		<%="<input name=\"updateContact\" type=\"hidden\" value=\""+version+"\">"%>
		<%="<input name=\"updateAddress\" type=\"hidden\" value=\""+updateAddress+"\">"%>
		<%
		int j = 0;
		ArrayList<PhoneNumber> alPhoneRetrieve = new ArrayList<PhoneNumber>();
		alPhoneRetrieve.addAll(contact.getPhones());
		System.out.println("CreateContact : SIZE : "+alPhoneRetrieve.size());
		for(j = 0; j < alPhoneRetrieve.size() ; j++){
			System.out.println("PhoneNumberPASSAGE : "+alPhoneRetrieve.get(j).getVersion());%>
			<%="<input name=\""+"updatePhones"+j+"\" type=\"hidden\" value=\""+alPhoneRetrieve.get(j).getIdPhoneNumber()+";"+alPhoneRetrieve.get(j).getVersion()+"\">"%>
		<%} %>
		<%
		j = 0;
		ArrayList<ContactGroup> cgRetrieve = new ArrayList<ContactGroup>();
		cgRetrieve.addAll(contact.getBooks());
		for(j = 0 ; j < cgRetrieve.size(); j++){
			System.out.println("ContactGroupPASSAGE");%>
			<%="<input name=\""+"updateCG"+j+"\" type=\"hidden\" value=\""+cgRetrieve.get(j).getIdContactGroup()+";"+cgRetrieve.get(j).getVersion()+"\">"%>
		<%} %>
<%	} %>
			<div class="row" >
				<div class="col-md-offset-2 col-md-3" style="width: 80%">
					<div class="form-group">
						<label for="firstname">First name</label> 
						<input type="text"
							<%if (!bFirstName) {%> <%=showError%> <%} else {%> <%=noError%>
							<%}%> class="form-control" name="firstname"
							value="<%=firstname%>" placeholder="First name">
					</div>

					<div class="from-group">
						<label for="lastname">Last name</label> <input type="text"
							<%if (!bLastName) {%> <%=showError%> <%} else {%> <%=noError%>
							<%}%> class="form-control" name="lastname" value="<%=lastname%>"
							placeholder="Last name">
					</div>


					<div class="form-group">
						<label for="email">Adresse email</label> <input type="email"
							<%if (!bEmail) {%> <%=showError%> <%} else {%> <%=noError%> <%}%>
							class="form-control" name="email" value="<%=email%>"
							placeholder="Email">
					</div>



					<div class="from-group">
						<label for="siret">Siret number</label> <input type="text"
							class="form-control" name="siret" value="<%=siret%>"
							placeholder="(optionnal) Siret number">
					</div>

					<fieldset style="margin-bottom: -2%;">
						<legend style="color: #5826AB80;">Address</legend>

						<div class="from-group">
							<label for="street">Street</label> <input type="text"
								<%if (!bStreet) {%> <%=showError%> <%} else {%> <%=noError%>
								<%}%> class="form-control" name="street" value="<%=street%>"
								placeholder="Street name">
						</div>
						<div class="from-group">
							<label for="city">City</label> <input type="text"
								<%if (!bCity) {%> <%=showError%> <%} else {%> <%=noError%> <%}%>
								class="form-control" name="city" value="<%=city%>"
								placeholder="City">
						</div>
						<div class="from-group">
							<label for="zip">Zip</label> <input type="number"
								<%if (!bZip) {%> <%=showError%> <%} else {%> <%=noError%> <%}%>
								class="form-control" name="zip" value="<%=zip%>"
								placeholder="Zip">
						</div>
						<div class="from-group">
							<label for="country">Country</label> <input type="text"
								<%if (!bCountry) {%> <%=showError%> <%} else {%> <%=noError%>
								<%}%> class="form-control" name="country" value="<%=country%>"
								placeholder="City">
						</div>
					</fieldset>
					<fieldset style="margin-top: 3%;">
						<legend style="color: #5826AB80;">Phones</legend>
						<div class="phones">
							<div class="form-inline" style="margin-bottom: 3px;">
								<div class="form-group">
									
									<div id="input-group-telephone" class="input-group-telephone">
										 
										<%
											int i = 0;
											if(alPhone.size() > 0){	//Il y a eu une erreur ou un modification de contact
												System.out.println("TAILLE DE ALPHONE : " + alPhone.size());
											}else{
												alPhone.add("");
												alPhoneKind.add("");
											}
											for(i = 0;i<alPhone.size();i++){
												String tmp = "<span> Téléphone "+(i+1)+" : ";
												tmp += "<input type=\"tel\" class=\"form-control\" name=\"telephone"+(i+1)+"\"";
												if(!bPhone)
													tmp += showError;
												else
													tmp += noError;
												tmp += " id=\"tel"+(i+1)+"\""+"value=\""+alPhone.get(i)+"\""+" maxlength=\"10\" style=\"border-radius: 4px;\"> </span>";
												tmp += " <label for=\"phonekind\">PhoneKind : </label>";
										%>
										<%=tmp%>
										<%		tmp = "<input type=\"text\"";
												if(!bPhoneKind)
													tmp += showError;
												else
													tmp += noError;
												tmp += "class=\"form-control\" id=\"phonekind"+(i+1)+"\" name=\"phonekind"+(i+1)+"\"";
												tmp += "value=\""+alPhoneKind.get(i)+"\" placeholder=\"PhoneKind\">";
												tmp += " <select id=\"chgphonekind"+(i+1)+"\" name=\"chgphonekind"+(i+1)+"\"onchange=\"changePhoneKind(event)\">";
												tmp += "<option value=\"\" selected>Choix d'un groupe existant</option>";
												List<String> pkg = PhoneNumberService.listPhoneNumberGroups();
												for (String cg : pkg) {
													tmp += "<option value=\""+cg+"\">"+cg+"</option>";
												}
												tmp += "</select>";
												tmp += "<br>";%>
										<%=tmp%>
										<%	} %>
										<br>
									</div> 
									<% 
									String tmp="";
									if(contact == null){
										tmp="<input class=\"btn btn btn-primary btn-block\" style=\"width: 20%\"  type=\"button\" id=\"more_fields\" onclick=\"add_fields_telephone();\" value=\"Ajouter un numéro\" />";
									}else{
										tmp="";
									}%>
									<%=tmp %>
									<br> <br>
									
								</div>
							</div>
						</div>
					</fieldset>
					<fieldset style="margin-top: 3%;">
						<legend style="color: #5826AB80;">Groupe</legend>
						<div class="contact_groups">
							<div class="form-inline" style="margin-bottom: 3px;">
								<div id="input-group-groupes" class="input-group-groupes">

										<%
											i = 0;
											if(alGroupes.size() > 0){	//Il y a eu une erreur ou un modification de contact
												System.out.println("TAILLE DE ALGROUPES : " + alGroupes.size());
											}else{
												alGroupes.add("");
											}
											for(i = 0;i<alGroupes.size();i++){
												tmp = "<label for=\"country\">Groupe "+(i+1)+" : </label>";
										%>
										<%=tmp%>
										<%		tmp = "<input type=\"text\"";
												tmp += noError;
												tmp += "class=\"form-control\" id=\"groupe"+(i+1)+"\" name=\"groupe"+(i+1)+"\"";
												tmp += "value=\""+alGroupes.get(i)+"\" placeholder=\"Nom du groupe\">";
												tmp += " <select id=\"chgcontact_groups"+(i+1)+"\" name=\"chgcontact_groups"+(i+1)+"\"onchange=\"changeGroups(event)\">";
												tmp += "<option value=\"\" selected>Choix du groupe existant</option>";
												List<ContactGroup> lcg = ContactGroupService.listContactGroups();
												for (ContactGroup cg : lcg) {
													tmp += "<option value=\""+cg.getGroupName()+"\">"+cg.getGroupName()+"</option>";
												}
												tmp += "</select>";
												tmp += "<br>";%>
										<%=tmp%>
										<%	} %>
										<br>
								</div>
								
								<% 
								tmp="";
								if(contact == null){
										tmp="<input class=\"btn btn btn-primary btn-block\" style=\"width: 20%\" type=\"button\" id=\"more_fields\" onclick=\"add_fields_groupe();\" value=\"Ajouter un autre groupe\" />";
								}else{
										tmp="";
								}%>
								<%=tmp %>
								
							</div>
						</div>
					</fieldset>
					<br> <input class="btn btn btn-primary btn-block"  style="width: 10%" type="submit" value="Envoyer"><br> 
				</div>
			</div>
			
		</form>
		
	</div>
</body>
</html>


