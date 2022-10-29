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
import com.example.ejercicio3.tablas.Transacciones;

public class MainActivity extends AppCompatActivity {
    //Josué Sauceda 202010060281 y Luis Romero 201730040069
    EditText txtnombre, txtapellido, txtedad, txtcorreo, txtdireccion;
    Button btnlista, btnsalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   txtnombre = (EditText) findViewById(R.id.txtnombre);
   txtapellido = (EditText) findViewById(R.id.txtapellido);
   txtedad = (EditText) findViewById(R.id.txtedad);
   txtcorreo = (EditText) findViewById(R.id.txtcorreo);
   txtdireccion = (EditText) findViewById(R.id.txtdireccion);
   btnsalvar = (Button) findViewById(R.id.btnsalvar);
   btnlista = (Button) findViewById(R.id.btnlista);

 btnsalvar.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         AgregarPersona();
     }
 });

    btnlista.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(intent);
        }
    });
}

    private void AgregarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombre, txtnombre.getText().toString());
        valores.put(Transacciones.apellido, txtapellido.getText().toString());
        valores.put(Transacciones.edad, txtedad.getText().toString());
        valores.put(Transacciones.correo, txtcorreo.getText().toString());
        valores.put(Transacciones.direccion, txtdireccion.getText().toString());

        Long resultado = db.insert(Transacciones.TbPersonas, Transacciones.id, valores);


        Toast.makeText(getApplicationContext(), "Registro Ingresado " + resultado.toString()
                , Toast.LENGTH_SHORT).show();

        db.close();

        ClearScreen();
    }

    private void ClearScreen()
    {
        txtnombre.setText("");
        txtapellido.setText("");
        txtedad.setText("");
        txtcorreo.setText("");
        txtdireccion.setText("");
    }
}
