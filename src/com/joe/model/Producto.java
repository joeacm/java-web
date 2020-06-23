package com.joe.model;

import java.sql.Date;

public class Producto 
{
	private int 	_id;
	private String 	_nombre;
	private double 	_cantidad;
	private double 	_precio;
	private Date 	_fechaCrear;
	private Date 	_fechaActualizar;
	
	public Producto() 
	{
		
	}
	
	public Producto(int id, String nombre, double cantidad, double precio, Date fecha_crear, Date fecha_actualizar) 
	{
		super();
		_id = id;
		_nombre = nombre;
		_cantidad = cantidad;
		_precio = precio;
		_fechaCrear = fecha_crear;
		_fechaActualizar = fecha_actualizar;
	}
		

	public int getId() 
	{
		return _id;
	}

	public void setId(int id) 
	{
		_id = id;
	}

	public String getNombre() 
	{
		return _nombre;
	}

	public void setNombre(String nombre) 
	{
		_nombre = nombre;
	}

	public double getCantidad() 
	{
		return _cantidad;
	}

	public void setCantidad(double cantidad) 
	{
		_cantidad = cantidad;
	}

	public double getPrecio() 
	{
		return _precio;
	}

	public void setPrecio(double precio) 
	{
		_precio = precio;
	}

	public Date getFecha_crear() 
	{
		return _fechaCrear;
	}

	public void setFecha_crear(Date fecha_crear) 
	{
		_fechaCrear = fecha_crear;
	}

	public Date getFecha_actualizar() 
	{
		return _fechaActualizar;
	}

	public void setFecha_actualizar(Date fecha_actualizar) 
	{
		_fechaActualizar = fecha_actualizar;
	}

	@Override
	public String toString() 
	{
		return "Producto [id=" + _id + ", nombre=" + _nombre + ", cantidad=" + _cantidad + ", precio=" + _precio
				+ ", fecha_crear=" + _fechaCrear + ", fecha_actualizar=" + _fechaActualizar + "]";
	}
	
	
	
	
}
