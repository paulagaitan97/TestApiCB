package co.com.ceiba.mobile.pruebadeingreso;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity;
import static  android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import  static  android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * EL test automatizado comprueba el swipeUp en la lista de usuarios , busca el usuario con el nombre
     * "Clementina DuBuque" y presiona el boton.
     * Nota: Para comprobar con exactitud este test se debe deshabilitar las animaciones del telefono
     * de pruebas.
     */
    @Test
    public void ensureActivityChange() throws InterruptedException {
        onView(ViewMatchers.withId(R.id.recyclerViewSearchResults)).perform(ViewActions.swipeUp());
        onView(withId(R.id.editTextSearch)).perform(typeText("Clementina DuBuque"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btn_view_post)).perform(click());
       // Thread.sleep(4000); -> Obligatorio si se tiene activa las animaciones
    }

    /**
     * Este test automatizado comprueba el funcionamiento de la lista de post
     * por usuario seleccionado.
     * @throws InterruptedException
     */
    @Test
    public void ensureDifferentText() throws InterruptedException {
       ensureActivityChange();
        onView(ViewMatchers.withId(R.id.recyclerViewPostsResults)).perform(ViewActions.swipeUp());
    }



}
