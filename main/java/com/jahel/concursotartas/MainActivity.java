package com.jahel.concursotartas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private String textoNombre, textoApellidos, textoEdad, textoNombreConcurso; //Variables
    private EditText nombre, apellidos, edad, nombreconcurso;
    private boolean otroconcurso = false, sexo = false;
    private RadioButton hombre, mujer, otro;
    private int edadUsuario = 0;
    private RadioGroup grupo;
    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_layout_main);


        check = findViewById(R.id.check);  //Asignación de las variables a las vistas del XML con su Id
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        edad = findViewById(R.id.edad);
        nombreconcurso = findViewById(R.id.nombreconcurso);

        grupo = findViewById(R.id.grupo);
        hombre = findViewById(R.id.hombre);
        mujer = findViewById(R.id.mujer);
        otro = findViewById(R.id.otro);

        check.setOnCheckedChangeListener(this);

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {  //Metodo para comprobar si han activado el checkbox

        if (isChecked) {  //Si lo han activado aparecera el campo nombreconcurso y otroconcurso se volverá true, me ayudará para saber si lo han pulsado o no, lo usaré mas adelante
            nombreconcurso.setVisibility(View.VISIBLE);
            otroconcurso = true;
        } else {
            nombreconcurso.setVisibility(View.GONE);
            otroconcurso = false;
            nombreconcurso.setText("");
            check.setChecked(false); //En caso de que no esté activado, ocurrirá lo contrario y con esta orden haré que se desactive visualmente, servirá mas adelante.
        }
    }

    public void boton(View vista) {

        if (hombre.isChecked() || mujer.isChecked() || otro.isChecked()) {
            sexo = true;
        }

        textoNombre = nombre.getText().toString();
        textoApellidos = apellidos.getText().toString();
        textoEdad = edad.getText().toString();

        if (!textoEdad.equals("")) {
            edadUsuario = Integer.parseInt(textoEdad);
        }

        textoNombreConcurso = (otroconcurso == true) ? nombreconcurso.getText().toString() : "nada";


        if (textoNombre.equals("") || textoApellidos.equals("") || edadUsuario <= 17 || textoNombreConcurso.equals("") || !sexo) {

            Toast.makeText(this, "No puedes inscribirte si eres menor de edad o hay un campo sin rellenar.", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(this, "Inscripción realizada con éxito.", Toast.LENGTH_LONG).show();

            nombre.setText("");
            apellidos.setText("");
            edad.setText("");
            onCheckedChanged(check, false);
            grupo.clearCheck();
            sexo = false;
        }

        textoNombre = "";
        textoApellidos = "";
        textoEdad = "";
        textoNombreConcurso = "";
        edadUsuario = 0;


    }

}


