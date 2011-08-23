<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META name="GENERATOR" content="IBM Software Development Platform">
<script type="text/javascript">
	function init() {
		document.getElementsByName("userName")[0].focus();
	}
</script>
<TITLE>Login Page</TITLE>
</HEAD>
<BODY onload="init();">
<center>
<form name="login" method="post">
	<table style="border: thin solid black;">
	  <tr>
	    <td>Name: </td>
	    <td><input name="userName" type="text" style="width: 120px"> </td>
	  </tr>
	  <tr>
	    <td>Password: </td>
	    <td><input name="password" type="password" style="width: 120px"> </td>
	  </tr>
	  <tr>
	  	<td align="center" colspan="2"><input name="login" type="submit" value="Submit"><input name="reset" type="reset" value="Clear"></td>
	  </tr>
	</table>
</form>
</center>
</BODY>
</HTML>
