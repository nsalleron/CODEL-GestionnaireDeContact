<!DOCTYPE html>
<%@page import="services.EntrepriseService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="entities.Contact"%>
<%@page import="entities.IContact"%>
<%@page import="entities.Entreprise"%>
<%@page import="entities.PhoneNumber"%>
<%@page import="entities.Address"%>
<%@page import="services.IContactService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<!-- Bootstrap core CSS -->
<link href="./bootstrap-theme.min.css" rel="stylesheet" />
<link href="./bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="style.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des contacts</title>
</head>
<script type="text/javascript">
	function tri_nom() {
		var arrayLignes = document.getElementById("monTableau").rows;
		var longueur = arrayLignes.length;
		
		for (var i = 1; i < longueur; i++) {
			var arrayColonnes = arrayLignes[i].cells;
			var nom = new String(arrayColonnes[0].innerText);

			for (var j = 1; j < longueur; j++) {
				var arrayColonnes2 = arrayLignes[j].cells;
				var nom2 = new String(arrayColonnes2[0].innerText);
				if (nom.valueOf() < nom2.valueOf()){
					//TODO Faire le cas avec plusieurs téléphone "bon chance"
					for (var k = 0; k < arrayColonnes2.length; k++) {
						//alert("K " + k);
						var tmp = arrayColonnes[k].innerText;
						arrayColonnes[k].innerText = arrayColonnes2[k].innerText;
						arrayColonnes2[k].innerText = tmp;
					}
				}
			}
		}
	}

	function tri_code_postale() {

		var arrayLignes = document.getElementById("monTableau").rows;
		var longueur = arrayLignes.length;

		for (var i = 1; i < longueur; i++) {
			var arrayColonnes = arrayLignes[i].cells;
			var code_postal = new String(arrayColonnes[5].innerText);

			for (var j = 1; j < longueur; j++) {
				var arrayColonnes2 = arrayLignes[j].cells;
				var code_postal2 = new String(arrayColonnes2[5].innerText);

				if (parseInt(code_postal, 10) < parseInt(code_postal2, 10)) {
					for (var k = 0; k < arrayColonnes2.length; k++) {
						var tmp = arrayColonnes[k].innerText;
						arrayColonnes[k].innerText = arrayColonnes2[k].innerText;
						arrayColonnes2[k].innerText = tmp;
					}
				}
			}
		}
	}
</script>
<body>
	<div class="col-md-offset-2 col-md-3" style="width: 100%">
		<h1 id="contacts">Voici vos contacts :</h1>
		<br>
		<%
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IContactService contactService = (IContactService) context.getBean("beanContactService");
			EntrepriseService entrepriseService = (EntrepriseService) context.getBean("beanEntrepriseService");
			String allNumber = "";
			List<IContact> contacts = contactService.listContacts();
		%>
		<div class="table-responsive">
			<table id="monTableau" class="table-striped" style="width: 70%">
				<tr>
					<th onclick="tri_nom();">Nom</th>
					<th>Prénom</th>
					<th>Email</th>
					<th>Rue</th>
					<th>Ville</th>
					<th onclick="tri_code_postale();">Code postal</th>
					<th>Pays</th>
					<th>Téléphones</th>
					<th>PhoneKind</th>
				</tr>
				<%
					System.out.println("AFTER LIST CONTACT");
					if (contacts.size() == 0)
						return;

					for (IContact c : contacts) {
						if (c instanceof Entreprise)
							continue;
						Address a = c.getAdd();
						Iterator<PhoneNumber> monSet = c.getPhones().iterator();
				%>

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

				<%
					while (monSet.hasNext()) {
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

				<%
					}
				%>


				<%
					}
				%>
				<%="<td>"%>
				<%="</td>"%>
				<%="<td>"%>
				<%="</td>"%>
				<%="</tr>"%>
			</table>
		</div>
	</div>

	<div class="col-md-offset-2 col-md-3" style="width: 70%">
		<h1 id="Entreprise">Voici vos contacts (entreprises) :</h1>
		<%
			List<Entreprise> entreprise = entrepriseService.listEntreprises();
		%>
		<%="<div class=\"table-responsive\">" + "<table class=\"table-striped\" style=\"width: 100%\">"
					+ "<tr>" + "<th>Nom</th>" + "<th>Prénom</th>" + "<th>Email</th>" + "<th>Siret</th>" + "<th>Rue</th>"
					+ "<th>Ville</th>" + "<th>Code postal</th>" + "<th>Pays</th>" + "<th>Téléphones</th>"
					+ "<th>PhoneKind</th>" + "</tr>"%>
		<%
			for (Entreprise c : entreprise) {
				Iterator<PhoneNumber> monSet = c.getPhones().iterator();
				Address a = c.getAdd();
		%>
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
		<%
			while (monSet.hasNext()) {
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

		<%
			}
		%>
		<%="</tr>"%>
		<%
			}
		%>
		<%="</table>"%>
		<%="</div>"%>
		<br>
		<form method="post" action="/CODEL-GestionnaireDeContact/Main.jsp">
			<input type="submit" class="btn btn-lg btn-primary btn-block"
				value="Retour">
		</form>
	</div>


</body>
</html>
