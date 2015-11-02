package cl.inacap.cursoandroid.unidad1.Crud;

import cl.inacap.cursoandroid.unidad1.Crud.R;

//import cl.inacap.cursoandroid.unidad1.activity.UsuariosActivity;
import cl.inacap.cursoandroid.unidad1.business.UsuarioBS;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		UsuarioBS usuario_bs = new UsuarioBS();
		final ProductosFrescosGlobal global = (ProductosFrescosGlobal) getApplicationContext();
		global.setUsuarios(usuario_bs.listaUsuarios(this.getApplication()));
		
		Button btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new OnClickListener() {
        	   @Override
        	   public void onClick(View v) {
        		   validarLoginUsuario();
        	   }
        	  });   
	}

	//Se realiza la validacion del usuario, se muestra un mensaje en caso de error
	public void validarLoginUsuario()
	{
		EditText txt_login = (EditText)findViewById(R.id.txt_usuario);
		EditText txt_contrasena = (EditText)findViewById(R.id.txt_contrasena);
		
		UsuarioBS usuario = new UsuarioBS();
		if(usuario.validarLogin(txt_login.getText().toString(), txt_contrasena.getText().toString(), this.getApplication()))
		{
			Toast.makeText(LoginActivity.this, "Usuario validado", Toast.LENGTH_LONG).show();
			txt_login.setText("");
			txt_contrasena.setText("");
			
			Intent intent = new Intent(LoginActivity.this, cl.inacap.cursoandroid.unidad1.Crud.UsuarioListaActivity.class);
			final cl.inacap.cursoandroid.unidad1.Crud.ProductosFrescosGlobal global = (cl.inacap.cursoandroid.unidad1.Crud.ProductosFrescosGlobal) getApplicationContext();
			global.setUsuario(usuario.getUsuario());
			
			LoginActivity.this.startActivity(intent);
		}
		else
		{
			Toast.makeText(LoginActivity.this, "Usuario o password incorrectos", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
