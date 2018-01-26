<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="services.ContactGroupService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1" />
<meta name="Content-Language" content="fr" />
<meta name="Description" content="" />
<meta name="Subject" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- Bootstrap core CSS -->
<!-- Bootstrap core CSS -->
<link href="./bootstrap.min.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="./signin.css" rel="stylesheet" />

<link href="./main.css" rel="stylesheet" />

<title>Gestionnaire de contact</title>
</head>
<body>
	<%
		/* Debug pour le remplissage automatique des champs */
		String debug = "1";
		request.getSession().setAttribute("DEBUG", debug);
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		ContactGroupService contactGroupService = (ContactGroupService) context.getBean("beanContactGroupService");
		
		/* Initialisation du groupe PAS_DE_GROUPE */
		if(contactGroupService != null)
			if(!contactGroupService.checkIfGroupExist("_PAS_DE_GROUPE_"))
				contactGroupService.createContactGroup("_PAS_DE_GROUPE_");
		
		String name = (String) request.getSession().getAttribute("name");
		String updateFailed = (String) request.getAttribute("updatefailed");
		//System.out.println("MAIN : " + updateFailed);
		if (name == null) {
			name = (String) session.getAttribute("name");
		}
		session.setAttribute("name", name);
	%>
	<div id="welcomeMsg">
		Bienvenue
		<%=name%>
	</div>

	<div class="choices">
		<h3>
			<a class="btn btn-lg btn-primary btn-block" href="CreateContact.jsp">Créer
				contact</a>
		</h3>
		<h3>
			<a class="btn btn-lg btn-primary btn-block" href="listContact.jsp">Lire
				les contacts</a>
		</h3>
		<h3>
			<a class="btn btn-lg btn-primary btn-block" href="UpdateContact.jsp">Modifier
				contact</a>
		</h3>
		<h3>
			<a class="btn btn-lg btn-primary btn-block" href="DeleteContact.jsp">Supprimer
				contact</a>
		</h3>
		<h3>
			<a class="btn btn-lg btn-primary btn-block" href="Test.jsp">Test du cache niveau 2</a>
		</h3>
		<!-- <h3><a class="btn btn-lg btn-primary btn-block" href="Test.jsp">Tests de la plateforme</a></h3> -->
	</div>

	<%
		if (updateFailed != null && updateFailed.length() > 0) {
			System.out.println(updateFailed);
	%>
	<%=updateFailed%>
	<%
		}
	%>

	

</body>
</html>
