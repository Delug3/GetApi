package com.delug3.rickandmorty;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesPersonajes_Activity extends AppCompatActivity {

    TextView textCodigoEpisodio,textNombreEpisodio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_personajes);

        Context context = getApplicationContext();

        textCodigoEpisodio = (TextView) findViewById(R.id.txtCodigoEpisodio);
        textNombreEpisodio = (TextView) findViewById(R.id.txtNombreEpisodio);

        Intent i = getIntent();
        Toast.makeText(DetallesPersonajes_Activity.this, i.getIntExtra("EPISODIO_ID", 0) + "", Toast.LENGTH_SHORT).show();

        String codigo,nombre;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                codigo= null;
                nombre=null;
            } else {
                codigo= extras.getString("EPISODIO_CODIGO");
                nombre= extras.getString("EPISODIO_NOMBRE");

                textCodigoEpisodio.setText(codigo);
                textNombreEpisodio.setText(nombre);

            }
        } else {
            codigo= (String) savedInstanceState.getSerializable("EPISODIO_CODIGO");
            nombre= (String) savedInstanceState.getSerializable("EPISODIO_NOMBRE");
        }

    }


}
