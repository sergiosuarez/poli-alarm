package com.mario.polialarm;

import com.mario.polialarm.db.AdminSQLiteOpenHelper;
import com.mario.objects.Materia;
import com.mario.polialarm.R;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends Activity {
	private EditText   nombre_mat, aula_mat;
	public Button btnSetAlarm;
	public Button btnQuitAlarm;
	public DatePicker dpDate;
	public TimePicker tpTime;
	public AlarmManager alarmManager;
	public Materia materia;
	
	public PendingIntent operation;
	public GregorianCalendar calendar;

	AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"materias.db", null, 1);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre_mat		= (EditText) findViewById(R.id.etxt_nom_materia);
        aula_mat		= (EditText) findViewById(R.id.etxt_aula_materia);
		dpDate 			= (DatePicker) findViewById(R.id.dp_date);
		tpTime			= (TimePicker) findViewById(R.id.tp_time);
		materia			= new Materia();
		

		TextView sum_muestrat	= (TextView) findViewById(R.id.sum_muestra);
		//obtiene el numero de la muestra que se va a ingresar
		String num_ob=admin.consulta_num_observacion();
		num_ob=verifica_num(num_ob);
		sum_muestrat.setText("# "+num_ob);
		
		OnClickListener setClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String nombre 		= nombre_mat.getText().toString();
				String aula 		= aula_mat.getText().toString();
				int year 			= dpDate.getYear();
				int month 			= dpDate.getMonth();
				int day				= dpDate.getDayOfMonth();
				int hour 			= tpTime.getCurrentHour();
				int minute 			= tpTime.getCurrentMinute();

				String num=admin.consulta_num_observacion();

				if(nombre.equalsIgnoreCase("")||aula.equalsIgnoreCase("")){
					
						Toast toast_msj=	Toast.makeText(getApplicationContext(),"Ingrese todos los campos de la Materia para Guardar..", Toast.LENGTH_LONG);
						toast_msj.show();
				}else{
						SQLiteDatabase bd = admin.getWritableDatabase();
						ContentValues registro = new ContentValues();
						
						registro.put("ma_nombre", nombre);
						registro.put("ma_aula", aula);
						registro.put("ma_estado", "N");
						registro.put("year", year);
						registro.put("month", month);
						registro.put("day", day);
						registro.put("hour", hour);
						registro.put("minute", minute);
						bd.insert("materias", null, registro);						
						bd.close();
						
						//Limpiar muestras
							TextView sum_muestrat	= (TextView) findViewById(R.id.sum_muestra);
							//obtiene el numero de la materia que se va a ingresar
							sum_muestrat.setText("# "+admin.consulta_num_observacion());
							nombre_mat.setText("");
							aula_mat.setText("");				
						//Fin Limpiar muestras
							
		
						Toast toast1 =	Toast.makeText(getApplicationContext(),	"Se cargaron los datos de la materia..", Toast.LENGTH_SHORT);
						toast1.show();
				
				}
				
			}
		};		
		
		OnClickListener quitClickListener = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
				 myAlertDialog.setTitle("PoliAlarm");
				 myAlertDialog.setMessage(" Esta seguro que desea finalizar??");
				 myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

				  public void onClick(DialogInterface arg0, int arg1) {
					  Toast toast1 =Toast.makeText(getApplicationContext(),"Finalizando..", Toast.LENGTH_SHORT);
					  toast1.show();
					  finish();
				  }});
				 

				 myAlertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
				       
				  public void onClick(DialogInterface arg0, int arg1) {
				  // do something when the Cancel button is clicked
					  Toast toast1 =Toast.makeText(getApplicationContext(),"Accion cancelada, puede seguir registrando materias..", Toast.LENGTH_SHORT);
						toast1.show();
				  }});
				 myAlertDialog.show();
			}
		};
		
        btnSetAlarm = ( Button ) findViewById(R.id.btn_set_alarm);
        btnSetAlarm.setOnClickListener(setClickListener);
        
        btnQuitAlarm = ( Button ) findViewById(R.id.btn_quit_alarm);
        btnQuitAlarm.setOnClickListener(quitClickListener);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    public String verifica_num(String num){
		//String num_ob;
		if(num.trim().compareTo("1")==0){
			Toast.makeText(this, "No Existen materias registradas",
					Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "Puedes ingresar la materia #:" +num,
					Toast.LENGTH_SHORT).show();
		}
		return num;
	}
}
