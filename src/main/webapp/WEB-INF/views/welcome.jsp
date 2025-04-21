<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap -->
    <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet">
	
    <title>Alphashop</title>
  </head>
 
  <body>
  
  <%@ include file="common/navbar.jspf" %>
 
  <h1 class="title">Benvenuti in Alphashop</h1>
   <h3 class="subtitle">Saluti ${name}, clicca <a href="/alphashop/articoli">qui</a> per vedere gli articoli disponibili!</h3>     
 
  <script src="<c:url value="/static/js/bootstrap.bundle.min.js" />"></script>    
  </body>
</html>