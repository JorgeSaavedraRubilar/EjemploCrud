package cl.inacap.cursoandroid.unidad1.datamodel;

/**
 * Esta clase define los objetos que contienen los datos del Usuario
 * @author: Inacap
 * @version: v1.001/05/2015
 * @see <a href = "http://www.inacap.cl" /> inacap.cl Programacion de aplicaciones moviles en Android </a>
 */

public class UsuarioDM {
	//Definicion de variables
	private int id_usuario;
	private String nombre_usuario;
	private String login_usuario;
	private String contrasena;

	//Constructor de la clase
	public UsuarioDM()
	{		
	}
	
	//Metodos Set para asignar datos
	
	public void setId(int id_usuario)
	{
		this.id_usuario = id_usuario;
	}
	
	public void setNombre(String nombre_usuario)
	{
		this.nombre_usuario = nombre_usuario;
	}
	
	public void setLogin(String login_usuario)
	{
		this.login_usuario = login_usuario;
	}
	
	public void setContrasena(String contrasena)
	{
		this.contrasena = contrasena;
	}
	
	//Metodos get para obtener datos
	
	public int getId()
	{
		return this.id_usuario;
	}
	
	public String getNombre()
	{
		return this.nombre_usuario;
	}
	
	public String getLogin()
	{
		return this.login_usuario;
	}
	
	public String getContrasena()
	{
		return this.contrasena;
	}
	
	public String toString()
	{
		return String.valueOf(this.id_usuario) + " : " + this.nombre_usuario + " (" + this.login_usuario + ")";
	}
}
