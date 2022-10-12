package com.dmosoft.appsimcredito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //instanciamos la base de datos (esto se coloca siempre el contexto, nombre, tabla cursor (factory), y la vercion
    clsDataBase ohDb = new clsDataBase(this, "dbbiblioteca",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intanciar y referenciar los Id,s del archivo xml (activity_main.xml)
        EditText nombre = findViewById(R.id.etnombre);
        EditText correo = findViewById(R.id.etcorreo);
        EditText contrasena = findViewById(R.id.etcontraseña);
        ImageButton btnsave = findViewById(R.id.btnsave);
        ImageButton btnbuscar = findViewById(R.id.btnbuscar);
        ImageButton btnupdate = findViewById(R.id.btnupdate);
        ImageButton btndelete = findViewById(R.id.btndelete);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(nombre.getText().toString(),correo.getText().toString(),contrasena.getText().toString());
            }
        });


    }

    private void saveUser(String snombre, String scorreo, String scontrasena) {
        //instanciamos la base de datos (esto se coloca siempre el contexto, nombre, tabla cursor (factory), y la vercion
        //clsDataBase ohDb = new clsDataBase(this, "dbbiblioteca",null,1);
        // instanciar la base de datos en modo de escritura (INSERT, IPDATE, DELETE);
        SQLiteDatabase db = ohDb.getReadableDatabase();
        //Try sirve para el manejo de exepciones
        try {
            // Crear un objeto de ContentValues para que conenga los mismo campos de la tabla user
            ContentValues cvUser = new ContentValues();
            cvUser.put("nombre",snombre);
            cvUser.put("correo",scorreo);
            cvUser.put("contrasena",scontrasena);
            // guardar en la tabla user lo que contiene cvuser

            db.insert("user",null,cvUser);
            db.close();
            Toast.makeText(getApplicationContext(),"Usuario Guardado Exitosamente ☻☻☻", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"ERROR!!!!, ☺☺☺", Toast.LENGTH_SHORT).show();
        }




    }
}