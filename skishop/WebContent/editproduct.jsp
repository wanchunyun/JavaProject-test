<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="editProduct" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${product.id }" />
	产品类型：<select name="pt">
			<c:forEach items="${pts }" var="pt">
				<c:if test="${pt.id==product.productTypeId }"><option value="${pt.id }" selected="selected">${pt.name }</option></c:if>
				<c:if test="${pt.id!=product.productTypeId }"><option value="${pt.id }">${pt.name }</option></c:if>
			</c:forEach>
			</select><br>
			<input type="text" name="name" value="${product.name }"/><br>
			<input type="text" name="color" /><br>
			<input type="file" name="listimg" /><br>
			<input type="submit" value="修改">
	</form>
</body>
</html>