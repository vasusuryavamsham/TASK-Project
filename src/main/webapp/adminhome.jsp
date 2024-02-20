<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin HomePage</title>
<link rel="stylesheet" type="text/css" href="admin.css" >
</head>
<body>
<h1 class="header">Online Movie Ticket Booking System</h1>
<div class="flex">
	<div class="sidebar">
		<img class="logo" src="photos/adminlg.png" >
		<h1>
		<%= request.getAttribute("name") %>
		</h1>
		<ul type=none class="sec">
		<li><a href="#add">ADD MOVIE</a></li>
		
		<li><a href="#list" >ALL MOVIES </a></li>
		</ul>
	</div>
	<div class="content">
		<div id="add" class="add">
			<form method="get" action="addmovie">
			<h2>Add Movie</h2><br>
			<label for="name">Enter Movie Name: </label> <br><input id="name" type="text" name="mname" required><br><br>
			<label for="price">Enter Movie price: </label> <br><input id="price" type="number" name="mprice" required><br><br>
			<label for="lang">Enter Movie language: </label> <br><input id="lang" type="text" name="mlang" required><br><br>
			<label for="city">Enter Movie city: </label> <br><input id="city" type="text" name="mcity" required><br><br>
			<input id="submit" type="submit" value="Add" >
			</form>
		</div>
		<br>
		<br>
		<hr>
		<br>
		<br>
		<div id="list" class="listm">
			<!-- <b><a href="listmovies">Refresh Movies list</a><br><br></b> -->
			<h2>Movies List</h2>
		<h3 id="result"></h3>
		</div>
	</div>
</div>
</body>
</html>