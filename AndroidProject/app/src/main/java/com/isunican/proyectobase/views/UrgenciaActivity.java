package com.isunican.proyectobase.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.isunican.proyectobase.R;
import com.isunican.proyectobase.model.Gasolinera;
import com.isunican.proyectobase.presenter.PresenterGasolineras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrgenciaActivity extends AppCompatActivity {

    ArrayList<Gasolinera> gasolineras;
    ArrayAdapter<Gasolinera> adapter;
    ListView listViewGasolineras;
    Toast toast;
    TextView puntoReferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgencia);
        Bundle bundle = getIntent().getExtras();
        gasolineras = bundle.getParcelableArrayList("ListaGasolinerasCercanas");
        Intent intent=getIntent();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_gasolinera2_foreground);
        puntoReferencia=findViewById(R.id.textView4);
        puntoReferencia.setText(intent.getExtras().getString("Etiqueta"));
        adapter = new UrgenciaActivity.GasolineraArrayAdapter(this, 0, gasolineras);
        listViewGasolineras = findViewById(R.id.listViewGasolineras);
        if (!gasolineras.isEmpty()) {
            // datos obtenidos con exito
            listViewGasolineras.setAdapter(adapter);
            toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_exito), Toast.LENGTH_LONG);
        }
        if (toast != null) {
            toast.show();
        }
        if(listViewGasolineras!=null) {
            listViewGasolineras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                    /* Obtengo el elemento directamente de su posicion,
                     * ya que es la misma que ocupa en la lista
                     */
                    Intent myIntent = new Intent(UrgenciaActivity.this, DetailActivity.class);
                    myIntent.putExtra(getResources().getString(R.string.pasoDatosGasolinera),
                    gasolineras.get(position));
                    UrgenciaActivity.this.startActivity(myIntent);

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1urgencia, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.itemInfo){
            Intent myIntent = new Intent(UrgenciaActivity.this, InfoActivity.class);
            UrgenciaActivity.this.startActivity(myIntent);
        }else if(item.getItemId()==R.id.itemPantallaPrincipal){
            Intent intentPrincipal=new Intent(UrgenciaActivity.this,MainActivity.class);
            UrgenciaActivity.this.startActivity(intentPrincipal);
        }
        return true;
    }
    class GasolineraArrayAdapter extends ArrayAdapter<Gasolinera> {

        private Context context;
        private List<Gasolinera> listaGasolineras;


        // Constructor
        public GasolineraArrayAdapter(Context context, int resource, List<Gasolinera> objects) {
            super(context, resource, objects);
            this.context = context;
            this.listaGasolineras = objects;

        }

        // Llamado al renderizar la lista
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Obtiene el elemento que se está mostrando
            Gasolinera gasolinera = listaGasolineras.get(position);

            // Indica el layout a usar en cada elemento de la lista
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_gasolinera, null);

            // Asocia las variables de dicho layout

            /*
            He cambiado las elementos de cada item de acuerdo a lo que exige la funcionalidad Listar gasolineras más cercanas
             */
            TextView direccion = view.findViewById(R.id.direccionId);
            TextView gasoleoB = view.findViewById(R.id.precioDieselId);
            TextView distancia = view.findViewById(R.id.distanciaHastaGasolineraId);

            // Y carga los datos del item

            direccion.setText(gasolinera.getDireccion());
            if (gasolinera.getGasoleoB() == 1000.0) { //Le cambie el valor de -1.0 a uno muy alto para que en la ordenacion estuvieran al final de la ListView. Este valor indica que no disponen del producto en la gasolinera.
                gasoleoB.setText(" N/D"); //Introduzco esta salida que significa no disponible.
            } else {
                gasoleoB.setText(" " + gasolinera.getGasoleoB() + " " + getResources().getString(R.string.moneda)); // Si el valor es normal por estar disponible se le asigna la unidad del €
            }
            distancia.setText(" "+gasolinera.getDistanciaEntreGasolineraYPunto()+" km");


            // Si las dimensiones de la pantalla son menores
            // reducimos el texto de las etiquetas para que se vea correctamente
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics(); //He modificado el ajuste de tamanho para los TextView que necesito
            if (displayMetrics.widthPixels < 720) {
                TextView tmp;
                tmp = view.findViewById(R.id.direccionId);
                tmp.setTextSize(11);
                tmp = view.findViewById(R.id.precioDieselId);
                tmp.setTextSize(11);
                tmp = view.findViewById(R.id.distanciaHastaGasolineraId);
                tmp.setTextSize(11);
            }

            return view;
        }
    }
}