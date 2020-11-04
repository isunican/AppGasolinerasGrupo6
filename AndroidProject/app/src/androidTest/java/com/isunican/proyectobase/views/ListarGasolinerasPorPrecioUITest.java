package com.isunican.proyectobase.views;

import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;


import com.isunican.proyectobase.presenter.PresenterGasolineras;

import org.junit.Rule;
import org.junit.Test;
import com.isunican.proyectobase.R;

import java.util.Arrays;
import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

public class ListarGasolinerasPorPrecioUITest {

    PresenterGasolineras presenter;
    List<Double> listaDistancias;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule= new ActivityTestRule<>(MainActivity.class);


    @Test
    public void listarGasolinerasPorPrecioTest(){

        presenter=new PresenterGasolineras();
        presenter.cargaDatosGasolineras(); //Se cargan las gasolineras ordenadas por el precio del gasoleoB por lo tanto se da por hecho que al mostrarse en el mismo orden en el ListView están ordenadas
        listaDistancias=Arrays.asList(0.023,0.12,0.223,0.3,0.345,0.9,2.4,50.2,80.65,94.678,100.765,200.0,700.0,1000.0,2670.0,34567.0,123356.0,2343498.0,16478426.0,742797564.0,234234234.0); //Le doy valores a la lista.

        //Caso de prueba ILPP.a , se comprueba que las gasolineras listadas estan ordenadas segun el precio del gasoleoB.
        //Caso de prueba ILPP.b , se comprueba que en los items del ListView se muestra una distancia con un numero de digitos y unidad variables.
        for(int i=0;i<presenter.getGasolineras().size();i++) {


            /*
            Se comprueba que salen por la ListView los item que deben salir con los valores esperados para cada item y que dentro de cada item se muestra la distancia con un numero de digitos y unidad variables.
             */

            //Direccion a la izquierda de cada item
            onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(i).onChildView(withId(R.id.direccionId)).check(matches(withText(presenter.getGasolineras().get(i).getDireccion())));

            //GasoleoB en el centro de cada item
            if(presenter.getGasolineras().get(i).getGasoleoB()!=1000.0) {
               onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(i).onChildView(withId(R.id.precioDieselId)).check(matches(withText(" " + String.valueOf(presenter.getGasolineras().get(i).getGasoleoB()) + " " + "€")));
           }else{
               onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(i).onChildView(withId(R.id.precioDieselId)).check(matches(withText(" N/D")));
           }

            //Distancia en metros o km predefinida a la derecha del item
            if(i%listaDistancias.size()>=0 && i%listaDistancias.size()<=10 ){
                onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(i).onChildView(withId(R.id.distanciaHastaGasolineraId)).check(matches(withText(String.valueOf(listaDistancias.get(i%listaDistancias.size()))+" km")));
            }else {
                onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(i).onChildView(withId(R.id.distanciaHastaGasolineraId)).check(matches(withText(String.valueOf(listaDistancias.get(i%listaDistancias.size()).intValue())+" m")));
            }

        }


        //Caso ILGPP.c se comprueba que no se ha podido cargar informacion de la API por no disponer de internet.


        
        //onView(withText("Aceptar")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

    }

}
