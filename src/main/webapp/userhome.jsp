<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<title>Home page</title>
<link rel="stylesheet" type="text/css" href="user.css">
</head>
<body>
<h1 id="header">Online Movie Ticket Booking System</h1>
<nav>
    <h3>Home</h3>
    <h4>About</h4>
    <h4>Help</h4>
    <form action="search" style="padding:0;">
    <div id="search"> 
    <select name="stype" style="border-radius:30px 0 0 30px;">
    <option selected value="a">List all</option>
    <option  value="m">Movie</option>
    <option value="l">Language</option>
    <option value="c">City</option>
    </select>
        <input type="text" name="searchmovie" placeholder="Search.." style="margin-left:-1%; border-radius:0 30px 30px 0;" >
    <button type="submit" style="border-radius:30px; width:8%;" ><i class="fa fa-search"></i></button>
    </div>
    </form>
    <div id="divin" style="width:30px;height:30px; display:inline-block; align-items:center;" >
    	<h1 id="init" >
    	<b ><%= request.getAttribute("initial") %></b>
    	</h1>
	</div>
    </nav>
    
 <form id="bookNowForm" action="bookingPage" method="get">
    <input type="hidden" id="movieName" name="movie">
    <input type="hidden" id="price" name="price">
    <input type="hidden" id="language" name="language">
    <input type="hidden" id="city" name="city">
</form>

<script>
function bclicked(movieName, price, language,city ) {
    // Set the values of hidden fields
    document.getElementById('movieName').value = movieName;
    document.getElementById('price').value = price;
    document.getElementById('language').value = language;
    document.getElementById('city').value = city;
	document.getElementById('bookNowForm').submit();
}
</script>
    
<div id="list">
<h2>Movies List</h2>
<p id="result" ></p>
</div>

</body>
</html>