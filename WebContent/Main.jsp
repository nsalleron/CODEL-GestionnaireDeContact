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
	String updateFailed = (String) request.getAttribute("updatefailed");
	System.out.println("MAIN : "+updateFailed);
	if (name == null){
		name = (String) session.getAttribute("name");
	}
	session.setAttribute("name", name);
%>
Bonjour <%= name %> :)

<h3><a href="CreateContact.jsp">Créer contact</a></h3>
<h3><a href="listContact.jsp">Lire les contacts</a></h3>
<h3><a href="UpdateContact.jsp">Modifier contact</a></h3>
<h3><a href="DeleteContact.jsp">Supprimer contact</a></h3>
<h3><a href="Test.jsp">Tests de la plateforme</a></h3>

<% if(updateFailed != null && updateFailed.length() > 0){%>
	<%=updateFailed%>
<%}%>


</body>
</html>