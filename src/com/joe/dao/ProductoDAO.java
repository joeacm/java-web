package com.joe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joe.connection.Conexion;
import com.joe.model.Producto;

public class ProductoDAO 
{
	//	constantes SQL.
	private static final String SQL_INSERT = "INSERT INTO productos (nombre, cantidad, precio, fecha_crear, fecha_actualizar) VALUES (?,?,?,?,?)";
	private static final String SQL_SELECT = "SELECT * FROM productos";
	private static final String SQL_UPDATE = "UPDATE productos SET nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";
	
	//	atributos para la conexion y el commit.
	private Connection _connection;
	private PreparedStatement _statement;
	private boolean _estadoOperacion;
	
	//	METODOS CRUD.
	
	//	agregar producto.
	public boolean guardar(Producto producto) throws SQLException
	{
		_estadoOperacion = false;
		_connection = obtenerConexion();
		
		try 
		{
			_connection.setAutoCommit(false);
			
			//utilizamos la constante INSERT.
			_statement = _connection.prepareStatement(SQL_INSERT);	
			
			//	es innecesario setear el id, porque es AI.
			//	_statement.setInt(1, null);
			_statement.setString(1, producto.getNombre());
			_statement.setDouble(2, producto.getCantidad());
			_statement.setDouble(3, producto.getPrecio());
			_statement.setDate(4, producto.getFecha_crear());
			_statement.setDate(5, producto.getFecha_actualizar());
			
			//	si es mayor a cero, se entiende que hubo filas afectadas.
			_estadoOperacion = _statement.executeUpdate() > 0;
			
			//	ejecutamos las querys.
			_connection.commit();
			
			_statement.close();			
			//	no se cierra simplemente lo devuelve al pool de conexiones.
			_connection.close();
		} 
		catch (SQLException e) 
		{
			_connection.rollback();
			e.printStackTrace();
		}
		
		return _estadoOperacion;
	}
	
	//	editar producto.
	public boolean editar(Producto producto) throws SQLException
	{
		_estadoOperacion = false;
		_connection = obtenerConexion();
		
		try 
		{
			_connection.setAutoCommit(false);
			
			//	utilizamos la constante UPDATE.
			_statement = _connection.prepareStatement(SQL_UPDATE);	
			
			//	pasamos los parametros.
			_statement.setString(1, producto.getNombre());
			_statement.setDouble(2, producto.getCantidad());
			_statement.setDouble(3, producto.getPrecio());
			_statement.setDate(4, producto.getFecha_actualizar());
			_statement.setInt(5, producto.getId());
			
			//	si es mayor a cero, se entiende que hubo filas afectadas.
			_estadoOperacion = _statement.executeUpdate() > 0;
			
			//	ejecutamos las querys.
			_connection.commit();
			_statement.close();
			
			//	no se cierra, simplemente lo devuelve al pool de conexiones.
			_connection.close();	
		} 
		catch (SQLException e) 
		{
			_connection.rollback();
			e.printStackTrace();
		}
		
		return _estadoOperacion;
		
	}
	
	//	eliminar producto.
	public boolean eliminar(int idProducto) throws SQLException
	{
		_estadoOperacion = false;
		_connection = obtenerConexion();
		
		try 
		{
			_connection.setAutoCommit(false);
			
			//	utilizamos la constante UPDATE.
			_statement = _connection.prepareStatement(SQL_DELETE);	
			
			//	seteamos los parametros.
			_statement.setInt(1, idProducto);
			
			//	si es mayor a cero, se entiende que hubo filas afectadas.
			_estadoOperacion = _statement.executeUpdate() > 0;
			
			//	ejecutamos las querys.
			_connection.commit();
			
			// cerramos conexiones.
			_statement.close();			
			//	no se cierra simplemente lo devuelve al pool de conexiones.
			_connection.close();	
		} 
		catch (SQLException e) 
		{
			_connection.rollback();
			e.printStackTrace();
		}
		
		return _estadoOperacion;
	}
	
	//	obtener lista de productos.
	public List<Producto> obtenerProductos() throws SQLException
	{
		List<Producto> listaProductos = new ArrayList<Producto>();
		//	 obtiene todos los registros de la consulta.
		ResultSet resultSet = null;		
		_estadoOperacion = false;
		_connection = obtenerConexion();
		
		try 
		{
			_statement = _connection.prepareStatement(SQL_SELECT);
			resultSet = _statement.executeQuery();
			
			while (resultSet.next())
			{
				Producto producto = new Producto();
				
				producto.setId(resultSet.getInt(1));
				producto.setNombre(resultSet.getString(2));
				producto.setCantidad(resultSet.getDouble(3));
				producto.setPrecio(resultSet.getDouble(4));
				producto.setFecha_crear(resultSet.getDate(5));
				producto.setFecha_actualizar(resultSet.getDate(6));
				
				listaProductos.add(producto);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return listaProductos;
	}
	
	//	obtener producto.
	public Producto obtenerProducto(int idProducto) throws SQLException
	{
		//	 obtiene todos los registros de la consulta.
		ResultSet resultSet = null;
		Producto producto = new Producto();
		String sql = null;
		
		_estadoOperacion = false;
		_connection = obtenerConexion();
		
		try 
		{
			sql = "SELECT * FROM productos WHERE id=?";
			_statement = _connection.prepareStatement(sql);			
			_statement.setInt(1, idProducto);
			
			resultSet = _statement.executeQuery();
			
			if (resultSet.next())
			{
				producto.setId(resultSet.getInt(1));
				producto.setNombre(resultSet.getString(2));
				producto.setCantidad(resultSet.getDouble(3));
				producto.setPrecio(resultSet.getDouble(4));
				producto.setFecha_crear(resultSet.getDate(5));
				producto.setFecha_actualizar(resultSet.getDate(6));
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	
		return producto;
	}
	
	//	obtener conexion.
	private Connection obtenerConexion() throws SQLException
	{
		return Conexion.getConnection();
	}
}
