<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suppression de contacts</title>
</head>
<body>
<form method="post" action="/CODEL-GestionnaireDeContact/DeleteServlet">
   Prenom:
  <input type="text" name="firstName"><br>
   Nom:
<input type="text" name="lastName"><br>
  <br>
  <br>
  <input type="submit" value="Submit">
</form>
</body>
</html>