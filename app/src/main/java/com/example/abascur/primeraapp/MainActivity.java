package com.example.abascur.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edit1;
    private EditText edit2;
    private TextView text1;
    private Button boton1;


    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtenemos una referencia a los controles de la interfaz
        text1=findViewById(R.id.text1);
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        boton1=findViewById(R.id.btnGuardar);

        //se instancia las sharedPreference
        prefs = getSharedPreferences("Preference", Context.MODE_PRIVATE);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Se obtiene el valor ingresado
                String soloNumeros=edit1.getText().toString();
                String SoloLetras=edit2.getText().toString();


                // para ocultar el teclado
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(edit2.getWindowToken(), 0);


                if(isValidForm()){
                    text1.setText("Nro Ingresos: "+getNumIngresos());
                    saveIngresos(getNumIngresos()+1);
                    sendSecondActivity(Integer.parseInt(soloNumeros),SoloLetras);
                    Log.d("Debug","Entro al if");
                }else{
                    Toast.makeText(getApplicationContext(),"Faltan Campos Por Completar",Toast.LENGTH_LONG).show();
                    Log.d("Debug","Error en el if");
                }


            }
        });

    }

    private boolean isValidForm(){
        //ValidarFormulario
        boolean r=false;
        if( TextUtils.isEmpty(edit1.getText())){

            edit1.setError( "El Nro es obligatorio" );

        }else if( TextUtils.isEmpty(edit2.getText())){

            edit2.setError( "El nombre es obligatorio" );

        }else{
            r=true;
        }

        return r;
    }

    private void sendSecondActivity(int nro, String nombre){
        //Comunicar actividades
        //Creamos el Intent
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        //Creamos la información a pasar entre actividades
        Bundle b = new Bundle();
        b.putString("nombre", nombre);
        b.putInt("nro", nro);
        //Añadimos la información al intent
        intent.putExtras(b);
        //Iniciamos la nueva actividad
        startActivity(intent);
    }

    public void saveIngresos(int num){
        //se guarda con sharedpreference
        SharedPreferences.Editor editor = prefs.edit();
        //guardar Entero en sharedPreference
        editor.putInt("NumIngresos", num);
        //guardar string en sharedPreference
       // editor.putString("Letras", "num");

        editor.apply();
    }

    public int getNumIngresos(){
        //Obtener String desde sharedPreference
      //  prefs.getString("Letras", "");
        //obtener int desde sharedPreference
       return prefs.getInt("NumIngresos", 0);
    }


    @Override
    protected void onResume() {
        text1.setText("Nro Ingresos: "+getNumIngresos());
        super.onResume();
    }

}
