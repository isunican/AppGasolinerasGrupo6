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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.isunican.proyectobase.R;
import com.isunican.proyectobase.model.Gasolinera;
import com.isunican.proyectobase.presenter.PresenterGasolineras;

import java.util.Arrays;
import java.util.List;

public class UrgenciaActivity extends AppCompatActivity {
    PresenterGasolineras presenterGasolineras;
    ProgressBar progressBar;
    ArrayAdapter<Gasolinera> adapter;
    // Swipe and refresh (para recargar la lista con un swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgencia);

    }

    private class CargaDatosGasolinerasTask extends AsyncTask<Void, Void, Boolean> {

        Activity activity;

        /**
         * Constructor de la tarea asincrona
         *
         * @param activity
         */
        public CargaDatosGasolinerasTask(Activity activity) {
            this.activity = activity;
        }

        /**
         * onPreExecute
         * <p>
         * Metodo ejecutado de forma previa a la ejecucion de la tarea definida en el metodo doInBackground()
         * Muestra un diálogo de progreso
         *
         * @deprecated este método esta obsoleto y pronto no podrá ser usado
         */
        @Deprecated
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);  //To show ProgressBar
        }

        /**
         * doInBackground
         * <p>
         * Tarea ejecutada en segundo plano
         * Llama al presenter para que lance el método de carga de los datos de las gasolineras
         *
         * @param params
         * @return boolean
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            return presenterGasolineras.cargaDatosGasolineras();
        }

        /**
         * onPostExecute
         * <p>
         * Se ejecuta al finalizar doInBackground
         * Oculta el diálogo de progreso.
         * Muestra en una lista los datos de las gasolineras cargadas,
         * creando un adapter y pasándoselo a la lista.
         * Define el manejo de la selección de los elementos de la lista,
         * lanzando con una intent una actividad de detalle
         * a la que pasamos un objeto Gasolinera
         *
         * @param res
         * @deprecated este método esta obsoleto y pronto no podrá ser usado
         */
        @Deprecated
        @Override
        protected void onPostExecute(Boolean res) {
            Toast toast;

            // Si el progressDialog estaba activado, lo oculta
            progressBar.setVisibility(View.GONE);

            mSwipeRefreshLayout.setRefreshing(false);

//            // Si se ha obtenido resultado en la tarea en segundo plano
//            if (Boolean.TRUE.equals(res)) {
//                // Definimos el array adapter
//                adapter = new UrgenciaActivity.GasolineraArrayAdapter(activity, 0, presenterGasolineras.getGasolineras());
//
//                // Obtenemos la vista de la lista
//                listViewGasolineras = findViewById(R.id.listViewGasolineras);
//
//                // Cargamos los datos en la lista
//                if (!presenterGasolineras.getGasolineras().isEmpty()) {
//                    // datos obtenidos con exito
//                    listViewGasolineras.setAdapter(adapter);
//                    toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_exito), Toast.LENGTH_LONG);
//                } else {
//                    // los datos estan siendo actualizados en el servidor, por lo que no son actualmente accesibles
//                    // sucede en torno a las :00 y :30 de cada hora
//                    toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_accesibles), Toast.LENGTH_LONG);
//                }
//            } else {
//                // error en la obtencion de datos desde el servidor
//                /*
//                Se crea un dialogo de error, este indica que no hay acceso a internet y que se debe de cerrar la aplicación. Esto sucede al seleccionar el botón aceptar del mismo.
//                */
//                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
//                builder.setTitle(R.string.tituloDialogoDeError);
//                builder.setMessage(R.string.mensajeDialogoDeError);
//                builder.setPositiveButton(R.string.botonDeAceptarDialogoError, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finishAffinity();
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//                toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_obtenidos), Toast.LENGTH_LONG);
//            }
//
//            // Muestra el mensaje del resultado de la operación en un toast
//            if (toast != null) {
//                toast.show();
//            }
//
//            /*
//             * Define el manejo de los eventos de click sobre elementos de la lista
//             * En este caso, al pulsar un elemento se lanzará una actividad con una vista de detalle
//             * a la que le pasamos el objeto Gasolinera sobre el que se pulsó, para que en el
//             * destino tenga todos los datos que necesita para mostrar.
//             * Para poder pasar un objeto Gasolinera mediante una intent con putExtra / getExtra,
//             * hemos tenido que hacer que el objeto Gasolinera implemente la interfaz Parcelable
//             */
//            if(listViewGasolineras!=null) {
//                listViewGasolineras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//
//                        /* Obtengo el elemento directamente de su posicion,
//                         * ya que es la misma que ocupa en la lista
//                         */
//                        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
//                        myIntent.putExtra(getResources().getString(R.string.pasoDatosGasolinera),
//                                presenterGasolineras.getGasolineras().get(position));
//                        MainActivity.this.startActivity(myIntent);
//
//                    }
//                });
//            }
//        }
//    }
//
            class GasolineraArrayAdapter extends ArrayAdapter<Gasolinera> {

                private Context context;
                private List<Gasolinera> listaGasolineras;
                private List<Double> listaDistancias; //Lista para introducir en cada item del ListView una distancia variable manualmente, es decir, no es calculada.

                // Constructor
                public GasolineraArrayAdapter(Context context, int resource, List<Gasolinera> objects) {
                    super(context, resource, objects);
                    this.context = context;
                    this.listaGasolineras = objects;
                    listaDistancias = Arrays.asList(0.023, 0.12, 0.223, 0.3, 0.345, 0.9, 2.4, 50.2, 80.65, 94.678, 100.765, 200.0, 700.0, 1000.0, 2670.0, 34567.0, 123356.0, 2343498.0, 16478426.0, 742797564.0, 234234234.0); //Le doy valores a la lista.


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
            He cambiado las elementos de cada item de acuerdo a lo que exige la funcionalidad Listar gasolineras por precio
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
                    if (position % listaDistancias.size() >= 0 && position % listaDistancias.size() <= 10) { //Los primeros valores son pequeños y decimales por lo que le asigno la unidad del km
                        distancia.setText(String.valueOf(listaDistancias.get(position % listaDistancias.size())) + " km");
                    } else {
                        distancia.setText(String.valueOf(Integer.valueOf(listaDistancias.get(position % listaDistancias.size()).intValue())) + " m"); //En la lista de Double a partir de cierta posicion los valores son mas grandes y les
                    }                                                                                                                             //asigno el m, además casteo a enteros los numeros decimales, ya que al ser metros no
                    //necesario el decimal dado el contexto.


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
    }
}