<!doctype html>
<html lang="en">
	<head>

		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
		      rel="stylesheet"
			  integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
			  crossorigin="anonymous">

		<title>Alphashop</title>
	</head>

	<body>
		<%@ include file="common/navbar.jspf" %>
		<h1 class="title">Benvenuti in Alphashop</h1>
		<h3 class="subtitle">Saluti ${name}, clicca <a href="/articoli">qui</a> per vedere gli articoli disponibili!</h3>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
				crossorigin="anonymous"/>
	</body>
</html>