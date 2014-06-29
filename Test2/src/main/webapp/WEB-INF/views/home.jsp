<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Visitor Board  
</h1>

<table width = "80%" cellpadding="0" align="center">
	<tr>
		<td> 글쓰기 </td>
	</tr>
	<tr>
		<td> Email </td>
		<td> 내용 </td>
		<td> password </td>
	</tr>
	
	
	<tr>
		<td><input type="text" name="email"></td>
		<td><input type="text" name="content1"></td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td><input type="button" value="write" OnClick="writeFunc()"></td>
	</tr>
</table>

<br><br>

<table width="90%" cellpadding="0" border="1">
	<tr>
		<td align="center">email</td>
		<td align="center">content</td>
		<td align="center">registerTime</td>
	</tr>
	
	<c:forEach var="listValue" items="${lists}">
		<tr>
			<td width="73">${listValue.email}</td>
			<td width="379">${listValue.content}</td>
			<td width="164">${listValue.registerTime}</td>
		</tr>
	</c:forEach>
</table>


<P>  The time on the server is ${serverTime}. </P>
<P>  db column number=${number} </P>
<P>  searching: ${searchPw} </P>
</body>
</html>
