<!DOCTYPE html>
<%@page import="services.ContactService"%>
<%@page import="services.EntrepriseService"%>
<%@page import="java.util.List"%>
<%@page import="entities.Contact"%>
<%@page import="entities.Entreprise"%>
<%@page import="entities.PhoneNumber"%>
<%@page import="entities.Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des contats</title>
</head>
<body>
	<h1 id="contacts">Voici vos contacts :</h1>
	<br>
	<ul>
	<%
	String allNumber = "";
	List<Contact> contacts = ContactService.listContacts();
	for(Contact t : contacts){
		if(t instanceof Entreprise)
				continue;%>
	
		<li>nom : <%=t.getFirstName() %> <br>
			prenom : <%=t.getLastName() %> <br>
			email : <%=t.getEmail() %> <br> 
			<%for(PhoneNumber pn: t.getPhones()){
						allNumber+= "        -> "+ pn.getPhoneKind()+" : "+pn.getPhoneNumber()+"<br>";
			} 
			   Address ad = t.getAdd();
			   String addr ="<br>"+
						"        -> "+ad.getCity()+"<br>"+
						"        -> "+ad.getCountry()+"<br>"+
						"        -> "+ad.getStreet()+"<br>"+
						"        -> "+ad.getZip()+"<br>";
			
	%> Addresse : <%=addr %> téléphones : <%=allNumber%> <%allNumber = "";}%>
		</li>
	</ul>
	<h1 id="Entreprise"> Voici vos contacts (ENTREPRISE):</h1>
	<ul>
	<%
	List<Entreprise> entreprise = EntrepriseService.listEntreprises();
	for(Entreprise t : entreprise ){
		%>
	<li>nom : <%=t.getFirstName() %> <br> prenom : <%=t.getLastName() %>
		<br> email : <%=t.getEmail() %> <br> <%for(PhoneNumber pn: t.getPhones()){
						allNumber+= "        -> "+ pn.getPhoneKind()+" : "+pn.getPhoneNumber()+"<br>";
			}%><% Address ad = t.getAdd();
			   String addr ="<br>"+
						"        -> "+ad.getCity()+"<br>"+
						"        -> "+ad.getCountry()+"<br>"+
						"        -> "+ad.getStreet()+"<br>"+
						"        -> "+ad.getZip()+"<br>";
			%> Addresse : <%=addr %> téléphones : <%=allNumber%> <%allNumber = "";%>
	</li>
</ul>
	<%	} %>


	<form method="post" action="/CODEL-GestionnaireDeContact/Main.jsp">
		<input type="submit" value="Retour">
	</form>

</body>
</html>