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
    input {
  float: right;
  clear: both;
         }
   textarea {
  float: right;
  clear: both;
         }
 
    body{
    width: 500px ;
    background-color: gray ;
    }
    form{
    border: 2px solid black ;
    margin:10px ;
    margin-bottom:20px ;
    padding:10px ;
    }
    select{
  float: right;
  clear: both;
    }
    </style>
</head>
<body>
<h1>New Auction</h1>
<a href="/home">Home</a>
<a href="/">Logout</a>
<p><form:errors path="auction.*"/></p>
<p><c:out value="${errors}" /></p>
<form:form method="POST" action="/newAuction" modelAttribute="auction">
        <p>
            <form:label path="name">Product name:</form:label>
            <form:input type="text" path="name"/>
  
        </p>
    	<p>
          Description:  <form:textarea path="description"></form:textarea>
        </p>
        <p>
            <form:label path="bid">Starting Bid:</form:label>
            <form:input type="number" path="bid" value="100"/>
        </p>
        <p>
            <form:label path="endDate">End Date:</form:label>
            <form:input type="date" path="endDate" min="${date}"/>
        </p>
        <br><br><br>
        <input type="submit" value="Create Auction"/>
</form:form>
</body>
</html>