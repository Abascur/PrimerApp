package com.example.abascur.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textNro,textNombre;
    private Button btnVolver;
    private CheckBox checkBoxConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Obtenemos una referencia a los controles de la interfaz
        //primero
        textNro = findViewById(R.id.textNro);
        textNombre = findViewById(R.id.textNombre);
        //despues
        btnVolver = findViewById(R.id.btnVolver);
        //luego
        checkBoxConfirm = findViewById(R.id.checkBoxConfirm);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        String  nombre =bundle.getString("nombre");
        String  nro = String.valueOf(bundle.getInt("nro"));
        textNombre.setText(nombre);
        textNro.setText(nro);


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //agrgar en el manifest
                //android:noHistory="true"
                if(checkBoxConfirm.isChecked()){
                    sendPrincipalActivity();
                }else{
                    Toast.makeText(getApplicationContext(),"Debe confirmar antes de volver!",Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    private void sendPrincipalActivity(){
        //Creamos el Intent
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);

        startActivity(intent);
    }
}
