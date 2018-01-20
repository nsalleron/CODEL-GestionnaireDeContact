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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
</head>
<script type="text/javascript">
	function changeGroups(e) {
		document.getElementById("groupe").value = e.target.value
	}
	function changePhoneKind(e) {
		document.getElementById("phonekind").value = e.target.value
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
		String information, firstname, lastname, email, siret = "", street, city, zip, country, groupe,
				phone, phonekind, version = "";
		String[] whereFails;
		boolean bFirstName = true, bLastName = true, bEmail = true, bStreet = true, bZip = true, bCity = true,
				bCountry = true, bPhoneKind = true;
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
		if (information.contains("phonekind"))
			bPhoneKind = false;%>
		<%=tmp%>
		</h1>
	<%	}
	}
	if(contact == null){
	// récupération des éléments déjà renseignés (cas d'erreur)
		firstname = request.getParameter("firstname") == null ? "" : request.getParameter("firstname");
		lastname = request.getParameter("lastname") == null ? "" : request.getParameter("lastname");
		email = request.getParameter("email") == null ? "" : request.getParameter("email");
		siret = request.getParameter("siret") == null ? "" : request.getParameter("siret");
		street = request.getParameter("street") == null ? "" : request.getParameter("street");
		city = request.getParameter("city") == null ? "" : request.getParameter("city");
		zip = request.getParameter("zip") == null ? "" : request.getParameter("zip");
		country = request.getParameter("country") == null ? "" : request.getParameter("country");
		groupe = request.getParameter("groupe") == null ? "" : request.getParameter("groupe");
		phonekind = request.getParameter("phonekind") == null ? "" : request.getParameter("phonekind");
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
		phone = contact.getPhones().iterator().next().getPhoneNumber();
		phonekind = contact.getPhones().iterator().next().getPhoneKind();
		version = ""+contact.getVersion();
		System.out.println("CreateContact.jsp : VERSION ----> "+version);
		request.setAttribute("version", version);
	}
	%>

		<form method="post" action= 
<%  if(contact == null){ %>
		<%=create %>
<%  } else {				 %>
		<%=update %>>
		<%="<input name=\"IdContact\" type=\"hidden\" value=\""+contact.getIdContact()+"\">"%>
		<%="<input name=\"IdAddr\" type=\"hidden\" value=\""+contact.getAdd().getIdAddress()+"\">"%>
		<%="<input name=\"version\" type=\"hidden\" value=\""+version+"\">"%>
<%	} %>
			<div class="row">
				<div class="col-md-offset-2 col-md-3">
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
									<div class="input-group">
										<label for="country">Téléphone :</label> <input type="tel"
											class="form-control" name="telephone" id="tel1"
											maxlength="10" style="border-radius: 4px;">
									</div>
									<br> <br>
									<div class="input-group">
										<label for="phonekind">PhoneKind : </label> <input type="text"
											<%if (!bCountry) {%> 
												<%=showError%> 
											<%} else {%> 
												<%=noError%>
											<%}%> class="form-control" id="phonekind" name="phonekind"
											value="<%=phonekind%>" placeholder="PhoneKind"> <br>
										<select id="chgphonekind" name="chgphonekind"
											onchange="changePhoneKind(event)">
											<option value="">Choix</option>
											<option value="Portable" selected>Portable</option>
											<option value="Maison">Maison</option>
											<option value="Bureau">Bureau</option>
											<%
												List<String> pkg = PhoneNumberService.listPhoneNumberGroups();
												for (String cg : pkg) {
											%>
											<option value="<%=cg%>"><%=cg%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>
							</div>
						</div>
					</fieldset>
					<fieldset style="margin-top: 3%;">
						<legend style="color: #5826AB80;">Groupe</legend>
						<div class="contact_groups">
							<div class="form-inline" style="margin-bottom: 3px;">
								<div class="from-group">
									<label for="country">Groupe : </label> <input type="text"
										class="form-control" id="groupe" name="contact_groups"
										value="<%=groupe%>" placeholder="Nom du groupe"> <select
										id="contact_groups" name="contact_groups"
										onchange="changeGroups(event)">
										<option value="">Choix</option>
										<%
											List<ContactGroup> lcg = ContactGroupService.listContactGroups();
											for (ContactGroup cg : lcg) {
										%>
										<option value="<%=cg.getGroupName()%>"><%=cg.getGroupName()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
						</div>
					</fieldset>
				</div>
				<br> <br> <input type="submit" value="Envoyer">
			</div>
		</form>
	</div>
</body>
</html>


