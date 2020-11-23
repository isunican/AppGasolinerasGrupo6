package com.isunican.proyectobase.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;
import com.isunican.proyectobase.R;
import com.isunican.proyectobase.model.Gasolinera;
import java.util.ArrayList;
import java.util.List;

public class UrgenciaActivity extends AppCompatActivity {

    ArrayList<Gasolinera> gasolineras;
    ArrayAdapter<Gasolinera> adapter;
    ListView listViewGasolineras;
    Toast toast;
    TextView puntoReferencia; //Se utiliza para mostrar la etiqueta pasado desde la MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgencia);

        Bundle bundle = getIntent().getExtras(); //Se saca el bundle del intent
        gasolineras = bundle.getParcelableArrayList("ListaGasolinerasCercanas"); //Se saca del bundle la lista de gasolineras pasadas de la actividad anterior
        Intent intent=getIntent(); //Se obtiene el intent
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_gasolinera2_foreground);
        puntoReferencia=findViewById(R.id.textView4); //Se le da valor a la vista
        puntoReferencia.setText(intent.getExtras().getString("Etiqueta")); //Se obtiene del intent la etiqueta pasada de la actividad anterior y se asigna al TextView
        adapter = new UrgenciaActivity.GasolineraArrayAdapter(this, 0, gasolineras); //Se crea un adapter GasolineraArrayAdapter al que se le pasa la lista de gasolineras a mostrar en el ListView
        listViewGasolineras = findViewById(R.id.listViewGasolineras); //Se le da valor al ListView
        if (!gasolineras.isEmpty()) { //Si la lista de gasolineras no esta vacia:
            // datos obtenidos con exito
            listViewGasolineras.setAdapter(adapter); //Se asigna el adapter al ListView para que muestre la lista de gasolineras ¡
            toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_exito), Toast.LENGTH_LONG);
        }
        if (toast != null) {
            toast.show();
        }
        if(listViewGasolineras!=null) { //Si el ListView no es un puntero a null:
            listViewGasolineras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> a, View v, int position, long id) { //Al seleccionar un elemento de ListView, una gasolinera:

                    /* Obtengo el elemento directamente de su posicion,
                     * ya que es la misma que ocupa en la lista
                     */
                    Intent myIntent = new Intent(UrgenciaActivity.this, DetailActivity.class);
                    myIntent.putExtra(getResources().getString(R.string.pasoDatosGasolinera),
                    gasolineras.get(position));
                    UrgenciaActivity.this.startActivity(myIntent); //Paso de UrgenciaActivity a DetailActivity pasando con el intent la gasolinera seleccionada en el ListView para mostrar su informacion

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1urgencia, menu); //Se crea un nuevo menu con solo dos entradas, la de Pantalla principal y la opcion de Info
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.itemInfo){
            Intent myIntent = new Intent(UrgenciaActivity.this, InfoActivity.class);
            UrgenciaActivity.this.startActivity(myIntent); //Pasamos a la InfoActivity
        }else if(item.getItemId()==R.id.itemPantallaPrincipal){
            Intent intentPrincipal=new Intent(UrgenciaActivity.this,MainActivity.class);
            UrgenciaActivity.this.startActivity(intentPrincipal); //Pasamos a la MainActivity
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

            direccion.setText(gasolinera.getDireccion()); //Le paso al TextView la direccion de la gasolinera para que la muestre en cada item del ListView
            gasoleoB.setText(" " + gasolinera.getGasoleoB() + getResources().getString(R.string.moneda)); //Se le asigna el precio en euros (€)
            distancia.setText(" "+gasolinera.getDistanciaEntreGasolineraYPunto()+" km"); //Se le asigna la distancia real entre el punto y la gasolinera para cada gasolinera


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