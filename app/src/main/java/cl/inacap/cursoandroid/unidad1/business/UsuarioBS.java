package cl.inacap.cursoandroid.unidad1.business;

import java.util.ArrayList;

import cl.inacap.cursoandroid.unidad1.datamodel.UsuarioDM;
import cl.inacap.cursoandroid.unidad1.dataaccess.UsuarioDA;
import cl.inacap.cursoandroid.unidad1.Crud.ProductosFrescosGlobal;

/**
 * Esta clase contiene los metodos que realizan operaciones sobre los datos, 
 * por ejemplo validaciones.
 * @author: Inacap
 * @version: v1.001/05/2015
 * @see <a href = "http://www.inacap.cl" /> inacap.cl Programacion de aplicaciones moviles en Android </a>
 */

public class UsuarioBS extends UsuarioDA {
	private String mensaje_validacion;
	
	/**
	 * Constructor de la clase, no recibe parametros
	 */
	public UsuarioBS()
	{		
		super();
	}//Cierre del constructor
	
	/**
	* Se asigna el mensaje de validacion de alguna operacion 
	*/
	public void setMensajeValidacion(String mensaje)
	{
		this.mensaje_validacion = mensaje;
	}
	
	/**
	* Se obtiene el mensaje de validacion de alguna operacion realizada 
	*/
	public String getMensajeValidacion()
	{
		return this.mensaje_validacion;
	}
	
	/**
	* Se realiza la validacion del Login de usuario 
	*/
	public boolean validarLogin(String login, String contrasena, android.app.Application app)
	{
		UsuarioDM usr_dm;
		ArrayList<UsuarioDM> usuarios = super.listaUsuarios(app);
		int largo = usuarios.size();
		for(int i=0;i < largo;i++)
		{
			usr_dm = usuarios.get(i);
			if(usr_dm.getLogin().equals(login) && usr_dm.getContrasena().equals(contrasena))
			{
				super.getUsuario().setId(usr_dm.getId());
				super.getUsuario().setNombre(usr_dm.getNombre());
				super.getUsuario().setLogin(usr_dm.getLogin());
				super.getUsuario().setContrasena(usr_dm.getContrasena());
				
				return true; 
			}
		}
		
		return false;
	}
	
	/**
	* Se realiza la validacion del registro de usuario
	*/
	public boolean agregarUsuario(String nombre, String login, String password, android.app.Application app)
	{
		//Validamos si existe login en la lista
		ArrayList<UsuarioDM> usuarios = listaUsuarios(app);
		int largo = usuarios.size();
		for(int i=0;i < largo;i++)
		{
			if(usuarios.get(i).getLogin().equals(login))
			{
				setMensajeValidacion("Ya existe un usuario con igual login");
				return false; 
			}
		}
		
		//Se obtiene el id del usuario
		int id_usuario = 0;
		for(int i=0;i < largo;i++)
		{
			if(usuarios.get(i).getId() > id_usuario)
			{
				id_usuario = usuarios.get(i).getId();
			}
		}
		
		id_usuario += 1;
		
		UsuarioDM usuario_dm = new UsuarioDM();
		usuario_dm.setId(id_usuario);
		usuario_dm.setNombre(nombre);
		usuario_dm.setLogin(login);
		usuario_dm.setContrasena(password);
		
		usuarios.add(usuario_dm);
		
		return true;
	}
	
	/**
	* Se realiza la validacion y modificacion  de usuario
	*/
	public boolean modificarUsuario(int id_usuario, String nombre, String login, String password, android.app.Application app)
	{
		//Validamos si existe login en la lista
		ArrayList<UsuarioDM> usuarios = listaUsuarios(app);
		int largo = usuarios.size();
		for(int i=0;i < largo;i++)
		{
			if(usuarios.get(i).getLogin().equals(login) && (id_usuario != usuarios.get(i).getId()))
			{
				setMensajeValidacion("Ya existe un usuario con igual login");
				return false; 
			}
		}
		
		
		//Se modificar el usuario coincidente con el ID
		for(int i=0;i < largo;i++)
		{
			if(usuarios.get(i).getId() == id_usuario)
			{
				ProductosFrescosGlobal global = (ProductosFrescosGlobal)app.getApplicationContext();
				
				usuarios.get(i).setNombre(nombre);
				usuarios.get(i).setLogin(login);
				usuarios.get(i).setContrasena(password);
				global.setUsuarios(usuarios);
				
				return true;
			}
		}
		
	
		return false;
	}
}
