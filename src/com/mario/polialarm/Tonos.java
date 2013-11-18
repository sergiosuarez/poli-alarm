package com.mario.polialarm;


import com.mario.polialarm.R;
import com.mario.polialarm.db.AdminSQLiteOpenHelper;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;

public class Tonos extends Activity {
	Button btn_m1;
	Button btn_m2;
	Button btn_m3;
	Button btn_m4;
	Button btn_m_salir;

	public MediaPlayer sound ;
	public AlertDialog.Builder builder;

	AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"materias.db", null, 1);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_dialog);

		btn_m1 = (Button) findViewById(R.id.btn_mel1);
		btn_m1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				/* Codigo para poder ***/
				sound = MediaPlayer.create(getApplicationContext(), R.raw.com_300_hu_hu_hu);
				  if (sound.isPlaying()) {
				   sound.stop();
				  } else {
				   try {
					   sound.start();
				    sound.setLooping(true);
				   } catch (IllegalStateException e) {
				    e.printStackTrace();
				   }
				  }

					/** Creating a alert dialog builder */
					builder = new AlertDialog.Builder(Tonos.this);
					
					/** Setting title for the alert dialog */
					builder.setTitle("Elegir tono?");
					
					/** Defining an OK button event listener */
					builder.setPositiveButton("OK", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							/** Exit application on click OK */
							sound.stop();

							insert_tono("com_300_hu_hu_hu");
							//Codigo q hace insert a la base
						}						
					});

					builder.setNegativeButton("Cancelar", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sound.stop();
						}						
					});
					builder.show();
			}
		});
		
		btn_m2 = (Button) findViewById(R.id.btn_mel2);
		btn_m2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				/* Codigo para poder ***/
				sound = MediaPlayer.create(getApplicationContext(), R.raw.com_beep_beep);
				  if (sound.isPlaying()) {
				   sound.stop();
				  } else {
				   try {
					   sound.start();
				    sound.setLooping(true);
				   } catch (IllegalStateException e) {
				    e.printStackTrace();
				   }
				  }
				  /** Creating a alert dialog builder */
					builder = new AlertDialog.Builder(Tonos.this);
					
					/** Setting title for the alert dialog */
					builder.setTitle("Elegir tono?");
					
					/** Defining an OK button event listener */
					builder.setPositiveButton("OK", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							/** Exit application on click OK */
							sound.stop();
							insert_tono("com_beep_beep");
							
							//Codigo q hace insert a la base
						}						
					});

					builder.setNegativeButton("Cancelar", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sound.stop();
						}						
					});
					builder.show();
			}
		});
		
		btn_m3 = (Button) findViewById(R.id.btn_mel3);
		btn_m3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				/* Codigo para poder ***/
				sound = MediaPlayer.create(getApplicationContext(), R.raw.com_por_que_senor_por_que_sms);
				  if (sound.isPlaying()) {
				   sound.stop();
				  } else {
				   try {
					   sound.start();
				    sound.setLooping(true);
				   } catch (IllegalStateException e) {
				    e.printStackTrace();
				   }
				  }
				  
				  /** Creating a alert dialog builder */
					builder = new AlertDialog.Builder(Tonos.this);
					
					/** Setting title for the alert dialog */
					builder.setTitle("Elegir tono?");
					
					/** Defining an OK button event listener */
					builder.setPositiveButton("OK", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							/** Exit application on click OK */
							sound.stop();
							insert_tono("com_por_que_senor_por_que_sms");
							
							//Codigo q hace insert a la base
						}						
					});

					builder.setNegativeButton("Cancelar", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sound.stop();
						}						
					});
					builder.show();
			}
		});
		

		btn_m4 = (Button) findViewById(R.id.btn_mel4);
		btn_m4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				/* Codigo para poder ***/
				sound = MediaPlayer.create(getApplicationContext(), R.raw.com_sms);
				  if (sound.isPlaying()) {
				   sound.stop();
				  } else {
				   try {
					   sound.start();
				    sound.setLooping(true);
				   } catch (IllegalStateException e) {
				    e.printStackTrace();
				   }
				  }
				  
				  /** Creating a alert dialog builder */
					builder = new AlertDialog.Builder(Tonos.this);
					
					/** Setting title for the alert dialog */
					builder.setTitle("Elegir tono?");
					
					/** Defining an OK button event listener */
					builder.setPositiveButton("OK", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							/** Exit application on click OK */
							sound.stop();
							insert_tono("com_sms");
							//Codigo q hace insert a la base
						}						
					});
					
					builder.setNegativeButton("Cancelar", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sound.stop();
						}						
					});
					builder.show();
			}
		});
		
		btn_m_salir = (Button) findViewById(R.id.btn_mel_salir);
		btn_m_salir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();

				Intent ir_menu_main = new Intent(getApplicationContext(), Menu.class);
				startActivity(ir_menu_main);
			}
		});
	}
//#53933f
	
	public void insert_tono(String tono){
		SQLiteDatabase bd = admin.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("so_nombre", tono);
		bd.update("sonido", valores, "so_id=1", null);						
		bd.close();
		
	}

}
