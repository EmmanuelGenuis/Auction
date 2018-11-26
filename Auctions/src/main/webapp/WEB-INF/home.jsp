<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Auctions</title>
	<style>
	a{
	background-color:gray;
	border-raduis: 10px ;
	float:right ;
	}
	
	#destination {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#destination td, #destination th {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
}

#destination tr:nth-child(even){background-color: #f2f2f2;}

#destination tr:hover {background-color: #ddd;
}

#destination th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: gray;
    color: white;
}
	
	</style>
</head>
<body>
	<h1>Welcome, <c:out value="${user.firstname}" /></h1>
	<a class="log" href="/logout">Logout</a>
	<h2>Auctions</h2>
     <table id="destination">
         <tr>
             <th>Product</th>
             <th>Seller</th>
             <th>Top bid</th>
             <th>Time Remaining</th>
             <th></th>
            
         </tr>
         <c:forEach items="${auctions}" var="auction">
         <tr>
             
                <td><a href="/auction/show/${auction.id}">${auction.name}</a></td>
                <td>${auction.seller}</td>
                <td>${auction.bid}</td>
                <td>${auction.difference} Days</td> 
                 <td>  
                <c:if test ="${user.firstname == auction.seller}">
                 <a href="/delete/${auction.id }">Delete</a>
 				</c:if> 
 				 </td> 
        </tr>
        </c:forEach>
       
    </table>
<a href="/new">New Auction</a>  
<p class="right">Your current Wallet:$<c:out value="${user.wallet}"></c:out></p>
</body>
</html>