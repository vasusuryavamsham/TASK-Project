<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Details</title>
<style>

html,body{
margin:0;
padding:0;
}
.form{
position: relative;
width:60%;
margin:2% auto;
text-align:center;
border:2px solid black;
border-radius:30px;
height:80vh;
display:flex;
justify-content:center;
flex-direction:column;
align-items:center;
background-image:url("./photos/billingbg.jpg");
background-size: 120%;
background-repeat:no-repeat;
background-position: center bottom;
}

p{
margin:0;
}
h1{
margin-top:-5%;
}


.flx{

display:flex;
align-items:flex-end;
justify-content:space-between;
border:0 dashed black;
border-bottom-width:2px;
margin:2% 0;

}
#submit{
margin-top:6%;
border:2px solid grey;
border-radius:30px;
width:80%;
text-decoration:none;
height:12%;
background-color:grey;
color:black;
display: inline-flex;
align-items: center;
justify-content:center;
}
.bill{

width:50%;

text-align:center;
}



</style>
</head>
<body>
  <div class="form">
    <h1>Booking Details</h1>
    <div class="bill">
    <div class="flx"><p>Movie Name: </p><b><%= request.getAttribute("movie") %></b></div>
    <div class="flx"><p>Price: </p><b><%= request.getAttribute("price") %></b></div>
    <div class="flx"><p>Language: </p><b><%= request.getAttribute("language") %></b></div>
    <div class="flx"><p>City: </p><b><%= request.getAttribute("city") %></b></div>
    
    <!-- Proceed to Pay button -->
    <!-- <form action="PaymentPage" method="post">
        <input type="submit" value="Proceed to Pay">
    </form> -->
    <a id="submit" href="Payment.html" >Proceed to Pay</a>
 	</div>
    </div>
</body>
</html>
