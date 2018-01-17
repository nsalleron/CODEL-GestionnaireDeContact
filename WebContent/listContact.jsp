<%@page import="services.ContactService"%>
<%@page import="java.util.List"%>
<%@page import="entities.Contact" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
Voici vos contacts : <br>


<%
	
	List<Contact> contacts = ContactService.listContact();
	for(Contact t : contacts){
		%>
		<li><%=t.getFirstName() %> <%=t.getLastName() %> at <%=t.getEmail() %>  <%=t.getBooks().toString()%></li>
		

<%	} %>


 <form method="post" action="/CODEL-GestionnaireDeContact/Main.jsp">
  <input type="submit" value="Retour">
</form>  

</body>
</html>