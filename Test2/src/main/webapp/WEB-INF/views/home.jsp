<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>

<script type="text/javascript">
	function formSubmit(value){
		var submitVal = document.inputForm;
		if(value == 'write'){
			submitVal.action = "/write";
			submitval.method = "post";
		}
		else if(value == "modify"){
			submitiVal.action = "modify";
			submitval.method = "post";
		}
		else if(value == "delete"){
			submitVal.action = "delete";
			submitval.method = "post";
		}
		submitVal.submit();
	}
</script>


<body>
<h1>
	Visitor Board  
</h1>

<div>
<form name="inputForm" action="change" method="post">
<table width = "80%" cellpadding="0" align="center">
	<tr>
		<td> 입력/수정/삭제 </td>
	</tr>
	<tr>
		<td> Email </td>
		<td> 내용 </td>
		<td> password </td>
	</tr>
	
	
	<tr>
		<td width="20%"><input type="text" name="email" ></td>
		<td width="70%"><input type="text" name="content" size="100%"></td>
		<td width="10%"><input type="password" name="passwd"></td>
	</tr>
	<tr>
	<td colspan="3">
		<!--
			<input type="button" value="write" onClick='formSubmit(this.form)'>
			<input type="button" value="modify" onClick='formSubmit(this.form)'>
			<input type="button" value="delete" onClick='formSubmit(this.form)'>
		-->
		<input type="submit" name="submitBtn" value="write">
		<input type="submit" name="submitBtn" value="modify">
		<input type="submit" name="submitBtn" value="delete">
	</td>
	</tr>
</table>
</form>
</div>

<br><br>
<table width="90%" cellpadding="1" border="2" align="center">
	<tr>
		<td align="center">email</td>
		<td align="center">content</td>
		<td align="center">registerTime</td>
		<td align="center">modifiedTime</td>
	</tr>
	
	<c:forEach var="listValue" items="${lists}">
		<tr>
			<td width="18%">${listValue.email}</td>
			<td width="50%">${listValue.content}</td>
			<td width="10%" align="center">${listValue.registerTime}</td>
			<td width="10%" align="center">${listValue.modifiedTime}</td>
		</tr>
	</c:forEach>
</table>

<P>  db column number=${number} </P>

</body>
</html>
