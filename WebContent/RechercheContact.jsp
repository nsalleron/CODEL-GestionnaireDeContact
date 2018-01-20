<%@page import="entities.ContactGroup"%>
<%@page import="entities.PhoneNumber"%>
<%@page import="entities.Contact"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Entreprise"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Contact contact = (Contact) request.getAttribute("Contact");
	
	if (contact == null){
		%> <p> Erreur </p> <% 
		return;
	}
%>

<form  method="post" action="/CODEL-GestionnaireDeContact/UpdateServlet">

		<input name="IdContact" type="hidden" value="<%=contact.getIdContact()%>">
		<input name="IdAddr" type="hidden" value="<%=contact.getAdd().getIdAddress()%>">
		
		
		<div class="from-group">
			<label for="firstname">First name</label> <input type="text"
			 class="form-control" name="firstname" value="<%=contact.getFirstName()%>"
				placeholder="First name">
		</div>
		<div class="from-group">
			<label for="lastname">Last name</label> <input type="text"
				class="form-control" name="lastname" value="<%=contact.getLastName()%>"
				placeholder="Last name">
		</div>
		<div class="from-group">
			<label for="email">Email address</label> <input type="email"
				class="form-control" name="email" value="<%=contact.getEmail()%>"
				placeholder="Email">
		</div>
		<%  if(contact instanceof Entreprise) { %>
		 
			<label for="siret">Siret number</label> <input type="text"
				class="form-control" name="siret" value="<%=((Entreprise)contact).getNumSiret()%>"
				placeholder="(optionnal) Siret number">
	
		<% } %>

		<fieldset style="margin-bottom: -2%;">
			<legend style="color: #5826AB80;">Address</legend>

			<div class="from-group">
				<label for="street">Street</label> <input type="text"
					class="form-control" name="street" value="<%=contact.getAdd().getStreet()%>"
					placeholder="Street name">
			</div>
			<div class="from-group">
				<label for="city">City</label> <input type="text"  name="city" value="<%=contact.getAdd().getCity()%>" placeholder="City">
			</div>
			<div class="from-group">
				<label for="zip">Zip</label> <input type="number" name="zip" value="<%=contact.getAdd().getZip()%>" placeholder="Zip">
			</div>
			<div class="from-group">
				<label for="country">Country</label> <input type="text" name="country" value="<%=contact.getAdd().getCountry()%>"
					placeholder="City">
			</div>
		</fieldset>
		<fieldset style="margin-top: 3%;">
			<legend style="color: #5826AB80;">Phones</legend>


			<div class="phones">
				<div class="form-inline" style="margin-bottom: 3px;">
					<div class="form-group">
						<div class="input-group">
							<% 
									int i=1;
									for(PhoneNumber phone : contact.getPhones()){
										%> 
										<label for="<%="tel"+i%>"  ><%=phone.getPhoneKind()%> : </label> 
										<input type="tel" class="form-control" name="<%="telephone"+i%>" id="<%="tel"+i%>"
								         maxlength="10" style="border-radius: 4px;" value="<%=phone.getPhoneNumber()%>"></input>
								         <input name="<%="iD"+i%>" type="hidden" value="<%=phone.getIdPhoneNumber()%>">
								         <input name="<%="kind"+i%>" type="hidden" value="<%=phone.getPhoneKind()%>">
									<%i++;}%>
						</div>
					</div>
				</div>
			</div>
		</fieldset>

		<div class="contact_groups">
			<div class="form-inline" style="margin-bottom: 3px;">
				<div class="from-group">
				<% 
					for(ContactGroup groupe : contact.getBooks()){
							%> 
						<label for="country">Groupe</label> <input type="text" class="form-control" name="contact_groups" value="<%=groupe.getGroupName()%>"
						placeholder="Nom du groupe"> 
					<%}%>
				</div>
			</div>
		</div>
		<br> <br> <input type="submit" value="Envoyer">
	</form>
</body>
</html>