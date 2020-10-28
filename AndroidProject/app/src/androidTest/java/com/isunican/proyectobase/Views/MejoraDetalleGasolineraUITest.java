package com.isunican.proyectobase.Views;

import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Clase de prueba que verifica que la intefaz de la historia de usuario de mejora de los detalles de gasolinera
 * funciona correctamente y sale por pantalla la información adecuada.
 */
public class MejoraDetalleGasolineraUITest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule= new ActivityTestRule<>(MainActivity.class);


    @Test
    public void mejoraDetallesGasolineraTest(){

        /*
        Hago click en el elemento 4 que se que tiene los siguientes datos:
        -Rotulo: CAMPSA
        -Direccion: CARRETERA ARGOÑOS SOMO KM. 28,7
        -Gasoleo: 1.079€
        -Gasolina: 1.179€
         */

        onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(3).perform(click());

        /*
        Ahora compruebo que cada valor de la interfaz es el esperado.
         */

        //Compruebo que la salida del rotulo es la esperada
        onView(withId(R.id.rotuloId)).check(matches(withText("CAMPSA")));
        //Compruebo que la salida de la direccion es la esperada
        onView(withId(R.id.direccionId)).check(matches(withText("CARRETERA ARGOÑOS SOMO KM. 28,7")));
        //Compruebo que la salida de la localidad es la esperada
        onView(withId(R.id.localidadId)).check(matches(withText("ARNUERO")));
        //Compruebo que la salida del gasoleo es la esperada
        onView(withId(R.id.precioGasoleoAId)).check(matches(withText(String.valueOf(1.079))));
        //Compruebo que la salida de la gasolina es la esperada
        onView(withId(R.id.precioGasolina95Id)).check(matches(withText(String.valueOf(1.179))));


    }
}
