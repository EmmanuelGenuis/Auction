<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Auctions</title>
    <style>
    input {
  float: right;
  clear: both;
         }
    body{
    width: 400px ;
    background-color: gray ;
    }
    form{
    border: 2px solid black ;
    margin:10px ;
    margin-bottom:20px ;
    padding:10px ;
    }
    
    </style>
</head>
<body>
    <h1>Register!</h1>
    
    <p><form:errors path="user.*"/></p>
    
    <form:form method="POST" action="/" modelAttribute="user">
      	<p>
            <form:label path="username">UserName:</form:label>
            <form:input type="text" path="username"/>
        </p>
      
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/>
        </p>
          <p>
            <form:label path="firstname">FirstName:</form:label>
            <form:input type="text" path="firstname"/>
        </p>
         <p>
            <form:label path="lastname">LastName:</form:label>
            <form:input type="text" path="lastname"/>
        </p>
        
        
        <br><br><br>
        <input type="submit" value="Register!"/>
    </form:form>
    
 <br>   
    
      <h1>Login</h1>
    <p><c:out value="${errors}" /></p>
    <form method="post" action="/login">
        <p>
            <label type="text" for="username">Username</label>
            <input type="text" id="username" name="username"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p><br><br><br>
        <input type="submit" value="Login!"/>
    </form> 
</body>
</html>