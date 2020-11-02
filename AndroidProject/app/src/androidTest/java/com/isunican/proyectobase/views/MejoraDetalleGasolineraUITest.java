package com.isunican.proyectobase.views;


import androidx.test.rule.ActivityTestRule;
import com.isunican.proyectobase.R;
import com.isunican.proyectobase.presenter.PresenterGasolineras;
import com.isunican.proyectobase.views.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Clase de prueba que verifica que la intefaz de la historia de usuario de mejora de los detalles de gasolinera
 * funciona correctamente y sale por pantalla la información adecuada.
 */

public class MejoraDetalleGasolineraUITest {

    PresenterGasolineras presenter;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule= new ActivityTestRule<>(MainActivity.class);


    @Test
    public void mejoraDetallesGasolineraTest(){

        presenter=new PresenterGasolineras();
        presenter.cargaDatosGasolineras();
        /*
        Hago click en el elemento 4

         */

        onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(3).perform(click());

        /*
        Ahora compruebo que cada valor de la interfaz es el esperado.
         */

        //Compruebo que la salida del rotulo es la esperada
        onView(withId(R.id.rotuloId)).check(matches(withText(presenter.getGasolineras().get(3).getRotulo())));
        //Compruebo que la salida de la direccion es la esperada
        onView(withId(R.id.direccionId)).check(matches(withText(presenter.getGasolineras().get(3).getDireccion())));
        //Compruebo que la salida de la localidad es la esperada
        onView(withId(R.id.localidadId)).check(matches(withText(presenter.getGasolineras().get(3).getLocalidad())));
        //Compruebo que la salida del gasoleo es la esperada
        onView(withId(R.id.precioGasoleoAId)).check(matches(withText(String.valueOf(presenter.getGasolineras().get(3).getGasoleoA()))));
        //Compruebo que la salida de la gasolina es la esperada
        onView(withId(R.id.precioGasolina95Id)).check(matches(withText(String.valueOf(presenter.getGasolineras().get(3).getGasolina95()))));


    }
}
