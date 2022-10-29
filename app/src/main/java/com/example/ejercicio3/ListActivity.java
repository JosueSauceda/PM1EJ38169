package com.example.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.ejercicio3.configuracion.SQLiteConexion;
import com.example.ejercicio3.tablas.Personas;
import com.example.ejercicio3.tablas.Transacciones;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listperson;
    ArrayList<Personas> lista;
    ArrayList<String> listaconcatenada;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        searchView = (SearchView) findViewById(R.id.buscar);
        listperson = (ListView) findViewById(R.id.listperson);

        GetListContacts();

        ArrayAdapter adc = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaconcatenada);
        listperson.setAdapter(adc);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adc.getFilter().filter(newText);

                return false;
            }
        });

        listperson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AccionesActivity.class);
                intent.putExtra("objeto", lista.get(position));
                startActivity(intent);
            }
        });
    }
    private void GetListContacts(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas listpersonas = null;
        lista = new ArrayList<Personas>();
        Cursor cursor = db.rawQuery(Transacciones.Getpersonas, null);
        while (cursor.moveToNext()){
            listpersonas = new Personas();
            listpersonas.setId(cursor.getInt(0));
            listpersonas.setNombre(cursor.getString(1));
            listpersonas.setApellido(cursor.getString(2));
            listpersonas.setEdad(cursor.getString(3));
            listpersonas.setCorreo(cursor.getString(4));
            listpersonas.setDireccion(cursor.getString(5));
            lista.add(listpersonas);
        }

        cursor.close();

        LLenarLista();
    }
    private void LLenarLista()
    {
        listaconcatenada = new ArrayList<String>();

        for(int i =0;  i < lista.size(); i++)
        {
            listaconcatenada.add(lista.get(i).getNombre() + " | " +
                    lista.get(i).getApellido() + " | " +
                    lista.get(i).getCorreo());
        }
    }


}
