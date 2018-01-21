<!DOCTYPE html>
<%@page import="services.ContactService"%>
<%@page import="services.EntrepriseService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="entities.Contact"%>
<%@page import="entities.Entreprise"%>
<%@page import="entities.PhoneNumber"%>
<%@page import="entities.Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="style.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des contacts</title>
</head>
<body>
	<div class="col-md-offset-2 col-md-3" style="width: 70%">
	<h1 id="contacts">Voici vos contacts :</h1>
	<br>
	<%
	String allNumber = "";
	List<Contact> contacts = ContactService.listContacts();
	%>
	<%="<div class=\"table-responsive\">"+
	"<table class=\"table-striped\" style=\"width: 100%\">"+
		"<tr>"+
		"<th>Nom</th>"+
		"<th>Prénom</th>"+
		"<th>Email</th>"+
		"<th>Rue</th>"+
		"<th>Ville</th>"+
		"<th>Code postal</th>"+
		"<th>Pays</th>"+
		"<th>Téléphones</th>"+
		"<th>PhoneKind</th>" +
		"</tr>" %>
	<%
	if(contacts.size() == 0)
		return;
	
		for (Contact c : contacts) {
			Address a = c.getAdd();
			Iterator<PhoneNumber> monSet = c.getPhones().iterator();%>
			
			<%="<tr>"%>
				<%="<td>"%>
					<%=c.getLastName()%>	
				<%="</td>"%>
				<%="<td>"%>
					<%=c.getFirstName()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=c.getEmail()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=a.getStreet()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=a.getCity()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=a.getZip()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=a.getCountry()%>
				<%="</td>"%>
				
				<% while(monSet.hasNext()){ 
					PhoneNumber pn = monSet.next();
					%>
				
					<%="<td>"%>
						<%=pn.getPhoneNumber()%>
    					<%="</td>"%>
    					<%="<td>"%>
						<%=pn.getPhoneKind()%>
    					<%="</td>"%>
    					<%="</tr>"%>
    					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
    					
				<%}%>
				
			<%="</tr>"%>
		<%}%>
<%="</table>"%>
<%="</div>" %>
	</div>
	
	<div class="col-md-offset-2 col-md-3" style="width: 70%">
	<h1 id="Entreprise"> Voici vos contacts (entreprises) :</h1>
	<%
	List<Entreprise> entreprise = EntrepriseService.listEntreprises();
	%>
	<%="<div class=\"table-responsive\">"+
	"<table class=\"table-striped\" style=\"width: 100%\">"+
		"<tr>"+
			"<th>Nom</th>"+
			"<th>Prénom</th>"+
			"<th>Email</th>"+
			"<th>Siret</th>" +
			"<th>Rue</th>"+
			"<th>Ville</th>"+
			"<th>Code postal</th>"+
			"<th>Pays</th>"+
			"<th>Téléphones</th>"+
			"<th>PhoneKind</th>" +
			"</tr>" %>
	<%
		for (Entreprise c : entreprise) {
			Iterator<PhoneNumber> monSet = c.getPhones().iterator();
			Address a = c.getAdd();%>
			<%="<tr>"%>
				<%="<td>"%>
					<%=c.getFirstName()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=c.getLastName()%>		
				<%="</td>"%>
				<%="<td>"%>
					<%=c.getEmail()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=c.getNumSiret()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=a.getStreet()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=a.getCity()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=a.getZip()%>
				<%="</td>"%>
				<%="<td>"%>
					<%=a.getCountry()%>
				<%="</td>"%>
				<% while(monSet.hasNext()){ 
					PhoneNumber pn = monSet.next();
					%>
				
					<%="<td>"%>
						<%=pn.getPhoneNumber()%>
    					<%="</td>"%>
    					<%="<td>"%>
						<%=pn.getPhoneKind()%>
    					<%="</td>"%>
    					<%="</tr>"%>
    					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
					<%="<td>"%>
					<%="</td>"%>
    					
				<%}%>
			<%="</tr>"%>
		<%}%>
<%="</table>"%>
<%="</div>" %>
	<br>
	<form method="post" action="/CODEL-GestionnaireDeContact/Main.jsp">
		<input type="submit" class="btn btn-lg btn-primary btn-block" value="Retour">
	</form>
	</div>
	

</body>
</html>
