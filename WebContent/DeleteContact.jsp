<%@page import="services.ContactService"%>
<%@page import="services.EntrepriseService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Contact"%>
<%@page import="entities.Entreprise"%>
<%@page import="entities.PhoneNumber"%>
<%@page import="entities.Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<!-- Bootstrap core CSS -->
<link href="./bootstrap.min.css" rel="stylesheet" />
<link href="./bootstrap-theme.min.css" rel="stylesheet" />
<link rel="stylesheet" href="style.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suppression d'un contact</title>
</head>
<body>
	<div class="col-md-offset-2 col-md-8">











		<h1>
			Suppression de contact <br /> <small> Merci de rechercher
				votre contact </small>
		</h1>
		<%
			String recherche = (String) request.getAttribute("recherche");
			
			if(recherche == null)
				recherche = "";
			
			boolean bAfficheTab = false;
			@SuppressWarnings("unchecked")
			ArrayList<Contact> contacts = (ArrayList<Contact>) request.getAttribute("contacts");

			if(contacts != null){
				bAfficheTab = true;
			}
			%>

		<form name="updateForm" method="post"
			<%="action=\"/CODEL-GestionnaireDeContact/RechercheServlet\"" %>>

			<input name="delete" type="hidden" value="byebye">
			<div class="form-group">
				<label for="recherche">Recherche</label> <input type="text"
					class="form-control" name="recherche" id="recherche"
					value="<%=recherche%>" placeholder="Recherche" autofocus> <br />
				<input name="submit" class="btn btn-lg btn-primary btn-block"
					type="submit" value="Rechercher">
			</div>

			<%if(bAfficheTab == true){ %>
			<%="<div class=\"table-responsive\">"+
				"<table class=\"table-striped\" style=\"width: 100%\">"+
					"<tr>"+
						"<th>Firstname</th>"+
						"<th>Lastname</th>"+
						"<th>Email</th>"+
						"<th>Edition</th>"+
					"</tr>" %>
			<%
				
				for (Contact c : contacts) {%>
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
			<%="<button class=\"editbtn\" name=\"idcontact\""%>
			<%="value=\""+c.getIdContact()+"\">"%>
			<%="Supprimer</button></td>"%>
			<%="</td>"%>
			<%="</tr>"%>
			<%}%>
			<%}%>
			<%="</table>"%>
			<%="</div>" %>
		</form>
		<script type="text/javascript"
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>
		<script type="text/javascript">
			document.getElementById("recherche").onchange = function() {
		   	 	document.getElementById("updateForm").submit();
			}
		</script>

	</div>
</body>
</html>
