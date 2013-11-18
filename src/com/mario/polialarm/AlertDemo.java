package com.mario.polialarm;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.mario.polialarm.R;
import com.mario.polialarm.db.AdminSQLiteOpenHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AlertDemo extends DialogFragment {
	public MediaPlayer sound ;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		/** Turn Screen On and Unlock the keypad when this alert dialog is displayed */
		getActivity().getWindow().addFlags(LayoutParams.FLAG_TURN_SCREEN_ON | LayoutParams.FLAG_DISMISS_KEYGUARD);
		
		
		/** Creating a alert dialog builder */
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		

		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity().getApplicationContext(),"materias.db", null, 1);
		String selectQuery = "select * from sonido";
		  SQLiteDatabase db = admin.getReadableDatabase();
		  Cursor cursor = db.rawQuery(selectQuery, null);
		  String tono="";
		  if (cursor.moveToFirst()) {
			  //Recorremos el cursor hasta que no haya m�s registros
			     do {
			          String nombre = cursor.getString(1);
			          tono=nombre;
			     } while(cursor.moveToNext());
			  
		  }
		  db.close();
			
		  if(tono.compareTo("com_sms")==0){
				sound = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.com_sms );
		  }else if(tono.compareTo("com_por_que_senor_por_que_sms")==0){
				sound = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.com_por_que_senor_por_que_sms );
		  }else if(tono.compareTo("com_beep_beep")==0){
				sound = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.com_beep_beep );
		  }else if(tono.compareTo("com_300_hu_hu_hu")==0){
				sound = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.com_300_hu_hu_hu );
		  }

		  
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
		
		final FrameLayout frameView = new FrameLayout(getActivity().getApplicationContext());
		builder.setView(frameView);

		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		
		String nombre="";
		String aula ="";
		String horas="";
		
		Calendar c2 = new GregorianCalendar();
		
		int hora = c2.get(Calendar.HOUR_OF_DAY);
		int minutos = c2.get(Calendar.MINUTE);
		
		String selectQuery1 = "select * from materias where hour="+hora+" and minute="+minutos;

		  Toast.makeText(getActivity().getApplicationContext(), "QUERYYY:"+selectQuery1,Toast.LENGTH_SHORT).show();
		  
		  SQLiteDatabase db1 = admin.getReadableDatabase();
		  Cursor cursor1 = db1.rawQuery(selectQuery1, null);
		  if (cursor1.moveToFirst()) {
			  //Recorremos el cursor hasta que no haya m�s registros
			     do {
			          nombre = cursor1.getString(1);
			          aula = cursor1.getString(2);
			          horas = cursor1.getString(7)+":"+cursor1.getString(8);
			     } while(cursor1.moveToNext());
			  
		  }
		  db1.close();

		/*TextView txt_nombre	= (TextView) getActivity().findViewById(R.id.txt_nom_materia);
		TextView txt_aula	= (TextView) getActivity().findViewById(R.id.txt_aula_materia);
		TextView txt_horas	= (TextView) getActivity().findViewById(R.id.txt_hora_materia);
		
		txt_nombre.setText(nombre);
		txt_aula.setText(aula);
		txt_horas.setText(horas);

		View dialoglayout = inflater.inflate(R.layout.simple_password, frameView);
		builder.setView(dialoglayout);
		*/

			builder.setTitle("Tienes Clases de:");
		/** Setting the content for the alert dialog */
		builder.setMessage(nombre+"--"+aula+"--"+horas);
		
		/** Defining an OK button event listener */
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				/** Exit application on click OK */
				sound.stop();
				getActivity().finish();
			}						
		});
		
		/** Creating the alert dialog window */
		return builder.create();
	}
	
	/** The application should be exit, if the user presses the back button */ 
	@Override
	public void onDestroy() {		
		super.onDestroy();
		getActivity().finish();
	}
	
}
