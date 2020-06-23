package com.joe.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joe.dao.ProductoDAO;
import com.joe.model.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "administra peticiones para la tabla productos", urlPatterns = { "/producto" })
public class ProductoController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// 	TODO Auto-generated method stub
		//	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String opcion = request.getParameter("opcion");
		ProductoDAO productoDAO = new ProductoDAO();
		
		//	 tambien puede desarrollarse con un switch.
		if (opcion.equals("crear")) 
		{
			System.out.println("Seleccionó CREAR");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (opcion.equals("listar")) 
		{
			System.out.println("Seleccionó LISTAR");
			
			List<Producto> listaProductos = new ArrayList<Producto>();
			
			try 
			{
				listaProductos = productoDAO.obtenerProductos();
				for (Producto producto : listaProductos) 
				{
					System.out.println(producto);
				}
				
				request.setAttribute("lista", listaProductos);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else if (opcion.equals("meditar")) 
		{
			//	guardamos el parametro id que viene de listar.jsp, tambien parseamos porque viene en tipo string;
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Seleccionó EDITAR id = " + id);
			Producto producto = new Producto();
			try 
			{
				producto = productoDAO.obtenerProducto(id);
				
				request.setAttribute("producto", producto);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else if (opcion.equals("eliminar")) 
		{
			//	guardamos el parametro id que viene de listar.jsp, tambien parseamos porque viene en tipo string;
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Seleccionó ELIMINAR id = " + id);
			
			try 
			{
				productoDAO.eliminar(id);
				
				System.out.println("Se eliminó correctamente");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/index.jsp");
				requestDispatcher.forward(request, response);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 	TODO Auto-generated method stub
		//	doGet(request, response);
		
		String opcion = request.getParameter("opcion");
		ProductoDAO productoDAO = new ProductoDAO();
		Date fechaActual = new Date();

		if (opcion.equals("guardar")) 
		{
			Producto producto = new Producto();
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFecha_crear(new java.sql.Date(fechaActual.getTime()));

			try 
			{
				//	registro.
				productoDAO.guardar(producto);

				//	mensaje para cerciorar.
				System.out.println("Se registró");

				//	para volver al index.
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else if (opcion.equals("editar")) 
		{
			Producto producto =  new Producto();
			
			producto.setId(Integer.parseInt(request.getParameter("id")));
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFecha_actualizar(new java.sql.Date(fechaActual.getTime()));
			
			try 
			{
				productoDAO.editar(producto);
				
				System.out.println("Editado");
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			}
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}

		}
		
	}

}
