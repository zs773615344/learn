<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome to struts2</title>
<s:head />
</head>
<body>
	<h1>haha</h1>

	<a href="xml-hello">hello</a>
	<br>
	<br>

	<s:form action="xml-login" method="post" namespace="/">
		<s:textfield  name="name" label="姓名"/> 
		<s:submit value="提交" />
	</s:form>
	<br>
	<br>

	<s:form action="xml-userLogin" method="post" namespace="/">
		<s:textfield name="userPassword.user" label="用户名" />
		<s:password name="userPassword.password" label="密码" />
		<s:submit value="提交" />
	</s:form>
	<br>
	<br>
	<a href='<s:url action="index" namespace="config-browser" />'>Launch
		the configuration browser</a>
	<br>
	<br>
	<a href="hello/hello">hello annotation </a>
	<br>
	<br>
	<a href="annotation">annotation</a>
	<br>
	<br>
	<a href='<s:url action="annotation" namespace=""/>'>annotation</a>
	
	   <br>
    <br>
    <a href='<s:url action="resthello" namespace=""/>'>resthello</a>
</body>
</html>