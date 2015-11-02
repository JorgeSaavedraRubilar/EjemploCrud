package cl.inacap.cursoandroid.unidad1.Crud;

import java.util.ArrayList;

import cl.inacap.cursoandroid.unidad1.datamodel.UsuarioDM;
import android.app.Application;

public class ProductosFrescosGlobal extends Application {
	private UsuarioDM usuario_dm;
	private UsuarioDM usuario_edicion_dm;
    private ArrayList<UsuarioDM> usuarios;
 
    public UsuarioDM getUsuario() { 
        return usuario_dm;
    }
     
    public void setUsuario(UsuarioDM usuario_dm) {      
       this.usuario_dm = usuario_dm;
    }
    
    public UsuarioDM getUsuarioEdicion() { 
        return usuario_edicion_dm;
    }
     
    public void setUsuarioEdicion(UsuarioDM usuario_dm) {      
       this.usuario_edicion_dm = usuario_dm;
    }
    
    public void setUsuarios(ArrayList<UsuarioDM> usuarios) {   
        this.usuarios = usuarios;
    }
    
    public ArrayList<UsuarioDM> getUsuarios() {      
        return this.usuarios;
    }
}
