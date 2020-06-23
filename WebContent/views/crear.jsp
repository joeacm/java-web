<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear Producto</title>
</head>
<body>
	<h1>Crear Producto</h1>
	
	<form action="producto" method="post">
	<input type="hidden" name="opcion" value="guardar">	
		<table>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="30"></td>
			</tr>
			
			<tr>
				<td>Cantidad:</td>
				<td><input type="text" name="cantidad" size="30"></td>
			</tr>
			
			<tr>	
				<td>Precio:</td>
				<td><input type="text" name="precio" size="30"></td>				
			</tr>
		</table> 
		<input type="submit" value="Guardar">
	</form>
</body>
</html>