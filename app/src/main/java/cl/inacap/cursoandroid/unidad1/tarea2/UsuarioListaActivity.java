package cl.inacap.cursoandroid.unidad1.Crud;

import java.util.ArrayList;

import cl.inacap.cursoandroid.unidad1.Crud.R;
import cl.inacap.cursoandroid.unidad1.business.UsuarioBS;
import cl.inacap.cursoandroid.unidad1.datamodel.UsuarioDM;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class UsuarioListaActivity extends Activity {
	private ArrayAdapter<UsuarioDM> adapter;
	private UsuarioDM usuario_dm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuario_lista);
	}
	
	/**
	 * Se realiza la carga de usuarios
	 * */
	private void cargarUsuarios()
	{
		final ListView lv_usuarios = (ListView)findViewById(R.id.lv_usuarios);
		
		UsuarioBS usuario_bs = new UsuarioBS();
		ArrayList<UsuarioDM> usuarios = usuario_bs.listaUsuarios(this.getApplication());
		
	    // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
	    // and the array that contains the data
	    adapter = new ArrayAdapter<UsuarioDM>(getApplicationContext(), android.R.layout.simple_spinner_item, usuarios);

	    // Here, you set the data in your ListView
	    lv_usuarios.setAdapter(adapter);
	    adapter.notifyDataSetChanged();
	    
	    // ListView Item Click Listener
	    lv_usuarios.setOnItemClickListener(new OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
                
               // ListView Clicked item index
               int itemPosition     = position;
               
               // ListView Clicked item value
               usuario_dm = (UsuarioDM)lv_usuarios.getItemAtPosition(position);
       		   final cl.inacap.cursoandroid.unidad1.Crud.ProductosFrescosGlobal global = (cl.inacap.cursoandroid.unidad1.Crud.ProductosFrescosGlobal) getApplicationContext();
       		   global.setUsuarioEdicion(usuario_dm);
       		   
               confirmarOperacion();  
                
              }
         }); 
	}

	/**
	 * Se confirma la operacion a realizar sobre un Usuario
	 * */
	private void confirmarOperacion()
	{
		final CharSequence[] items = {"Editar", "Borrar", "Cancelar"};
		 
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Que operacion desea hacer con: " + usuario_dm.getNombre() + "?");
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	if(item == 0){
		    		editarUsuario();
		    	}
		    	else if(item == 1)
		    	{
		    		borrarUsusario();
		    	}
		    	else if(item == 2)
		    	{
		    		dialog.cancel();	
		    	}
		    }
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private void editarUsuario()
	{
		Intent intent = new Intent(UsuarioListaActivity.this, cl.inacap.cursoandroid.unidad1.Crud.UsuarioNuevoActivity.class);
		intent.putExtra("editar", true);
		UsuarioListaActivity.this.startActivity(intent);
	}
	
	/**
	 * Se realiza la eliminacion de un usuario
	 * */
	private void borrarUsusario()
	{
		final cl.inacap.cursoandroid.unidad1.Crud.ProductosFrescosGlobal global = (cl.inacap.cursoandroid.unidad1.Crud.ProductosFrescosGlobal) getApplicationContext();
		
		if(usuario_dm.getId() == global.getUsuario().getId())
		{
			Toast.makeText(this, "No se puede eliminar el usuario Logeado", Toast.LENGTH_SHORT).show();
			return;
		}
		
		//Se valida si el usuario no es el usuario activo
		int largo = global.getUsuarios().size();
		ArrayList<UsuarioDM> usuarios = global.getUsuarios();
		for(int i=0;i < largo;i++)
		{
			if(usuario_dm.getId() == usuarios.get(i).getId())
			{
				usuarios.remove(i);
				break;
			}	
		}
		global.setUsuarios(usuarios);
		cargarUsuarios();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.usuario_lista, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.mn_usuario_nuevo) {
			Intent intent = new Intent(UsuarioListaActivity.this, UsuarioNuevoActivity.class);
			intent.putExtra("editar", false);
			
			UsuarioListaActivity.this.startActivity(intent);
		
			return true;
		}
		else if (id == R.id.mn_usuario_salir) {
			confirmarSalir();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override 
	protected void onStart() {
	   super.onStart();
	   //Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
	   cargarUsuarios();
	}
		 
	@Override 
	protected void onResume() {
	   super.onResume();
	   //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
	}
	 
	@Override 
	protected void onPause() {
	   //Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
	   super.onPause();
	}
	 
	@Override 
	protected void onStop() {
	   super.onStop();
	   //Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show(); 
	}
		 
	@Override 
	protected void onRestart() {
	   super.onRestart();
	   //Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
	}
		
	/**
	 * Se pregunta al usuario si realmente desea salir de la aplicacion
	 * */
	private void confirmarSalir()
	{
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
        dialogo1.setTitle("Confirmacion");  
        dialogo1.setMessage("Esta seguro de salir de la aplicacion?");
        dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogo1, int id) {  
            	final ProductosFrescosGlobal global = (ProductosFrescosGlobal)getApplicationContext();
    			global.setUsuario(null);  
    			finish();
            }  
        });  
        dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogo1, int id) {  
                return;
            }  
        });            
        dialogo1.show();  
	}
	
}
