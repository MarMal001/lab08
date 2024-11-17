package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
          * @throws SecurityException 
          * @throws NoSuchMethodException if the 0-ary constructor do not exist
          * @throws InvocationTargetException if the constructor throws exceptions
          * @throws InstantiationException if the constructor throws exceptions
          * @throws IllegalAccessException in case of reflection issues
          * @throws IllegalArgumentException in case of reflection issues
          */
         public static void main(final String... args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        // app.addView(new DrawNumberSwingView()); 
        // app.addView(new DrawNumberStandardOutput()); -->esercizio 83.2
        Class<?> class1 = Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
        Class<?> class2 = Class.forName("it.unibo.mvc.view.DrawNumberStandardOutput");
        for (int i = 0; i < 3; i++) {
            app.addView((DrawNumberView)class1.getConstructor().newInstance());
            app.addView((DrawNumberView)class2.getConstructor().newInstance());
        }

    }
}
