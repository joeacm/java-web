<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Producto</title>
</head>
<body>
	<form action="producto" method="post">
	<!-- var es el nombre de la variable que se va usar en el jsp -->
	<!-- value es el nombre que manda el servlet desde controller en este caso producto -->
	<c:set var="producto" value="${producto }"></c:set>
		<input type="hidden" name="opcion" value="editar">
		<input type="hidden" name="id" value="${producto.id}">
	
		<table border="1">
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="30" value="${producto.nombre}"></td>
			</tr>
			
			<tr>
				<td>Cantidad:</td>
				<td><input type="text" name="cantidad" size="30" value="${producto.cantidad}"></td>
			</tr>
			
			<tr>	
				<td>Precio:</td>
				<td><input type="text" name="precio" size="30" value="${producto.precio}"></td>				
			</tr>
		</table> 
		<input type="submit" value="Guardar">
	</form>
</body>
</html>