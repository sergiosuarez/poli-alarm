package com.mario.polialarm;


import com.mario.polialarm.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class Menu extends Activity {
	Button btn_crear;
	Button btn_mostrar;
	Button btn_melodia;

	Button btn_salir;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_principal);

		btn_crear = (Button) findViewById(R.id.btn_crear);
		btn_crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent ir_menu_main = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(ir_menu_main);
			}
		});
		
		btn_mostrar = (Button) findViewById(R.id.btn_mostrar_cal);
		btn_mostrar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
				Intent ir_menu_main = new Intent(getApplicationContext(), Mostrar_calendario.class);
				startActivity(ir_menu_main);
			}
		});
		
		btn_melodia = (Button) findViewById(R.id.btn_melodia);
		btn_melodia.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				finish();
				Intent ir_menu_tonos= new Intent(getApplicationContext(), Tonos.class);
				startActivity(ir_menu_tonos);
			}
		});
		
		btn_salir = (Button) findViewById(R.id.btn_salir);
		btn_salir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
	}
//#53933f

}
