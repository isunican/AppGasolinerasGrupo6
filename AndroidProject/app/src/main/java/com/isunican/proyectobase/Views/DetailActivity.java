package com.isunican.proyectobase.Views;

import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Model.*;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;


/*
------------------------------------------------------------------
    Vista de detalle

    Presenta datos de detalle de una Gasolinera concreta.
    La gasolinera a mostrar se le pasa directamente a la actividad
    en la llamada por intent (usando putExtra / getExtra)
    Para ello Gasolinera implementa la interfaz Parcelable
------------------------------------------------------------------
*/
public class DetailActivity extends AppCompatActivity {

    TextView rotulo;
    TextView direccion;
    TextView localidad;
    TextView precioGasoleoA;
    TextView precioGasolina95;
    Gasolinera g;

    /**
     * onCreate
     *
     * Crea los elementos que conforman la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);

        // captura el TextView
        // obtiene el objeto Gasolinera a mostrar
        // y lo introduce en el TextView convertido a cadena de texto
        rotulo = findViewById(R.id.rotuloId);
        direccion = findViewById(R.id.direccionId);
        localidad = findViewById(R.id.localidadId);
        precioGasoleoA = findViewById(R.id.precioGasoleoAId);
        precioGasolina95 = findViewById(R.id.precioGasolina95Id);

        g = getIntent().getExtras().getParcelable(getResources().getString(R.string.pasoDatosGasolinera));
        rotulo.setText(g.getRotulo());
        direccion.setText(g.getDireccion());
        localidad.setText(g.getLocalidad());
        precioGasoleoA.setText(String.valueOf(g.getGasoleoA()));
        precioGasolina95.setText(String.valueOf(g.getGasolina95()));

    }
}