package com.mario.polialarm;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.mario.objects.Materia;
import com.mario.polialarm.db.AdminSQLiteOpenHelper;
import com.mario.polialarm.R;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class Mostrar_calendario extends Activity {
	Button btn_menu_principal;
	Button btn_activar;
	Button btn_borrar;
	
	List<Materia> lista_materias;
	List<String> list;
	ArrayAdapter<String> adapter;

    public TextView mat1;
    public TextView mat2;
    public TextView mat3;
    public TextView mat4;
    public TextView mat5;
    public TextView mat6;
    public TextView mat7;
    public TextView mat8;
    
    public Intent i ;
    public PendingIntent operation = null;
    public AlarmManager alarmManager = null;
	
	AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"materias.db", null, 1);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrar_calendario);
		
		mat1		= (TextView) findViewById(R.id.txt_mat1);
		mat2		= (TextView) findViewById(R.id.txt_mat2);
		mat3		= (TextView) findViewById(R.id.txt_mat3);
	    mat4		= (TextView) findViewById(R.id.txt_mat4);
	    mat5		= (TextView) findViewById(R.id.txt_mat5);
	    mat6		= (TextView) findViewById(R.id.txt_mat6);
	    mat7		= (TextView) findViewById(R.id.txt_mat7);
	    mat8		= (TextView) findViewById(R.id.txt_mat8);
	    TextView[]  texts={mat1,mat2,mat3,mat4,mat5,mat6,mat7,mat8};

		//i= new Intent("com.mario.polialarm.demoactivity");
		
		lista_materias = admin.obtener_materias();
		int cont=0;
		for (Materia rec:lista_materias){
			
			String nom= rec.ma_nombre;
			String aula= rec.ma_aula;
			String hora= ""+rec.hour+":"+rec.minute;
			String dia= ""+rec.day+"/"+rec.month+"/"+rec.year;
			texts[cont].setText(nom+"-"+aula+" - "+dia+" - "+hora);
			cont++;
		}
		
		

		btn_menu_principal = (Button) findViewById(R.id.btn_mos_menu);
		btn_menu_principal.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
				Intent ir_menu_main = new Intent(getApplicationContext(), Menu.class);
				startActivity(ir_menu_main);
			}
		});
		
		btn_borrar = (Button) findViewById(R.id.btn_mos_borrar);
		btn_borrar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				  SQLiteDatabase db = admin.getReadableDatabase();
				  db.execSQL("delete from materias");
				  /** Alert is set successfully */
			        Toast.makeText(getBaseContext(), "Calendario encerado exitosamente..!",Toast.LENGTH_SHORT).show();
					
			}
		});
		
		btn_activar = (Button) findViewById(R.id.btn_mos_activar);
		btn_activar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				/*Intent ir_menu_main = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(ir_menu_main);*/
				
				//Activando las alarmas que estan en la BD

				/** This intent invokes the activity DemoActivity, which in turn opens the AlertDialog window */
				
				
				lista_materias = admin.obtener_materias();
				int cont=0;
				for (Materia rec:lista_materias){
					
					String nom= rec.ma_nombre;
					
					/*
					String aula= rec.ma_aula;
					String hora= ""+rec.hour+":"+rec.minute;
					String dia= ""+rec.day+"/"+rec.month+"/"+rec.year;

				    TextView[]  texts={mat1,mat2,mat3,mat4,mat5,mat6,mat7,mat8};
					texts[cont].setText(nom+"-"+aula+" - "+dia+" - "+hora);
					*/

					/** Creating a Pending Intent */
					PendingIntent operation = PendingIntent.getActivity(getBaseContext(), 0, new Intent("com.mario.polialarm.demoactivity"),
Intent.FLAG_ACTIVITY_NEW_TASK);
					
					/** Getting a reference to the System Service ALARM_SERVICE */
					AlarmManager alarmManager= (AlarmManager) getBaseContext().getSystemService(ALARM_SERVICE);
														
					/** Creating a calendar object corresponding to the date and time set by the user */
					GregorianCalendar calendario = new GregorianCalendar(rec.year,rec.month,rec.day, rec.hour, rec.minute);
					
					/** Converting the date and time in to milliseconds elapsed since epoch */
					long alarm_time = calendario.getTimeInMillis();
					//		cont]
					/** Setting an alarm, which invokes the operation at alart_time */
			        alarmManager.set(AlarmManager.RTC_WAKEUP  , alarm_time , operation);
			        
			        /** Alert is set successfully */
			        Toast.makeText(getBaseContext(), "Alarma para:"+nom+" fue activada exitosamente..",Toast.LENGTH_SHORT).show();
					
					cont++;
				}
				
				
				Toast toast1 =	Toast.makeText(getApplicationContext(),	"Se activaron todos los horarios del calendario actual..", Toast.LENGTH_SHORT);
				toast1.show();
			}
		});

	}
//#53933f

}
