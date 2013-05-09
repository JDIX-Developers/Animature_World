package com.jdix.animature;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

public class AnimaxGameMenuActivity extends ListActivity {

	private ArrayList	evento;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		/*
		 * super.onCreate(savedInstanceState);
		 * setContentView(R.layout.activity_animax_game_menu); final
		 * ArrayList<String[]> lista = new ArrayList<String[]>();      final
		 * String[] evento1 = {"1","Charmander",""};     lista.add(evento1);  
		 *      final String[] evento2 = {"2","Charmeleon",""};
		 *     lista.add(evento2);       final String[] evento3 =
		 * {"3","Charizard",""};     lista.add(evento3); // Transformamos los
		 * elementos String[] en HashMap para // posteriormente incluirlos en el
		 * Array Global que se utilizará // para rellenar la lista evento = new
		 * ArrayList<HashMap<String, String>>(); for (final String[] evento:
		 * lista) { final HashMap<String, String> datosEvento = new
		 * HashMap<String, String>(); // Aquí es dónde utilizamos las
		 * referencias creadas inicialmente // en el elemento "from"
		 * datosEvento.put("Number", evento[0]); datosEvento.put("Name",
		 * evento[1]); datosEvento.put("Type", evento[2]);
		 * eventos.add(datosEvento); } // Una vez tenemos toda la información
		 * necesaria para rellenar la lista // creamos un elemento que nos
		 * facilitará la tarea: // SimpleAdapter(Actividad, Array de HashMap con
		 * elementos, Fichero XML // del // diseño de cada fila, Cadenas del
		 * HashMap, Ids del Fichero XML del // diseño de cada fila) final
		 * SimpleAdapter ListadoAdapter = new SimpleAdapter(this, Eventos,
		 * R.layout.row, from, to); setListAdapter(ListadoAdapter);
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animax_game_menu, menu);
		return true;
	}

}
