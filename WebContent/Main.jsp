<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String name = (String) request.getSession().getAttribute("name");
	if (name == null){
		name = (String) session.getAttribute("name");
	}
	session.setAttribute("name", name);
%>
Bonjour <%= name %> :)

<a href="CreateContact.jsp"><h3>Créer contact</h3></a>
<a href="ReadContact.jsp"><h3>Lire les contacts</h3></a>
<a href="UpdateContact.jsp"><h3>Modifier contact</h3></a>
<a href="DeleteContact.jsp"><h3>Supprimer contact</h3></a>



</body>
</html>