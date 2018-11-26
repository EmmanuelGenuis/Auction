<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Auctions</title>
<style>
	a{
	background-color:gray;
	border-raduis: 10px ;
	float:right ;
	}
	p{
	color: gray ;
	}
	
</style>

</head>
<body>
<h1><c:out value="${auction.name}"/></h1><br><br>
<h3>Created by:<c:out value="${auction.seller}"/></h3>
<a href="/home">Home</a><br><br>
<a href="/">Logout</a>
<h3>Time remaining:<c:out value="${time}"/></h3>
<p><c:out value="${auction.description}"/><p>
<h2>Current Highest Bid:<c:out value="${auction.highestBid} "/></h2>
<h2>By:<c:out value="${auction.user.name}"/></h2>

<br><br>
<form method="post" action="/bid/${auction.id}">
        <p>
      
            <input type="number" id="username" name="username" placeholder="Your Bid goes here..."/>
        </p>
    
        <input type="submit" value="Bid!"/>
</form>  

</body>