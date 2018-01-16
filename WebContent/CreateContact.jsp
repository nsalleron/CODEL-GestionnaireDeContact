<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Création d'un contact</title>
</head>
<body>

<%
		/* Déclaration des éléments */
		String information, firstname, lastname, email, siret, street, city, zip, country;
		String[] whereFails;
		boolean okFirstName = true, okLastName = true ,
				okEmail= true , okStreet = true ,
				okZip = true, okCity = true, okCountry = true;
		
		String showError = "STYLE=\"color: #000000; background-color: #ff6633;\"";
		String noError = "STYLE=\"color: #000000; background-color: #FFFFFF;\"";
		Boolean success;
		information = ((String)request.getAttribute("information"));
		success = ((Boolean)request.getAttribute("success"));
		
		
		
		if(information != null){
			
			/* Affichage endroit fail */
			whereFails = information.split(";");
			if(!success){ %>
				<h1>Il y a eu une erreur. Vérifier les champs : 
				<%String tmp = "";
				for(String str : whereFails){ 
					tmp+=str+", ";
				} 
				tmp.subSequence(0, tmp.length() - 2);
				
				/*Vérification de qui à échoué */
				if(information.contains("firstname"))
					okFirstName = false;
				if(information.contains("lastname"))
					okLastName = false;
				if(information.contains("email"))
					okEmail = false;
				if(information.contains("street"))
					okStreet = false;
				if(information.contains("zip"))
					okZip = false;
				if(information.contains("city"))
					okCity= false;
				if(information.contains("country"))
					okCountry = false;
				%>
				<%=tmp%>
				</h1>
			<% } else { %>
				<h1>Création d'un contact</h1>
			<% }%>
		<%} else{ %>
			<h1>Création d'un contact</h1>
		<%}
		
		// récupération des éléments déjà renseignés (cas d'erreur)
		firstname = request.getParameter("firstname")==null ? "" : request.getParameter("firstname");
		lastname = request.getParameter("lastname")==null ? "" : request.getParameter("lastname");
		email = request.getParameter("email")==null ? "" : request.getParameter("email");
		siret = request.getParameter("siret")==null ? "" : request.getParameter("siret");
		
		street = request.getParameter("street")==null ? "" : request.getParameter("street");
		city = request.getParameter("city")==null ? "" : request.getParameter("city");
		zip = request.getParameter("zip")==null ? "" : request.getParameter("zip");
		country = request.getParameter("country")==null ? "" : request.getParameter("country");
	%>

<form method="post" action="/CODEL-GestionnaireDeContact/CreateServlet">
   <div class="from-group">
				<label for="firstname">First name</label> 
				<input type="text" <%if(!okFirstName){%>
										<%=showError %>
									<% }else{ %>
										<%=noError %>
									<%} %> class="form-control" name="firstname" value="<%=firstname %> "
					placeholder="First name">
			</div>
			<div class="from-group">
				<label for="lastname">Last name</label> 
				<input type="text" <%if(!okLastName){%>
										<%=showError %>
									<% }else{ %>
										<%=noError %>
									<%} %> class="form-control" name="lastname" value="<%=lastname %>"
					placeholder="Last name">
			</div>
			<div class="from-group">
				<label for="email">Email address</label>
				<input type="email" <%if(!okEmail){%>
										<%=showError %>
									<% }else{ %>
										<%=noError %>
									<%} %>class="form-control" name="email" value="<%=email %>"
					placeholder="Email">
			</div>
			<div class="from-group">
				<label for="siret">Siret number</label>
				<input type="text" class="form-control" name="siret" value="<%=siret %>"
					placeholder="(optionnal) Siret number">
			</div>
			
			<fieldset style="margin-bottom: -2%;">
    			<legend style="color: #5826AB80;">Address</legend>
   			
			<div class="from-group">
				<label for="street">Street</label> 
				<input type="text" <%if(!okStreet){%>
										<%=showError %>
									<% }else{ %>
										<%=noError %>
									<%} %>class="form-control" name="street" value="<%=street %>"
					placeholder="Street name">
			</div>
			<div class="from-group">
				<label for="city">City</label>
				<input type="text" <%if(!okCity){%>
										<%=showError %>
									<% }else{ %>
										<%=noError %>
									<%} %>class="form-control" name="city" value="<%=city%>"
					placeholder="City">
			</div>
			<div class="from-group">
				<label for="zip">Zip</label> 
				<input type="number" <%if(!okZip){%>
										<%=showError %>
									<% }else{ %>
										<%=noError %>
									<%} %>class="form-control" name="zip" value="<%=zip%>"
					placeholder="Zip">
			</div>
			<div class="from-group">
				<label for="country">Country</label> 
				<input type="text" <%if(!okCountry){%>
										<%=showError %>
									<% }else{ %>
										<%=noError %>
									<%} %>class="form-control" name="country" value="<%=country%>"
					placeholder="City">
			</div>
			</fieldset>
			<fieldset style="margin-top: 3%;">
    			<legend style="color: #5826AB80;">Phones</legend>
   			

			<div class="phones">
				<div class="form-inline" style="margin-bottom:3px;">
					<div class="form-group">
						<div class="input-group">
							<input type="tel" class="form-control" name="telephone" id="tel1" maxlength="10" style="border-radius: 4px;">
						</div>
						<select id="phonekind">
  							<option value="valeur1"selected>Valeur 1</option> 
  							<option value="valeur2">Valeur 2</option>
  							<option value="valeur3">Valeur 3</option>
						</select>
					</div>
				</div>
			</div>
			</fieldset>
  <br>
  <br>
  <input type="submit" value="Envoyer">
</form> 	
</body>
</html>


