package cl.inacap.cursoandroid.unidad1.test;

import junit.framework.TestCase;

import cl.inacap.cursoandroid.unidad1.Crud.ProductosFrescosGlobal;
import cl.inacap.cursoandroid.unidad1.business.UsuarioBS;

/**
 * Created by Jorge on 01-11-2015.
 */
public class modificarusuarioTest extends TestCase {
    public void testmodificarusuario(){
        UsuarioBS UBs = new UsuarioBS();
        boolean resultado =UBs.modificarUsuario(7,"Jorge","Jorge","Jorge",  this.getApplication() );
        assertFalse(resultado);
//Aqui tendria que cumplirse el assertFalse pues el usuario indicado no esta.
//Pero en el quinto parametro del metodo no me lo reconoce.
//esto es para generar un cambio en el repositorio de github

}
}
