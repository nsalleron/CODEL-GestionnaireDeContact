<%@page import="services.TestsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test de l'environnement</title>
</head>
<body>

<h3> Test du niveau 2 cache : </h3> <br>
<%for( String s : (String[]) TestsService.checkIfLevelTwoIsUp()){%>
	<%=s%>
<% }%>
</body>
</html>
