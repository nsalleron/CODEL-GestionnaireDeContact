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
<link href="./bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="./bootstrap-theme.min.css" rel="stylesheet"  type="text/css"/>
<link rel="stylesheet" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<script type="text/javascript">
$(document).ready(function(){
   $('#submit').live('blur',function(){
      $('#updateForm').submit();
   });
});
</script>
<title>Mise à jour d'un contact</title>
</head>
<body>
	<div class="col-md-offset-2 col-md-8">

		<h1>
			Modification de contact <br /> <small> Merci de rechercher
				votre contact </small>
		</h1>
		<form name="updateForm" method="post"
			action="/CODEL-GestionnaireDeContact/RechercheServlet">

			<%
			String recherche = "";
			boolean bAfficheTab = false;
			recherche = (String) request.getAttribute("recherche");
			@SuppressWarnings("unchecked")
			ArrayList<Contact> contacts = (ArrayList<Contact>) request.getAttribute("contacts");
			if ((recherche != null && recherche.length() > 0) || (contacts != null && contacts.size()>0)) {
				bAfficheTab = true;
			}else{
				recherche = "";
			}
		%>

			<div class="form-group">
				<label for="recherche">Recherche</label> <input type="text"
					class="form-control" name="recherche" id="recherche"
					value="<%=recherche%>" placeholder="Recherche" autofocus
					onchange="this.parentNode.submit()"> <br />
					<input name="submit" class="btn btn-lg btn-primary btn-block" type="submit" value="Rechercher">
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
			<%="value=\""+c.getIdContact()+"\">Modifier</button></td>"%>
			<%="</td>"%>
			<%="</tr>"%>
			<%}%>
			<%}%>
			<%="</table>"%>
			<%="</div>" %>
		</form>
	</div>
</body>
</html>
