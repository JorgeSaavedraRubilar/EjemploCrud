package cl.inacap.cursoandroid.unidad1.dataaccess;

import java.util.ArrayList;

import cl.inacap.cursoandroid.unidad1.datamodel.UsuarioDM;
import cl.inacap.cursoandroid.unidad1.Crud.ProductosFrescosGlobal;

/**
 * Esta clase contiene los metodos que controlan en acceso a los datos.
 * Los Datos pueden estar en una Base de Datos, un array en memoria o
 * consumirse a traves de algun servicio web.
 * @author: Inacap
 * @version: v1.001/05/2015
 * @see <a href = "http://www.inacap.cl" /> inacap.cl  Programacion de aplicaciones moviles en Android </a>
 */

public class UsuarioDA {
	
	//Definicion de variables
	private UsuarioDM usuario_dm;
	
	/**
	 * Constructor de la clase, no recibe parametros. 
	 * Se realiza inicializacion de variables
	 */
	public UsuarioDA()
	{		
		usuario_dm = new UsuarioDM();
	}//Cierre del constructor
	
	/**
	 * 
	 * @return Asigna el objeto usuario asociado a la clase
	 */
	public void setUsuario(UsuarioDM usuario_dm)
	{
		this.usuario_dm = usuario_dm;
	}
	
	/**
	 * 
	 * @return Retorna el objeto usuario asociado a la clase
	 */
	public UsuarioDM getUsuario()
	{
		return usuario_dm;
	}
	
	/**
	 * Se obtiene la lista de usuarios del sistema
	 * @return Retorna la lista de usuarios registrados en la aplicacion
	 * @params app: recibe la aplicacion para poder asignar los datos
	 */
	public ArrayList<UsuarioDM> listaUsuarios(android.app.Application app)
	{
		final ProductosFrescosGlobal global = (ProductosFrescosGlobal) app.getApplicationContext();
		if(global.getUsuarios() == null)
		{
			ArrayList<UsuarioDM> lista = new ArrayList<UsuarioDM>();
			UsuarioDM usuario_dm = new UsuarioDM();
			usuario_dm.setId(1);
			usuario_dm.setNombre("Usuario Test");
			usuario_dm.setLogin("test");
			usuario_dm.setContrasena("test");
			
			lista.add(usuario_dm);
	
			usuario_dm = new UsuarioDM();
			usuario_dm.setId(2);
			usuario_dm.setNombre("Juan Perez");
			usuario_dm.setLogin("juan");
			usuario_dm.setContrasena("juan");
	
			lista.add(usuario_dm);
			
			global.setUsuarios(lista);
			return lista;
		}
		else
		{
			return global.getUsuarios();
		}
	}//Cierre del metodo
}
