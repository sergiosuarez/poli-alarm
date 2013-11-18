package com.mario.polialarm.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import com.mario.objects.Materia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

	public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
		super(context, nombre, factory, version);
	}
	
	/*
	 *							MATERIAS 
	public String ma_id;
	public String ma_nombre;
	public String ma_aula;
	public String ma_estado;
	public int year;
	public int month;
	public int day;
	public int hour;
	public int minute;
	 * */

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Creacion de Tablas
		db.execSQL("create table materias(ma_id integer primary key AUTOINCREMENT, ma_nombre text,ma_aula text,ma_estado text, year integer,month integer,day integer,hour integer,minute integer)");
		db.execSQL("create table sonido(so_id integer primary key AUTOINCREMENT, so_nombre text)");
		db.execSQL("insert into sonido(so_nombre) VALUES('tono1')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
		// Borrado de Tablas
		db.execSQL("drop table if exists materias");
		db.execSQL("drop table if exists sonido");	
		//Creacion de Tablas
		db.execSQL("create table materias(ma_id integer primary key AUTOINCREMENT, ma_nombre text,ma_aula text,ma_estado text, year integer,month integer,day integer,hour integer,minute integer)");
		db.execSQL("create table sonido(so_id integer primary key AUTOINCREMENT, so_nombre text)");
		db.execSQL("insert into sonido(so_nombre) VALUES('tono1')");
	}	

	/* Funcion para traer los datos de la tabla materia que tienen estado N = no y S=si*/
	 public List<Materia> obtener_materias() {
		 	List<Materia> campos = new ArrayList<Materia>();
		  String selectQuery = "select * from materias where ma_estado='N'";
		  SQLiteDatabase db = this.getReadableDatabase();
		  Cursor cursor = db.rawQuery(selectQuery, null);
		  if (cursor.moveToFirst()) {
		   do {
			   Materia mu= new Materia();
			   mu.setMa_id(cursor.getString(0));
			   mu.setMa_nombre(cursor.getString(1));
			   mu.setMa_aula(cursor.getString(2));
			   mu.setMa_estado(cursor.getString(3));
			   mu.setYear(cursor.getInt(4));
			   mu.setMonth(cursor.getInt(5));
			   mu.setDay(cursor.getInt(6));
			   mu.setHour(cursor.getInt(7));
			   mu.setMinute(cursor.getInt(8));
			   campos.add(mu);
		   } while (cursor.moveToNext());
		  }
		  cursor.close();
		  db.close();
		  return campos;
		 }
	 
	 public String consulta_num_observacion(){
			//AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"ecogis.db", null, 1);
			SQLiteDatabase bd = this.getWritableDatabase();
			//Aqui hay que poner el id de la finca para ver el numero de observaciones actuales	
			Cursor fila = bd.rawQuery("select ma_nombre from materias", null);
			int sum;
			if (fila.moveToFirst()) {
				sum=1;
				do {
					sum++;					
				} while (fila.moveToNext());
			} else{				
				sum=1;
			}
			bd.close();
			return sum+"";
		}
	 
}