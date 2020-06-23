<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar Producto</title>
</head>
<body>
	<h1>Listar Productos</h1>
	
	<table border="1">
		<tr>
			<td>	Id					</td>
			<td>	Nombre				</td>
			<td>	Cantidad			</td>
			<td>	Precio				</td>
			<td>	Fecha Creación		</td>
			<td>	Fecha Actualización	</td>
			<td>	Accion				</td>
		</tr>
		
		<!-- Insertamos codigo java en el jsp  -->
		<!-- Los atributos deben ser escritos tal cual son en la clase,
			 recordemos que envian un objeto en este caso Producto() -->
		<!-- En este caso los atributos son tal cual la DB pero deberian ser tipo Object() -->
		<c:forEach var="producto" items="${lista}">
		<tr>
			<td> <a href="producto?opcion=meditar&id=<c:out value="${ producto.id}"></c:out>"> <c:out value="${ producto.id}"></c:out> </a> </td>
			<td> <c:out value="${ producto.nombre}"></c:out> </td>
			<td> <c:out value="${ producto.cantidad}"></c:out> </td>
			<td> <c:out value="${ producto.precio}"></c:out> </td>
			<td> <c:out value="${ producto.fecha_crear}"></c:out> </td>
			<td> <c:out value="${ producto.fecha_actualizar}"></c:out> </td>
			<td> <a href="producto?opcion=eliminar&id=<c:out value="${ producto.id}"></c:out>"> Eliminar </a> </td>
		</tr>
		</c:forEach>		
	</table>
</body>
</html>