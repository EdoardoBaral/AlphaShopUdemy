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
 
 <h1 style="text-align: center;margin: 0px auto;display; block;color: red;padding-bottom: 1em;">${intestazione}</h1>
 <h3 style="text-align: center;margin: 0px auto;display; block;color: green;">${saluti}</h3>   
 
  <script src="<c:url value="/static/js/bootstrap.bundle.min.js" />"></script>    
  </body>
</html>