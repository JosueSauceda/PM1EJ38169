package com.example.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio3.configuracion.SQLiteConexion;
import com.example.ejercicio3.tablas.Personas;
import com.example.ejercicio3.tablas.Transacciones;

public class AccionesActivity extends AppCompatActivity {

    SQLiteConexion conexion;
    Button btneliminar, btnact;
    EditText txtnombre, txtapellido, txtedad, txtcorreo, txtdireccion;
    private Personas obj;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acciones);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        obj = (Personas) getIntent().getSerializableExtra("objeto");

        txtnombre = (EditText) findViewById(R.id.txtnombr);
        txtapellido = (EditText) findViewById(R.id.txtape);
        txtedad = (EditText) findViewById(R.id.txted);
        txtcorreo = (EditText) findViewById(R.id.txtcorr);
        txtdireccion = (EditText) findViewById(R.id.txtdir);
        btneliminar = (Button) findViewById(R.id.btneliminar);
        btnact = (Button) findViewById(R.id.btnact);

        id = obj.getId();
        txtnombre.setText(obj.getNombre());
        txtapellido.setText(obj.getApellido());
        txtedad.setText(obj.getEdad());
        txtcorreo.setText(obj.getCorreo());
        txtdireccion.setText(obj.getDireccion());


        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });
        btnact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
            }
        });

    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {
                id.toString()
        };

        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.TbPersonas, wherecond, params);

        Toast.makeText(getApplicationContext(), "Persona Eliminada", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
    }
    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {
                id.toString()
        };

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, txtnombre.getText().toString());
        valores.put(Transacciones.apellido, txtapellido.getText().toString());
        valores.put(Transacciones.edad, txtedad.getText().toString());
        valores.put(Transacciones.correo, txtcorreo.getText().toString());
        valores.put(Transacciones.direccion, txtdireccion.getText().toString());

        db.update(Transacciones.TbPersonas, valores, Transacciones.id + "=?", params);

        Toast.makeText(getApplicationContext(), "Persona Actualizada", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
    }
}